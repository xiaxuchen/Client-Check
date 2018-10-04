package com.cxyz.commons;

public class Date {
	
	private String year = "0000";
	private String month = "00";
	private String day = "00";
	
	
	public Date(){}
	
	/**
	 * 默认加上1900年
	 * @param date
	 */
	public static Date fromUDate(java.util.Date date)
	{
		return Date.fromUDate(date, false);
	}
	
	
	/**
	 * 将java.util.Date转化为DateTime
	 * @param date
	 * @param isDate 为true则从0开始算
	 */
	public static Date fromUDate(java.util.Date date,boolean isDate)
	{
		Date d = new Date();
		d.setMonth(date.getMonth()+1);
		d.setDay(date.getDate());
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
		return Date.fromSDate(date, false);
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

	/**
	 * 补齐格式
	 * @param str
	 * @param len
	 * @return
	 */
	protected String setZero(String str,int len)
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
		return year+":"+month+":"+day;
	}

}
