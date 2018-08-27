/*
 * Copyright 2017 the original author or authors.
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

package spt.boost.java.lang;

import java.time.Duration;

import lombok.NonNull;

/**
 * {@link Thread} utilities
 */
public class Threads {
	
	/**
	 * Constructor
	 */
	protected Threads() {
		
		/* NOP */
	}
	
	/**
	 * Sleep
	 * 
	 * @param duration {@link Duration}
	 * @throws UncheckedInterruptedException if thread interrupted
	 */
	public static void sleep(@NonNull Duration duration) throws UncheckedInterruptedException {
		
		sleep(duration.toMillis());
	}
	
	/**
	 * Sleep
	 * 
	 * @param millis millis
	 * @throws UncheckedInterruptedException if thread interrupted
	 */
	public static void sleep(long millis) throws UncheckedInterruptedException {
		
		try {
			
			Thread.sleep(millis);
		}
		catch (InterruptedException e) {
			
			throw new UncheckedInterruptedException("Thread interrupted", e);
		}
	}
}
