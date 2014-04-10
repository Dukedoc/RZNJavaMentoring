package marketplace.model.dao.interfaces;

import marketplace.model.to.Bid;

/**
 * Data Access Object for Bid.
 * 
 * @author Roman_Ten
 * 
 */
public interface BidDAO {
    /**
     * Return value for incorrect date.
     */
    int ERROR_DATE = -1;
    /**
     * Return value for try to bid item with flag try it now.
     */
    int ERROR_BUY_IT_NOW = -2;
    /**
     * The return value in an attempt to bid the price is less than the starting
     * price.
     */
    int ERROR_START_PRICE = -3;
    /**
     * The return value in an attempt to bid greatest previous bid then bid
     * increment.
     */
    int ERROR_BID_INCREMENT = -4;
    /**
     * The return value in an attempt to bid with incorrect id.
     */
    int ERROR_BID_ID = -5;
    /**
     * The return value for SQLException.
     */
    int ERROR_SQL = -6;

    /**
     * The return value if bidder equals seller.
     */
    int ERROR_BIDDER_EQUALS_SELLER = -7;

    /**
     * The return value if bidder equals last bidder.
     */
    int ERROR_BIDDER_EQUALS_LAST_BIDDER = -8;
    /**
     * The return value if bidder in black list of seller.
     */
    int ERROR_BLACK_LIST = -9;

    /**
     * Add bid into bid.
     * 
     * @param bid
     *            is transfer object bid
     * @return BID_ID either is itemID or -1 for error with date or -2 for error
     *         with buy it now or -3 for error with bid smaller then start
     *         price, -4 for error with bid smaller then bid increment. -5 for
     *         error with incorrect bid_id -6 for SQL Exception. -7 if bidder
     *         equals seller. -8 if Bidder equals last bidder. -9 if bidder is
     *         in the black list of seller.
     */
    int bidAdd(Bid bid);

    /**
     * to buy it now.
     * 
     * @param bid
     *            is transfer object bid
     * @return BID_ID either is bidID or -1 for error with date
     */
    int buyItNow(Bid bid);
}
