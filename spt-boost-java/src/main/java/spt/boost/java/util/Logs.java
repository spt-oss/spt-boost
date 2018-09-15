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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import spt.boost.java.lang.Strings;

/**
 * Log message builder for logger and {@link Exception}
 */
@EqualsAndHashCode
public class Logs {
	
	/**
	 * Bind
	 */
	private static final String BIND = "{}";
	
	/**
	 * Message
	 */
	private final String message;
	
	/**
	 * Values
	 */
	private final List<String> values = new ArrayList<>();
	
	/**
	 * Constructor
	 * 
	 * @param message {@link #message}
	 */
	protected Logs(@NonNull String message) {
		
		this.message = message;
	}
	
	/**
	 * Of
	 * 
	 * @param message {@link #message}
	 * @return {@link Logs}
	 */
	public static Logs of(@NonNull CharSequence message) {
		
		return new Logs(message.toString());
	}
	
	/**
	 * With
	 * 
	 * @param values {@link #values}
	 * @return {@link Logs}
	 */
	public String with(@NonNull Object... values) {
		
		for (Object value : values) {
			
			this.values.add(String.valueOf(value));
		}
		
		return this.toString();
	}
	
	@Override
	public String toString() {
		
		int bindCount = Strings.count(this.message, BIND);
		
		if (this.values.size() < bindCount) {
			
			throw new IllegalStateException(String.format("Invalid bind count: %d", bindCount));
		}
		
		// Message and binds
		StringBuilder buffer = new StringBuilder();
		
		if (0 < bindCount) {
			
			buffer.append(String.format(this.message.replaceAll(Pattern.quote(BIND), "%s"), this.values.toArray()));
		}
		else {
			
			buffer.append(this.message);
		}
		
		// Parameters
		List<String> values = this.values.subList(bindCount, this.values.size());
		
		if (0 < values.size()) {
			
			buffer.append(": ");
			buffer.append(String.join(", ", values));
		}
		
		return buffer.toString();
	}
}
