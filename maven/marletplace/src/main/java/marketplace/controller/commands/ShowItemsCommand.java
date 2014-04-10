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
 * Command for Show Items.
 * 
 * @author Roman_Ten
 * 
 */
public class ShowItemsCommand extends AbstractCommand {

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
    public ShowItemsCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {

	ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getItemDAO();
	List<ItemDetails> allItemList = null;

	// search
	if (getHelperFactory().getSearchHelper().getType() == null) {
	    // Show all items.
	    allItemList = itemDAO.getItemDetailsList();
	} else if (getHelperFactory().getSearchHelper().getType().equals("")) {
	    allItemList = itemDAO.getItemDetailsList();
	} else {	     
	    String type = getHelperFactory().getSearchHelper().getType();
	    String keywords = getHelperFactory().getSearchHelper()
		    .getKeywords();
	    if (type.equals(NAME)) {
		allItemList = itemDAO.findItemDetailsByName(keywords);
	    } else if (type.equals(DESCRIPTION)) {
		allItemList = itemDAO.findItemDetailsByDescription(keywords);
	    } else if (type.equals(UID2)) {
		int uid = Integer.parseInt(keywords);
		allItemList = new ArrayList<ItemDetails>();
		allItemList.add(itemDAO.findItemDetailsByUID(uid));
	    } else if (type.equals(CATEGORY)) {
		allItemList = itemDAO.findItemsByCategory(keywords);
	    }
	}
	
	// sorting
	Sorting sort = new Sorting(getHelperFactory(), allItemList);
	allItemList = sort.sort();

	// paging
	PaginalOutput paginalOutput = new PaginalOutput(getHelperFactory(),
		allItemList);
	allItemList = paginalOutput.paging();

	getHelperFactory().getItemHelper().setItemList(allItemList);
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

	getHelperFactory().getCommandHelper().setCommandResult(
		CommandResultEnum.TRUE);
    }
}
