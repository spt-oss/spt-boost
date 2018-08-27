
package spt.boost.java.lang;

import java.lang.Thread.UncaughtExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Simple {@link UncaughtExceptionHandler}
 */
@RequiredArgsConstructor
public class SimpleUncaughtExceptionHandler implements UncaughtExceptionHandler {
	
	/**
	 * {@link Logger}
	 */
	@NonNull
	private final Logger logger;
	
	/**
	 * Constructor
	 */
	public SimpleUncaughtExceptionHandler() {
		
		this(LoggerFactory.getLogger(Thread.class));
	}
	
	@Override
	public void uncaughtException(Thread thread, Throwable e) {
		
		this.logger.error("Error occurred in thread", e);
	}
}
