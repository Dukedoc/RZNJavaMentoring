/**
 * 
 */
package marketplace.controller.commands;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.controller.utils.BidError;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.BidDAO;
import marketplace.model.to.Bid;

/**
 * Command for bidding.
 * 
 * @author Roman_Ten
 * 
 */
public class BiddingCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the helper factory/
     */
    public BiddingCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	try {
	    int bidID = 0;
	    float bidValue = getHelperFactory().getBidHelper().getBidValue();
	    int itemID = getHelperFactory().getBidHelper().getItemID();
	    int bidderID = getHelperFactory().getUserHelper()
		    .getUserFromSession().getUserID();
	    Bid bid = new Bid();
	    bid.setBid(bidValue);
	    bid.setBidderID(bidderID);
	    bid.setItemID(itemID);
	    BidDAO bidDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		    .getBidDAO();
	    bidID = bidDAO.bidAdd(bid);

	    if (bidID >= 0) {
		getHelperFactory().getCommandHelper().setCommandResult(
			CommandResultEnum.TRUE);
	    } else {
		getHelperFactory().getCommandHelper().setCommandResult(
			BidError.parseToCommandResult(bidID));
	    }
	} catch (NumberFormatException e) {
	    getHelperFactory().getCommandHelper().setCommandResult(
		    CommandResultEnum.FORM_NOT_VALID);
	}

    }

}
