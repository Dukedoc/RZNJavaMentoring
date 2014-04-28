/**
 * 
 */
package marketplace.controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.User;

/**
 * Authentication filter.
 * 
 * @author Roman_Ten
 * 
 */
public class AuthenticationFilter implements Filter {
    private UserDAO userDAO;

    /**
     * {@inheritDoc}
     */
    public void destroy() {
	userDAO = null;
    }

    /**
     * {@inheritDoc}
     */
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	HelperFactory helperFactory = HelperFactory.getHelperFactory(
		HelperFactory.WEB, (HttpServletRequest) request);
	User validatedUser = helperFactory.getUserHelper().getUserFromSession();

	if (validatedUser != null) {
	    String login = validatedUser.getLogin();
	    String password = validatedUser.getPassword();
	    User trueUser = userDAO.getUserByLogin(login);
	    if (trueUser != null) {
		String truePassword = trueUser.getPassword();
		if (!password.equals(truePassword)) {
		    helperFactory.getUserHelper().setUser(null);
		}
	    } else {
		helperFactory.getUserHelper().setUser(null);
	    }

	}
	chain.doFilter(request, response);
    }

    /**
     * {@inheritDoc}
     */
    public void init(FilterConfig arg0) throws ServletException {
	userDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUserDAO();
    }

}
