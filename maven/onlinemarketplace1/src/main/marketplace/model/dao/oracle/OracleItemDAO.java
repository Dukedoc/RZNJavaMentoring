package marketplace.model.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import marketplace.model.dao.factory.OracleDAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.to.AdvancedSearchTO;
import marketplace.model.to.Category;
import marketplace.model.to.Item;
import marketplace.model.to.ItemDetails;

/**
 * Item DAO for Oracle
 * 
 * @author Roman_Ten
 * 
 */

public class OracleItemDAO implements ItemDAO {
    private static final String PERCENT = "%";
    private static final String BIDDER_COUNT = "BIDDER_COUNT";
    private static final String SQL_ADVANCED_SEARCH_BIDDER_COUNT = " AND BIDDER_COUNT = ?";
    private static final String SQL_ADVANCED_SEARCH_STOP_DATE = " AND STOP_DATE = ?";
    private static final String START_DATE = "START_DATE";
    private static final String SQL_ADVANCED_SEARCH_START_DATE = " AND START_DATE = ?";
    private static final String SQL_ADVANCED_SEARCH_BUY_IT_NOW = " AND BUY_IT_NOW = ?";
    private static final String MAX_PRICE2 = "MAX_PRICE2";
    private static final String MIN_PRICE2 = "MIN_PRICE2";
    private static final String SQL_ADVANCED_SEARCH_PRICE_BETWEEN = " AND ((BID IS NOT NULL AND BID BETWEEN ? AND ?) OR (BID IS NULL AND START_PRICE BETWEEN ? AND ?))";
    private static final String MAX_PRICE1 = "MAX_PRICE1";
    private static final String SQL_ADVANCED_SEARCH_MAX_PRICE = " AND (BID <= ?)";
    private static final String MIN_PRICE1 = "MIN_PRICE1";
    private static final String SQL_ADVANCED_SEARCH_MIN_PRICE = " AND (BID >= ?)";
    private static final String SQL_ADVANCED_SEARCH_DESCRIPTION = " AND LOWER(DESCRIPTION) LIKE LOWER(?)";
    private static final String SQL_ADVANCED_SEARCH_TITLE = " AND (LOWER(TITLE) LIKE LOWER(?))";
    private static final String EMPTY = "";
    private static final String SQL_ADVANCED_SEARCH_ITEM_ID = " AND (ITEM_ID = ?)";
    private static final String SQL_ADVANCED_SEARCH_BEGIN = "SELECT * FROM ITEM_DETAILS WHERE (BID=BEST_OFFER OR BEST_OFFER IS NULL)";
    private static final String BID = "BID";
    private static final String SQL_BIDS_HISTORY = "SELECT * FROM item_details WHERE ITEM_ID=? AND (BID IS NOT NULL)";
    private static final String SQL_SET_SEND_EMAIL = "UPDATE ITEMS SET SEND_EMAIL = 't' WHERE ITEM_ID = ?";
    private static final String SEND_EMAIL = "SEND_EMAIL";
    private static final String BIDDER_E_MAIL = "BIDDER_EMAIL";
    private static final String SELLER_E_MAIL = "SELLER_EMAIL";
    private static final String SQL_FIND_BY_CATEGORY = "SELECT * FROM ITEM_DETAILS WHERE LOWER(CATEGORY) LIKE LOWER(?)";
    private static final String LEVEL = "LEVEL";
    private static final String PARENT_CATEGORY_ID = "PARENT_CATEGORY_ID";
    private static final String SQL_CATEGORIES_LIST = "SELECT CATEGORY_ID, TITLE, PARENT_CATEGORY_ID, LEVEL FROM CATEGORIES START WITH parent_category_id IS NULL CONNECT BY PRIOR CATEGORY_ID=PARENT_CATEGORY_ID ORDER SIBLINGS BY TITLE";
    private static final String CATEGORY = "CATEGORY";
    private static final String FALSE = "f";
    private static final String TRUE = "t";
    private static final String SQL_EDIT_ITEM = "UPDATE ITEMS SET TITLE=?, DESCRIPTION=?, START_PRICE=?, TIME_LEFT=?, BUY_IT_NOW=?, BID_INCREMENT=?, CATEGORY_ID = ? WHERE ITEM_ID=?";
    private static final String CATEGORY_ID = "CATEGORY_ID";
    private static final String BIDDER_ID = "BIDDER_ID";
    private static final String SQL_SEARCH_BY_ITEM_ID = "SELECT * FROM ITEMS WHERE ITEM_ID = ?";
    private static final String STOP_DATE = "STOP_DATE";
    private static final String BIDDER = "BIDDER";
    private static final String BEST_OFFER = "BEST_OFFER";
    private static final String SELLER = "SELLER";
    /**
     * SQL for delete item by ITEM_ID.
     */
    private static final String SQL_DELETE = "DELETE FROM ITEMS WHERE ITEM_ID = ?";
    /**
     * Column Name.
     */
    private static final String BID_INCREMENT = "BID_INCREMENT";
    /**
     * Column Name.
     */
    private static final String BUY_IT_NOW = "BUY_IT_NOW";
    /**
     * Column Name.
     */
    private static final String START_BIDDING_DATE = "START_BIDDING_DATE";
    /**
     * Column Name.
     */
    private static final String TIME_LEFT = "TIME_LEFT";
    /**
     * Column Name.
     */
    private static final String START_PRICE = "START_PRICE";
    /**
     * Column Name.
     */
    private static final String DESCRIPTION = "DESCRIPTION";
    /**
     * Column Name.
     */
    private static final String TITLE = "TITLE";
    /**
     * Column Name.
     */
    private static final String SELLER_ID = "SELLER_ID";
    /**
     * Column Name.
     */
    private static final String ITEM_ID = "ITEM_ID";
    /**
     * Label for exception.
     */
    private static final String EXCEPTION_GENERATE_KEY = "Error generate key";
    /**
     * SQL for insert item.
     */
    private static final String SQL_INSERT_ADD_ITEM = "INSERT INTO ITEMS (SELLER_ID, TITLE, DESCRIPTION, START_PRICE, TIME_LEFT, START_BIDDING_DATE, BUY_IT_NOW, BID_INCREMENT, CATEGORY_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    /**
     * SQL for find by description.
     */
    private static final String SQL_SELECT_FIND_BY_DESCRIPTION = "SELECT * FROM ITEM_DETAILS WHERE LOWER(DESCRIPTION) LIKE LOWER(?) AND (BID=BEST_OFFER OR BEST_OFFER IS NULL)";
    /**
     * SQL for find by name.
     */
    private static final String SQL_SELECT_ITEMS_FIND_BY_NAME = "SELECT * FROM ITEM_DETAILS WHERE LOWER(TITLE) LIKE LOWER(?) AND (BID=BEST_OFFER OR BEST_OFFER IS NULL)";
    /**
     * sql for find item by item_id.
     */
    private static final String SQL_FIND_ITEM_BY_ID = "SELECT * FROM ITEM_DETAILS WHERE ITEM_ID = ? AND (BID=BEST_OFFER OR BEST_OFFER IS NULL)";
    /**
     * SQL select all items.
     */
    private static final String SQL_SELECT_ALL_ITEMS = "SELECT * FROM item_details WHERE BID=BEST_OFFER OR BEST_OFFER IS NULL";
    /**
     * Array with column name.
     */
    private static final String[] COL_ITEM_ID = { ITEM_ID };

    /**
     * {@inheritDoc}
     */
    public List<ItemDetails> getItemDetailsList() {
	Connection connection = null;
	Statement statement = null;
	List<ItemDetails> list = new ArrayList<ItemDetails>();

	try {
	    connection = OracleDAOFactory.createConnection();
	    statement = connection.createStatement();
	    ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_ITEMS);
	    while (rs.next()) {
		list.add(this.setItemDetails(rs));
	    }
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

	return list;
    }

    /**
     * {@inheritDoc}
     */
    public ItemDetails findItemDetailsByUID(int uid) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ItemDetails itemDetails = null;
	try {
	    conn = OracleDAOFactory.createConnection();
	    pstmt = conn.prepareStatement(SQL_FIND_ITEM_BY_ID);
	    pstmt.setInt(1, uid);
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
		itemDetails = this.setItemDetails(rs);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (pstmt != null) {
		    pstmt.close();
		}
		if (conn != null) {
		    conn.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}

	return itemDetails;
    }

    /**
     * {@inheritDoc}
     */
    public List<ItemDetails> findItemDetailsByName(String title) {
	List<ItemDetails> list = new ArrayList<ItemDetails>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection
		    .prepareStatement(SQL_SELECT_ITEMS_FIND_BY_NAME);
	    preparedStatement.setString(1, PERCENT + title + PERCENT);
	    ResultSet rs = preparedStatement.executeQuery();
	    while (rs.next()) {
		list.add(this.setItemDetails(rs));
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
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

	return list;
    }

    /**
     * {@inheritDoc}
     */
    public List<ItemDetails> findItemDetailsByDescription(String description) {
	List<ItemDetails> list = new ArrayList<ItemDetails>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection
		    .prepareStatement(SQL_SELECT_FIND_BY_DESCRIPTION);
	    preparedStatement.setString(1, PERCENT + description + PERCENT);
	    ResultSet rs = preparedStatement.executeQuery();
	    while (rs.next()) {
		list.add(this.setItemDetails(rs));
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
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

	return list;
    }

    /**
     * {@inheritDoc}
     */
    public int addItem(Item item) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	int itemID = -1;

	try {
	    connection = OracleDAOFactory.createConnection();
	    String sql = SQL_INSERT_ADD_ITEM;
	    preparedStatement = connection.prepareStatement(sql, COL_ITEM_ID);
	    preparedStatement.setInt(1, item.getSellerID());
	    preparedStatement.setString(2, item.getTitle());
	    preparedStatement.setString(3, item.getDescription());
	    preparedStatement.setFloat(4, item.getStartPrice());
	    preparedStatement.setDouble(5, item.getTimeLeft());
	    Timestamp date = new Timestamp(item.getStartBiddingDate()
		    .getTimeInMillis());
	    preparedStatement.setTimestamp(6, date);
	    preparedStatement.setString(7, item.isBuyItNow() ? TRUE : FALSE);
	    preparedStatement.setFloat(8, item.getBidIncrement());
	    preparedStatement.setInt(9, item.getCategoryID());
	    preparedStatement.executeUpdate();
	    ResultSet rs = preparedStatement.getGeneratedKeys();
	    if (rs.next()) {
		itemID = rs.getInt(1);
		item.setItemID(itemID);

	    } else {
		throw new SQLException(EXCEPTION_GENERATE_KEY);
	    }
	    connection.commit();

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
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

	return itemID;
    }

    /**
     * {@inheritDoc}
     */
    public int deleteItemByUID(int uid) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	int executeUpdate = 0;
	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection.prepareStatement(SQL_DELETE);
	    preparedStatement.setInt(1, uid);
	    executeUpdate = preparedStatement.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
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
	return executeUpdate;
    }

    /**
     * {@inheritDoc}
     */
    public Item findItemByUID(int uid) {
	Item item = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;
	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection
		    .prepareStatement(SQL_SEARCH_BY_ITEM_ID);
	    preparedStatement.setInt(1, uid);
	    rs = preparedStatement.executeQuery();
	    if (rs.next()) {
		item = setItem(rs);
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
	return item;
    }

    /**
     * {@inheritDoc}
     */
    public void editItem(int itemID, Item item) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection.prepareStatement(SQL_EDIT_ITEM);
	    preparedStatement.setString(1, item.getTitle());
	    preparedStatement.setString(2, item.getDescription());
	    preparedStatement.setFloat(3, item.getStartPrice());
	    preparedStatement.setDouble(4, item.getTimeLeft());
	    preparedStatement.setString(5, item.isBuyItNow() ? TRUE : FALSE);
	    preparedStatement.setFloat(6, item.getBidIncrement());
	    preparedStatement.setInt(7, item.getCategoryID());
	    preparedStatement.setInt(8, itemID);
	    preparedStatement.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
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
    public List<Category> getCategoriesList() {
	List<Category> list = new ArrayList<Category>();
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	try {
	    connection = OracleDAOFactory.createConnection();
	    statement = connection.createStatement();
	    rs = statement.executeQuery(SQL_CATEGORIES_LIST);
	    while (rs.next()) {
		list.add(setCategory(rs));
	    }
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
	return list;
    }

    // Create new category and insert fields from result set.
    private Category setCategory(ResultSet rs) throws SQLException {
	Category category = new Category();
	int level = rs.getInt(LEVEL);
	category.setCategoryID(rs.getInt(CATEGORY_ID));
	String title = rs.getString(TITLE).trim();
	String indent = EMPTY;
	String tab = " | ";
	for (int i = 1; i < level; i++) {
	    indent += tab;
	}
	category.setTitle(indent + title);
	category.setParentCategoryID(rs.getInt(PARENT_CATEGORY_ID));
	return category;
    }

    /**
     * {@inheritDoc}
     */
    public List<ItemDetails> findItemsByCategory(String category) {
	List<ItemDetails> list = new ArrayList<ItemDetails>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;

	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection
		    .prepareStatement(SQL_FIND_BY_CATEGORY);
	    preparedStatement.setString(1, PERCENT + category + PERCENT);
	    rs = preparedStatement.executeQuery();
	    while (rs.next()) {
		list.add(setItemDetails(rs));
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

	return list;
    }

    /**
     * {@inheritDoc}
     */
    public void setSendEmail(int itemID) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	try {
	    connection = OracleDAOFactory.createConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(SQL_SET_SEND_EMAIL);
	    preparedStatement.setInt(1, itemID);
	    preparedStatement.executeUpdate();
	    connection.commit();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
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
    public List<ItemDetails> getBidsHistoryList(int itemID) {
	List<ItemDetails> list = new ArrayList<ItemDetails>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;

	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection.prepareStatement(SQL_BIDS_HISTORY);
	    preparedStatement.setInt(1, itemID);
	    rs = preparedStatement.executeQuery();
	    while (rs.next()) {
		list.add(setItemDetails(rs));
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
	return list;
    }

    public List<ItemDetails> advancedSearch(AdvancedSearchTO advancedSearchTO) {
	List<ItemDetails> itemDetailsList = new ArrayList<ItemDetails>();
	Map<String, Integer> fields = new HashMap<String, Integer>();
	int fieldNumber = 0;
	StringBuilder sql = new StringBuilder(
		SQL_ADVANCED_SEARCH_BEGIN);
	if (advancedSearchTO.getItemID() != null) {
	    sql.append(SQL_ADVANCED_SEARCH_ITEM_ID);
	    fields.put(ITEM_ID, ++fieldNumber);
	}
	if ((advancedSearchTO.getTitle() != null)
		&& (!advancedSearchTO.getTitle().equals(EMPTY))) {
	    sql.append(SQL_ADVANCED_SEARCH_TITLE);
	    fields.put(TITLE, ++fieldNumber);
	}
	if ((advancedSearchTO.getDescription() != null)
		&& !advancedSearchTO.getDescription().equals(EMPTY)) {
	    sql.append(SQL_ADVANCED_SEARCH_DESCRIPTION);
	    fields.put(DESCRIPTION, ++fieldNumber);
	}
	if (advancedSearchTO.getMinPrice() != null
		&& advancedSearchTO.getMaxPrice() == null) {
	    sql.append(SQL_ADVANCED_SEARCH_MIN_PRICE);
	    fields.put(MIN_PRICE1, ++fieldNumber);
	}

	if (advancedSearchTO.getMinPrice() == null
		&& advancedSearchTO.getMaxPrice() != null) {
	    sql.append(SQL_ADVANCED_SEARCH_MAX_PRICE);
	    fields.put(MAX_PRICE1, ++fieldNumber);
	}

	if (advancedSearchTO.getMinPrice() != null
		&& advancedSearchTO.getMaxPrice() != null) {
	    sql
		    .append(SQL_ADVANCED_SEARCH_PRICE_BETWEEN);
	    fields.put(MIN_PRICE1, ++fieldNumber);
	    fields.put(MAX_PRICE1, ++fieldNumber);
	    fields.put(MIN_PRICE2, ++fieldNumber);
	    fields.put(MAX_PRICE2, ++fieldNumber);
	}
	if (advancedSearchTO.isBuyItNow()) {
	    sql.append(SQL_ADVANCED_SEARCH_BUY_IT_NOW);
	    fields.put(BUY_IT_NOW, ++fieldNumber);
	}
	if (advancedSearchTO.getStartDate() != null) {
	    sql.append(SQL_ADVANCED_SEARCH_START_DATE);
	    fields.put(START_DATE, ++fieldNumber);
	}
	if (advancedSearchTO.getStopDate() != null) {
	    sql.append(SQL_ADVANCED_SEARCH_STOP_DATE);
	    fields.put(STOP_DATE, ++fieldNumber);
	}
	if (advancedSearchTO.getBidderCount() != null) {
	    sql.append(SQL_ADVANCED_SEARCH_BIDDER_COUNT);
	    fields.put(BIDDER_COUNT, ++fieldNumber);
	}

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;

	try {
	    connection = OracleDAOFactory.createConnection();
	    preparedStatement = connection.prepareStatement(sql.toString());
	    if (advancedSearchTO.getItemID() != null) {
		preparedStatement.setInt(fields.get(ITEM_ID), advancedSearchTO
			.getItemID());
	    }
	    if ((advancedSearchTO.getTitle() != null)
		    && (!advancedSearchTO.getTitle().equals(EMPTY))) {
		preparedStatement.setString(fields.get(TITLE), PERCENT
			+ advancedSearchTO.getTitle() + PERCENT);
	    }
	    if ((advancedSearchTO.getDescription() != null)
		    && (!advancedSearchTO.getDescription().equals(EMPTY))) {
		preparedStatement.setString(fields.get(DESCRIPTION), PERCENT
			+ advancedSearchTO.getDescription() + PERCENT);
	    }

	    if (advancedSearchTO.getMinPrice() != null
		    && advancedSearchTO.getMaxPrice() == null) {
		preparedStatement.setFloat(fields.get(MIN_PRICE1),
			advancedSearchTO.getMinPrice());
	    }

	    if (advancedSearchTO.getMinPrice() == null
		    && advancedSearchTO.getMaxPrice() != null) {
		preparedStatement.setFloat(fields.get(MAX_PRICE1),
			advancedSearchTO.getMaxPrice());
	    }

	    if ((advancedSearchTO.getMinPrice() != null)
		    && (advancedSearchTO.getMaxPrice() != null)) {
		preparedStatement.setFloat(fields.get(MIN_PRICE1),
			advancedSearchTO.getMinPrice());
		preparedStatement.setFloat(fields.get(MAX_PRICE1),
			advancedSearchTO.getMaxPrice());
		preparedStatement.setFloat(fields.get(MIN_PRICE2),
			advancedSearchTO.getMinPrice());
		preparedStatement.setFloat(fields.get(MAX_PRICE2),
			advancedSearchTO.getMaxPrice());
	    }

	    if (advancedSearchTO.isBuyItNow()) {
		preparedStatement.setString(fields.get(BUY_IT_NOW), TRUE);
	    }

	    if (advancedSearchTO.getStartDate() != null) {
		Timestamp timestamp = new Timestamp(advancedSearchTO
			.getStartDate().getTimeInMillis());
		preparedStatement.setTimestamp(fields.get(START_DATE),
			timestamp);
	    }
	    if (advancedSearchTO.getStopDate() != null) {
		Timestamp timestamp = new Timestamp(advancedSearchTO
			.getStopDate().getTimeInMillis());
		preparedStatement.setTimestamp(fields.get("STOP_DATE"),
			timestamp);
	    }
	    if (advancedSearchTO.getBidderCount() != null) {
		preparedStatement.setInt(fields.get(BIDDER_COUNT),
			advancedSearchTO.getBidderCount());
	    }
	    rs = preparedStatement.executeQuery();
	    while (rs.next()) {
		itemDetailsList.add(setItemDetails(rs));
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
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

	return itemDetailsList;
    }

    // Create new ItemDetails and set attributes from db.
    private ItemDetails setItemDetails(ResultSet rs) throws SQLException {
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
	Calendar currentDate = Calendar.getInstance();
	if (itemDetails.getStopDate() != null) {
	    if (itemDetails.getStopDate().getTimeInMillis() < currentDate
		    .getTimeInMillis()) {
		itemDetails.setStatus("NOT FOR SELL");
	    }
	}

	return itemDetails;
    }

    // Create new Item and set attributes from db.
    private Item setItem(ResultSet rs) throws SQLException {
	Item item = new Item();
	item.setItemID(rs.getInt(ITEM_ID));
	item.setSellerID(rs.getInt(SELLER_ID));
	item.setTitle(rs.getString(TITLE));
	item.setDescription(rs.getString(DESCRIPTION));
	item.setStartPrice(rs.getFloat(START_PRICE));
	item.setTimeLeft(rs.getFloat(TIME_LEFT));
	Calendar startBiddingDate = Calendar.getInstance();
	startBiddingDate.setTime(rs.getTimestamp(START_BIDDING_DATE));
	item.setStartBiddingDate(startBiddingDate);
	item
		.setBuyItNow((rs.getString(BUY_IT_NOW).toLowerCase()
			.equals(TRUE)) ? true : false);
	item.setBidIncrement(rs.getFloat(BID_INCREMENT));
	item.setCategoryID(rs.getInt(CATEGORY_ID));
	return item;
    }

}
