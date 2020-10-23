package be.occam.velo.domain.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Timing {
	
	public static String datePattern 
		= "dd/MM/yyyy";
	
	public static String timePattern
		= "HH:mm";
	
	public static String momentPattern 
		= new StringBuilder( datePattern ).append( " - " ).append( timePattern ).toString();
	
	public static final SimpleDateFormat momentFormat
		= new SimpleDateFormat( momentPattern );
	
	public static final SimpleDateFormat dateFormat
		= new SimpleDateFormat( new StringBuilder( datePattern ).toString() );
	
	public static final SimpleDateFormat timeFormat
		= new SimpleDateFormat( new StringBuilder( timePattern ).toString() );
	
	public static Date date( String date ) {
		
		try {
			return dateFormat.parse( date );
		}
		catch( ParseException e ) {
			throw new RuntimeException( e );
		}
		
	}
	
	public static Date moment( String date ) {
		
		try {
			return momentFormat.parse( date );
		}
		catch( ParseException e ) {
			throw new RuntimeException( e );
		}
		
	}
	
	public static Date moment( String date, String time ) {
		
		return moment( new StringBuilder( date ).append( " " ).append( time ).toString() );

	}
	
	public static String date( Date date ) {
		
		return dateFormat.format( date );
		
	}
	
	public static String date( Date date, String format ) {
		
		return new SimpleDateFormat( format). format( date );
		
	}
	
	public static String moment( Date date ) {
		
		return momentFormat.format( date );
		
	}
	
	public static Date currentMonthStart( ) {
		
		GregorianCalendar calendar
			= new GregorianCalendar();
		
		calendar.set( Calendar.DAY_OF_MONTH , 1 );
		
		calendar.set( Calendar.HOUR_OF_DAY, 0 );
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND , 0 );
		
		return calendar.getTime();

	}
	
	public static Date previousMonthStart( ) {
		
		GregorianCalendar calendar
			= new GregorianCalendar();
		
		calendar.add( Calendar.MONTH, -1 );
		
		calendar.set( Calendar.DAY_OF_MONTH , 1 );
		
		calendar.set( Calendar.HOUR_OF_DAY, 0 );
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND , 0 );
		
		return calendar.getTime();
		
		

	}
	
	public static Date monthStart( Integer month ) {
		
		GregorianCalendar calendar
			= new GregorianCalendar();
		
		calendar.set( Calendar.MONTH, month - 1 );
		calendar.set( Calendar.DAY_OF_MONTH , 1 );
		
		calendar.set( Calendar.HOUR_OF_DAY, 0 );
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND , 0 );
		
		return calendar.getTime();
		
		

	}
	
	public static Date monthEnd( Integer month ) {
		
		GregorianCalendar calendar
			= new GregorianCalendar();
		
		calendar.set( Calendar.MONTH, month - 1 );
		calendar.set( Calendar.DAY_OF_MONTH , 1 );
		
		calendar.set( Calendar.HOUR_OF_DAY, 23 );
		calendar.set( Calendar.MINUTE, 59 );
		calendar.set( Calendar.SECOND , 59 );
		
		// last day of month
		calendar.set( Calendar.DAY_OF_MONTH, calendar.getActualMaximum( Calendar.DAY_OF_MONTH ) );
		
		return calendar.getTime();
		
		

	}
	
	public static Date dayBeforeYesterdayStart( ) {
		
		GregorianCalendar calendar
			= new GregorianCalendar();
		
		calendar.add( Calendar.DAY_OF_MONTH, -2 );
		
		calendar.set( Calendar.HOUR_OF_DAY, 0 );
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND , 0 );
		
		return calendar.getTime();
		
	}
	
	public static Date dayStart( String day ) {
		
		GregorianCalendar calendar
			= new GregorianCalendar();
		
		Date d = Timing.date( day );
		calendar.setTime( d );
		
		calendar.set( Calendar.HOUR_OF_DAY, 0 );
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND , 0 );
		
		return calendar.getTime();
		
	}
	
	public static Date dayStop( String day ) {
		
		GregorianCalendar calendar
			= new GregorianCalendar();
		
		Date d = Timing.date( day );
		calendar.setTime( d );
		
		calendar.set( Calendar.HOUR_OF_DAY, 23 );
		calendar.set( Calendar.MINUTE, 59 );
		calendar.set( Calendar.SECOND , 59 );
		
		return calendar.getTime();
		
	}
	
	public static Date yesterdayStart( ) {
		
		GregorianCalendar calendar
			= new GregorianCalendar();
		
		calendar.add( Calendar.DAY_OF_MONTH, -1 );
		
		calendar.set( Calendar.HOUR_OF_DAY, 0 );
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND , 0 );
		
		return calendar.getTime();
		
	}
	
	public static Date yesterdayStop( ) {
		
		GregorianCalendar calendar
			= new GregorianCalendar();
		
		calendar.add( Calendar.DAY_OF_MONTH, -1 );
		
		calendar.set( Calendar.HOUR_OF_DAY, 23 );
		calendar.set( Calendar.MINUTE, 59 );
		calendar.set( Calendar.SECOND , 59 );
		
		return calendar.getTime();
		
	}
	
	public static Date todayStart( ) {
		
		GregorianCalendar calendar
			= new GregorianCalendar();
		
		calendar.set( Calendar.HOUR_OF_DAY, 0 );
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND , 0 );
		
		return calendar.getTime();
		
		

	}

}
