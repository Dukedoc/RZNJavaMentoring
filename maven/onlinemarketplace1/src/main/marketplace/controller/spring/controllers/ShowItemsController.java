package marketplace.controller.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.controller.utils.BidError;
import marketplace.controller.utils.PaginalOutput;
import marketplace.controller.utils.Sorting;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.to.ItemDetails;
import marketplace.model.to.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Controller for Show Items.
 * 
 * @author Roman_Ten
 * 
 */
public class ShowItemsController extends AbstractController {
    private static final String ITEM_LIST_ATTRIBUTE = "itemList";
    private static final String USER_ATTRIBUTE = "user";
    private static final String SHOW_ITEMS_VIEW = "showItems";
    private static final String ERROR_MSG_PARAMETER = "errorMsg";
    private static final String EMPTY_STRING = "";
    private static final String CATEGORY = "category";
    private static final String UID2 = "uid";
    private static final String DESCRIPTION = "description";
    private static final String NAME = "name";

    private User user;
    private DAOFactory daoFactory;
    private HelperFactory helperFactory;

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
     * Set User.
     * 
     * @param user
     *            the User.
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
	ItemDAO itemDAO = daoFactory.getItemDAO();
	helperFactory = HelperFactory.getHelperFactory(HelperFactory.WEB,
		request);
	List<ItemDetails> allItemList = itemDAO.getItemDetailsList();
	if (helperFactory.getSearchHelper().getType() == null) {
	    // Show all items.
	    allItemList = itemDAO.getItemDetailsList();
	} else if (helperFactory.getSearchHelper().getType().equals(
		EMPTY_STRING)) {
	    allItemList = itemDAO.getItemDetailsList();
	} else {
	    String type = helperFactory.getSearchHelper().getType();
	    String keywords = helperFactory.getSearchHelper().getKeywords();
	    if (type.equals(NAME)) {
		allItemList = itemDAO.findItemDetailsByName(keywords);
	    } else if (type.equals(DESCRIPTION)) {
		allItemList = itemDAO.findItemDetailsByDescription(keywords);
	    } else if (type.equals(UID2)) {
		int uid = Integer.parseInt(keywords);
		allItemList = new ArrayList<ItemDetails>();
		allItemList.add(itemDAO.findItemDetailsByUID(uid));
	    } else if (type.equals(CATEGORY)) {
		allItemList = itemDAO.findItemsByCategory(keywords);
	    }
	}

	// sorting
	Sorting sort = new Sorting(helperFactory, allItemList);
	allItemList = sort.sort();

	// paging
	PaginalOutput paginalOutput = new PaginalOutput(helperFactory,
		allItemList);
	allItemList = paginalOutput.paging();

	String errorMessage = request.getParameter(ERROR_MSG_PARAMETER);
	ModelAndView modelAndView = new ModelAndView(SHOW_ITEMS_VIEW);
	modelAndView.addObject(USER_ATTRIBUTE, user);
	modelAndView.addObject(ITEM_LIST_ATTRIBUTE, allItemList);
	if (errorMessage != null && !errorMessage.equals(EMPTY_STRING)) {
	    int errorCode = Integer.parseInt(errorMessage);
	    CommandResultEnum errorResultEnum = BidError
		    .parseToCommandResult(errorCode);
	    modelAndView.addObject(ERROR_MSG_PARAMETER, errorResultEnum
		    .getText());
	}
	return modelAndView;
    }

}
