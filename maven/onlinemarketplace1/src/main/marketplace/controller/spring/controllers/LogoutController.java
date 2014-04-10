/**
 * 
 */
package marketplace.controller.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.model.to.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Controller for logout.
 * 
 * @author Roman_Ten
 * 
 */
public class LogoutController extends AbstractController {

    private User user;

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
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
	    HttpServletResponse arg1) throws Exception {
	User nullUser = new User();
	user.feel(nullUser);
	return new ModelAndView("login", "user", user);
    }

}
