/**
 * 
 */
package marketplace.controller.enumerations;

/**
 * Enumeration for Command Result.
 * 
 * @author Roman_Ten
 * 
 */
public enum CommandResultEnum {
    TRUE("True"), NOT_AUTORIZATED("You are not logged in."), ERROR_DB(
	    "An error has occurred. Please contact with support."), LOGIN_ERROR(
	    "Incorrect login or password"), BID_ERROR_THE_SELLER_IS_THE_BIDDER(
	    "The Bidder is the seller"), BID_ERROR_THE_BIDDER_IS_LAST_BIDDER(
	    "You bid is the last. You don't interrupt your bid."), BID_ERROR_DATE(
	    "Item not for sale"), BID_ERROR_BUY_IT_NOW(
	    "This item for buy it now"), BID_ERROR_BID_SMALLER_THEN_START_PRICE(
	    "The bid is smaller then start price."), BID_ERROR_BID_SMALLER_THEN_BID_INCREMENT(
	    "Current bid must be greater than an amount equal to the previous increment bid"), SQL_EXCEPTION(
	    "An error has occurred. Please contact with support."), BID_ERROR_BIDDER_IN_BLACK_LIST(
	    "You is in a black list of Seller"), BLACK_LIST_ERROR_DUPLICATE(
	    "The user is already in your black list."), BLACK_LIST_ADD_USER(
	    "User was successfully added to your Black list."), UNKNOWN_ERROR(
	    "Unknown error. Please contact with support."), LOGIN_EXIST(
	    "This login exists"), FORM_NOT_VALID("Form not valid");

    // Result description.
    private String text;

    // Constructor. Add description to Enum.
    private CommandResultEnum(String text) {
	this.text = text;
    }

    /**
     * Get description.
     * 
     * @return the text.
     */
    public String getText() {
	return this.text;
    }
}
