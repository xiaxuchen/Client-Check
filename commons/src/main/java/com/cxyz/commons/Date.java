package com.cxyz.commons;

import java.sql.Timestamp;

public class Date {
	
	private String year = "0000";
	private String month = "00";

	private String day = "00";
	private String hour = "00";
	private String minute = "00";
	private String second = "00";
	
	public Date(){}
	
	/**
	 * 默认直接获取
	 * @param date
	 */
	public static Date fromUDate(java.util.Date date)
	{
		return Date.fromUDate(date, true);
	}
	
	
	/**
	 * 
	 * @param date
	 * @param isDate 为true则从0开始算
	 */
	public static Date fromUDate(java.util.Date date,boolean isDate)
	{
		Date d = new Date();
		d.setMonth(date.getMonth()+1);
		System.out.println(date.getDate());
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
	public static Date fromSDate(java.sql.Date date)
	{
		return Date.fromSDate(date, true);
	}
	
	/**
	 * 把java.sql.Date转化为我们的Date
	 * @param date 
	 * @param isDate 如果是true则从0开始算
	 * @return
	 */
	public static Date fromSDate(java.sql.Date date,boolean isDate)
	{
		if(date==null)
			return null;
		return Date.fromUDate(new java.util.Date(date.getTime()), isDate); 
	}
	
	/**
	 * 从TimeStamp转化为我的Date类型
	 * @param ts TimeStamp
	 * @return
	 */
	public static Date fromTS(Timestamp ts)
	{
		if(ts==null)
		{
			return null;
		}
		return Date.fromUDate(new java.util.Date(ts.getTime()),false);
	}
	
	/**
	 * 从TimeStamp转化为我的Date类型
	 * @param ts TimeStamp
	 * @param isAdd 是否加上1900年
	 * @return
	 */
	public static Date fromTS(Timestamp ts,boolean isAdd)
	{
		if(ts==null)
		{
			return null;
		}
		return Date.fromUDate(new java.util.Date(ts.getTime()),isAdd);
	}
	
	public String getYear() {
		return year;
	}


	public Date setYear(String year) {
		String temp = setZero(year, 4);
		if(temp!=null)
			this.year = temp;
		return this;
	}
	
	public Date setYear(int year)
	{
		return this.setYear(String.valueOf(year));
	}


	public String getMonth() {
		return month;
	}


	public Date setMonth(String month) {
		String temp = setZero(month, 2);
		if(temp!=null)
			this.month = temp;
		return this;
	}
	
	public Date setMonth(int month)
	{
		return this.setMonth(String.valueOf(month));
	}


	public String getDay() {
		return day;
	}


	public Date setDay(String day) {
		String temp = setZero(day, 2);
		if(temp!=null)
			this.day = temp;
		return this;
	}
	
	public Date setDay(int day)
	{
		return this.setDay(String.valueOf(day));
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
	

	
	/**
	 * 补齐格式
	 * @param str
	 * @param len
	 * @return
	 */
	private String setZero(String str,int len)
	{
		if(str.length() == len)
			return str;
		else if(str.length()<len)
		{
			for(int i = 0;i<len-str.length();i++)
			{
				str = "0"+str;
			}
			return str;
		}else
			return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		sb.append(":");
		sb.append(month);
		sb.append(":");
		sb.append(day);
		sb.append(":");
		sb.append(hour);
		sb.append(":");
		sb.append(minute);
		sb.append(":");
		sb.append(second);
		return sb.toString();
	}
	
}
