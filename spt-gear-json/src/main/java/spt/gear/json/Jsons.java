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

package spt.gear.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import spt.gear.java.util.Logs;

/**
 * JSON utilities
 */
public class Jsons {
	
	/**
	 * {@link Logger}
	 */
	private static final Logger logger = LoggerFactory.getLogger(Jsons.class);
	
	/**
	 * {@link ObjectMapper}
	 */
	private static ObjectMapper objectMapper = new ObjectMapper()
	/* @formatter:off */
		.findAndRegisterModules()
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		/* @formatter:on */
	
	/**
	 * Constructor
	 */
	protected Jsons() {
		
		/* NOP */
	}
	
	/**
	 * Set {@link #objectMapper}
	 * 
	 * @param objectMapper {@link #objectMapper}
	 */
	public static synchronized void setObjectMapper(ObjectMapper objectMapper) {
		
		Jsons.objectMapper = objectMapper;
	}
	
	/**
	 * Serialize
	 * 
	 * @param value value
	 * @return JSON
	 * @throws IllegalStateException if failed to serialize
	 */
	public static String serialize(Object value) throws IllegalStateException {
		
		try {
			
			return objectMapper.writeValueAsString(value);
		}
		catch (JsonProcessingException e) {
			
			throw new IllegalStateException(Logs.format("Failed to serialize: {}", e.getMessage()));
		}
	}
	
	/**
	 * Deserialize
	 * 
	 * @param json JSON
	 * @param clazz {@link Class}
	 * @param fallback fallback
	 * @param <T> value type
	 * @return value
	 */
	public static <T> T deserialize(String json, Class<T> clazz, T fallback) {
		
		if (json == null) {
			
			return fallback;
		}
		
		try {
			
			return objectMapper.readValue(json, clazz);
		}
		catch (IOException e) {
			
			logger.warn("Failed to deserialize: {}, {}", clazz.getSimpleName(), json); // TODO @checkstyle:ignore
			
			return fallback;
		}
	}
	
	/**
	 * Deserialize
	 * 
	 * @param json JSON
	 * @param reference {@link TypeReference}
	 * @param fallback fallback
	 * @param <T> value type
	 * @return value
	 */
	public static <T> T deserialize(String json, TypeReference<T> reference, T fallback) {
		
		if (json == null) {
			
			return fallback;
		}
		
		try {
			
			return objectMapper.readValue(json, reference);
		}
		catch (IOException e) {
			
			logger.warn("Failed to deserialize: {}, {}", reference.getType(), e.getMessage());
			
			return fallback;
		}
	}
}
