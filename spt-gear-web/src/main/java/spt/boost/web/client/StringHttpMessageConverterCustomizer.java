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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * {@link RestTemplateCustomizer}: {@link StringHttpMessageConverter}
 */
@RequiredArgsConstructor
public class StringHttpMessageConverterCustomizer implements RestTemplateCustomizer {
	
	/**
	 * {@link Charset}
	 */
	@NonNull
	private final Charset charset;
	
	/**
	 * Constructor
	 */
	public StringHttpMessageConverterCustomizer() {
		
		this(StandardCharsets.UTF_8);
	}
	
	@Override
	public void customize(RestTemplate restTemplate) {
		
		new RestTemplateBuilder()
		/* @formatter:off */
			.additionalMessageConverters(new StringHttpMessageConverter(this.charset))
			.configure(restTemplate);
			/* @formatter:on */
	}
}
