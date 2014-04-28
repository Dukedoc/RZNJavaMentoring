package marketplace.controller.commands;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.controller.listeners.FrontControllerListener;
import marketplace.controller.utils.BidError;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.BidDAO;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.email.Email;
import marketplace.model.email.EmailFactory;
import marketplace.model.email.EmailType;
import marketplace.model.to.Bid;
import marketplace.model.to.ItemDetails;

/**
 * Buy it now command.
 * 
 * @author Roman_Ten
 */
public class BuyItNowCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public BuyItNowCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	if (getHelperFactory().getUserHelper().getUserFromSession() != null) {
	    int buyerID = getHelperFactory().getUserHelper()
		    .getUserFromSession().getUserID();
	    DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
	    int itemID = getHelperFactory().getBidHelper().getItemID();
	    float price = getHelperFactory().getBidHelper().getBidValue();
	    Bid bid = new Bid();
	    bid.setItemID(itemID);
	    bid.setBidderID(buyerID);
	    bid.setBid(price);
	    BidDAO bidDAO = daoFactory.getBidDAO();
	    int bidID = bidDAO.buyItNow(bid);
	    if (bidID >= 0) {
		ItemDAO itemDAO = daoFactory.getItemDAO();
		ItemDetails itemDetails = itemDAO.findItemDetailsByUID(itemID);
		Email email = EmailFactory.createEmail(EmailType.ITEM_BOUGHT,
			itemDetails);
		email.send();
		FrontControllerListener.removeSchedule(itemID);
		itemDAO.setSendEmail(itemID);
		getHelperFactory().getCommandHelper().setCommandResult(
			CommandResultEnum.TRUE);
	    } else {
		getHelperFactory().getCommandHelper().setCommandResult(
			BidError.parseToCommandResult(bidID));
	    }
	} else {
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.NOT_AUTORIZATED);
	}

    }

}
