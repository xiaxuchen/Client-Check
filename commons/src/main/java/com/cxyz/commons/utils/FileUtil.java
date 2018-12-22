package com.cxyz.commons.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * Created by 夏旭晨 on 2018/9/21.
 * 文件操作工具类
 */
public class FileUtil {

    /**声明各种类型文件的dataType**/
    private static final String DATA_TYPE_ALL = "*/*";//未指定明确的文件类型，不能使用精确类型的工具打开，需要用户选择
    private static final String DATA_TYPE_APK = "application/vnd.android.package-archive";
    private static final String DATA_TYPE_VIDEO = "video/*";
    private static final String DATA_TYPE_AUDIO = "audio/*";
    private static final String DATA_TYPE_HTML = "text/html";
    private static final String DATA_TYPE_IMAGE = "image/*";
    private static final String DATA_TYPE_PPT = "application/vnd.ms-powerpoint";
    private static final String DATA_TYPE_EXCEL = "application/vnd.ms-excel";
    private static final String DATA_TYPE_WORD = "application/msword";
    private static final String DATA_TYPE_CHM = "application/x-chm";
    private static final String DATA_TYPE_TXT = "text/plain";
    private static final String DATA_TYPE_PDF = "application/pdf";

    /**
     * 在指定的位置创建指定的文件
     * @param filePath 完整的文件路径
     * @param mkdir 是否创建相关的文件夹
     * @throws IOException
     */
    public static void mkFile(String filePath, boolean mkdir) throws IOException {
        File file = new File(filePath);
        /**
         * mkdirs()创建多层目录，mkdir()创建单层目录
         * writeObject时才创建磁盘文件。
         * 若不创建文件，readObject出错。
         */
        file.getParentFile().mkdirs();
        file.createNewFile();
        file = null;
    }

    /**
     * 在指定的位置创建文件夹
     * @param dirPath 文件夹路径
     * @return 若创建成功，则返回True；反之，则返回False
     */
    public static boolean mkDir(String dirPath) {
        return new File(dirPath).mkdirs();
    }

    /**
     * 删除指定的文件
     * @param filePath 文件路径
     * @return 若删除成功，则返回True；反之，则返回False
     */
    public static boolean delFile(String filePath) {
        return new File(filePath).delete();
    }

    /**
     * 删除指定的文件夹
     * @param dirPath 文件夹路径
     * @param delFile 文件夹中是否包含文件
     * @return 若删除成功，则返回True；反之，则返回False
     */
    public static boolean delDir(String dirPath, boolean delFile) {
        if (delFile) {
            File file = new File(dirPath);
            if (file.isFile()) {
                return file.delete();
            } else if (file.isDirectory()) {
                if (file.listFiles().length == 0) {
                    return file.delete();
                } else {
                    int zFiles = file.listFiles().length;
                    File[] delfile = file.listFiles();
                    for (int i = 0; i < zFiles; i++) {
                        if (delfile[i].isDirectory()) {
                            delDir(delfile[i].getAbsolutePath(), true);
                        }
                        delfile[i].delete();
                    }
                    return file.delete();
                }
            } else {
                return false;
            }
        } else {
            return new File(dirPath).delete();
        }
    }

    /**
     * 复制文件/文件夹 若要进行文件夹复制，请勿将目标文件夹置于源文件夹中
     * @param source 源文件（夹）
     * @param target 目标文件（夹）
     * @param isFolder 若进行文件夹复制，则为True；反之为False
     * @throws IOException
     */
    public static void copy(String source, String target, boolean isFolder) throws IOException{
        if (isFolder) {
            new File(target).mkdirs();
            File a = new File(source);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (source.endsWith(File.separator)) {
                    temp = new File(source + file[i]);
                } else {
                    temp = new File(source + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(target + File.separator + temp.getName().toString());
                    byte[] b = new byte[1024];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                } if (temp.isDirectory()) {
                    copy(source + File.separator + file[i], target + File.separator + file[i], true);
                }
            }
        } else {
            int byteread = 0;
            File oldfile = new File(source);
            if (oldfile.exists()) {
                InputStream inputStream = new FileInputStream(source);
                File file = new File(target);
                file.getParentFile().mkdirs();
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                while ((byteread = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer, 0, byteread);
                }
                inputStream.close();
                outputStream.close();
            }
        }
    }

    public static void open(Context mContext,File file)
    {
        String filePath = file.getPath();
        if (!file.exists()){
            //如果文件不存在
            Toast.makeText(mContext, "打开失败，原因：文件已经被移动或者删除", Toast.LENGTH_SHORT).show();
            return;
        }
    /* 取得扩展名 */
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase(Locale.getDefault());
    /* 依扩展名的类型决定MimeType */
        Intent intent = null;
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") || end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            intent =  generateVideoAudioIntent(mContext,filePath,DATA_TYPE_AUDIO);
        } else if (end.equals("3gp") || end.equals("mp4")) {
            intent = generateVideoAudioIntent(mContext,filePath,DATA_TYPE_VIDEO);
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg") || end.equals("bmp")) {
            intent = generateCommonIntent(mContext,filePath,DATA_TYPE_IMAGE);
        } else if (end.equals("apk")) {
            intent = generateCommonIntent(mContext,filePath,DATA_TYPE_APK);
        }else if (end.equals("html") || end.equals("htm")){
            intent = generateHtmlFileIntent(filePath);
        } else if (end.equals("ppt")) {
            intent = generateCommonIntent(mContext,filePath,DATA_TYPE_PPT);
        } else if (end.equals("xls")||end.equals("xlsx")) {
            intent = generateCommonIntent(mContext,filePath,DATA_TYPE_EXCEL);
        } else if (end.equals("doc")) {
            intent = generateCommonIntent(mContext,filePath,DATA_TYPE_WORD);
        } else if (end.equals("pdf")) {
            intent = generateCommonIntent(mContext,filePath,DATA_TYPE_PDF);
        } else if (end.equals("chm")) {
            intent = generateCommonIntent(mContext,filePath,DATA_TYPE_CHM);
        } else if (end.equals("txt")) {
            intent = generateCommonIntent(mContext,filePath,DATA_TYPE_TXT);
        } else {
            intent = generateCommonIntent(mContext,filePath,DATA_TYPE_ALL);
        }
        mContext.startActivity(intent);
    }

    /**
     * 获取对应文件的Uri
     * @param intent 相应的Intent
     * @param file 文件对象
     * @return
     */
    private static Uri getUri(Context mContext,Intent intent, File file) {
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //判断版本是否在7.0以上
            uri =
                    FileProvider.getUriForFile(mContext,
                            mContext.getPackageName() + ".fileprovider",
                            file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }


    /**
     * 产生除了视频、音频、网页文件外，打开其他类型文件的Intent
     * @param filePath 文件路径
     * @param dataType 文件类型
     * @return
     */
    private static Intent generateCommonIntent(Context context,String filePath, String dataType) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        File file = new File(filePath);
        Uri uri = getUri(context,intent, file);
        intent.setDataAndType(uri, dataType);
        return intent;
    }

    /**
     * 产生打开视频或音频的Intent
     * @param filePath 文件路径
     * @param dataType 文件类型
     * @return
     */
    private static Intent generateVideoAudioIntent(Context context,String filePath, String dataType){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        File file = new File(filePath);
        intent.setDataAndType(getUri(context,intent,file), dataType);
        return intent;
    }

    /**
     * 产生打开网页文件的Intent
     * @param filePath 文件路径
     * @return
     */
    private static Intent generateHtmlFileIntent(String filePath) {
        Uri uri = Uri.parse(filePath)
                .buildUpon()
                .encodedAuthority("com.android.htmlfileprovider")
                .scheme("content")
                .encodedPath(filePath)
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, DATA_TYPE_HTML);
        return intent;
    }

}
