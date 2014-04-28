package marketplace.controller.helpers.web;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import marketplace.controller.enumerations.ItemValidationFields;
import marketplace.controller.helpers.ItemHelper;
import marketplace.model.exception.ValidatorException;
import marketplace.model.to.Category;
import marketplace.model.to.Item;
import marketplace.model.to.ItemDetails;

/**
 * Item Helper for web application.
 * 
 * @author Roman_Ten
 * 
 */
public class WebItemHelper implements ItemHelper {

    private static final String DELIM = ":";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String TIME_LEFT_REGEX = "^[0-9]*(:[0-5][0-9])?$";
    private static final String BIDS_DETAIL_LIST_ATTRIBUTE = "bidsDetailList";
    private static final String DIRECTION_PARAMETER = "direction";
    private static final String SORT_PARAMETER = "sort";
    private static final String PAGE_COUNT_ATTRIBUTE = "pageCount";
    private static final String PAGE_PARAMETER = "page";
    private static final String CATEGORY_ID_PARAMETER = "categoryID";
    private static final String ITEM_ATTRIBUTE = "item";
    private static final String ITEM_ID_PARAMETER = "itemID";
    private static final String BUY_IT_NOW_PARAMETER = "buyItNow";
    private static final String TIME_LEFT_PARAMETER = "timeLeft";
    private static final String START_PRICE_PARAMETER = "startPrice";
    private static final String DESCRIPTION_PARAMETER = "description";
    private static final String BID_INCREMENT_PARAMETER = "bidIncrement";
    private static final String TITLE_PARAMETER = "title";
    private static final String ITEM_LIST_ATTRIBUTE = "itemList";
    private static final String CATEGORIES_LIST_ATTRIBUTE = "categoriesList";
    private static final int MINUTES_IN_HOUR = 60;
    private WebHelperFactory factory;

    /**
     * Constructor.
     * 
     * @param webHelperFactory
     *            the helper factory for web.
     */
    public WebItemHelper(WebHelperFactory webHelperFactory) {
	this.factory = webHelperFactory;
    }

    /**
     * {@inheritDoc}
     */
    public void setItemList(List<ItemDetails> itemList) {
	factory.getRequest().setAttribute(ITEM_LIST_ATTRIBUTE, itemList);
    }

    /**
     * {@inheritDoc}
     * 
     */
    public String getTitle() {
	return factory.getRequest().getParameter(TITLE_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    public float getBidIncrement() throws ValidatorException {
	float bidIncrement = 0;
	String string = factory.getRequest().getParameter(BID_INCREMENT_PARAMETER);
	if (string != null && !string.equals("")) {
	    try {
		bidIncrement = Float.parseFloat(factory.getRequest()
			.getParameter(BID_INCREMENT_PARAMETER));
	    } catch (NumberFormatException e) {
		throw new ValidatorException(ItemValidationFields.BID_INCREMENT);
	    }
	}
	return bidIncrement;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
	return factory.getRequest().getParameter(DESCRIPTION_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    public float getStartPrice() throws ValidatorException {
	float startPrice = 0;
	if (factory.getRequest().getParameter(START_PRICE_PARAMETER) != null
		&& !factory.getRequest().getParameter(START_PRICE_PARAMETER)
			.equals("")) {
	    try {
		startPrice = Float.parseFloat(factory.getRequest()
			.getParameter(START_PRICE_PARAMETER));
	    } catch (NumberFormatException e) {
		throw new ValidatorException(ItemValidationFields.START_PRICE);
	    }
	}
	return startPrice;
    }

    /**
     * {@inheritDoc}
     */
    public float getTimeLeft() throws ValidatorException {
	float timeLeft = 0;
	String timeLeftStr = factory.getRequest().getParameter(
		TIME_LEFT_PARAMETER);
	if ((timeLeftStr != null) && (!timeLeftStr.equals(""))) {
	    if (Pattern.matches(TIME_LEFT_REGEX, timeLeftStr)) {
		timeLeft = getTimeLeftFromString(timeLeftStr);
	    } else {
		throw new ValidatorException(ItemValidationFields.TIME_LEFT);
	    }
	}
	return timeLeft;
    }

    private float getTimeLeftFromString(String timeLeftStr)
	    throws ValidatorException {
	float timeLeft;
	try {
	    StringTokenizer st = new StringTokenizer(timeLeftStr, DELIM);
	    String str = st.nextToken();

	    int hours = Integer.parseInt(str);
	    float minute = 0;
	    if (st.hasMoreElements()) {
		str = st.nextToken();
		minute = Float.parseFloat(str);
	    }
	    timeLeft = hours + minute / MINUTES_IN_HOUR;
	} catch (NumberFormatException e) {
	    throw new ValidatorException(ItemValidationFields.TIME_LEFT);
	}
	return timeLeft;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isBuyItNow() {
	return Boolean.parseBoolean(factory.getRequest().getParameter(
		BUY_IT_NOW_PARAMETER));
    }

    /**
     * {@inheritDoc}
     */
    public int getItemID() {
	int itemID = -1;
	if (factory.getRequest().getParameter(ITEM_ID_PARAMETER) != null) {
	    if (!factory.getRequest().getParameter(ITEM_ID_PARAMETER)
		    .equals("")) {
		itemID = Integer.parseInt(factory.getRequest().getParameter(
			ITEM_ID_PARAMETER));
	    }
	}
	return itemID;
    }

    /**
     * {@inheritDoc}
     */
    public void setItem(Item item) {
	factory.getRequest().setAttribute(ITEM_ATTRIBUTE, item);
    }

    /**
     * {@inheritDoc}
     */
    public void setCategoriesList(List<Category> categoriesList) {
	factory.getRequest().setAttribute(CATEGORIES_LIST_ATTRIBUTE,
		categoriesList);
    }

    /**
     * {@inheritDoc}
     */
    public int getCategoryID() {
	return Integer.parseInt(factory.getRequest().getParameter(
		CATEGORY_ID_PARAMETER));
    }

    /**
     * {@inheritDoc}
     */
    public int getListNumber() {
	int listNumber = 0;
	String listNumberStr = factory.getRequest()
		.getParameter(PAGE_PARAMETER);
	if (listNumberStr != null) {
	    if (!listNumberStr.equals("")) {
		listNumber = Integer.parseInt(factory.getRequest()
			.getParameter(PAGE_PARAMETER));
	    }
	}
	return listNumber;
    }

    /**
     * {@inheritDoc}
     */
    public void setPageCount(int pageCount) {
	factory.getRequest().setAttribute(PAGE_COUNT_ATTRIBUTE, pageCount);
    }

    /**
     * {@inheritDoc}
     */
    public String getSortField() {
	return factory.getRequest().getParameter(SORT_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    public String getSortDirection() {
	return factory.getRequest().getParameter(DIRECTION_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    public void setBidsHistory(List<ItemDetails> bidDetailList) {
	factory.getRequest().setAttribute(BIDS_DETAIL_LIST_ATTRIBUTE,
		bidDetailList);
    }

    public void setErrorMessage(String message) {
	factory.getRequest().setAttribute(ERROR_MESSAGE_ATTRIBUTE, message);

    }

}
