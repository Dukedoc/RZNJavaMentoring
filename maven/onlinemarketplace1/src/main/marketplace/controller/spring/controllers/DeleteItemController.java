/**
 * 
 */
package marketplace.controller.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller for Delete Item.
 * 
 * @author Roman_Ten
 * 
 */
public class DeleteItemController extends AbstractController {

    private String nextView;
    private DAOFactory daoFactory;

    /**
     * Set Next View.
     * 
     * @param nextView
     *            the nextView to set
     */
    public void setNextView(String nextView) {
	this.nextView = nextView;
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
	HelperFactory helperFactory = HelperFactory.getHelperFactory(
		HelperFactory.WEB, request);
	int itemID = helperFactory.getItemHelper().getItemID();
	ItemDAO itemDAO = daoFactory.getItemDAO();
	itemDAO.deleteItemByUID(itemID);
	return new ModelAndView(new RedirectView(nextView));
    }
}
