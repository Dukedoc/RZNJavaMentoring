package marketplace.model.email;

import marketplace.model.to.ItemDetails;

/**
 * Create and send e-mail when item complete.
 * 
 * @author Roman_Ten
 * 
 */
public class ItemCompleteEmail implements Email {

    private ItemDetails itemDetails;

    /**
     * Constructor.
     * 
     * @param itemDetails
     *            the item details.
     */
    public ItemCompleteEmail(ItemDetails itemDetails) {
	this.itemDetails = itemDetails;
    }

    /**
     * {@inheritDoc}
     */
    public void send() {
	if (!itemDetails.isSendEmail()) {
	    if (itemDetails.getBidder() == null) {
		Email email = EmailFactory.createEmail(EmailType.TIME_LEFT,
			itemDetails);
		email.send();
	    } else if (itemDetails.getBidder().equals("")) {
		Email email = EmailFactory.createEmail(EmailType.TIME_LEFT,
			itemDetails);
		email.send();
	    } else {
		Email email = EmailFactory.createEmail(EmailType.ITEM_BOUGHT,
			itemDetails);
		email.send();
	    }
	}

    }

}
