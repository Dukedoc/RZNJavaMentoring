/**
 * 
 */
package marketplace.model.email;

import marketplace.model.to.ItemDetails;

/**
 * E-mail factory.
 * 
 * @author Roman_Ten
 * 
 */
public final class EmailFactory {

    // private constructor for class-utility.
    private EmailFactory() {

    }

    /**
     * Create e-mail.
     * 
     * @param type
     *            type of e-mail.
     * @param itemDetails
     *            the item details.
     * @return E-mail.
     */
    public static Email createEmail(EmailType type, ItemDetails itemDetails) {
	switch (type) {
	case ITEM_COMPLETE:
	    return new ItemCompleteEmail(itemDetails);
	case TIME_LEFT:
	    return new TimeLeftEmail(itemDetails);
	case ITEM_BOUGHT:
	    return new ItemBoughtEmail(itemDetails);
	default:
	    return null;
	}

    }

}
