/**
 * 
 */
package marketplace.controller.commands;

import java.util.List;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.to.Category;
import marketplace.model.to.Item;

/**
 * Command for edit item.
 * 
 * @author Roman_Ten
 * 
 */
public class EditItemCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public EditItemCommand(HelperFactory helperFactory) {
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
	Item item = itemDAO.findItemByUID(itemID);
	List<Category> categoriesList = itemDAO.getCategoriesList();
	getHelperFactory().getItemHelper().setCategoriesList(categoriesList);
	getHelperFactory().getItemHelper().setItem(item);
	getHelperFactory().getCommandHelper().setCommandResult(
		CommandResultEnum.TRUE);

    }

}
