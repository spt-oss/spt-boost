
package spt.boost.web.servlet.config;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * NOP {@link SortArgumentResolver}
 */
public class NopSortArgumentResolver implements SortArgumentResolver {
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		return false;
	}
	
	@Override
	public Sort resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		
		return Sort.unsorted();
	}
}
