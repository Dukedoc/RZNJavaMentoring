/**
 * 
 */
package marketplace.controller.helpers.web;

import marketplace.controller.helpers.SearchHelper;

/**
 * Search Helper for web.
 * 
 * @author Roman_Ten
 * 
 */
public class WebSearchHelper implements SearchHelper {

    private static final String SEARCH_TYPE_PARAMETER = "searchType";
    private static final String KEYWORDS_PARAMETER = "keywords";
    private WebHelperFactory factory;

    /**
     * Constructor.
     * 
     * @param webHelperFactory
     *            the Helper Factory for web application.
     */
    public WebSearchHelper(WebHelperFactory webHelperFactory) {
	this.factory = webHelperFactory;
    }

    /**
     * {@inheritDoc}
     */
    public String getKeywords() {
	return factory.getRequest().getParameter(KEYWORDS_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    public String getType() {
	return factory.getRequest().getParameter(SEARCH_TYPE_PARAMETER);
    }

}
