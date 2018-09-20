
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
