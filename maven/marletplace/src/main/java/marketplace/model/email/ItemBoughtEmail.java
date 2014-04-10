/**
 * 
 */
package marketplace.model.email;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import marketplace.model.to.ItemDetails;

/**
 * Create and send e-mail when item bought.
 * 
 * @author Roman_Ten
 * 
 */
public class ItemBoughtEmail extends AbstractEmail implements Email {
    private static final String MESSAGE_SELLER_WAS_PURCHASED_BY = ") was purchased by ";
    private static final String MESSAGE_SELLER_YOU_ITEM = "! \n \n You item (";
    private static final String MESSAGE_TEXT_SUBSCRIPT = ".\n\nRegards\nOnlineMarketplace";
    private static final String MESSAGE_TEXT_FROM = " from ";
    private static final String MESSAGE_BIDDER_TEXT_YOUR_BOUGHT = "! \n \n You bought the ";
    private static final String MESSAGE_TEXT_DEAR = "Dear ";
    private static final String MESSAGE_SUBJECT_FOR_BIDDER = "OnlineMarketplace. Your bought item.";
    private static final String MESSAGE_SUBJECT_FOR_SIDDER = "OnlineMarketplace. Your sold item.";

    private String bidderName;
    private String bidderEmail;
    private String sellerName;
    private String sellerEmail;
    private String itemTitle;

    /**
     * Constructor for bought item e-mail.
     * 
     * @param itemDetails
     *            the Item Details.
     */
    public ItemBoughtEmail(ItemDetails itemDetails) {
	super(itemDetails);
	this.bidderName = itemDetails.getBidder();
	this.bidderEmail = itemDetails.getBidderEmail();
	this.sellerName = itemDetails.getSeller();
	this.sellerEmail = itemDetails.getSellerEmail();
	this.itemTitle = itemDetails.getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send() {
	sendMailToSeller();
	sendMailToBidder();

    }

    // Send mail to bidder.
    private void sendMailToBidder() {
	Message message = new MimeMessage(session);
	try {
	    message.setFrom(new InternetAddress(FROM));
	    InternetAddress[] address = { new InternetAddress(bidderEmail) };
	    message.setRecipients(Message.RecipientType.TO, address);
	    message.setSubject(MESSAGE_SUBJECT_FOR_BIDDER);
	    message.setSentDate(new Date());
	    message.setText(MESSAGE_TEXT_DEAR + bidderName
		    + MESSAGE_BIDDER_TEXT_YOUR_BOUGHT + itemTitle
		    + MESSAGE_TEXT_FROM + sellerName + MESSAGE_TEXT_SUBSCRIPT);
	    Transport.send(message);
	} catch (AddressException e) {
	    e.printStackTrace();
	} catch (MessagingException e) {
	    e.printStackTrace();
	}
    }

    // Send mail to seller.
    private void sendMailToSeller() {
	Message message = new MimeMessage(session);
	try {
	    message.setFrom(new InternetAddress(FROM));
	    InternetAddress[] address = { new InternetAddress(sellerEmail) };
	    message.setRecipients(Message.RecipientType.TO, address);
	    message.setSubject(MESSAGE_SUBJECT_FOR_SIDDER);
	    message.setSentDate(new Date());
	    message.setText(MESSAGE_TEXT_DEAR + sellerName
		    + MESSAGE_SELLER_YOU_ITEM + itemTitle
		    + MESSAGE_SELLER_WAS_PURCHASED_BY + bidderName
		    + MESSAGE_TEXT_SUBSCRIPT);
	    Transport.send(message);
	} catch (AddressException e) {
	    e.printStackTrace();
	} catch (MessagingException e) {
	    e.printStackTrace();
	}
    }

}
