package marketplace.model.to;

import java.io.Serializable;

/**
 * Bid Transfer Object.
 *
 * @author Roman_Ten
 */
public class Bid implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 1L;
    private int bidID;
    private int bidderID;
    private int itemID;
    private float bid;

    /**
     * Get bid_id for bid.
     *
     * @return the bidID is BID_ID.
     */
    public int getBidID() {
        return bidID;
    }

    /**
     * Set BID_ID.
     *
     * @param bidID the bidID to set.
     */
    public void setBidID(int bidID) {
        this.bidID = bidID;
    }

    /**
     * Get Bidder_ID.
     *
     * @return the bidderID is Bidder ID.
     */
    public int getBidderID() {
        return bidderID;
    }

    /**
     * Set BIDDER_ID.
     *
     * @param bidderID the bidderID to set
     */
    public void setBidderID(int bidderID) {
        this.bidderID = bidderID;
    }

    /**
     * Get ITEM_ID.
     *
     * @return the itemID is ITEM_ID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Set ITEM_ID.
     *
     * @param itemID the itemID to set.
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * Get BID.
     *
     * @return the bid is BID.
     */
    public float getBid() {
        return bid;
    }

    /**
     * Set BID.
     *
     * @param bid the bid to set.
     */
    public void setBid(float bid) {
        this.bid = bid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(bid);
        result = prime * result + bidID;
        result = prime * result + bidderID;
        result = prime * result + itemID;
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
        if (!(obj instanceof Bid)) {
            return false;
        }
        Bid other = (Bid) obj;
        if (Float.floatToIntBits(bid) != Float.floatToIntBits(other.bid)) {
            return false;
        }
        if (bidID != other.bidID) {
            return false;
        }
        if (bidderID != other.bidderID) {
            return false;
        }
        if (itemID != other.itemID) {
            return false;
        }
        return true;
    }

    /**
     * Print Bid to String.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Bid [bidID=" + bidID + ", bidderID=" + bidderID + ", itemID="
                + itemID + ", bid=" + bid + "]";
    }

}
