/**
 * 
 */
package marketplace.controller.helpers.web;

import javax.servlet.http.HttpServletRequest;

import marketplace.controller.helpers.BidHelper;
import marketplace.controller.helpers.CommandHelper;
import marketplace.controller.helpers.HelperFactory;
import marketplace.controller.helpers.ItemHelper;
import marketplace.controller.helpers.UserHelper;
import marketplace.controller.helpers.SearchHelper;

/**
 * Helper Factory for Web Application.
 * 
 * @author Roman_Ten
 * 
 */
public class WebHelperFactory extends HelperFactory {

    private HttpServletRequest request;

    /**
     * Constructor.
     * 
     * @param request
     *            the HttpServletRequest.
     */
    public WebHelperFactory(HttpServletRequest request) {
	this.request = request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandHelper getCommandHelper() {
	return new WebCommandHelper(this);
    }

    /**
     * Get Request.
     * 
     * @return the request
     */
    HttpServletRequest getRequest() {
	return request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SearchHelper getSearchHelper() {
	return new WebSearchHelper(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserHelper getUserHelper() {
	return new WebUserHelper(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemHelper getItemHelper() {
	return new WebItemHelper(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BidHelper getBidHelper() {
	return new WebBidHelper(this);
    }

}
