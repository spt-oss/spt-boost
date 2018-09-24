
package spt.boost.web.servlet.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;
import org.springframework.web.util.UrlPathHelper;

/**
 * Index {@link UrlPathHelper}
 */
public class IndexUrlPathHelper extends UrlPathHelper {
	
	/**
	 * Index name
	 */
	private String indexName;
	
	/**
	 * Constrcutor
	 */
	public IndexUrlPathHelper() {
		
		this("index");
	}
	
	/**
	 * Constructor
	 * 
	 * @param indexName index name
	 */
	public IndexUrlPathHelper(String indexName) {
		
		Assert.hasText(indexName, "Index name is empty");
		
		this.indexName = indexName;
	}
	
	@Override
	public String getLookupPathForRequest(HttpServletRequest request) {
		
		String path = super.getLookupPathForRequest(request);
		
		if (path.endsWith("/")) {
			
			return path + this.indexName;
		}
		
		return path;
	}
}
