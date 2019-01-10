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

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

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
	 * Fix {@link Page#getTotalElements()}
	 * 
	 * @param page {@link Page}
	 * @param maxTotal max {@link Page#getTotalElements()}
	 * @param <T> content type
	 * @return {@link Page}
	 */
	public static <T> Page<T> fixTotalElements(@NonNull Page<T> page, long maxTotal) {
		
		long total = page.getTotalElements();
		
		if (maxTotal < total) {
			
			total = maxTotal;
		}
		
		return new PageImpl<>(page.getContent(), page.getPageable(), total);
	}
	
	/**
	 * Fix {@link Page#getContent()}
	 * 
	 * @param page {@link Page}
	 * @param content {@link Page#getContent()}
	 * @param <T> content type
	 * @return {@link Page}
	 */
	public static <T> Page<T> fixContent(@NonNull Page<?> page, List<T> content) {
		
		return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
	}
}
