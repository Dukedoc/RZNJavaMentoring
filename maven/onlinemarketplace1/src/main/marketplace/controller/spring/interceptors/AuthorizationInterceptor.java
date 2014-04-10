/**
 * 
 */
package marketplace.controller.spring.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.model.to.User;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Authorization Interceptor.
 * 
 * @author Roman_Ten
 * 
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static final String NOT_AUTHORIZED_VIEW = "notAuthorized.htm";
    private User user;
    private List<String> authorizationUrlList;

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
     * Set URL List.
     * 
     * @param authorizationUrlList
     *            the authorizationUrlList to set
     */
    public void setAuthorizationUrlList(List<String> authorizationList) {
	this.authorizationUrlList = authorizationList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
	    HttpServletResponse response, Object handler) throws Exception {
	boolean result = true;
	if (isGuest()) {
	    for (String url : authorizationUrlList) {
		if (request.getRequestURI().contains(url)) {
		    response.sendRedirect(NOT_AUTHORIZED_VIEW);
		    result = false;
		}
	    }
	}
	return result;
    }

    private boolean isGuest() {
	return user == null || user.getUserID() == 0;
    }
}
