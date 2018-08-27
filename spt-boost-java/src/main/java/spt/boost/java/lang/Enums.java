
package spt.boost.java.lang;

import lombok.NonNull;
import spt.boost.java.util.Logs;

/**
 * {@link Enum} utilities
 */
public class Enums {
	
	/**
	 * Constructor
	 */
	protected Enums() {
		
		/* NOP */
	}
	
	/**
	 * Of
	 * 
	 * @param clazz {@link Enum}
	 * @param id ID
	 * @param <E> {@link Enum} type
	 * @param <I> ID type
	 * @return {@link Enum}
	 * @throws IllegalStateException if not found
	 */
	public static <E extends Enum<E> & Identifiable<I>, I> E of(@NonNull Class<E> clazz, @NonNull I id)
		throws IllegalStateException {
		
		for (E constant : clazz.getEnumConstants()) {
			
			if (constant.getId().equals(id)) {
				
				return constant;
			}
		}
		
		throw new IllegalStateException(Logs.of("Enum not found").with(clazz, id));
	}
}
