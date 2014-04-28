package marketplace.model.to;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Item Transfer Object.
 * 
 * @author Roman_Ten
 * 
 */
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int MINUTE = 6000;

    private int itemID;
    private int sellerID;
    private String title;
    private String description;
    private float startPrice;
    private float timeLeft;
    private Calendar startBiddingDate;
    private boolean buyItNow;
    private float bidIncrement;
    private int categoryID;

    /**
     * Get Item_ID.
     * 
     * @return the itemID is ITEM_ID.
     */
    public int getItemID() {
	return itemID;
    }

    /**
     * Set ITEM_ID.
     * 
     * @param itemID
     *            the itemID to set
     */
    public void setItemID(int itemID) {
	this.itemID = itemID;
    }

    /**
     * Get SELLER_ID.
     * 
     * @return the sellerID is SELLER_ID.
     */
    public int getSellerID() {
	return sellerID;
    }

    /**
     * Set SELLER_ID.
     * 
     * @param sellerID
     *            the sellerID to set
     */
    public void setSellerID(int sellerID) {
	this.sellerID = sellerID;
    }

    /**
     * Get Title.
     * 
     * @return the title is TITLE.
     */
    public String getTitle() {
	return title;
    }

    /**
     * Set TITLE.
     * 
     * @param title
     *            the title to set.
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * get Description.
     * 
     * @return the description is DESCRIPTION.
     */
    public String getDescription() {
	return description;
    }

    /**
     * Set DESCRIPTION.
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * get StartPrice for item.
     * 
     * @return the startPrice is START_PRICE.
     */
    public float getStartPrice() {
	return startPrice;
    }

    /**
     * Set START_PRICE.
     * 
     * @param startPrice
     *            the startPrice to set
     */
    public void setStartPrice(float startPrice) {
	this.startPrice = startPrice;
    }

    /**
     * get time left for item.
     * 
     * @return the timeLeft is TIME_LEFT.
     */
    public float getTimeLeft() {
	return timeLeft;
    }

    /**
     * SET TIME_LEFT.
     * 
     * @param timeLeft
     *            the timeLeft to set
     */
    public void setTimeLeft(float timeLeft) {
	this.timeLeft = timeLeft;
    }

    /**
     * get start bidding date for item.
     * 
     * @return the startBiddingDate is START_BIDDING_DATE.
     */
    public Calendar getStartBiddingDate() {
	return startBiddingDate;
    }

    /**
     * Set START_BIDDING_DATE.
     * 
     * @param startBiddingDate
     *            the startBiddingDate to set
     */
    public void setStartBiddingDate(Calendar startBiddingDate) {
	this.startBiddingDate = startBiddingDate;
    }

    /**
     * get buy it now.
     * 
     * @return the buyItNow is flag BUY_IT_NOW.
     */
    public boolean isBuyItNow() {
	return buyItNow;
    }

    /**
     * Set flag BUY_IT_NOW.
     * 
     * @param buyItNow
     *            the buyItNow to set
     */
    public void setBuyItNow(boolean buyItNow) {
	this.buyItNow = buyItNow;
    }

    /**
     * get bid increment for item.
     * 
     * @return the bidIncrement is BID_INCREMENT.
     */
    public float getBidIncrement() {
	return bidIncrement;
    }

    /**
     * Set BID_INCREMENT.
     * 
     * @param bidIncrement
     *            the bidIncrement to set.
     */
    public void setBidIncrement(float bidIncrement) {
	this.bidIncrement = bidIncrement;
    }

    /**
     * Set Category ID.
     * 
     * @param categoryID
     *            the categoryID to set
     */
    public void setCategoryID(int categoryID) {
	this.categoryID = categoryID;
    }

    /**
     * Get Category ID.
     * 
     * @return the categoryID
     */
    public int getCategoryID() {
	return categoryID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
	return "Item [itemID=" + itemID + ", categoryID=" + categoryID
		+ ", title=" + title + ", description=" + description
		+ ", sellerID=" + sellerID + ", startPrice=" + startPrice
		+ ", bidIncrement=" + bidIncrement + ", buyItNow=" + buyItNow
		+ ", startBiddingDate=" + startBiddingDate + ", timeLeft="
		+ timeLeft + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (Float.compare(item.bidIncrement, bidIncrement) != 0) return false;
        if (buyItNow != item.buyItNow) return false;
        if (categoryID != item.categoryID) return false;
        if (itemID != item.itemID) return false;
        if (sellerID != item.sellerID) return false;
        if (Float.compare(item.startPrice, startPrice) != 0) return false;
        if (Float.compare(item.timeLeft, timeLeft) != 0) return false;
        if (!description.equals(item.description)) return false;
        if (Math.abs(startBiddingDate.getTimeInMillis() - item.startBiddingDate.getTimeInMillis()) > MINUTE) return false;
        if (!title.equals(item.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemID;
        result = 31 * result + sellerID;
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (startPrice != +0.0f ? Float.floatToIntBits(startPrice) : 0);
        result = 31 * result + (timeLeft != +0.0f ? Float.floatToIntBits(timeLeft) : 0);
        result = 31 * result + startBiddingDate.hashCode();
        result = 31 * result + (buyItNow ? 1 : 0);
        result = 31 * result + (bidIncrement != +0.0f ? Float.floatToIntBits(bidIncrement) : 0);
        result = 31 * result + categoryID;
        return result;
    }
}
