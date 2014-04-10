/**
 * 
 */
package marketplace.controller.spring.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.User;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Interceptors for authentifacation.
 * 
 * @author Roman_Ten
 * 
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

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
    public boolean preHandle(HttpServletRequest request,
	    HttpServletResponse response, Object handler) throws Exception {
	if ((user != null) && (user.getUserID() != 0)) {
	    UserDAO userDAO = daoFactory.getUserDAO();
	    User trueUser = userDAO.getUserByLogin(user.getLogin());
	    if (trueUser == null
		    || !trueUser.getPassword().equals(user.getPassword())) {
		User nullUser = new User();
		user.feel(nullUser);
	    }
	}
	return true;
    }

}
