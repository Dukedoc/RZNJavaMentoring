package marketplace.controller.spring.controllers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.to.Item;
import marketplace.model.to.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * Controller for add item.
 * 
 * @author Roman_Ten
 * 
 */
public class AddItemController extends SimpleFormController {

    private static final String USER_ATTRIBUTE = "user";
    private static final String CATEGORIES_LIST_ATTRIBUTE = "categoriesList";
    private User user;

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
	ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getItemDAO();
	referenceData.put(CATEGORIES_LIST_ATTRIBUTE, itemDAO
		.getCategoriesList());
	referenceData.put(USER_ATTRIBUTE, user);
	return referenceData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
	Item item = (Item) command;
	ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getItemDAO();
	ModelAndView modelAndView = null;
	Calendar currentDate = Calendar.getInstance();
	currentDate.set(Calendar.SECOND, 0);
	currentDate.set(Calendar.MILLISECOND, 0);
	item.setStartBiddingDate(currentDate);
	item.setSellerID(user.getUserID());
	if (itemDAO.addItem(item) > 0) {
	    modelAndView = new ModelAndView(getSuccessView());
	} else {
	    modelAndView = new ModelAndView(getFormView());
	}
	return modelAndView;
    }

}
