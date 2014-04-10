/**
 * 
 */
package marketplace.controller.spring.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.to.AdvancedSearchTO;
import marketplace.model.to.User;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * Controller for Advanced Search.
 * 
 * @author Roman_Ten
 * 
 */
public class AdvancedSearchController extends SimpleFormController {

    private static final String FALSE = "false";
    private static final String BIDDER_COUNT = "bidderCount";
    private static final String STOP_DATE = "stopDate";
    private static final String START_DATE = "startDate";
    private static final String TRUE = "true";
    private static final String BUY_IT_NOW = "buyItNow";
    private static final String MAX_PRICE = "maxPrice";
    private static final String MIN_PRICE = "minPrice";
    private static final String DESCRIPTION = "description";
    private static final String TITLE = "title";
    private static final String ITEM_ID = "itemID";
    private static final String GET = "GET";
    private static final String ITEM_LIST_ATTRIBUTE = "itemList";
    private static final String USER_ATTRIBUTE = "user";
    private User user;
    private Cookie[] cookies;

    /**
     * Set User.
     * 
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
	this.user = user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<String, Object> referenceData(HttpServletRequest request)
	    throws Exception {
	Map<String, Object> referenceData = new HashMap<String, Object>();
	referenceData.put(USER_ATTRIBUTE, user);
	return referenceData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request)
	    throws Exception {
	Object command = super.formBackingObject(request);
	AdvancedSearchTO advancedSearchTO = (AdvancedSearchTO) command;

	if (request.getMethod().equals(GET)) {
	    cookies = request.getCookies();
	    String itemID = getCookieValue(ITEM_ID);
	    if (notEmpty(itemID)) {
		advancedSearchTO.setItemID(Integer.parseInt(itemID));
	    }
	    advancedSearchTO.setTitle(getCookieValue(TITLE));
	    advancedSearchTO.setDescription(getCookieValue(DESCRIPTION));
	    if (notEmpty(getCookieValue(MIN_PRICE))) {
		advancedSearchTO.setMinPrice(Float
			.parseFloat(getCookieValue(MIN_PRICE)));
	    }
	    if (notEmpty(getCookieValue(MAX_PRICE))) {
		advancedSearchTO.setMaxPrice(Float
			.parseFloat(getCookieValue(MAX_PRICE)));
	    }
	    if (notEmpty(getCookieValue(BUY_IT_NOW))) {
		if (getCookieValue(BUY_IT_NOW).equals(TRUE)) {
		    advancedSearchTO.setBuyItNow(true);
		} else {
		    advancedSearchTO.setBuyItNow(false);
		}
	    }
	    advancedSearchTO.setStartDateStr(getCookieValue(START_DATE));
	    advancedSearchTO.setStartDateStr(getCookieValue(STOP_DATE));
	    if (notEmpty(getCookieValue(BIDDER_COUNT))) {
		advancedSearchTO.setBidderCount(Integer
			.parseInt(getCookieValue(BIDDER_COUNT)));
	    }

	    command = advancedSearchTO;
	}
	return command;
    }

    private boolean notEmpty(String itemID) {
	return (itemID != null) && (!itemID.equals(""));
    }

    private String getCookieValue(String cookieName) {
	String cookieValue = null;
	for (Cookie cookie : cookies) {
	    if (cookieName.equals(cookie.getName())) {
		cookieValue = cookie.getValue();
	    }
	}
	return cookieValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
	    HttpServletResponse response, Object command, BindException errors)
	    throws Exception {

	AdvancedSearchTO advancedSearch = (AdvancedSearchTO) command;

	List<Cookie> cookiesList = new ArrayList<Cookie>();
	String itemID = null;
	if (advancedSearch.getItemID() != null) {
	    itemID = advancedSearch.getItemID().toString();
	}
	cookiesList.add(new Cookie(ITEM_ID, itemID));
	String title = advancedSearch.getTitle();
	cookiesList.add(new Cookie(TITLE, title));

	String description = advancedSearch.getDescription();
	cookiesList.add(new Cookie(DESCRIPTION, description));

	String minPrice = null;
	if (advancedSearch.getMinPrice() != null) {
	    minPrice = advancedSearch.getMinPrice().toString();

	}
	cookiesList.add(new Cookie(MIN_PRICE, minPrice));
	String maxPrice = null;
	if (advancedSearch.getMaxPrice() != null) {
	    maxPrice = advancedSearch.getMaxPrice().toString();
	}
	cookiesList.add(new Cookie(MAX_PRICE, maxPrice));
	String showOnlyBuyItNow = advancedSearch.isBuyItNow() ? TRUE : FALSE;
	cookiesList.add(new Cookie(BUY_IT_NOW, showOnlyBuyItNow));

	String startDate = advancedSearch.getStartDateStr();
	cookiesList.add(new Cookie(START_DATE, startDate));

	String stopDate = advancedSearch.getStopDateStr();
	cookiesList.add(new Cookie(STOP_DATE, stopDate));

	String bidderCount = null;
	if (advancedSearch.getBidderCount() != null) {
	    bidderCount = advancedSearch.getBidderCount().toString();
	}
	cookiesList.add(new Cookie(BIDDER_COUNT, bidderCount));

	for (Cookie cookie : cookiesList) {
	    response.addCookie(cookie);
	}

	ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getItemDAO();
	ModelAndView modelAndView = new ModelAndView(getSuccessView());
	modelAndView.addObject(ITEM_LIST_ATTRIBUTE, itemDAO
		.advancedSearch(advancedSearch));
	return modelAndView;

    }
}
