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

package spt.gear.data.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.NonNull;

/**
 * {@link Pageable} utilities
 */
public class Pageables {
	
	/**
	 * Constructor
	 */
	protected Pageables() {
		
		/* NOP */
	}
	
	/**
	 * Fix {@link Pageable#getPageNumber()}
	 * 
	 * @param page {@link Pageable#getPageNumber()}
	 * @param size {@link Pageable#getPageSize()}
	 * @param maxTotal max total
	 * @return {@link Pageable}
	 */
	public static Pageable fixPageNumber(int page, int size, long maxTotal) {
		
		return fixPageNumber(PageRequest.of(page, size), maxTotal);
	}
	
	/**
	 * Fix {@link Pageable#getPageNumber()}
	 * 
	 * @param pageable {@link Pageable}
	 * @param size {@link Pageable#getPageSize()}
	 * @param maxTotal max total
	 * @return {@link Pageable}
	 */
	public static Pageable fixPageNumber(@NonNull Pageable pageable, int size, long maxTotal) {
		
		return fixPageNumber(PageRequest.of(pageable.getPageNumber(), size, pageable.getSort()), maxTotal);
	}
	
	/**
	 * Fix {@link Pageable#getPageNumber()}
	 * 
	 * @param pageable {@link Pageable}
	 * @param maxTotal max total
	 * @return {@link Pageable}
	 */
	public static Pageable fixPageNumber(@NonNull Pageable pageable, long maxTotal) {
		
		int page = pageable.getPageNumber();
		
		if (maxTotal <= pageable.getOffset()) {
			
			page = 0;
		}
		
		return PageRequest.of(page, pageable.getPageSize(), pageable.getSort());
	}
}
