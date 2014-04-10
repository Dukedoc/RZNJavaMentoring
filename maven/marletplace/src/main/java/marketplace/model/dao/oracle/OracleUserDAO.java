package marketplace.model.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import marketplace.model.dao.factory.OracleDAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.ItemDetails;
import marketplace.model.to.User;

/**
 * User Data Access Object for Oracle.
 * 
 * @author Roman_Ten
 * 
 */
public class OracleUserDAO implements UserDAO {
    private static final String INCORRECT_USER_ID = "Incorrect userID";
    private static final String SQL_USER_NEXT_ID = "SELECT USERS_SEQ.NEXTVAL FROM DUAL";
    private static final String TIME_IS_UP_STATUS = "Time is up";
    private static final String SOLD_STATUS = "Sold";
    private static final String BID_STATUS = "Bid";
    private static final String SQL_REMOVE_FROM_BLACK_LIST = "DELETE FROM BLACK_LIST WHERE SELLER_ID=? AND BIDDER_ID = ?";
    private static final String SQL_USER_BLACK_LIST = "SELECT user_id, FULL_NAME FROM BLACK_LIST bl INNER JOIN USERS u ON bl.BIDDER_ID=u.USER_ID WHERE bl.SELLER_ID = ?";
    private static final String SQL_ADD_TO_BLACK_LIST = "INSERT INTO black_list (SELLER_ID, BIDDER_ID)"
	    + " SELECT ?,? FROM DUAL"
	    + " WHERE NOT EXISTS (SELECT * FROM BLACK_LIST WHERE SELLER_ID = ? AND BIDDER_ID = ?)";
    private static final String BID = "BID";
    private static final String SELLER_E_MAIL = "SELLER_EMAIL";
    private static final String BIDDER_E_MAIL = "BIDDER_EMAIL";
    private static final String E_MAIL = "EMAIL";
    private static final String PHONE = "PHONE";
    private static final String SQL_ADD_USER = "MERGE INTO USERS u USING DUAL ON (u.login = ?) WHEN NOT MATCHED THEN INSERT (USER_ID, FULL_NAME, BILLING_ADDRESS, PHONE, EMAIL, LOGIN, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?)";;
    private static final String STOP_DATE = "STOP_DATE";
    private static final String BIDDER = "BIDDER";
    private static final String BEST_OFFER = "BEST_OFFER";
    private static final String SELLER = "SELLER";
    private static final String PASSWORD = "PASSWORD";
    private static final String LOGIN = "LOGIN";
    private static final String BILLING_ADDRESS = "BILLING_ADDRESS";
    private static final String FULL_NAME = "FULL_NAME";
    private static final String USER_ID = "USER_ID";
    private static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM USERS WHERE LOWER(LOGIN) = LOWER(?)";
    private static final String ITEM_ID = "ITEM_ID";
    private static final String TITLE = "TITLE";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String START_PRICE = "START_PRICE";
    private static final String BID_INCREMENT = "BID_INCREMENT";
    private static final String BUY_IT_NOW = "BUY_IT_NOW";
    private static final String CATEGORY = "CATEGORY";
    private static final String SELLER_ID = "SELLER_ID";
    private static final String BIDDER_ID = "BIDDER_ID";
    private static final Object TRUE = "t";
    private static final String SEND_EMAIL = "SEND_EMAIL";
    private static final String SQL_GET_USER_ITEMS = "SELECT * "
	    + " FROM ITEM_DETAILS "
	    + "WHERE ITEM_ID IN (SELECT DISTINCT ITEM_ID FROM "
	    + "ITEM_DETAILS WHERE BIDDER_ID=? OR SELLER_ID=?"
	    + " ) AND (BID=BEST_OFFER OR BEST_OFFER IS NULL)";

    /**
     * {@inheritDoc}
     */
    public User getUserByLogin(String login) {
	User user = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;

	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection
		    .prepareStatement(SQL_GET_USER_BY_LOGIN);
	    preparedStatement.setString(1, login);
	    rs = preparedStatement.executeQuery();
	    if (rs.next()) {
		user = setUserFromResultSet(rs);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
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

	return user;
    }

    // create user and insert fields from result set.
    private User setUserFromResultSet(ResultSet rs) throws SQLException {
	User user = new User();
	user.setUserID(rs.getInt(USER_ID));
	user.setFullName(rs.getString(FULL_NAME));
	user.setBillingAddress(rs.getString(BILLING_ADDRESS));
	user.setEmail(rs.getString(E_MAIL));
	user.setPhone(rs.getString(PHONE));
	user.setLogin(rs.getString(LOGIN));
	user.setPassword(rs.getString(PASSWORD));
	return user;
    }

    /**
     * {@inheritDoc}
     */
    public List<ItemDetails> getUserItems(User user) {
	List<ItemDetails> itemDetailsList = new ArrayList<ItemDetails>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;
	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection.prepareStatement(SQL_GET_USER_ITEMS);
	    preparedStatement.setInt(1, user.getUserID());
	    preparedStatement.setInt(2, user.getUserID());
	    rs = preparedStatement.executeQuery();
	    while (rs.next()) {
		ItemDetails itemDetails = insertItemDetails(rs);
		if (itemDetails.getSellerID() != user.getUserID()) {
		    itemDetails.setStatus(BID_STATUS);
		}
		if (itemDetails.getStopDate() != null) {
		    if (itemDetails.getStopDate().getTimeInMillis() < System
			    .currentTimeMillis()) {
			if (itemDetails.getBidderID() == user.getUserID()
				|| itemDetails.getSellerID() == user
					.getUserID()) {
			    itemDetails.setStatus(SOLD_STATUS);
			}
			if (itemDetails.getBid() == 0) {
			    itemDetails.setStatus(TIME_IS_UP_STATUS);
			}
		    }
		}
		itemDetailsList.add(itemDetails);

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
	return itemDetailsList;
    }

    /**
     * {@inheritDoc}
     */
    public int addToBlackList(int sellerID, int bidderID) {
	int errorCode = 0;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;

	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection
		    .prepareStatement(SQL_ADD_TO_BLACK_LIST);
	    preparedStatement.setInt(1, sellerID);
	    preparedStatement.setInt(2, bidderID);
	    preparedStatement.setInt(3, sellerID);
	    preparedStatement.setInt(4, bidderID);
	    errorCode = preparedStatement.executeUpdate();
	    connection.commit();
	} catch (SQLException e) {
	    e.printStackTrace();
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
	return errorCode;

    }

    /**
     * {@inheritDoc}
     */
    public int addUser(User user) {
	Connection connection = null;
	PreparedStatement prepareStatement = null;
	Statement statement = null;
	ResultSet rs = null;
	int userID = 0;
	int rowsInserted = 0;
	try {
	    connection = OracleDAOFactory.createConnection();
	    statement = connection.createStatement();
	    rs = statement.executeQuery(SQL_USER_NEXT_ID);
	    if (rs.next()) {
		userID = rs.getInt(1);
	    } else {
		throw new SQLException(INCORRECT_USER_ID);
	    }
	    prepareStatement = connection.prepareStatement(SQL_ADD_USER);
	    prepareStatement.setString(1, user.getLogin());
	    prepareStatement.setInt(2, userID);
	    prepareStatement.setString(3, user.getFullName());
	    prepareStatement.setString(4, user.getBillingAddress());
	    prepareStatement.setString(5, user.getPhone());
	    prepareStatement.setString(6, user.getEmail());
	    prepareStatement.setString(7, user.getLogin());
	    prepareStatement.setString(8, user.getPassword());
	    rowsInserted = prepareStatement.executeUpdate();
	    if (rowsInserted == 0) {
		return LOGIN_EXIST;
	    } else {
		user.setUserID(userID);
	    }
	} catch (SQLException e) {
	    userID = SQL_ERROR;
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		e1.printStackTrace();
	    }
	    e.printStackTrace();
	} finally {
	    try {
		if (rs != null) {
		    rs.close();
		}
		if (statement != null) {
		    statement.close();
		}
		if (prepareStatement != null) {
		    prepareStatement.close();
		}
		if (connection != null) {
		    connection.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return userID;

    }

    /**
     * {@inheritDoc}
     */
    public List<User> getBlackList(User user) {
	List<User> list = new ArrayList<User>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;

	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection
		    .prepareStatement(SQL_USER_BLACK_LIST);
	    preparedStatement.setInt(1, user.getUserID());
	    rs = preparedStatement.executeQuery();
	    while (rs.next()) {
		User bidder = new User();
		bidder.setUserID(rs.getInt(USER_ID));
		bidder.setFullName(rs.getString(FULL_NAME));
		list.add(bidder);
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

	return list;
    }

    /**
     * {@inheritDoc}
     */
    public void removeFromBlackList(int sellerID, int bidderID) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection
		    .prepareStatement(SQL_REMOVE_FROM_BLACK_LIST);
	    preparedStatement.setInt(1, sellerID);
	    preparedStatement.setInt(2, bidderID);
	    preparedStatement.executeUpdate();
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
    }

    // Create new ItemDetails and insert field from result set.
    private ItemDetails insertItemDetails(ResultSet rs) throws SQLException {
	ItemDetails itemDetails = new ItemDetails();
	itemDetails.setItemID(rs.getInt(ITEM_ID));
	itemDetails.setTitle(rs.getString(TITLE));
	itemDetails.setDescription(rs.getString(DESCRIPTION));
	itemDetails.setSellerID(rs.getInt(SELLER_ID));
	itemDetails.setSeller(rs.getString(SELLER));
	itemDetails.setSellerEmail(rs.getString(SELLER_E_MAIL));
	itemDetails.setStartPrice(rs.getFloat(START_PRICE));
	itemDetails.setBidIncrement(rs.getFloat(BID_INCREMENT));
	itemDetails.setBid(rs.getFloat(BID));
	itemDetails.setBestOffer(rs.getFloat(BEST_OFFER));
	itemDetails.setBidderID(rs.getInt(BIDDER_ID));
	itemDetails.setBidder(rs.getString(BIDDER));
	itemDetails.setBidderEmail(rs.getString(BIDDER_E_MAIL));
	if (rs.getDate(STOP_DATE) != null) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(rs.getTimestamp(STOP_DATE));
	    itemDetails.setStopDate(calendar);
	} else {
	    itemDetails.setStopDate(null);
	}

	itemDetails.setBuyItNow((rs.getString(BUY_IT_NOW).toLowerCase()
		.equals(TRUE)) ? true : false);
	itemDetails.setCategory(rs.getString(CATEGORY));
	itemDetails.setSendEmail(rs.getString(SEND_EMAIL).toLowerCase().equals(
		TRUE) ? true : false);
	return itemDetails;
    }

}
