package marketplace.model.to;

import java.io.Serializable;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Item Transfer Object.
 * 
 * @author Roman_Ten
 * 
 */
public class Item implements Serializable {
    private static final String TIME_LEFT_EXCEPTION_MESSAGE = "Time left must be in a format: \"HH:MM\". Example: 3:59";
    private static final int MINUTES_IN_HOUR = 60;
    private static final String TIME_LEFT_REGEX = "^[0-9]*(:[0-5]?[0-9])?$";

    private static final long serialVersionUID = 1L;

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
    public double getTimeLeft() {
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
     * Get TimeLeft string in format HH:MM;
     * 
     * @return String of time left.
     */
    public String getTimeLeftStr() {
	int hours = (int) timeLeft;
	long minutes = Math.round((timeLeft - hours) * MINUTES_IN_HOUR);
	return hours + ":" + minutes;
    }

    /**
     * Set time left from String in format HH:MM.
     * 
     * @param timeLeft
     *            the string in format HH:MM.
     * @throws Exception
     *             if format is not HH:MM.
     */
    public void setTimeLeftStr(String timeLeft) throws Exception {
	if (!Pattern.matches(TIME_LEFT_REGEX, timeLeft)) {
	    throw new Exception(TIME_LEFT_EXCEPTION_MESSAGE);
	}
	StringTokenizer tokenizer = new StringTokenizer(timeLeft, ":");
	int hours = 0;
	int minutes = 0;
	if (tokenizer.hasMoreTokens()) {
	    hours = Integer.parseInt(tokenizer.nextToken());
	}
	if (tokenizer.hasMoreTokens()) {
	    minutes = Integer.parseInt(tokenizer.nextToken());
	}
	this.timeLeft = hours + ((float) minutes) / MINUTES_IN_HOUR;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Float.floatToIntBits(bidIncrement);
	result = prime * result + (buyItNow ? 1231 : 1237);
	result = prime * result + categoryID;
	result = prime * result
		+ ((description == null) ? 0 : description.hashCode());
	result = prime * result + itemID;
	result = prime * result + sellerID;
	result = prime
		* result
		+ ((startBiddingDate == null) ? 0 : startBiddingDate.hashCode());
	result = prime * result + Float.floatToIntBits(startPrice);
	result = prime * result + Float.floatToIntBits(timeLeft);
	result = prime * result + ((title == null) ? 0 : title.hashCode());
	return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof Item)) {
	    return false;
	}
	Item other = (Item) obj;
	if (Float.floatToIntBits(bidIncrement) != Float
		.floatToIntBits(other.bidIncrement)) {
	    return false;
	}
	if (buyItNow != other.buyItNow) {
	    return false;
	}
	if (categoryID != other.categoryID) {
	    return false;
	}
	if (description == null) {
	    if (other.description != null) {
		return false;
	    }
	} else if (!description.equals(other.description)) {
	    return false;
	}
	if (itemID != other.itemID) {
	    return false;
	}
	if (sellerID != other.sellerID) {
	    return false;
	}
	if (startBiddingDate == null) {
	    if (other.startBiddingDate != null) {
		return false;
	    }
	} else if (!startBiddingDate.equals(other.startBiddingDate)) {
	    return false;
	}
	if (Float.floatToIntBits(startPrice) != Float
		.floatToIntBits(other.startPrice)) {
	    return false;
	}
	if (Float.floatToIntBits(timeLeft) != Float
		.floatToIntBits(other.timeLeft)) {
	    return false;
	}
	if (title == null) {
	    if (other.title != null) {
		return false;
	    }
	} else if (!title.equals(other.title)) {
	    return false;
	}
	return true;
    }

}
