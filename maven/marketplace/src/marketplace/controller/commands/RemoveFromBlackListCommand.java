/**
 * 
 */
package marketplace.controller.commands;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;

/**
 * Remove from black list command.
 * 
 * @author Roman_Ten
 * 
 */
public class RemoveFromBlackListCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public RemoveFromBlackListCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	if (getHelperFactory().getUserHelper().getUserFromSession() != null) {
	    int bidderID = getHelperFactory().getUserHelper().getUserID();
	    int sellerID = getHelperFactory().getUserHelper()
		    .getUserFromSession().getUserID();

	    UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		    .getUserDAO();
	    userDAO.removeFromBlackList(sellerID, bidderID);
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.TRUE);
	} else {
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.NOT_AUTORIZATED);
	}
    }

}
