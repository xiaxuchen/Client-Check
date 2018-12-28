package com.cxyz.commons.date;

import com.cxyz.commons.utils.LogUtil;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateTime extends Date{
	private String hour = "00";
	private String minute = "00";
	private String second = "00";


	public DateTime(){}

	public DateTime(String year,String month,String day,String hour, String minute, String second) {
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
	}

	/**
	 * 比较
	 * @param anOther
	 * @return
	 */
	public int compareTo(DateTime anOther)
	{
		if(toInt(getYear())>toInt(anOther.getYear()))
			return 1;
		else if(toInt(getYear())<toInt(anOther.getYear()))
			return -1;
		else {
			if(toInt(getMonth())>toInt(anOther.getMonth()))
				return 1;
			else if(toInt(getMonth())<toInt(anOther.getMonth()))
				return -1;
			else{
				if(toInt(getDay())>toInt(anOther.getDay()))
					return 1;
				else if(toInt(getDay())<toInt(anOther.getDay()))
					return -1;
				else{
					if(toInt(getHour())>toInt(anOther.getHour()))
						return 1;
					else if(toInt(getHour())<toInt(anOther.getHour()))
						return -1;
					else{
						if(toInt(getMinute())>toInt(anOther.getMinute()))
							return 1;
						else if(toInt(getMinute())<toInt(anOther.getMinute()))
							return -1;
						else{
							if(toInt(getSecond())>toInt(anOther.getSecond()))
								return 1;
							else if(toInt(getSecond())<toInt(anOther.getSecond()))
								return -1;
							else{
								return 0;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 默认加上1900年
	 * @param date
	 */
	public static DateTime fromUDate(java.util.Date date)
	{
		return DateTime.fromUDate(date, false);
	}
	
	
	/**
	 * 将java.util.Date转化为DateTime
	 * @param date
	 * @param isDate 为true则从0开始算
	 */
	public static DateTime fromUDate(java.util.Date date,boolean isDate)
	{
		DateTime d = new DateTime();
		d.setMonth(date.getMonth()+1);
		d.setDay(date.getDate());
		d.setHour(date.getHours());
		d.setMinute(date.getMinutes());
		d.setSecond(date.getSeconds());
		if(isDate)
		{
			d.setYear(date.getYear());
		}else{
			d.setYear(date.getYear()+1900);
		}
		return d;
	}
	
	/**
	 * 把java.sql.Date转化为我们的Date
	 * @param date
	 * @return
	 */
	public static DateTime fromSDate(java.sql.Date date)
	{
		return DateTime.fromSDate(date, false);
	}
	
	/**
	 * 把java.sql.Date转化为我们的Date
	 * @param date 
	 * @param isDate 如果是true则从0开始算
	 * @return
	 */
	public static DateTime fromSDate(java.sql.Date date,boolean isDate)
	{
		if(date==null)
			return null;
		return DateTime.fromUDate(new java.util.Date(date.getTime()), isDate); 
	}
	
	/**
	 * 从TimeStamp转化为我的Date类型,不从零开始算
	 * @param ts TimeStamp
	 * @return
	 */
	public static DateTime fromTS(Timestamp ts)
	{
		if(ts==null)
		{
			return null;
		}
		return DateTime.fromUDate(new java.util.Date(ts.getTime()),false);
	}
	
	/**
	 * 从TimeStamp转化为我的Date类型
	 * @param ts TimeStamp
	 * @param isDate 是否从零开始算
	 * @return
	 */
	public static DateTime fromTS(Timestamp ts,boolean isDate)
	{
		if(ts==null)
		{
			return null;
		}
		return DateTime.fromUDate(new java.util.Date(ts.getTime()),isDate);
	}
	
	public String getHour() {
		return hour;
	}


	public Date setHour(String hour) {
		String temp = setZero(hour, 2);
		if(temp!=null)
			this.hour = temp;
		return this;
	}
	
	public Date setHour(int hour)
	{
		return this.setHour(String.valueOf(hour));
	}


	public String getMinute() {
		return minute;
	}


	public Date setMinute(String minute) {
		String temp = setZero(minute, 2);
		if(temp!=null)
			this.minute = temp;
		return this;
	}
	
	public Date setMinute(int minute)
	{
		return this.setMinute(String.valueOf(minute));
	}


	public String getSecond() {
		return second;
	}


	public Date setSecond(String second) {
		String temp = setZero(second, 2);
		if(temp!=null)
			this.second = temp;
		return this;
	}
	
	public Date setSecond(int second)
	{
		return this.setSecond(String.valueOf(second));
	}
	
	@Override
	public String toString() {
		return getYear()+":"+getMonth()+":"+getDay()+":"+hour+":"+minute+":"+second;
	}

	/**
	 * 获取timeStamp
	 * @return
	 */
	public Timestamp toTimeStamp()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(2018,0,1,0,0,0);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取毫秒数
	 * @return
	 */
	public Long getTimeInMillis()
	{
		LogUtil.e(getYear()+getMonth()+getDay()+getHour()+getMinute());
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(2018,0,1,0,0,0);
		LogUtil.e(calendar.getTime().toLocaleString());
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取时间的字符串
	 * @return
	 */
	public String getTime()
	{
		return hour+":"+minute;
	}


	private int toInt(String s)
	{
		return Integer.parseInt(s);
	}
	
}
