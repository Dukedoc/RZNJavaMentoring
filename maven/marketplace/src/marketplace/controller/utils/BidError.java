/**
 * 
 */
package marketplace.controller.utils;

import marketplace.controller.enumerations.CommandResultEnum;

/**
 * Parse bidding error. Class-utility.
 * 
 * @author Roman_Ten
 */
public final class BidError {
    private static final int BIDDER_IN_BLACK_LIST = -9;
    private static final int THE_BIDDER_IS_LAST_BIDDER = -8;
    private static final int THE_SELLER_IS_THE_BIDDER = -7;
    private static final int SQL_EXCEPTION = -6;
    private static final int BID_INCREMENT = -4;
    private static final int BID_SMALLER_THEN_START_PRICE = -3;
    private static final int BUY_IT_NOW = -2;
    private static final int BID_ERROR_DATE = -1;

    /*
     * Empty private constructor.
     */
    private BidError() {

    }

    /**
     * errorCode is -1 for error with date or -2 for error with buy it now or -3
     * for error with bid smaller then start price, -4 for error with bid
     * smaller then bid increment. -5 for error with incorrect bid_id -6 for SQL
     * Exception. -7 if bidder equals seller. -8 if Bidder equals last bidder.
     * -9 if bidder is in the black list of seller.
     * 
     * @param errorCode
     *            the Error Code.
     * @return Command Result Enumeration.
     */
    public static CommandResultEnum parseToCommandResult(int errorCode) {
	CommandResultEnum errorResult = null;
	switch (errorCode) {
	case BID_ERROR_DATE:
	    errorResult = CommandResultEnum.BID_ERROR_DATE;
	    break;
	case BUY_IT_NOW:
	    errorResult = CommandResultEnum.BID_ERROR_BUY_IT_NOW;
	    break;
	case BID_SMALLER_THEN_START_PRICE:
	    errorResult = CommandResultEnum.BID_ERROR_BID_SMALLER_THEN_START_PRICE;
	    break;
	case BID_INCREMENT:
	    errorResult = CommandResultEnum.BID_ERROR_BID_SMALLER_THEN_BID_INCREMENT;
	    break;
	case SQL_EXCEPTION:
	    errorResult = CommandResultEnum.SQL_EXCEPTION;
	    break;
	case THE_SELLER_IS_THE_BIDDER:
	    errorResult = CommandResultEnum.BID_ERROR_THE_SELLER_IS_THE_BIDDER;
	    break;
	case THE_BIDDER_IS_LAST_BIDDER:
	    errorResult = CommandResultEnum.BID_ERROR_THE_BIDDER_IS_LAST_BIDDER;
	    break;
	case BIDDER_IN_BLACK_LIST:
	    errorResult = CommandResultEnum.BID_ERROR_BIDDER_IN_BLACK_LIST;
	    break;
	default:
	    break;
	}
	return errorResult;
    }
}
