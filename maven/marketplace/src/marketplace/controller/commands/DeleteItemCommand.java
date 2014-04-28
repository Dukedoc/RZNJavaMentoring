/**
 * 
 */
package marketplace.controller.commands;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;

/**
 * Delete Item Command.
 * 
 * @author Roman_Ten
 * 
 */
public class DeleteItemCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public DeleteItemCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	int itemID = getHelperFactory().getItemHelper().getItemID();
	ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getItemDAO();
	itemDAO.deleteItemByUID(itemID);
	getHelperFactory().getCommandHelper().setCommandResult(
		CommandResultEnum.TRUE);
    }

}
