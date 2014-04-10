/**
 * 
 */
package marketplace.controller.commands;

import java.util.List;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.User;

/**
 * Command for show black list.
 * 
 * @author Roman_Ten
 */
public class BlackListCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the helper factory.
     */
    public BlackListCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	User user = getHelperFactory().getUserHelper().getUserFromSession();
	if (user != null) {
	    UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		    .getUserDAO();
	    List<User> userBlackList = userDAO.getBlackList(user);
	    getHelperFactory().getUserHelper().setBlackList(userBlackList);
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.TRUE);
	} else {
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.NOT_AUTORIZATED);
	}

    }

}
