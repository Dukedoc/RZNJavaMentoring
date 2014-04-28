/**
 * 
 */
package marketplace.controller.helpers.web;

import marketplace.controller.helpers.BidHelper;

/**
 * Bid helper for web application.
 * 
 * @author Roman_Ten
 * 
 */
public class WebBidHelper implements BidHelper {
    private static final String PARAMETER_BIDDER_ID = "bidderID";
    private static final String PARAMETER_ITEM_ID = "itemID";
    private static final String PARAMETER_BID = "bid";
    // attribute name for error.
    private static final String ERROR_MESSAGE = "errorMsg";
    // helper factory for web application.
    private WebHelperFactory helperFactory;

    /**
     * Constructor.
     * 
     * @param webHelperFactory
     *            the Web Helper Factory.
     */
    public WebBidHelper(WebHelperFactory webHelperFactory) {
	this.helperFactory = webHelperFactory;
    }

    /**
     * {@inheritDoc}
     */
    public float getBidValue() {
	return Float.parseFloat(helperFactory.getRequest().getParameter(
		PARAMETER_BID));
    }

    /**
     * {@inheritDoc}
     */
    public int getItemID() {
	return Integer.parseInt(helperFactory.getRequest().getParameter(
		PARAMETER_ITEM_ID));
    }

    /**
     * {@inheritDoc}
     */
    public void setErrorMsg(String message) {
	helperFactory.getRequest().setAttribute(ERROR_MESSAGE, message);
    }

    /**
     * {@inheritDoc}
     */
    public int getBidderID() {
	return Integer.parseInt(helperFactory.getRequest().getParameter(
		PARAMETER_BIDDER_ID));
    }

}
