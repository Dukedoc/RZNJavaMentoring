/**
 * 
 */
package marketplace.controller.enumerations;

/**
 * Enumeration for field name.
 * 
 * @author Roman_Ten
 * 
 */
public enum SortFieldEnum {
    ITEM_ID("item_id"), TITLE("title"), DESCRIPTION("description"), CATEGORY(
	    "category"), SELLER("seller"), START_PRICE("startPrice"), BID_INCREMENT(
	    "bidIncrement"), BEST_OFFER("bestOffer"), BIDDER("bidder"), STOP_DATE(
	    "stopDate");

    // text description for field.
    private String text;

    // Constructor. Add description.
    private SortFieldEnum(String text) {
	this.text = text;
    }

    /**
     * Get description.
     * 
     * @return text description.
     */
    public String getText() {
	return text;
    }

}
