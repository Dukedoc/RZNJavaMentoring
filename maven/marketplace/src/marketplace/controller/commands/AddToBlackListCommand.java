package marketplace.controller.commands;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;

/**
 * Command for add to the black list.
 * 
 * @author Roman_Ten
 */
public class AddToBlackListCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the helper factory.
     */
    public AddToBlackListCommand(final HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void execute() {
	if (getHelperFactory().getUserHelper().getUserFromSession() != null) {
	    int sellerID = getHelperFactory().getUserHelper()
		    .getUserFromSession().getUserID();
	    int bidderID = getHelperFactory().getUserHelper().getUserID();
	    UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		    .getUserDAO();
	    if (userDAO.addToBlackList(sellerID, bidderID) > 0) {
		getHelperFactory().getCommandHelper().setCommandResult(
			CommandResultEnum.BLACK_LIST_ADD_USER);
	    } else {
		getHelperFactory().getCommandHelper().setErrorMessage(
			CommandResultEnum.BLACK_LIST_ERROR_DUPLICATE.getText());
		getHelperFactory().getCommandHelper().setCommandResult(
			CommandResultEnum.BLACK_LIST_ERROR_DUPLICATE);
	    }

	} else {
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.NOT_AUTORIZATED);
	}
    }

}
