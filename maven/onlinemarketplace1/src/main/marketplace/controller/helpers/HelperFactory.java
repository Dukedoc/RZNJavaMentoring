/**
 * 
 */
package marketplace.controller.helpers;

import javax.servlet.http.HttpServletRequest;

import marketplace.controller.helpers.web.WebHelperFactory;

/**
 * Helper Factory.
 * 
 * @author Roman_Ten
 * 
 */
public abstract class HelperFactory {

    /**
     * Parameter for Web Application.
     */
    public static final int WEB = 0;

    /**
     * Create Helper Factory.
     * 
     * @param whichFactory
     *            type of factory.
     * @param request
     *            the HttpServlet request.
     * @return helper factory.
     */
    public static HelperFactory getHelperFactory(int whichFactory,
	    HttpServletRequest request) {
	switch (whichFactory) {
	case 0:
	    return new WebHelperFactory(request);
	default:
	    return null;
	}
    }

    /**
     * Create Command Helper.
     * 
     * @return command helper.
     */
    public abstract CommandHelper getCommandHelper();

    /**
     * Create search helper.
     * 
     * @return search helper.
     */
    public abstract SearchHelper getSearchHelper();

    /**
     * Create User helper.
     * 
     * @return user helper.
     */
    public abstract UserHelper getUserHelper();

    /**
     * Create Item Helper.
     * 
     * @return item helper.
     */
    public abstract ItemHelper getItemHelper();

    /**
     * Create Bid Helper.
     * 
     * @return bid helper.
     */
    public abstract BidHelper getBidHelper();
}
