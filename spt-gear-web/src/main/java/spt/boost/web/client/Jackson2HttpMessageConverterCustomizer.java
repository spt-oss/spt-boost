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

package spt.boost.web.client;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.CustomRestTemplate;
import org.springframework.web.client.RestTemplate;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * {@link RestTemplateCustomizer}: {@link MappingJackson2HttpMessageConverter}
 */
@RequiredArgsConstructor
public class Jackson2HttpMessageConverterCustomizer implements RestTemplateCustomizer {
	
	/**
	 * {@link ObjectMapperCustomizer}
	 */
	@NonNull
	private final ObjectMapperCustomizer objectMapperCustomizer;
	
	/**
	 * Constructor
	 */
	public Jackson2HttpMessageConverterCustomizer() {
		
		this(new SimpleObjectMapperCustomizer());
	}
	
	@Override
	public void customize(RestTemplate restTemplate) {
		
		MappingJackson2HttpMessageConverter converter = CustomRestTemplate
			.getMappingJackson2HttpMessageConverter(restTemplate);
		
		this.objectMapperCustomizer.customize(converter.getObjectMapper());
	}
}
