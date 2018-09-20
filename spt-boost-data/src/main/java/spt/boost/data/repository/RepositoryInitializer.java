
package spt.boost.data.repository;

import org.springframework.dao.DataAccessException;

/**
 * Repository initializer
 */
public interface RepositoryInitializer {
	
	/**
	 * Initialize
	 * 
	 * @throws DataAccessException if failed to initialize
	 */
	void initialize() throws DataAccessException;
}
