/**
 * 
 */
package marketplace.controller.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.enumerations.UserResultValidation;
import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.User;

/**
 * Add User Command.
 * 
 * @author Roman_Ten
 * 
 */
public class AddUserCommand extends AbstractCommand {

    private static final String PASSWORD_REGEX = "^[\\w\\.!@#$%^&]{6,}$";
    private static final String LOGIN_REGEX = "^\\w+$";
    private static final String PHONE_REGEX = "^\\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
    // error code for exist user.
    private static final int EXIST_USER = -2;
    // SQL exception message.
    private static final String LOGIN_EXIST_MESSAGE = "This login name exist";
    private String fullName;
    private String billingAddress;
    private String phone;
    private String email;
    private String login;
    private String password;
    private String rePassword;

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the helper factory.
     */
    public AddUserCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	fullName = getHelperFactory().getUserHelper().getFullName();
	billingAddress = getHelperFactory().getUserHelper().getBillingAddress();
	phone = getHelperFactory().getUserHelper().getPhone();
	email = getHelperFactory().getUserHelper().getEmail();
	login = getHelperFactory().getUserHelper().getLogin();
	password = getHelperFactory().getUserHelper().getPassword();
	rePassword = getHelperFactory().getUserHelper().getRePassword();
	List<UserResultValidation> resultValidationList = validate();
	User user = new User();
	user.setFullName(fullName);
	user.setBillingAddress(billingAddress);
	user.setPhone(phone);
	user.setLogin(login);
	user.setEmail(email);
	user.setPassword(password);
	if (formValid(resultValidationList)) {
	    UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		    .getUserDAO();
	    int userID;
	    userID = userDAO.addUser(user);
	    if (userID > 0) {
		getHelperFactory().getUserHelper().setUser(user);
		getHelperFactory().getCommandHelper().setCommandResult(
			CommandResultEnum.TRUE);
	    } else if (userID == EXIST_USER) {
		getHelperFactory().getUserHelper().setErrorMessage(
			LOGIN_EXIST_MESSAGE);
		getHelperFactory().getCommandHelper().setCommandResult(
			CommandResultEnum.LOGIN_EXIST);
	    } else {		
		getHelperFactory().getCommandHelper().setCommandResult(
			CommandResultEnum.ERROR_DB);
	    }
	} else {
	    StringBuilder messageError = new StringBuilder();
	    for (UserResultValidation result : resultValidationList) {
		messageError.append(result.getMessage());
	    }
	    getHelperFactory().getUserHelper().setUser(user);
	    getHelperFactory().getUserHelper().setErrorMessage(
		    messageError.toString());
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.FORM_NOT_VALID);
	}

    }

    private boolean formValid(List<UserResultValidation> resultValidationList) {
	return (resultValidationList.size() == 0);
    }

    private List<UserResultValidation> validate() {
	List<UserResultValidation> resultList = new ArrayList<UserResultValidation>();

	if (fullName.trim().equals("")) {
	    resultList.add(UserResultValidation.FULL_NAME);
	}
	if (billingAddress.trim().equals("")) {
	    resultList.add(UserResultValidation.ADDRESS);
	}
	if (!Pattern.matches(PHONE_REGEX, phone)) {
	    resultList.add(UserResultValidation.PHONE);
	}
	if (!Pattern.matches(EMAIL_REGEX, email)) {
	    resultList.add(UserResultValidation.EMAIL);
	}
	if (!Pattern.matches(LOGIN_REGEX, login)) {
	    resultList.add(UserResultValidation.LOGIN);
	}
	if (login.equals(fullName)) {
	    resultList.add(UserResultValidation.LOGIN_EQUALS_PASSWORD);
	}
	if (!password.equals(rePassword)) {
	    resultList.add(UserResultValidation.PASSWORDS_DO_NOT_MATCH);
	}
	if (!Pattern.matches(PASSWORD_REGEX, password)) {
	    resultList.add(UserResultValidation.PASSWORD_NOT_VALID);
	}
	return resultList;
    }

}
