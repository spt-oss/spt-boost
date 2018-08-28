/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package spt.boost.java.util;

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
	private List<Object> values;
	
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
		
		this.values = Arrays.asList(values);
		
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
		if (this.values != null) {
			
			List<String> values = this.values.stream()
			/* @formatter:off */
				.map(value -> String.valueOf(value))
				.collect(Collectors.toList());
				/* @formatter:on */
			
			buffer.append(": ");
			buffer.append(String.join(", ", values));
		}
		
		return buffer.toString();
	}
}
