/**
 * 
 */
package marketplace.controller.commands;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.User;

/**
 * Login Command.
 * 
 * @author Roman_Ten
 * 
 */
public class LoginCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public LoginCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	User user = null;
	String login = getHelperFactory().getUserHelper().getLogin();
	String password = getHelperFactory().getUserHelper().getPassword();
	
	
	UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getUserDAO();
	user = userDAO.getUserByLogin(login);
	if (user != null) {
	    String truePassword = user.getPassword();
	    if (password.equals(truePassword)) {
		getHelperFactory().getUserHelper().setUser(user);
		getHelperFactory().getCommandHelper().setCommandResult(
			CommandResultEnum.TRUE);
	    } else {
		getHelperFactory().getCommandHelper().setErrorMessage(
			CommandResultEnum.LOGIN_ERROR.getText());
		getHelperFactory().getCommandHelper().setCommandResult(
			CommandResultEnum.LOGIN_ERROR);
	    }
	} else {
	    getHelperFactory().getCommandHelper().setErrorMessage(
		    CommandResultEnum.LOGIN_ERROR.getText());
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.LOGIN_ERROR);
	}
    }

}
