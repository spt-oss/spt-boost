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

package spt.gear.data.jpa.repository;

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
				
				logger.debug("Initializing '{}'", name);
				
				repository.initialize();
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
