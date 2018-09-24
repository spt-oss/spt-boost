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
package spt.boost.web.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.NonNull;

/**
 * Simple {@link ObjectMapperCustomizer}
 */
public class SimpleObjectMapperCustomizer implements ObjectMapperCustomizer {
	
	@Override
	public void customize(@NonNull ObjectMapper objectMapper) {
		
		objectMapper
		/* @formatter:off */
			.findAndRegisterModules()
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			/* @formatter:on */
	}
}
