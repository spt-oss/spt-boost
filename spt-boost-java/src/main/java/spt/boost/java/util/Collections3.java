
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
