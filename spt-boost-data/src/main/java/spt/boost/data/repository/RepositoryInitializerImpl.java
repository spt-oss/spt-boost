
package spt.boost.data.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.dao.DataAccessException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * {@link RepositoryInitializer} implementation
 */
@RequiredArgsConstructor
public class RepositoryInitializerImpl implements RepositoryInitializer {
	
	/**
	 * {@link Logger}
	 */
	private static final Logger logger = LoggerFactory.getLogger(RepositoryInitializerImpl.class);
	
	/**
	 * {@link ListableBeanFactory}
	 */
	@NonNull
	private final ListableBeanFactory beanFactory;
	
	/**
	 * Ignore {@link DataAccessException}
	 */
	@Setter
	@Accessors(chain = true)
	private boolean ignoreDataAccessException = true;
	
	@Override
	public void initialize() throws DataAccessException {
		
		this.beanFactory.getBeansOfType(InitializableRepository.class).forEach((name, repository) -> {
			
			try {
				
				logger.debug("Initializing repository '{}' started", name);
				
				repository.initialize();
				
				logger.debug("Initializing repository '{}' finished", name);
			}
			catch (DataAccessException e) {
				
				if (!this.ignoreDataAccessException) {
					
					throw e;
				}
				
				logger.error("Failed to initialize repository '{}'", name, e);
			}
		});
	}
}
