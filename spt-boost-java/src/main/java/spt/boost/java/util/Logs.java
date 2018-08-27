
package spt.boost.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * Log message builder for {@link Logger} and {@link Exception}
 */
@EqualsAndHashCode
public class Logs {
	
	/**
	 * Message
	 */
	private final String message;
	
	/**
	 * Binding variables
	 */
	private final List<Object> binds;
	
	/**
	 * Parameter values
	 */
	private final List<Object> values = new ArrayList<>();
	
	/**
	 * Constructor
	 * 
	 * @param message {@link #message}
	 * @param binds {@link #binds}
	 */
	protected Logs(String message, Object... binds) {
		
		this.message = message;
		this.binds = Arrays.asList(binds);
	}
	
	/**
	 * Of
	 * 
	 * @param message {@link #message}
	 * @return {@link Logs}
	 */
	public static Logs of(String message) {
		
		return new Logs(message);
	}
	
	/**
	 * Of
	 * 
	 * @param message {@link #message}
	 * @param binds {@link #binds}
	 * @return {@link Logs}
	 */
	public static Logs of(String message, Object... binds) {
		
		return new Logs(message, binds);
	}
	
	/**
	 * Of
	 * 
	 * @param message {@link #message}
	 * @return {@link Logs}
	 */
	public static Logs of(@NonNull StringBuilder message) {
		
		return new Logs(message.toString());
	}
	
	/**
	 * Of
	 * 
	 * @param message {@link #message}
	 * @param binds {@link #binds}
	 * @return {@link Logs}
	 */
	public static Logs of(@NonNull StringBuilder message, Object... binds) {
		
		return new Logs(message.toString(), binds);
	}
	
	/**
	 * With
	 * 
	 * @param values {@link #values}
	 * @return {@link Logs}
	 */
	public String with(Object... values) {
		
		this.values.addAll(Arrays.asList(values));
		
		return this.toString();
	}
	
	@Override
	public String toString() {
		
		StringBuilder buffer = new StringBuilder();
		
		// Binding variables
		if (!this.binds.isEmpty()) {
			
			buffer.append(MessageFormatter.arrayFormat(this.message, this.binds.toArray()).getMessage());
		}
		else {
			
			buffer.append(this.message);
		}
		
		// Parameters
		if (!this.values.isEmpty()) {
			
			buffer.append(": ");
			
			buffer.append(
			/* @formatter:off */
				this.values.stream()
					.map(value -> String.valueOf(value))
					.collect(Collectors.collectingAndThen(Collectors.toList(), values -> String.join(", ", values)))
				/* @formatter:on */
			);
		}
		
		return buffer.toString();
	}
}
