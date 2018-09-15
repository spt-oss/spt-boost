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

package spt.boost.java.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link Test}: {@link Logs}
 */
public class LogsTests {
	
	/**
	 * {@link Logs#of(CharSequence)} etc.
	 */
	@Test
	public void of() {
		
		String name = LogsTests.class.getName();
		
		assertThat(Logs.of(name)).isEqualTo(new Logs(name));
		assertThat(Logs.of(new StringBuilder(name).append(name))).isEqualTo(new Logs(name + name));
	}
	
	/**
	 * {@link Logs#with(Object...)}
	 */
	@Test
	public void with() {
		
		assertThat(Logs.of("with {} {} {}").with(null, 1, "b1")).isEqualTo("with null 1 b1");
		assertThat(Logs.of("with {} {}").with(null, 1, "b2")).isEqualTo("with null 1: b2");
		assertThat(Logs.of("with {}").with(null, 1, "b3")).isEqualTo("with null: 1, b3");
		
		try {
			
			Logs.of("with {} {} {} {}").with(null, 1, "b4");
			
			Assert.fail();
		}
		catch (IllegalStateException e) {
			
			/* NOP */
		}
	}
}
