package sl.util;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


public class DateUtils {
	public static final String DATE_FORMAT_STRING_YYYY_MM_DD = "yyyy-MM-dd";
	static SimpleDateFormat DATE_FORMAT_CURRENT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat DATE_FORMAT_YYYY_MM_DD = new SimpleDateFormat(DATE_FORMAT_STRING_YYYY_MM_DD);
	
	
	public static Date paserSringToDate(String dataString) throws ParseException{
		return DATE_FORMAT_CURRENT.parse(dataString);
	}
	public static final String getToday(){
		return DATE_FORMAT_YYYY_MM_DD.format(new Date());
	}
	
	public static final String getyyyy_MM_dd(Date date){
		return DATE_FORMAT_YYYY_MM_DD.format(date);
	}
	
	public static final Date getyyyy_MM_dd(String date){
		try {
			return DATE_FORMAT_YYYY_MM_DD.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static final String getFull(Date date){
		 return DateFormat.getDateInstance(DateFormat.FULL).format(date);
	}
	
	public static final String getCurrentTime(){
		return DATE_FORMAT_CURRENT.format(new Date());
	}
	public static final String createCurrentTimeFile(){
		return DATE_FORMAT_CURRENT.format(new Date());
	}
	/**
	 * 将iso8061格式的日期字符串转化为java.util.Date
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static final Date stringtoDateISO8061(String date) throws ParseException{
		DateTimeFormatter formatter = null;
		DateTime time = null ;
		try{
			formatter = ISODateTimeFormat.dateTimeParser();
			time = formatter.parseDateTime(date);
		}catch(Exception e){
			formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss HH:mm");;
			time = formatter.parseDateTime(date);
		}
		return time.toDate();
	}
	
	public static final Date stringToDate(String date) throws ParseException{
		Date resultDate = null ;
		if(null != date && !"".equals(date)){
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			resultDate = sdf.parse(date);
			String sdfDateStr = sdf1.format(resultDate);
			resultDate = sdf1.parse(sdfDateStr);
		}
		return resultDate;
	}
	
	/***
	 * 
	 * 函数功能说明  欧洲时间转化为北京时间，北京在东8区，推送比赛时间为东二区，相差6个时区
	 * huad  Aug 13, 2014 
	 * @param date  yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException   
	 * Date    
	 */
	public static final Date paserStringtoBJDate(String date) throws ParseException{
	        Date aDate = DATE_FORMAT_CURRENT.parse(date);
	        long lDate = aDate.getTime() + 6*60*60*1000;
	        return new Date(lDate);
	    }
	
	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getTwoTeamMins(Date startDate,Date endDate){
		if(startDate == null || endDate == null){
			return 0;
		}
		long mins = 60L * 1000L;
		long  result = 0 ;
		result = (endDate.getTime() - startDate.getTime()) / mins;
		return Integer.parseInt(result + "");
	}
	
	/**
	 * 将yyyy-MM-dd'T'HH:mm:ss HH:mm格式的日期字符串转化为java.util.Date
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static final Date stringtoDate(String date) throws ParseException{
		return DateUtils.stringtoDateISO8061(date);
	}
	public static final void createDirIfNot(String path){
		File f = new File(path);
		if( ! f.exists()){
			f.mkdirs();
		}
		//else do nothing
	}
	public static final String formatNanoToSeconds(long end,long begin){
		return String.format("%.2f", (end-begin)/1000000000.0);
	}
	
	/**
	 * 当前时间的前小时时间
	 * @param hour
	 * @return
	 */
	public static Timestamp  getBeforeHour(int hour){
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	Timestamp ts = new Timestamp(date.getTime()-hour*60*60*1000);
		return ts;
	}
	
	/**
	 * 
	 * @param date
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Date> getDay(Date date,int start,int end){
		List<Date> resultDate =  new ArrayList<Date>();
		for(int i = start; i<= end;i++){
			Date tempDate = new Date(date.getTime() + i*24*60*60*1000 );
			resultDate.add(tempDate);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		}
		return resultDate;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
//		DateUtils.getDayAndWeek(new Date(),-2,6);
//		DateUtils.getBeforeHour(24);
//		Date date = new Date();
//		
//		Date date1 = new Date();
		
		//System.out.println(DateUtils.getCurrentTime());
		
	}
}
