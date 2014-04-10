/**
 * 
 */
package marketplace.model.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import marketplace.model.to.ItemDetails;

/**
 * Abstract class for Email.
 * 
 * @author Roman_Ten
 * 
 */
public abstract class AbstractEmail implements Email {
    private static final String FALSE = "false";
    private static final String TRUE = "true";
    private static final String MAIL_SMTP_SOCKET_FACTORY_FALLBACK = "mail.smtp.socketFactory.fallback";
    private static final String MAIL_SMTP_SOCKET_FACTORY_CLASS_VALUE = "javax.net.ssl.SSLSocketFactory";
    private static final String MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
    private static final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
    private static final String MAIL_SMTP_DEBUG = "mail.smtp.debug";
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    private static final String MAIL_SMTP_PORT = "mail.smtp.port";
    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
    private static final String MAIL_SMTP_USER = "mail.smtp.user";
    private static final String SERVER_PORT = "465";
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USER = "dlya.derma@gmail.com";
    private static final String PWD = "R0ManA10";
    /**
     * Field from for e-mail.
     */
    protected static final String FROM = "dlya.derma@gmail.com";
    /**
     * Subscript for email.
     */
    protected static final String MESSAGE_TEXT_SUBSCRIPT = ".\n\nRegards\nOnlineMarketplace";
    /**
     * Java Mail Session.
     */
    protected Session session;
    private Authenticator auth = new SMTPAuthenticator();
    private Transport transport;
    private static final String PROTOCOL = "smtps";
    private ItemDetails itemDetails;

    /**
     * Constructor.
     * 
     * @param itemDetails
     *            the item Details transfer object.
     */
    public AbstractEmail(ItemDetails itemDetails) {
	this.itemDetails = itemDetails;
	init();
    }

    // create session.
    private void init() {
	Properties props = new Properties();
	props.put(MAIL_SMTP_USER, USER);
	props.put(MAIL_SMTP_HOST, SMTP_SERVER);
	props.put(MAIL_SMTP_PORT, SERVER_PORT);
	props.put(MAIL_SMTP_STARTTLS_ENABLE, TRUE);
	props.put(MAIL_SMTP_AUTH, TRUE);
	props.put(MAIL_SMTP_DEBUG, TRUE);
	props.put(MAIL_SMTP_SOCKET_FACTORY_PORT, SERVER_PORT);
	props.put(MAIL_SMTP_SOCKET_FACTORY_CLASS,
		MAIL_SMTP_SOCKET_FACTORY_CLASS_VALUE);
	props.put(MAIL_SMTP_SOCKET_FACTORY_FALLBACK, FALSE);
	session = Session.getInstance(props, auth);
	try {
	    transport = session.getTransport(PROTOCOL);
	    transport.connect(SMTP_SERVER, USER, PWD);
	} catch (NoSuchProviderException e) {
	    e.printStackTrace();
	} catch (MessagingException e) {
	    e.printStackTrace();
	}

    }

    /**
     * {@inheritDoc}
     */
    public abstract void send();

    /**
     * Get itemDetails.
     * 
     * @return the itemDetails
     */
    public ItemDetails getItemDetails() {
	return itemDetails;
    }

    // SMTP Authenticator.
    private class SMTPAuthenticator extends javax.mail.Authenticator {
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(USER, PWD);
	}
    }

}
