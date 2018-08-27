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

package spt.boost.java.lang;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@link Test}: {@link Enums}
 */
public class EnumsTests {
	
	/**
	 * {@link Enums#of(Class, Object)}
	 */
	@Test
	public void of() {
		
		for (ExampleEnum value : ExampleEnum.values()) {
			
			assertThat(Enums.of(ExampleEnum.class, value.getId())).isEqualTo(value);
		}
		
		try {
			
			Enums.of(ExampleEnum.class, 0L);
			
			fail();
		}
		catch (IllegalStateException e) {
			
			/* NOP */
		}
	}
	
	/**
	 * Example {@link Enum}
	 */
	@AllArgsConstructor
	protected enum ExampleEnum implements Identifiable<Long> {
		
		/**
		 * A
		 */
		A(1L),
		
		/**
		 * B
		 */
		B(2L);
		
		/**
		 * ID
		 */
		@Getter
		private final Long id;
	}
}
