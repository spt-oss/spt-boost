/*
 * Copyright 2018-2019 the original author or authors.
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

import org.slf4j.helpers.MessageFormatter;

/**
 * Log utilities: {@link org.slf4j.Logger} and {@link Exception}
 */
public class Logs {
	
	/**
	 * Constructor
	 */
	protected Logs() {
		
		/* NOP */
	}
	
	/**
	 * Format
	 * 
	 * @param message message
	 * @param arguments arguments
	 * @return log
	 */
	public static String format(String message, Object... arguments) {
		
		return MessageFormatter.arrayFormat(message, arguments, null).getMessage();
	}
}
