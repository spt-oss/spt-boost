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

package spt.boost.app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.util.StringUtils;

/**
 * {@link org.springframework.boot.actuate.endpoint.annotation.Endpoint} utilities
 */
public class Endpoints {
	
	/**
	 * {@link Logger}
	 */
	private static final Logger logger = LoggerFactory.getLogger(Endpoints.class);
	
	/**
	 * Property name for shutdown URL
	 */
	public static final String SHUTDOWN_URL_PROPERTY_NAME = "management.endpoint.shutdown.url";
	
	/**
	 * Constructor
	 */
	protected Endpoints() {
		
		/* NOP */
	}
	
	/**
	 * Shutdown
	 * 
	 * @param args args
	 * @return {@code true} if shutdowned
	 * @throws IllegalStateException if failed to shutdown
	 */
	public static boolean shutdown(String... args) throws IllegalStateException {
		
		String url = new SimpleCommandLinePropertySource(args).getProperty(SHUTDOWN_URL_PROPERTY_NAME);
		
		if (!StringUtils.hasText(url)) {
			
			return false;
		}
		
		URL request = null;
		
		try {
			
			request = new URL(url);
		}
		catch (MalformedURLException e) {
			
			throw new IllegalStateException(String.format("Invalid shutdown endpoint URL: %s", url), e);
		}
		
		HttpURLConnection connection = null;
		
		try {
			
			connection = (HttpURLConnection) request.openConnection();
			connection.setRequestMethod("POST");
			
			int status = connection.getResponseCode();
			
			if (status == 200) {
				
				logger.debug("Active application stopped");
				
				return true;
			}
			
			throw new IllegalStateException(String.format("Shutdown endpoint did not return OK: %s", status));
		}
		catch (IOException e) {
			
			logger.debug("Application is not running");
			
			return false;
		}
		finally {
			
			if (connection != null) {
				
				connection.disconnect();
			}
		}
	}
}
