
package spt.gear.java.time;

import java.time.format.DateTimeFormatter;

/**
 * {@link DateTimeFormatter} utilities
 */
public class DateTimeFormatters {
	
	/**
	 * Constructor
	 */
	protected DateTimeFormatters() {
		
		/* NOP */
	}
	
	/**
	 * {@link DateTimeFormatter#ISO_LOCAL_DATE_TIME}: Without nanos
	 */
	public static final DateTimeFormatter ISO_LOCAL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
}
