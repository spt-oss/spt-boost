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

import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Custom {@link Comparator}
 * 
 * @see Comparator
 */
public class Comparator2 {
	
	/**
	 * Constructor
	 */
	protected Comparator2() {
		
		/* NOP */
	}
	
	/**
	 * Shuffle
	 * 
	 * @param <T> element type
	 * @return {@link Comparator}
	 * @see "https://stackoverflow.com/questions/29241746/shuffle-random-comparator"
	 */
	public static <T> Comparator<T> shuffle() {
		
		Map<Object, UUID> ids = new IdentityHashMap<>();
		
		return Comparator.comparing(key -> ids.computeIfAbsent(key, k -> UUID.randomUUID()));
	}
}
