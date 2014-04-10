package marketplace.controller.commands;

import java.util.List;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.to.ItemDetails;

/**
 * Command for Show History.
 * 
 * @author Roman_Ten
 * 
 */
public class ShowHistoryCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public ShowHistoryCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {

	ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getItemDAO();
	int itemID = getHelperFactory().getItemHelper().getItemID();
	List<ItemDetails> bidHistoryList = itemDAO.getBidsHistoryList(itemID);
	getHelperFactory().getItemHelper().setBidsHistory(bidHistoryList);
	getHelperFactory().getCommandHelper().setCommandResult(
		CommandResultEnum.TRUE);
	if (getHelperFactory().getCommandHelper().getError() != null) {
	    CommandResultEnum error = CommandResultEnum
		    .valueOf(getHelperFactory().getCommandHelper().getError());
	    getHelperFactory().getCommandHelper().setErrorMessage(
		    error.getText());
	}
    }

}
