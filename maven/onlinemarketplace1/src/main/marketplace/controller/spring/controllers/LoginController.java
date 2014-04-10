/**
 * 
 */
package marketplace.controller.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.User;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * Controller for login.
 * 
 * @author Roman_Ten
 * 
 */
public class LoginController extends SimpleFormController {

    private User user;

    /**
     * Set user.
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
    protected ModelAndView onSubmit(HttpServletRequest request,
	    HttpServletResponse response, Object command, BindException errors)
	    throws Exception {
	User userFromForm = (User) command;
	UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getUserDAO();
	userFromForm = userDAO.getUserByLogin(userFromForm.getLogin());
	user.feel(userFromForm);
	ModelAndView modelAndView = new ModelAndView(getSuccessView());
	return modelAndView;
    }

}
