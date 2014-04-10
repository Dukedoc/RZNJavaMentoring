package marketplace.controller.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.controller.helpers.HelperFactory;
import marketplace.controller.utils.PaginalOutput;
import marketplace.controller.utils.Sorting;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.ItemDetails;
import marketplace.model.to.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Controller for show my items.
 * 
 * @author Roman_Ten
 * 
 */
public class ShowMyItemsController extends AbstractController {

    private static final String ITEM_LIST_ATTRIBUTE = "itemList";
    private static final String USER_ATTRIBUTE = "user";
    private User user;
    private DAOFactory daoFactory;

    /**
     * Set user.
     * 
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
	this.user = user;
    }

    /**
     * Set DAOFactory.
     * 
     * @param daoFactory
     *            the daoFactory to set
     */
    public void setDaoFactory(DAOFactory daoFactory) {
	this.daoFactory = daoFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	List<ItemDetails> itemsList = new ArrayList<ItemDetails>();
	UserDAO userDAO = daoFactory.getUserDAO();
	itemsList = userDAO.getUserItems(user);
	HelperFactory helperFactory = HelperFactory.getHelperFactory(
		HelperFactory.WEB, request);
	Sorting sorting = new Sorting(helperFactory, itemsList);
	itemsList = sorting.sort();
	PaginalOutput paginalOutput = new PaginalOutput(helperFactory,
		itemsList);
	itemsList = paginalOutput.paging();
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject(USER_ATTRIBUTE, user);
	modelAndView.addObject(ITEM_LIST_ATTRIBUTE, itemsList);
	return modelAndView;

    }
}
