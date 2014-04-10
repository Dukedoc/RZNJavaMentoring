/**
 * 
 */
package marketplace.controller.enumerations;

/**
 * Enumeration for AdvancedSearch.
 * 
 * @author Roman_Ten
 * 
 */
public enum AdvancedSearchControllerFields {

    BIDDER_COUNT("bidderCount"), STOP_DATE("stopDate"), START_DATE("startDate"), BUY_IT_NOW(
	    "buyItNow"), MAX_PRICE("maxPrice"), MIN_PRICE("minPrice"), DESCRIPTION(
	    "description"), TITLE("title"), ITEM_ID("itemID");

    private String value;

    private AdvancedSearchControllerFields(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }
}
