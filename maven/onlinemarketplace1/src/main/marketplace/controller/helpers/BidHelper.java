/**
 * 
 */
package marketplace.controller.helpers;

/**
 * Interface for BidHelper.
 * 
 * @author Roman_Ten
 * 
 */
public interface BidHelper {

    /**
     * Get Bid value.
     * 
     * @return bid value.
     */
    float getBidValue();

    /**
     * Get Item ID.
     * 
     * @return itemID.
     */
    int getItemID();

    /**
     * Set Error Message.
     * 
     * @param message
     *            the error message.
     */
    void setErrorMsg(String message);

    /**
     * Get Bidder ID from request.
     * 
     * @return bidderID.
     */
    int getBidderID();

}
