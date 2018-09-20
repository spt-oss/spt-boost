
package spt.boost.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import lombok.NonNull;

/**
 * {@link JpaRepository} utilities
 */
public class JpaRepositories {
	
	/**
	 * Constructor
	 */
	protected JpaRepositories() {
		
		/* NOP */
	}
	
	/**
	 * Find all
	 * 
	 * @param repository {@link JpaRepository}
	 * @param firstPageable first {@link Pageable}
	 * @param <T> entity type
	 * @return {@link List}: entity
	 */
	public static <T> List<T> findAll(@NonNull JpaRepository<T, ?> repository, Pageable firstPageable) {
		
		List<T> entities = new ArrayList<>();
		Page<T> page = repository.findAll(firstPageable);
		
		while (page.hasContent()) {
			
			entities.addAll(page.getContent());
			
			if (!page.hasNext()) {
				
				break;
			}
			
			page = repository.findAll(page.nextPageable());
		}
		
		return entities;
	}
	
	/**
	 * For each
	 * 
	 * @param repository {@link JpaRepository}
	 * @param firstPageable first {@link Pageable}
	 * @param action action
	 * @param <T> entity type
	 */
	public static <T> void forEach(@NonNull JpaRepository<T, ?> repository, Pageable firstPageable,
		Consumer<T> action) {
		
		Page<T> page = repository.findAll(firstPageable);
		
		while (page.hasContent()) {
			
			page.getContent().forEach(action);
			
			if (!page.hasNext()) {
				
				break;
			}
			
			page = repository.findAll(page.nextPageable());
		}
	}
}
