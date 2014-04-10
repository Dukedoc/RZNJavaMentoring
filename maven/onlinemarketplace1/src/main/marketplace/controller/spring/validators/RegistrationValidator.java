/**
 * 
 */
package marketplace.controller.spring.validators;

import java.util.regex.Pattern;

import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.User;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Registration validate.
 * 
 * @author Roman_Ten
 * 
 */
public class RegistrationValidator implements Validator {

    private static final String LOGIN_EXISTS_MESSAGE = "The login exists. Please enter another login";
    private static final String LOGIN_EXISTS_ERROR_CODE = "required.loginExists";
    private static final String PASSWORD_NOT_VALID_MESSAGE = "Password must be longer than 6 characters.";
    private static final String PASSWORD_NOT_VALID_ERROR_CODE = "required.passwordNotValid";
    private static final String PASSWORDS_NOT_MATCH_MESSAGE = "You password confirmation does not equal to your Password.";
    private static final String PASSWORDS_NOT_MATCH_ERROR_CODE = "required.passwordsNotMatch";
    private static final String PASSWORD_FIELD = "password";
    private static final String LOGIN_EQUALS_PASSWORD_MESSAGE = "Your Login must be different from Full Name.";
    private static final String LOGIN_EQUALS_PASSWORD_ERROR_CODE = "required.loginEqualsPassword";
    private static final String EMAIL_MESSAGE = "Email is not correct. Example: example@example.com.";
    private static final String EMAIL_ERROR_CODE = "required.email";
    private static final String EMAIL_FIELD = "email";
    private static final String PHONE_MESSAGE = "Phone must be in (123) 456-78-90 format.";
    private static final String PHONE_ERROR_ERROR_CODE = "required.phone";
    private static final String PHONE_FIELD = "phone";
    private static final String LOGIN_IS_EMPTY_MESSAGE = "Your Login is empty.";
    private static final String LOGIN_EMPTY_ERROR_CODE = "required.loginEmpty";
    private static final String LOGIN_FIELD = "login";
    private static final String YOUR_BILLING_ADDRESS_IS_EMPTY_MESSAGE = "Your Billing Address is empty.";
    private static final String REQUIRED_BILLING_ADDRESS_EMPTY_ERROR_CODE = "required.billingAddressEmpty";
    private static final String BILLING_ADDRESS_FIELD = "billingAddress";
    private static final String FULL_NAME_IS_EMPTY_MESSAGE = "Full name is empty.";
    private static final String FULL_NAME_EMPTY_ERROR_CODE = "required.fullNameEmpty";
    private static final String FULL_NAME_FIELD = "fullName";
    private static final String PASSWORD_REGEX = "^[\\w\\.!@#$%^&]{6,}$";
    private static final String PHONE_REGEX = "^\\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";

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
	User user = (User) command;
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, FULL_NAME_FIELD,
		FULL_NAME_EMPTY_ERROR_CODE, FULL_NAME_IS_EMPTY_MESSAGE);
	ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		BILLING_ADDRESS_FIELD,
		REQUIRED_BILLING_ADDRESS_EMPTY_ERROR_CODE,
		YOUR_BILLING_ADDRESS_IS_EMPTY_MESSAGE);
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, LOGIN_FIELD,
		LOGIN_EMPTY_ERROR_CODE, LOGIN_IS_EMPTY_MESSAGE);

	if (!Pattern.matches(PHONE_REGEX, user.getPhone())) {
	    errors.rejectValue(PHONE_FIELD, PHONE_ERROR_ERROR_CODE,
		    PHONE_MESSAGE);
	}
	if (!Pattern.matches(EMAIL_REGEX, user.getEmail())) {
	    errors.rejectValue(EMAIL_FIELD, EMAIL_ERROR_CODE, EMAIL_MESSAGE);
	}

	if (user.getLogin().equals(user.getFullName())) {
	    errors.rejectValue(LOGIN_FIELD, LOGIN_EQUALS_PASSWORD_ERROR_CODE,
		    LOGIN_EQUALS_PASSWORD_MESSAGE);
	}
	if (!user.getPassword().equals(user.getRePassword())) {
	    errors.rejectValue(PASSWORD_FIELD, PASSWORDS_NOT_MATCH_ERROR_CODE,
		    PASSWORDS_NOT_MATCH_MESSAGE);
	}
	if (!Pattern.matches(PASSWORD_REGEX, user.getPassword())) {
	    errors.rejectValue(PASSWORD_FIELD, PASSWORD_NOT_VALID_ERROR_CODE,
		    PASSWORD_NOT_VALID_MESSAGE);
	}

	UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getUserDAO();
	if (userDAO.getUserByLogin(user.getLogin()) != null) {
	    errors.rejectValue(LOGIN_FIELD, LOGIN_EXISTS_ERROR_CODE,
		    LOGIN_EXISTS_MESSAGE);
	}
    }

}
