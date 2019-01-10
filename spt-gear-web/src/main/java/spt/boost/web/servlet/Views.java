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
package spt.boost.web.servlet;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

import lombok.NonNull;

/**
 * Custom {@link View}
 */
public class Views extends ModelAndView {
	
	/**
	 * Path separator
	 */
	private static final String PATH_SEPARATOR = "/";
	
	/**
	 * Path separator pattern
	 */
	private static final String PATH_SEPARATOR_PATTERN = "/+";
	
	/**
	 * Constructor
	 * 
	 * @param path path
	 */
	protected Views(String path) {
		
		super(path);
	}
	
	/**
	 * Constructor
	 * 
	 * @param view {@link View}
	 */
	protected Views(View view) {
		
		super(view);
	}
	
	/**
	 * Template
	 * 
	 * @param path path
	 * @return {@link Views}
	 */
	public static Views template(@NonNull String path) {
		
		// Avoid circular view path
		return new Views(path.replaceAll("^" + PATH_SEPARATOR_PATTERN, ""));
	}
	
	/**
	 * Template
	 * 
	 * @param controller controller
	 * @param path path
	 * @return {@link Views}
	 */
	public static Views template(@NonNull Object controller, String path) {
		
		return template(controller.getClass(), path);
	}
	
	/**
	 * Template
	 * 
	 * @param controllerClass controller class
	 * @param path path
	 * @return {@link Views}
	 */
	public static Views template(Class<?> controllerClass, String path) {
		
		return template(getRequestMappingPath(controllerClass, path));
	}
	
	/**
	 * Redirect
	 * 
	 * @param path path
	 * @return {@link RedirectView}
	 */
	public static Views redirect(String path) {
		
		RedirectView delegate = new RedirectView(path);
		delegate.setHttp10Compatible(false);
		delegate.setExposeModelAttributes(false);
		
		return new Views(delegate);
	}
	
	/**
	 * Redirect
	 * 
	 * @param status {@link HttpStatus}
	 * @param path path
	 * @return {@link RedirectView}
	 */
	public static Views redirect(HttpStatus status, String path) {
		
		RedirectView delegate = new RedirectView(path);
		delegate.setStatusCode(status);
		delegate.setHttp10Compatible(false);
		delegate.setExposeModelAttributes(false);
		
		return new Views(delegate);
	}
	
	/**
	 * Redirect
	 * 
	 * @param controller controller
	 * @param path path
	 * @return {@link Views}
	 */
	public static Views redirect(@NonNull Object controller, String path) {
		
		return redirect(controller.getClass(), path);
	}
	
	/**
	 * Redirect
	 * 
	 * @param controllerClass controller class
	 * @param path path
	 * @return {@link Views}
	 */
	public static Views redirect(Class<?> controllerClass, String path) {
		
		return redirect(getRequestMappingPath(controllerClass, path));
	}
	
	/**
	 * Forward
	 * 
	 * @param path path
	 * @return {@link Views}
	 */
	public static Views forward(String path) {
		
		return new Views(new InternalResourceView(path));
	}
	
	/**
	 * Parse {@link RequestMapping#path()}
	 * 
	 * @param controllerClass controller class
	 * @param path path
	 * @return path
	 */
	protected static String getRequestMappingPath(Class<?> controllerClass, String path) {
		
		String url = path;
		
		if (controllerClass != null) {
			
			url = String.join(PATH_SEPARATOR, getRequestMappingPath(controllerClass), path);
		}
		
		return url.replaceAll(PATH_SEPARATOR_PATTERN, PATH_SEPARATOR);
	}
	
	/**
	 * Get {@link RequestMapping#path()}
	 * 
	 * @param controllerClass controller class
	 * @return path
	 */
	protected static String getRequestMappingPath(Class<?> controllerClass) {
		
		RequestMapping annotation = AnnotationUtils.getAnnotation(controllerClass, RequestMapping.class);
		
		if (annotation == null) {
			
			return "";
		}
		
		String[] paths = annotation.value();
		
		if (paths.length == 0) {
			
			paths = annotation.path();
			
			if (paths.length == 0) {
				
				return "";
			}
		}
		
		return paths[0];
	}
}
