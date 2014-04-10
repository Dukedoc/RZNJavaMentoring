package marketplace.controller.spring.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.to.Item;
import marketplace.model.to.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * Controller for Edit Item.
 * 
 * @author Roman_Ten
 * 
 */
public class EditItemController extends SimpleFormController {

    private static final String USER_ATTRIBUTE = "user";
    private static final String CATEGORIES_LIST_ATTRIBUTE = "categoriesList";
    private User user;
    private HelperFactory helperFactory;
    private DAOFactory daoFactory;

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
    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
	Map<String, Object> referenceData = new HashMap<String, Object>();
	ItemDAO itemDAO = daoFactory.getItemDAO();
	referenceData.put(CATEGORIES_LIST_ATTRIBUTE, itemDAO
		.getCategoriesList());
	referenceData.put(USER_ATTRIBUTE, user);
	return referenceData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request)
	    throws Exception {
	helperFactory = HelperFactory.getHelperFactory(HelperFactory.WEB,
		request);
	int itemID = helperFactory.getItemHelper().getItemID();
	ItemDAO itemDAO = daoFactory.getItemDAO();
	Item editItem = itemDAO.findItemByUID(itemID);
	return editItem;
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
	Item item = (Item) command;
	ItemDAO itemDAO = daoFactory.getItemDAO();
	itemDAO.editItem(item.getItemID(), item);
	return new ModelAndView(getSuccessView());
    }

}
