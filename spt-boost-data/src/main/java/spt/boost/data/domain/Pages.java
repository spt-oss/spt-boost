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

package spt.boost.data.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.NonNull;

/**
 * {@link Page} utilities
 */
public class Pages {
	
	/**
	 * Constructor
	 */
	protected Pages() {
		
		/* NOP */
	}
	
	/**
	 * Fix
	 * 
	 * @param pageable {@link Pageable}
	 * @param total {@link Page#getTotalElements()}
	 * @return {@link Pageable}
	 */
	public static Pageable fix(@NonNull Pageable pageable, long total) {
		
		// Fix number
		int number = pageable.getPageNumber();
		
		if (total <= pageable.getOffset()) {
			
			number = 0;
		}
		
		return PageRequest.of(number, pageable.getPageSize(), pageable.getSort());
	}
	
	/**
	 * Fix
	 * 
	 * @param page {@link Page}
	 * @param maxTotal max {@link Page#getTotalElements()}
	 * @param <T> content type
	 * @return {@link Pageable}
	 */
	public static <T> Page<T> fix(@NonNull Page<T> page, long maxTotal) {
		
		// Fix total
		long total = page.getTotalElements();
		
		if (maxTotal < total) {
			
			total = maxTotal;
		}
		
		return new PageImpl<>(page.getContent(), page.getPageable(), total);
	}
}
