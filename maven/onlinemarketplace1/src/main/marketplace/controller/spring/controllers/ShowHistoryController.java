package marketplace.controller.spring.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.to.ItemDetails;
import marketplace.model.to.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Controller for show history.
 * 
 * @author Roman_Ten.
 * 
 */
public class ShowHistoryController extends AbstractController {
    private static final String HISTORY_LIST_ATTRIBUTE = "bidsDetailList";
    private static final String USER_ATTRIBUTE = "user";
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
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject(USER_ATTRIBUTE, user);
	HelperFactory helperFactory = HelperFactory.getHelperFactory(
		HelperFactory.WEB, request);
	int itemID = helperFactory.getItemHelper().getItemID();
	ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getItemDAO();
	List<ItemDetails> historyList = itemDAO.getBidsHistoryList(itemID);
	modelAndView.addObject(HISTORY_LIST_ATTRIBUTE, historyList);
	return modelAndView;
    }

}
