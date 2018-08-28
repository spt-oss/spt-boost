
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
