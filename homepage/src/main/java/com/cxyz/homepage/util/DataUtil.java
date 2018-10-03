package com.cxyz.homepage.util;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.cxyz.commons.utils.LogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        /** 存放不同的日期模板格式的sdf的Map */
        private static ThreadLocal<Map<String, SimpleDateFormat>> sdfMap = new ThreadLocal<Map<String, SimpleDateFormat>>() {
            @Override
            protected Map<String, SimpleDateFormat> initialValue() {
                //System.out.println(Thread.currentThread().getName()	+ " init pattern: " + Thread.currentThread());
                return new HashMap<String, SimpleDateFormat>();
            }
        };

        /**
         * 返回一个SimpleDateFormat,每个线程只会new一次pattern对应的sdf
         * @param pattern
         * @return 一个SimpleDateFormat
         */
        private static SimpleDateFormat getSdf(final String pattern) {
            Map<String, SimpleDateFormat> tl = sdfMap.get();
            SimpleDateFormat sdf = tl.get(pattern);
            if (sdf == null) {
                //System.out.println(Thread.currentThread().getName()+" put new sdf of pattern " + pattern + " to map");
                sdf = new SimpleDateFormat(pattern);
                tl.put(pattern, sdf);
            }
            return sdf;
        }

        /**
         * 这样每个线程只会有一个SimpleDateFormat
         * @param date
         * @param pattern
         * @return
         */
        public static String format(Date date, String pattern) {
            return getSdf(pattern).format(date);
        }

        public static Date parse(String dateStr, String pattern)
                throws ParseException {
            return getSdf(pattern).parse(dateStr);
        }

    /**
     *@parm 这是一个获取时间差(one-two两段时间相隔多长)的方法
     *@parm one(2004-03-26 13:31:40) two(2004-01-02 11:30:24)
     *@return HashMap (day)天,(hour)小时,(min)分,(s)秒
     */
    public static HashMap subDate(String one, String two){

        HashMap sub = new HashMap();

        try {
            SimpleDateFormat df = getSdf("yyyy-MM-dd HH:mm:ss");
            // SimpleDateFormat df = new SimpleDateormat("yyyy-MM-dd HH:mm:ss");

            Date now = null;
            Date date= null;
            now = df.parse(one);
            date = df.parse(two);

        long l=now.getTime()-date.getTime();

        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        long s=(l/1000-day*24*60*60-hour*60*60-min*60);

        sub.put("day",day);
        sub.put("hour",hour);
        sub.put("min",min);
        sub.put("s",s);
        } catch (ParseException e) {
            e.printStackTrace();
            LogUtil.d("时间差出问题了");
        }
        //System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");

        return sub;
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
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
