/**
 * 
 */
package marketplace.controller.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Add to Black List Controller.
 * 
 * @author Roman_Ten
 * 
 */
public class AddToBlackListController extends AbstractController {
    private User user;
    private DAOFactory daoFactory;
    private String nextView;

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
     * Set nextView.
     * 
     * @param nextView
     *            the nextView to set
     */
    public void setNextView(String nextView) {
	this.nextView = nextView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	int bidderID = HelperFactory.getHelperFactory(HelperFactory.WEB,
		request).getUserHelper().getUserID();
	UserDAO userDAO = daoFactory.getUserDAO();
	ModelAndView modelAndView = new ModelAndView(new RedirectView(nextView));
	userDAO.addToBlackList(user.getUserID(), bidderID);
	return modelAndView;
    }

}
