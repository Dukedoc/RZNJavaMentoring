/**
 * 
 */
package marketplace.model.email;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import marketplace.model.to.ItemDetails;

/**
 * Create and send e-mail when time left end.
 * 
 * @author Roman_Ten
 * 
 */
public class TimeLeftEmail extends AbstractEmail {

    private static final String HAS_EXPIRED = " has expired.";
    private static final String THE_TIME_OF_SALE_OF_THE = "!\n\nThe time of sale of the ";
    private static final String MESSAGE_SUBJECT = "The time of sale of the item has expired";
    private static final String MESSAGE_TEXT_DEAR = "Dear, ";
    private String sellerName;
    private String sellerEmail;
    private String itemTitle;

    /**
     * Constructor.
     * 
     * @param itemDetails
     *            the item details.
     */
    public TimeLeftEmail(ItemDetails itemDetails) {
	super(itemDetails);
	this.sellerName = itemDetails.getSeller();
	this.sellerEmail = itemDetails.getSellerEmail();
	this.itemTitle = itemDetails.getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send() {
	Message message = new MimeMessage(session);
	try {
	    message.setFrom(new InternetAddress(FROM));
	    InternetAddress[] address = { new InternetAddress(sellerEmail) };
	    message.setRecipients(Message.RecipientType.TO, address);
	    message.setSubject(MESSAGE_SUBJECT);
	    message.setSentDate(new Date());
	    message.setText(MESSAGE_TEXT_DEAR + sellerName
		    + THE_TIME_OF_SALE_OF_THE + itemTitle + HAS_EXPIRED
		    + MESSAGE_TEXT_SUBSCRIPT);
	    Transport.send(message);
	} catch (MessagingException e) {
	    e.printStackTrace();
	}
    }

}
