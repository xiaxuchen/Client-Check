package ferture_z.com.z.dataUtil;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 鱼塘主 on 2018/9/25.
 * 实时得到当前时间的工具类
 */

public class DataUtil extends Thread{

    public SimpleDateFormat aDate;
    public  TextView date,week;
    private int msgKey1 = 22;
    public DataUtil(TextView date,TextView week){
        this.date = date;
        this.week =week;
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
                Message msg = new Message();
                msg.what = msgKey1;
                mHandler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 22:
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    String dates = sdf.format(new Date());

                    date.setText(dates);
                    week.setText(getWeek());
                    break;

                default:
                    break;
            }
        }
    };

    /**
     * 获取今天星期几
     * @return
     */
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
            default:
                return "";
        }
    }


}
