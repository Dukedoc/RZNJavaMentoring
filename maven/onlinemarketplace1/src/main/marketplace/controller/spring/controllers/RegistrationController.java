/**
 * 
 */
package marketplace.controller.spring.controllers;

import javax.servlet.http.HttpServletRequest;

import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * Controller for Registration.
 * 
 * @author Roman_Ten
 * 
 */
public class RegistrationController extends SimpleFormController {

    private User user;

    /**
     * Set User.
     * 
     * @param user
     *            the user.
     */
    public void setUser(User user) {
	this.user = user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
	ModelAndView modelAndView = null;
	User formUser = (User) command;
	UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getUserDAO();
	int addResult = userDAO.addUser(formUser);
	if (addResult > 0) {
	    user.feel(formUser);
	    modelAndView = new ModelAndView(getSuccessView());
	} else {
	    modelAndView = new ModelAndView(getFormView());
	}
	return modelAndView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request)
	    throws Exception {
	return new User();
    }

}
