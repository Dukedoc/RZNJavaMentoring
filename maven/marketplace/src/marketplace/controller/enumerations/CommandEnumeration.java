/**
 * 
 */
package marketplace.controller.enumerations;

/**
 * Command Enumeration.
 * 
 * @author Roman_Ten
 * 
 */
public enum CommandEnumeration {
    SHOW_ITEMS("showItems"), SEARCH("search"), SHOW_LOGIN("showLogin"), LOGIN(
	    "login"), SHOW_MY_ITEMS("showMyItems"), SELL("sell"), PUBLISH_ITEM(
	    "publishItem"), LOGIN_AS_GUEST("loginAsGuest"), LOGOUT("logout"), REGISTRATION(
	    "registration"), ADD_USER("addUser"), BIDDING("bidding"), DELETE_ITEM(
	    "deleteItem"), EDIT_ITEM("editItem"), BUY_IT_NOW("buyItNow"), INCORRECT_LOGIN(
	    "incorrectLogin"), NOT_AUTHORIZED("notAuthorized"), INCORRECT_BID_INCREMENT(
	    "incorrectBidIncrement"), INCORRECT_BID_START_PRICE(
	    "incorrectBidStartPrice"), ERROR_DB("errorDB"), SHOW_HISTORY(
	    "showHistory"), PAGING("paging"), ADD_TO_BLACK_LIST(
	    "addToBlackList"), BLACK_LIST("blackList"), REMOVE_FROM_BLACK_LIST(
	    "removeFromBlackList");

    private String text;

    // Set Enumeration to map.
    private CommandEnumeration(String text) {
	CommandEnumMap.setCommand(text, this);
	this.text = text;
    }

    /**
     * Get text.
     * 
     * @return text description.
     */
    public String getText() {
	return this.text;
    }

    /**
     * Get enumeration from text description.
     * 
     * @param commandName
     *            the description of command name.
     * @return CommandEnumeration.
     */
    public static CommandEnumeration fromString(String commandName) {
	return CommandEnumMap.getCommand(commandName);
    }
}
