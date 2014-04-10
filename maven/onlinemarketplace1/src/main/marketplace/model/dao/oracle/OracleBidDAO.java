package marketplace.model.dao.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import marketplace.model.dao.factory.OracleDAOFactory;
import marketplace.model.dao.interfaces.BidDAO;
import marketplace.model.to.Bid;
import marketplace.model.to.ItemDetails;

/**
 * Bid Data Access Object for oracle.
 * 
 * @author Roman_Ten
 */
public class OracleBidDAO implements BidDAO {
    private static final String BID_ID = "BID_ID";
    private static final String SQL_SELECT_FROM_BLACK_LIST = "SELECT * FROM BLACK_LIST WHERE SELLER_ID = ? AND BIDDER_ID = ?";
    private static final String ERROR_GENERATE_BID_ID = "Error generate bid_id";
    private static final String SQL_GET_SELLER_ID = "SELECT SELLER_ID FROM ITEMS WHERE ITEM_ID = ?";
    private static final String EXCEPTION_SELLER_ID = "Exception. Seller ID.";
    private static final String SELLER_ID = "SELLER_ID";
    private static final String SQL_BUY_IT_NOW = "{call buyItNow(?, ?, ?)}";
    private static final String BIDDER_ID = "BIDDER_ID";
    private static final String SQL_CHECK_CURRENT_BIDDER_AND_LAST_BIDDER = "SELECT BIDDER_ID FROM (SELECT BID, bidder_id FROM BIDS WHERE ITEM_ID = ? ORDER BY BID DESC) WHERE ROWNUM=1";
    /**
     * SQL for insert bid.
     */
    private static final String SQL_INSERT_BIDS = "INSERT INTO BIDS (BIDDER_ID, ITEM_ID, BID) VALUES (?, ?, ?)";
    /**
     * SQL for select maximum bid.
     */
    private static final String SQL_SELECT_MAX_BID = "SELECT BID FROM BIDS WHERE ITEM_ID = ? AND BID=(SELECT MAX(BID) FROM BIDS WHERE ITEM_ID = ?) FOR UPDATE";
    /**
     * Array with field name.
     */
    private static final String[] COL_BID_ID = { BID_ID };

    /**
     * {@inheritDoc}
     */
    public final int bidAdd(final Bid bid) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;

	int itemID = bid.getItemID();
	ItemDetails itemDetails = new OracleItemDAO()
		.findItemDetailsByUID(itemID);

	try {
	    // check date
	    if (itemDetails.getStopDate() != null) {
		if (System.currentTimeMillis() >= itemDetails.getStopDate()
			.getTimeInMillis()) {
		    return ERROR_DATE;
		}
	    }

	    // check buyItNow
	    if (itemDetails.isBuyItNow()) {
		return ERROR_BUY_IT_NOW;
	    }

	    // check start price
	    float currentBid = bid.getBid();
	    float maxBid = itemDetails.getStartPrice();
	    if (currentBid < maxBid) {
		return ERROR_START_PRICE;
	    }

	    // check bidder equals seller
	    if (bid.getBidderID() == itemDetails.getSellerID()) {
		return ERROR_BIDDER_EQUALS_SELLER;
	    }

	    // check bidder bidding his same bid
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection
		    .prepareStatement(SQL_CHECK_CURRENT_BIDDER_AND_LAST_BIDDER);
	    preparedStatement.setInt(1, itemID);
	    rs = preparedStatement.executeQuery();
	    if (rs.next()) {
		if (rs.getInt(BIDDER_ID) == bid.getBidderID()) {
		    return ERROR_BIDDER_EQUALS_LAST_BIDDER;
		}
	    }

	    // check max bid
	    float bidIncrement = itemDetails.getBidIncrement();
	    connection = OracleDAOFactory.createConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(SQL_SELECT_MAX_BID);
	    preparedStatement.setInt(1, itemID);
	    preparedStatement.setInt(2, itemID);
	    rs = preparedStatement.executeQuery();
	    while (rs.next()) {
		if (rs.getInt(1) > maxBid) {
		    maxBid = rs.getInt(1);
		}
	    }
	    if (currentBid - maxBid < bidIncrement) {
		connection.commit();
		return ERROR_BID_INCREMENT;
	    }

	    // check black list
	    if (checkBlackList(bid)) {
		return ERROR_BLACK_LIST;
	    }

	    // add bid

	    preparedStatement = connection.prepareStatement(SQL_INSERT_BIDS,
		    COL_BID_ID);
	    preparedStatement.setInt(1, bid.getBidderID());
	    preparedStatement.setInt(2, bid.getItemID());
	    preparedStatement.setFloat(3, bid.getBid());
	    preparedStatement.executeUpdate();
	    connection.commit();
	    rs = preparedStatement.getGeneratedKeys();
	    if (rs.next()) {
		int bidID = rs.getInt(1);
		bid.setBidID(bidID);
		return bidID;
	    } else {
		throw new SQLException(ERROR_GENERATE_BID_ID);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	    return ERROR_SQL;
	} finally {
	    try {
		if (rs != null) {
		    rs.close();
		}
		if (preparedStatement != null) {
		    preparedStatement.close();
		}
		if (connection != null) {
		    connection.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}

    }

    /**
     * {@inheritDoc}
     */
    public int buyItNow(Bid bid) {
	int bidID = -1;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;
	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection.prepareStatement(SQL_GET_SELLER_ID);
	    preparedStatement.setInt(1, bid.getItemID());
	    rs = preparedStatement.executeQuery();
	    if (rs.next()) {
		if (rs.getInt(SELLER_ID) == bid.getBidderID()) {
		    return ERROR_BIDDER_EQUALS_SELLER;
		}
	    } else {
		return ERROR_SQL;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (connection != null) {
		    connection.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	// check black list
	if (checkBlackList(bid)) {
	    return ERROR_BLACK_LIST;
	}
	CallableStatement statement = null;
	try {
	    connection = OracleDAOFactory.createConnection();
	    statement = connection.prepareCall(SQL_BUY_IT_NOW);
	    statement.setInt(1, bid.getItemID());
	    statement.setInt(2, bid.getBidderID());
	    statement.registerOutParameter(3, java.sql.Types.INTEGER);
	    statement.execute();
	    bidID = statement.getInt(3);

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (statement != null) {
		    statement.close();
		}
		if (connection != null) {
		    connection.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return bidID;
    }

    // Check user in black list.
    private boolean checkBlackList(Bid bid) {
	boolean inBlackList = false;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;
	int sellerID = -1;

	try {
	    connection = OracleDAOFactory.createConnection();
	    try {
		preparedStatement = connection
			.prepareStatement(SQL_GET_SELLER_ID);
		preparedStatement.setInt(1, bid.getItemID());
		rs = preparedStatement.executeQuery();
		if (rs.next()) {
		    sellerID = rs.getInt(1);
		} else {
		    throw new SQLException(EXCEPTION_SELLER_ID);
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    } finally {
		try {
		    if (preparedStatement != null) {
			preparedStatement.close();
		    }
		    if (rs != null) {
			rs.close();
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    try {
		if (sellerID >= 0) {
		    preparedStatement = connection
			    .prepareStatement(SQL_SELECT_FROM_BLACK_LIST);
		    preparedStatement.setInt(1, sellerID);
		    preparedStatement.setInt(2, bid.getBidderID());
		    rs = preparedStatement.executeQuery();
		    if (rs.next()) {
			inBlackList = true;
		    }
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    } finally {
		try {
		    if (preparedStatement != null) {
			preparedStatement.close();
		    }
		    if (rs != null) {
			rs.close();
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (connection != null) {
		    connection.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}

	return inBlackList;
    }

}
