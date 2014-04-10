/**
 * 
 */
package marketplace.model.to;

import java.util.Calendar;
import java.util.Comparator;

import marketplace.controller.enumerations.SortFieldEnum;

/**
 * Comparator for ItemDetails.
 * 
 * @author Roman_Ten
 * 
 */
public final class ItemDetailsSort implements Comparator<ItemDetails> {

    private SortFieldEnum field;

    /**
     * Constructor.
     * 
     * @param field
     *            field for sort.
     */
    public ItemDetailsSort(SortFieldEnum field) {
	this.field = field;
    }

    /**
     * java.util.comparator.
     * 
     * @see java.util.Comparator#compare(Object, Object)
     * @param o1
     *            the first object to be compared.
     * @param o2
     *            the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the first
     *         argument is less than, equal to, or greater than the second.
     */
    public int compare(ItemDetails o1, ItemDetails o2) {
	switch (field) {
	case ITEM_ID:
	    int id1 = (o1).getItemID();
	    int id2 = (o2).getItemID();
	    return id1 - id2;
	case TITLE:
	    String title1 = (o1).getTitle();
	    String title2 = (o2).getTitle();
	    if (title1 == null) {
		return -1;
	    }
	    if (title2 == null) {
		return 1;
	    }
	    return title1.compareTo(title2);
	case DESCRIPTION:
	    String desctiption1 = (o1).getDescription();
	    String desctiption2 = (o2).getDescription();
	    if (desctiption1 == null) {
		return -1;
	    }
	    if (desctiption2 == null) {
		return 1;
	    }
	    return desctiption1.compareTo(desctiption2);
	case CATEGORY:
	    String category1 = (o1).getCategory();
	    String category2 = (o2).getCategory();
	    if (category1 == null) {
		return -1;
	    }
	    if (category2 == null) {
		return 1;
	    }
	    return category1.compareTo(category2);
	case SELLER:
	    String seller1 = (o1).getSeller();
	    String seller2 = (o2).getSeller();
	    if (seller1 == null) {
		return -1;
	    }
	    if (seller2 == null) {
		return 1;
	    }
	    return seller1.compareTo(seller2);
	case START_PRICE:
	    float startPrice1 = (o1).getStartPrice();
	    float startPrice2 = (o2).getStartPrice();
	    return (int) (startPrice1 - startPrice2);
	case BID_INCREMENT:
	    float bidIncrement1 = (o1).getBidIncrement();
	    float bidIncrement2 = (o2).getBidIncrement();
	    return (int) (bidIncrement1 - bidIncrement2);
	case BEST_OFFER:
	    float bestOffer1 = (o1).getBestOffer();
	    float bestOffer2 = (o2).getBestOffer();
	    return (int) (bestOffer1 - bestOffer2);
	case BIDDER:
	    String bidder1 = (o1).getBidder();
	    String bidder2 = (o2).getBidder();
	    if (bidder1 == null) {
		return -1;
	    }
	    if (bidder2 == null) {
		return 1;
	    }
	    return bidder1.compareTo(bidder2);
	case STOP_DATE:
	    Calendar stopDate1 = (o1).getStopDate();
	    Calendar stopDate2 = (o2).getStopDate();
	    if (stopDate1 == null) {
		return -1;
	    }
	    if (stopDate2 == null) {
		return 1;
	    }
	    return stopDate1.compareTo(stopDate2);
	default:
	    return 0;
	}

    }

}
