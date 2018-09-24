
package spt.boost.web.servlet.config;

import java.util.Map;

import javax.servlet.RequestDispatcher;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import lombok.NonNull;

/**
 * Simple {@link org.springframework.boot.web.servlet.error.ErrorAttributes}
 * 
 * <pre>
 * <code>
 * {
 *     "status": 400,
 *     "error": "Bad Request",
 *     "exception": "org.springframework.web.bind.MissingServletRequestParameterException",
 *     "message": "Required String parameter 'id' is not present",
 *     "errors": [(Validation results)]
 * }
 * </code>
 * </pre>
 */
public class SimpleErrorAttributes extends DefaultErrorAttributes {
	
	/**
	 * Include exception
	 */
	private final boolean includeException;
	
	/**
	 * Constructor
	 * 
	 * @param includeException {@link #includeException}
	 */
	public SimpleErrorAttributes(boolean includeException) {
		
		super(includeException);
		
		this.includeException = includeException;
	}
	
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		
		this.rewriteRequest(webRequest);
		
		Map<String, Object> attributes = super.getErrorAttributes(webRequest, includeStackTrace);
		
		this.removeUnused(attributes);
		this.hideMessage(attributes);
		
		return attributes;
	}
	
	/**
	 * Rewrite request
	 * 
	 * @param webRequest {@link WebRequest}
	 */
	protected void rewriteRequest(@NonNull WebRequest webRequest) {
		
		// Remove error message to use exception message
		webRequest.removeAttribute(RequestDispatcher.ERROR_MESSAGE, RequestAttributes.SCOPE_REQUEST);
	}
	
	/**
	 * Remove unused
	 * 
	 * @param attributes attributes
	 */
	protected void removeUnused(@NonNull Map<String, Object> attributes) {
		
		attributes.remove("timestamp");
		attributes.remove("path");
	}
	
	/**
	 * Hide message
	 * 
	 * @param attributes attributes
	 */
	protected void hideMessage(@NonNull Map<String, Object> attributes) {
		
		if (!this.includeException) {
			
			attributes.remove("message"); // TODO @checkstyle:ignore
		}
		
		Object message = attributes.get("message");
		
		if (message != null && message.equals("No message available")) {
			
			attributes.remove("message");
		}
	}
}
