/**
 * 
 */
package marketplace.controller.commands;

import java.util.List;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.controller.utils.PaginalOutput;
import marketplace.controller.utils.Sorting;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.ItemDetails;
import marketplace.model.to.User;

/**
 * Command for Show My Items.
 * 
 * @author Roman_Ten
 * 
 */
public class ShowMyItemsCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public ShowMyItemsCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	List<ItemDetails> list = null;
	UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getUserDAO();
	User user = getHelperFactory().getUserHelper().getUserFromSession();
	if (user != null) {
	    list = userDAO.getUserItems(user);
	    Sorting sorting = new Sorting(getHelperFactory(), list);
	    list = sorting.sort();
	    PaginalOutput paginalOutput = new PaginalOutput(getHelperFactory(),
		    list);
	    list = paginalOutput.paging();
	    getHelperFactory().getItemHelper().setItemList(list);

	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.TRUE);
	}
    }

}
