package com.cxyz.commons.autoupdate;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * Description:文件下载及线程间通信
 * User: chenzheng
 * Date: 2016/12/14 0014
 * Time: 16:24
 */
public class UpdateDownloadRequest implements Runnable{

    private String downloadUrl;
    private String localFilePath;
    private UpdateDownloadListener listener;
    private boolean isDownloading = false;
    private long currentLength;

    private DownloadResponseHandler downloadResponseHandler;

    public UpdateDownloadRequest(String downloadUrl, String localFilePath, UpdateDownloadListener listener){

        this.downloadUrl = downloadUrl;
        this.localFilePath = localFilePath;
        this.listener = listener;
        this.isDownloading = true;
        downloadResponseHandler = new DownloadResponseHandler();

    }

    private String getTwoPointFloatStr(float value){
        DecimalFormat df = new DecimalFormat("0.00000000000");
        return df.format(value);

    }

    private void makeRequest() throws IOException, InterruptedIOException{

        if(!Thread.currentThread().isInterrupted()){

            try {
                URL url = new URL(downloadUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.connect();
                currentLength = connection.getContentLength();
                if(!Thread.currentThread().isInterrupted()){
                    downloadResponseHandler.sendResponseMessage(connection.getInputStream());

                }


            }catch (Exception e){
                throw e;

            }
        }
    }

    @Override
    public void run() {

        try{

            makeRequest();
        }catch (IOException e){

        }
    }

    /**
     * 下载过程中的异常
     */
    public enum FailureCode{
        UnknownHost, Socket, SocketTimeout, connectionTimeout,IO, HttpResponse,
        Json, Interrupted

    }

    public class DownloadResponseHandler{

        protected static final int SUCCESS_MESSAGE = 0;
        protected static final int FAILURE_MESSAGE = 1;
        protected static final int START_MESSAGE = 2;
        protected static final int FINISH_MESSAGE = 3;
        protected static final int NETWORK_OFF = 4;
        private static final int PROGRESS_CHANGED = 5;

        private float completeSize = 0;
        private int progress = 0;

        private Handler handler;

        public DownloadResponseHandler(){

            handler = new Handler(Looper.getMainLooper()) {

                @Override
                public void handleMessage(Message msg) {
                    handleSelfMessage(msg);
                }
            };

        }



        protected void sendFinishMessage(){
            sendMessage(obtainMessage(FINISH_MESSAGE, null));
        }

        private void sendProgressChangedMessage(int progress){
            sendMessage(obtainMessage(PROGRESS_CHANGED, new Object[]{progress}));

        }

        protected void sendFailureMessage(FailureCode failureCode){
            sendMessage(obtainMessage(FAILURE_MESSAGE, new Object[]{failureCode}));

        }

        protected void sendMessage(Message msg){
            if(handler!=null){
                handler.sendMessage(msg);
            }else{
                handleSelfMessage(msg);
            }

        }

        protected Message obtainMessage(int responseMessge, Object response){
            Message msg = null;
            if(handler!=null){
                msg = handler.obtainMessage(responseMessge, response);
            }else{
                msg = Message.obtain();
                msg.what = responseMessge;
                msg.obj = response;
            }
            return msg;

        }

        protected void handleSelfMessage(Message msg){

            Object[] response;
            switch (msg.what){
                case FAILURE_MESSAGE:
                    response = (Object[]) msg.obj;
                    sendFailureMessage((FailureCode) response[0]);
                    break;
                case PROGRESS_CHANGED:
                    response = (Object[]) msg.obj;
                    handleProgressChangedMessage(((Integer)response[0]).intValue());
                    break;
                case FINISH_MESSAGE:
                    onFinish();
                    break;
            }
        }

        protected void handleProgressChangedMessage(int progress){
            listener.onProgressChanged(progress, downloadUrl);
        }

        protected void onFinish(){
            listener.onFinished(completeSize, "");

        }

        private void handleFailureMessage(FailureCode failureCode){
            onFailure(failureCode);

        }

        protected void onFailure(FailureCode failureCode){

            listener.onFailure();
        }

        void sendResponseMessage(InputStream is){

            RandomAccessFile randomAccessFile = null;
            completeSize=0;
            try{
                byte[] buffer = new byte[1024];
                int length=-1;//读写长度
                int limit=0;
                randomAccessFile = new RandomAccessFile(localFilePath, "rwd");

                while((length = is.read(buffer))!=-1){

                    if(isDownloading){

                        randomAccessFile.write(buffer, 0 ,length);
                        completeSize += length;
                        if(completeSize < currentLength){

                            Log.e("tag", "completeSize="+completeSize);
                            Log.e("tag", "currentLength="+currentLength);
                            progress = (int)(Float.parseFloat(getTwoPointFloatStr(completeSize/currentLength))*100);
                            Log.e("tag", "下载进度："+progress);
                            if(limit % 30==0 && progress <= 100){//隔30次更新一次notification
                                sendProgressChangedMessage(progress);

                            }
                            limit++;
                        }
                    }
                }
                sendFinishMessage();
            }catch(IOException e){
                sendFailureMessage(FailureCode.IO);

            }finally{
                try{
                    if(is!=null){
                        is.close();
                    }
                    if(randomAccessFile!=null){
                        randomAccessFile.close();
                    }
                }catch(IOException e){
                    sendFailureMessage(FailureCode.IO);
                }


            }


        }
    }
}

