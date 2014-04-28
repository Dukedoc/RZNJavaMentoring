/**
 * 
 */
package marketplace.model.to;

import java.util.Calendar;

/**
 * Transfer Object for showing full information about items.
 * 
 * @author Roman_Ten
 * 
 */
public class ItemDetails {
    private int itemID;
    private String title;
    private String description;
    private int sellerID;
    private String seller;
    private String sellerEmail;
    private float startPrice;
    private float bidIncrement;
    private float bid;
    private float bestOffer;
    private int bidderID;
    private String bidder;
    private String bidderEmail;
    private Calendar stopDate;
    private boolean buyItNow;
    private String status;
    private String category;
    private boolean sendEmail;

    /**
     * Get Item ID.
     * 
     * @return the itemID
     */
    public int getItemID() {
	return itemID;
    }

    /**
     * Set item ID.
     * 
     * @param itemID
     *            the itemID to set
     */
    public void setItemID(int itemID) {
	this.itemID = itemID;
    }

    /**
     * Get a title.
     * 
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * Set a title.
     * 
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * Get a description.
     * 
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * Set a description.
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Set seller ID.
     * 
     * @param sellerID
     *            the sellerID to set
     */
    public void setSellerID(int sellerID) {
	this.sellerID = sellerID;
    }

    /**
     * Get seller ID.
     * 
     * @return the sellerID
     */
    public int getSellerID() {
	return sellerID;
    }

    /**
     * Get Seller.
     * 
     * @return the seller
     */
    public String getSeller() {
	return seller;
    }

    /**
     * Get Seller e-mail.
     * 
     * @return the sellerEmail
     */
    public String getSellerEmail() {
	return sellerEmail;
    }

    /**
     * Set seller e-mail.
     * 
     * @param sellerEmail
     *            the sellerEmail to set
     */
    public void setSellerEmail(String sellerEmail) {
	this.sellerEmail = sellerEmail;
    }

    /**
     * Set seller.
     * 
     * @param seller
     *            the seller to set
     */
    public void setSeller(String seller) {
	this.seller = seller;
    }

    /**
     * Get start price.
     * 
     * @return the startPrice
     */
    public float getStartPrice() {
	return startPrice;
    }

    /**
     * Set Start Price.
     * 
     * @param startPrice
     *            the startPrice to set
     */
    public void setStartPrice(float startPrice) {
	this.startPrice = startPrice;
    }

    /**
     * Get Bid Increment.
     * 
     * @return the bidIncrement
     */
    public float getBidIncrement() {
	return bidIncrement;
    }

    /**
     * Set Bid Increment.
     * 
     * @param bidIncrement
     *            the bidIncrement to set
     */
    public void setBidIncrement(float bidIncrement) {
	this.bidIncrement = bidIncrement;
    }

    /**
     * Get bid.
     * 
     * @return the bid
     */
    public float getBid() {
	return bid;
    }

    /**
     * Set bid.
     * 
     * @param bid
     *            the bid to set
     */
    public void setBid(float bid) {
	this.bid = bid;
    }

    /**
     * Get a best Offer.
     * 
     * @return the bestOffer
     */
    public float getBestOffer() {
	return bestOffer;
    }

    /**
     * Set a best offer.
     * 
     * @param bestOffer
     *            the bestOffer to set
     */
    public void setBestOffer(float bestOffer) {
	this.bestOffer = bestOffer;
    }

    /**
     * Set a bidder ID.
     * 
     * @param bidderID
     *            the bidderID to set
     */
    public void setBidderID(int bidderID) {
	this.bidderID = bidderID;
    }

    /**
     * get a bidder ID.
     * 
     * @return the bidderID
     */
    public int getBidderID() {
	return bidderID;
    }

    /**
     * Get a bidder.
     * 
     * @return the bidder
     */
    public String getBidder() {
	return bidder;
    }

    /**
     * Set a bidder.
     * 
     * @param bidder
     *            the bidder to set
     */
    public void setBidder(String bidder) {
	this.bidder = bidder;
    }

    /**
     * Get bidder e-mail.
     * 
     * @return the bidderEmail
     */
    public String getBidderEmail() {
	return bidderEmail;
    }

    /**
     * Set bidder e-mail.
     * 
     * @param bidderEmail
     *            the bidderEmail to set
     */
    public void setBidderEmail(String bidderEmail) {
	this.bidderEmail = bidderEmail;
    }

    /**
     * Get stop date.
     * 
     * @return the stopDate
     */
    public Calendar getStopDate() {
	return stopDate;
    }

    /**
     * Set stop date.
     * 
     * @param stopDate
     *            the stopDate to set
     */
    public void setStopDate(Calendar stopDate) {
	this.stopDate = stopDate;
    }

    /**
     * Set buy it now.
     * 
     * @param buyItNow
     *            the buyItNow to set
     */
    public void setBuyItNow(boolean buyItNow) {
	this.buyItNow = buyItNow;
    }

    /**
     * Get buy it now.
     * 
     * @return the buyItNow
     */
    public boolean isBuyItNow() {
	return buyItNow;
    }

    /**
     * Set Category.
     * 
     * @param category
     *            the category to set
     */
    public void setCategory(String category) {
	this.category = category;
    }

    /**
     * Get Category.
     * 
     * @return the category
     */
    public String getCategory() {
	return category;
    }

    /**
     * Set status.
     * 
     * @param status
     *            the status to set.
     */
    public void setStatus(String status) {
	this.status = status;
    }

    /**
     * Get status.
     * 
     * @return the status.
     */
    public String getStatus() {
	return status;
    }

    /**
     * Get sendEmail.
     * 
     * @return the sendEmail
     */
    public boolean isSendEmail() {
	return sendEmail;
    }

    /**
     * set sendEmail.
     * 
     * @param sendEmail
     *            the sendEmail to set
     */
    public void setSendEmail(boolean sendEmail) {
	this.sendEmail = sendEmail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
	return "ItemDetails [itemID=" + itemID + ", title=" + title
		+ ", description=" + description + ", seller=" + seller
		+ ", startPrice=" + startPrice + ", bidIncrement="
		+ bidIncrement + ", bid=" + bid + ", bestOffer=" + bestOffer
		+ ", bidder=" + bidder + ", stopDate=" + stopDate
		+ ", buyItNow=" + buyItNow + "]";
    }

}
