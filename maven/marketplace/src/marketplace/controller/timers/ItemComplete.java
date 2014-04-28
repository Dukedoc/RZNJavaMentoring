/**
 * 
 */
package marketplace.controller.timers;

import java.util.TimerTask;

import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.email.Email;
import marketplace.model.email.EmailFactory;
import marketplace.model.email.EmailType;
import marketplace.model.to.ItemDetails;

/**
 * Timer task for item complete.
 * 
 * @author Roman_Ten
 * 
 */
public class ItemComplete extends TimerTask {

    // private ItemDetails itemDetails;
    private int itemID;

    /**
     * Constructor.
     * 
     * @param itemID
     *            the item ID.
     */
    public ItemComplete(int itemID) {
	this.itemID = itemID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
	ItemDetails itemDetails = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getItemDAO().findItemDetailsByUID(itemID);
	Email email = EmailFactory.createEmail(EmailType.ITEM_COMPLETE,
		itemDetails);
	email.send();
	ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getItemDAO();
	itemDAO.setSendEmail(itemDetails.getItemID());
    }

}
