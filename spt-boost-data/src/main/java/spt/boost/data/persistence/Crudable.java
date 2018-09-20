
package spt.boost.data.persistence;

import java.time.LocalDateTime;

/**
 * Crudable
 */
public interface Crudable {
	
	/**
	 * Is persistent?
	 * 
	 * @return {@code true} if persistent
	 */
	boolean isPersistent();
	
	/**
	 * Get creation date
	 * 
	 * @return created date
	 */
	LocalDateTime getCreationDate();
	
	/**
	 * Get modification date
	 * 
	 * @return modification date
	 */
	LocalDateTime getModificationDate();
	
	/**
	 * Get version
	 * 
	 * @return version
	 */
	Long getVersion();
}
