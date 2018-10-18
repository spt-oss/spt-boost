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

package spt.boost.data.jpa.model;

import java.time.LocalDateTime;
import java.util.Objects;
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
	private LocalDateTime creationDate;
	
	/**
	 * {@link #getModificationDate()}
	 */
	private LocalDateTime modificationDate;
	
	/**
	 * Initial {@link #modificationDate}
	 */
	@Transient
	private LocalDateTime initialModificationDate;
	
	/**
	 * {@link #getVersion()}
	 */
	@Version
	private Long version;
	
	/**
	 * {@link PostLoad}
	 */
	@PostLoad
	protected void postLoad() {
		
		this.setPersistent(true);
		this.setInitialModificationDate(this.modificationDate);
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
		
		if (Objects.equals(this.modificationDate, this.initialModificationDate)) {
			
			this.setModificationDate(LocalDateTime.now());
		}
	}
}
