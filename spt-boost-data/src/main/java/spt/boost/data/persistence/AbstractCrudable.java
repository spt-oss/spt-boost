
package spt.boost.data.persistence;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Abstract {@link Crudable}
 */
@MappedSuperclass
@Data
@Accessors(chain = true)
public abstract class AbstractCrudable implements Crudable {
	
	/**
	 * {@link #isPersistent()}
	 */
	@Setter(AccessLevel.PROTECTED)
	@Transient
	private boolean persistent;
	
	/**
	 * {@link #getCreationDate()}
	 */
	@Setter(AccessLevel.PROTECTED)
	private LocalDateTime creationDate;
	
	/**
	 * {@link #getModificationDate()}
	 */
	@Setter(AccessLevel.PROTECTED)
	private LocalDateTime modificationDate;
	
	/**
	 * {@link #getVersion()}
	 */
	@Setter(AccessLevel.PROTECTED)
	@Version
	private Long version;
	
	/**
	 * {@link PostLoad}
	 */
	@PostLoad
	protected void postLoad() {
		
		this.setPersistent(true);
	}
	
	/**
	 * {@link PrePersist}
	 */
	@PrePersist
	protected void prePersist() {
		
		LocalDateTime now = LocalDateTime.now();
		
		this.setCreationDate(Optional.ofNullable(this.creationDate).orElse(now));
		this.setModificationDate(Optional.ofNullable(this.modificationDate).orElse(now));
		this.setVersion(Optional.ofNullable(this.version).orElse(1L));
	}
	
	/**
	 * {@link PreUpdate}
	 */
	@PreUpdate
	protected void preUpdate() {
		
		this.setModificationDate(Optional.ofNullable(this.modificationDate).orElse(LocalDateTime.now()));
	}
}
