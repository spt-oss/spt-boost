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

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.NonNull;

/**
 * Custom {@link Collections}
 * 
 * @see Collections
 * @see "com.google.common.collect.Collections2"
 */
public class Collections3 {
	
	/**
	 * Constructor
	 */
	protected Collections3() {
		
		/* NOP */
	}
	
	/**
	 * New shuffle
	 * 
	 * @param sources sources
	 * @param <T> element type
	 * @return results
	 */
	public static <T> List<T> newShuffle(@NonNull List<T> sources) {
		
		List<T> results = new ArrayList<>(sources.size());
		results.addAll(sources);
		
		Collections.shuffle(results, new SecureRandom());
		
		return results;
	}
}
