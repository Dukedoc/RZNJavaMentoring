/**
 * 
 */
package marketplace.controller.commands;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.enumerations.ItemValidationFields;
import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.exception.ValidatorException;
import marketplace.model.to.Category;
import marketplace.model.to.Item;
import marketplace.model.to.ItemDetails;

/**
 * Command for item.
 * 
 * @author Roman_Ten
 * 
 */
public class SellItemCommand extends AbstractCommand {

    private static final double DELTA_NULL = 0.0001;
    private List<ItemValidationFields> notValidFieldsList = new ArrayList<ItemValidationFields>();
    private ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
	    .getItemDAO();

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public SellItemCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	Item item = getItemFromRequest();
	if (notValidFieldsList.size() == 0) {
	    checkValidFields(item);
	}
	if (notValidFieldsList.size() == 0) {
	    if (itemExists()) {
		editItem(item);
	    } else {
		addItem(item);
	    }
	} else {
	    showErrorMessage(item);
	}
    }

    private void checkValidFields(Item item) {
	if (item.getTitle().trim().equals("")) {
	    notValidFieldsList.add(ItemValidationFields.TITLE);
	}
	if (item.getStartPrice() < DELTA_NULL) {
	    notValidFieldsList.add(ItemValidationFields.START_PRICE);
	}
	if (item.isBuyItNow()) {
	    if (item.getBidIncrement() > DELTA_NULL) {
		notValidFieldsList.add(ItemValidationFields.BID_INCREMENT_EXISTS);
	    }
	} else {
	    if (item.getBidIncrement() < DELTA_NULL) {
		notValidFieldsList.add(ItemValidationFields.BID_INCREMENT_NOT_EXISTS);
	    }
	    if (item.getTimeLeft() < DELTA_NULL) {
		notValidFieldsList.add(ItemValidationFields.TIME_LEFT_NULL);
	    }
	}
    }

    private Item getItemFromRequest() {
	Item item = new Item();
	String title = getHelperFactory().getItemHelper().getTitle();
	String description = getHelperFactory().getItemHelper()
		.getDescription();
	float startPrice = 0;
	try {
	    startPrice = getHelperFactory().getItemHelper().getStartPrice();
	} catch (ValidatorException e) {
	    notValidFieldsList.add(e.getField());
	}
	float timeLeft = 0;
	try {
	    timeLeft = getHelperFactory().getItemHelper().getTimeLeft();
	} catch (ValidatorException e) {
	    notValidFieldsList.add(e.getField());
	}
	boolean buyItNow = getHelperFactory().getItemHelper().isBuyItNow();
	float bidIncrement = 0;
	try {
	    bidIncrement = getHelperFactory().getItemHelper().getBidIncrement();
	} catch (ValidatorException e) {
	    notValidFieldsList.add(e.getField());
	}
	int sellerID = getHelperFactory().getUserHelper().getUserFromSession()
		.getUserID();
	int categoryID = getHelperFactory().getItemHelper().getCategoryID();

	item.setTitle(title);
	item.setDescription(description);
	item.setStartPrice(startPrice);
	item.setTimeLeft(timeLeft);
	item.setBuyItNow(buyItNow);
	item.setBidIncrement(bidIncrement);
	Calendar cal = Calendar.getInstance();
	cal.setTimeInMillis(System.currentTimeMillis());
	item.setStartBiddingDate(cal);
	item.setSellerID(sellerID);
	item.setCategoryID(categoryID);

	return item;
    }

    private void addItem(Item item) {
	int itemID = itemDAO.addItem(item);
	if (itemID > 0) {
	    ItemDetails itemDetails = itemDAO.findItemDetailsByUID(itemID);
	    getHelperFactory().getCommandHelper().addTimerSchedule(itemDetails);
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.TRUE);
	} else {
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.ERROR_DB);
	}
    }

    private void editItem(Item item) {
	int itemID = getHelperFactory().getItemHelper().getItemID();
	Calendar stopDateBeforeUpdate = itemDAO.findItemDetailsByUID(itemID)
		.getStopDate();
	itemDAO.editItem(getHelperFactory().getItemHelper().getItemID(), item);
	ItemDetails itemDetails = itemDAO.findItemDetailsByUID(itemID);
	Calendar stopDateAfterUpdate = itemDetails.getStopDate();
	if (timeChange(stopDateBeforeUpdate, stopDateAfterUpdate)) {
	    getHelperFactory().getCommandHelper().changeTimerSchedule(
		    itemDetails);
	}
	getHelperFactory().getCommandHelper().setCommandResult(
		CommandResultEnum.TRUE);
    }

    private boolean timeChange(Calendar stopDateBeforeUpdate,
	    Calendar stopDateAfterUpdate) {
	return !stopDateBeforeUpdate.equals(stopDateAfterUpdate);
    }

    private boolean itemExists() {
	return getHelperFactory().getItemHelper().getItemID() > 0;
    }

    private void showErrorMessage(Item item) {
	StringBuilder errorMessage = new StringBuilder();
	for (ItemValidationFields field : notValidFieldsList) {
	    errorMessage.append(field.getMessage()).append(' ');
	}
	List<Category> categoriesList = itemDAO.getCategoriesList();
	getHelperFactory().getItemHelper().setCategoriesList(categoriesList);
	getHelperFactory().getItemHelper().setErrorMessage(
		errorMessage.toString());
	/* getHelperFactory().getItemHelper().setItem(item); */
	getHelperFactory().getCommandHelper().setCommandResult(
		CommandResultEnum.FORM_NOT_VALID);
    }

}
