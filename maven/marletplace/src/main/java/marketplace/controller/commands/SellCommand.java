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
import marketplace.model.to.User;

/**
 * Command for Sell.
 * 
 * @author Roman_Ten
 * 
 */
public class SellCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public SellCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	if (getHelperFactory().getUserHelper().getUserFromSession() instanceof User) {
	    ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		    .getItemDAO();
	    List<Category> categoriesList = itemDAO.getCategoriesList();
	    getHelperFactory().getItemHelper()
		    .setCategoriesList(categoriesList);
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.TRUE);
	} else {
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.NOT_AUTORIZATED);
	}
    }

}
