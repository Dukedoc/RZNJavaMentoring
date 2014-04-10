/**
 * 
 */
package marketplace.controller.helpers.web;

import java.util.List;

import marketplace.controller.helpers.UserHelper;
import marketplace.model.to.User;

/**
 * Login Helper for web implementation.
 * 
 * @author Roman_Ten
 * 
 */
public class WebUserHelper implements UserHelper {

    private static final String RE_PASSWORD_PARAMETER = "rePassword";
    private static final String USER_ID_PARAMETER = "userID";
    private static final String USERS_LIST_ATTRIBUTE = "usersList";
    private static final String ERROR_MESSAGE_ATRIBUTE = "errorMsg";
    private static final String PHONE_PARAMETER = "phone";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String EMAIL_PARAMETER = "email";
    private static final String FULL_NAME_PARAMETER = "fullName";
    private static final String ADDRESS_PARAMETER = "address";
    private static final String USER_ATTRIBUTE = "user";
    private static final String LOGIN_PARAMETER = "login";
    private WebHelperFactory webHelperFactory;

    /**
     * Constructor.
     * 
     * @param webHelperFactory
     *            the helper factory for web application.
     */
    public WebUserHelper(WebHelperFactory webHelperFactory) {
	this.webHelperFactory = webHelperFactory;
    }

    /**
     * {@inheritDoc}
     */
    public String getLogin() {
	return webHelperFactory.getRequest().getParameter(LOGIN_PARAMETER);

    }

    /**
     * {@inheritDoc}
     */
    public void setUser(User user) {
	webHelperFactory.getRequest().getSession().setAttribute(USER_ATTRIBUTE,
		user);
    }

    /**
     * {@inheritDoc}
     */
    public User getUserFromSession() {
	if (webHelperFactory.getRequest().getSession().getAttribute(
		USER_ATTRIBUTE) instanceof User) {
	    return (User) webHelperFactory.getRequest().getSession()
		    .getAttribute(USER_ATTRIBUTE);
	} else {
	    return null;
	}
    }

    /**
     * {@inheritDoc}
     */
    public void logout() {
	webHelperFactory.getRequest().getSession().removeAttribute(
		USER_ATTRIBUTE);

    }

    /**
     * {@inheritDoc}
     */
    public String getBillingAddress() {
	return webHelperFactory.getRequest().getParameter(ADDRESS_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    public String getFullName() {
	return webHelperFactory.getRequest().getParameter(FULL_NAME_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    public String getEmail() {
	return webHelperFactory.getRequest().getParameter(EMAIL_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    public String getPassword() {
	return webHelperFactory.getRequest().getParameter(PASSWORD_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    public String getPhone() {
	return webHelperFactory.getRequest().getParameter(PHONE_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    public void setIncorrectLoginMsg(String incorrectLoginOrPassword) {
	webHelperFactory.getRequest().setAttribute(ERROR_MESSAGE_ATRIBUTE,
		incorrectLoginOrPassword);
    }

    /**
     * {@inheritDoc}
     */
    public void setBlackList(List<User> userBlackList) {
	webHelperFactory.getRequest().setAttribute(USERS_LIST_ATTRIBUTE,
		userBlackList);
    }

    /**
     * {@inheritDoc}
     */
    public int getUserID() {
	return Integer.parseInt(webHelperFactory.getRequest().getParameter(
		USER_ID_PARAMETER));
    }

    /**
     * {@inheritDoc}
     */
    public void setErrorMessage(String message) {
	webHelperFactory.getRequest().setAttribute(ERROR_MESSAGE_ATRIBUTE,
		message);

    }

    /**
     * {@inheritDoc}
     */
    public String getRePassword() {
	return webHelperFactory.getRequest()
		.getParameter(RE_PASSWORD_PARAMETER);
    }

}
