package marketplace.controller.helpers;

import java.util.List;

import marketplace.model.to.User;

/**
 * Interface for User Helper.
 * 
 * @author Roman_Ten
 * 
 */
public interface UserHelper {

    /**
     * Get login from parameter.
     * 
     * @return login.
     */
    String getLogin();

    /**
     * Set user TO to request attribute.
     * 
     * @param user
     *            user transfer object.
     */
    void setUser(User user);

    /**
     * Get User transfer object from attribute..
     * 
     * @return user transfer object.
     */
    User getUserFromSession();

    /**
     * Logout.
     */
    void logout();

    /**
     * Get Full Name from request parameters.
     * 
     * @return user full name.
     */
    String getFullName();

    /**
     * Get Billing address from request parameters.
     * 
     * @return user's billing address.
     */
    String getBillingAddress();

    /**
     * Get user's phone from request parameters.
     * 
     * @return user's phone.
     */
    String getPhone();

    /**
     * Get user's password from request parameters.
     * 
     * @return user's password.
     */
    String getPassword();

    /**
     * Set message for incorrect login or password.
     * 
     * @param incorrectLoginOrPassword
     *            message.
     */
    void setIncorrectLoginMsg(String incorrectLoginOrPassword);

    /**
     * Set Black List.
     * 
     * @param userBlackList
     *            the user's black list
     */
    void setBlackList(List<User> userBlackList);

    /**
     * Get User ID.
     * 
     * @return user ID.
     */
    int getUserID();

    /**
     * Get user's e-mail.
     * 
     * @return e-mail.
     */
    String getEmail();

    void setErrorMessage(String loginExistMessage);

    String getRePassword();
}
