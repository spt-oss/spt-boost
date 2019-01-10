/*
 * Copyright 2018-2019 the original author or authors.
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
package spt.gear.java.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * {@link Test}: {@link Logs}
 */
public class LogsTests {
	
	/**
	 * {@link Logs#format(String, Object...)}
	 */
	@Test
	public void format() {
		
		assertThat(Logs.format("log: {} {} {}", 1, "a", new Exception())).isEqualTo("log: 1 a java.lang.Exception");
		assertThat(Logs.format("log: {} {}", 2, "b")).isEqualTo("log: 2 b");
		assertThat(Logs.format("log: {}", null, 1)).isEqualTo("log: null");
	}
}
