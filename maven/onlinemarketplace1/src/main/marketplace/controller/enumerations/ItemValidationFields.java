package marketplace.controller.enumerations;

/**
 * Not validate fields in Item's form. There are field's name and error
 * messages.
 * 
 * @author Roman_Ten
 * 
 */
public enum ItemValidationFields {
    TITLE("The title must be not null."), BID_INCREMENT(
	    "The bid increment is not valid."), START_PRICE(
	    "The start Price is not valid."), TIME_LEFT(
	    "The time left is not valid."), BID_INCREMENT_EXISTS(
	    "If Buy it now then bid increment must be empty."), BID_INCREMENT_NOT_EXISTS(
	    "Bid increment must be bigger then null."), TIME_LEFT_NULL(
	    "Time left must be bigger then null.");

    private String message;

    private ItemValidationFields(String message) {
	this.message = message;
    }

    /**
     * Get error message.
     * 
     * @return the message.
     */
    public String getMessage() {
	return message;
    }

}
