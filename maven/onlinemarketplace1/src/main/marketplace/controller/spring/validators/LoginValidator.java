/**
 * 
 */
package marketplace.controller.spring.validators;

import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.User;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Login validator.
 * 
 * @author Roman_Ten
 * 
 */
public class LoginValidator implements Validator {

    private static final String INCORRECT_LOGIN_OR_PASSWORD_MESSAGE = "Incorrect Login or Password";
    private static final String LOGIN_INCORRECT_ERROR_CODE = "login.incorrectLogin";
    private static final String LOGIN_FIELD = "login";

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean supports(Class theClass) {
	return theClass.equals(User.class);
    }

    /**
     * {@inheritDoc}
     */
    public void validate(Object command, Errors errors) {
	User checkUser = (User) command;

	UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getUserDAO();
	User user = userDAO.getUserByLogin(checkUser.getLogin());
	if (user == null || !user.getPassword().equals(checkUser.getPassword())) {
	    errors.rejectValue(LOGIN_FIELD, LOGIN_INCORRECT_ERROR_CODE,
		    INCORRECT_LOGIN_OR_PASSWORD_MESSAGE);
	}
    }
}
