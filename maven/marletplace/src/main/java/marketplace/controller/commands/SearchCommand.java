/**
 * 
 */
package marketplace.controller.commands;

import java.util.ArrayList;
import java.util.List;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.controller.utils.PaginalOutput;
import marketplace.controller.utils.Sorting;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.to.ItemDetails;

/**
 * Command for search.
 * 
 * @author Roman_Ten
 * 
 */
public class SearchCommand extends AbstractCommand {

    private static final String CATEGORY = "category";
    private static final String UID2 = "uid";
    private static final String DESCRIPTION = "description";
    private static final String NAME = "name";

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public SearchCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getItemDAO();
	List<ItemDetails> itemList = null;
	String type = getHelperFactory().getSearchHelper().getType();
	String keywords = getHelperFactory().getSearchHelper().getKeywords();
	if (type != null) {
	    if (type.equals(NAME)) {
		itemList = itemDAO.findItemDetailsByName(keywords);
	    } else if (type.equals(DESCRIPTION)) {
		itemList = itemDAO.findItemDetailsByDescription(keywords);
	    } else if (type.equals(UID2)) {
		int uid = Integer.parseInt(keywords);
		itemList = new ArrayList<ItemDetails>();
		itemList.add(itemDAO.findItemDetailsByUID(uid));
	    } else if (type.equals(CATEGORY)) {
		itemList = itemDAO.findItemsByCategory(keywords);
	    }
	}
	if (itemList != null) {
	    Sorting sort = new Sorting(getHelperFactory(), itemList);
	    itemList = sort.sort();
	    // paging
	    PaginalOutput paginalOutput = new PaginalOutput(getHelperFactory(),
		    itemList);
	    itemList = paginalOutput.paging();
	}

	String errorStr = getHelperFactory().getCommandHelper().getError();

	if (errorStr != null) {
	    if (!errorStr.equals("")) {
		CommandResultEnum error = null;
		try {
		    error = CommandResultEnum.valueOf(errorStr);
		} catch (IllegalArgumentException e) {
		    error = CommandResultEnum.UNKNOWN_ERROR;
		} finally {
		    getHelperFactory().getBidHelper().setErrorMsg(
			    error.getText());
		}
	    }

	}
	getHelperFactory().getItemHelper().setItemList(itemList);
	getHelperFactory().getCommandHelper().setCommandResult(
		CommandResultEnum.TRUE);

    }
}
