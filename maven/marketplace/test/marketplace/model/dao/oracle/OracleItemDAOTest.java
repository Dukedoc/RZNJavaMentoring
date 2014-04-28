/**
 *
 */
package marketplace.model.dao.oracle;

import java.sql.*;
import java.util.Calendar;

import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.factory.OracleDAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.to.Item;

import junit.framework.TestCase;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * JUnit test for OracleItemDAO
 *
 * @author Roman_Ten
 */
public class OracleItemDAOTest extends TestCase {
    /**
     * The message for exception
     */
    private static final String ERROR_GENERATE_KEY = "Error generate key";
    /**
     * SQL delete item by title and description
     */
    private static final String SQL_DELETE = "DELETE FROM ITEMS WHERE (TITLE=?) AND (DESCRIPTION = ?)";
    /**
     * SQL insert item
     */
    private static final String SQL_INSERT = "INSERT INTO ITEMS (SELLER_ID, TITLE, DESCRIPTION, START_PRICE, TIME_LEFT, START_BIDDING_DATE, BUY_IT_NOW, BID_INCREMENT, CATEGORY_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    /**
     * seller id for test item1
     */
    private static final int SELLER_ID_1 = 1;
    /**
     * title for test item1
     */
    private static final String TITLE_1 = "Bumazhnaya salffetka";
    /**
     * description for test item1
     */
    private static final String DESCRIPTION_1 = "Magkaya, pushistaya. Vam ponravitsya";
    /**
     * start price for test item1
     */
    private static final float START_PRICE_1 = 5;
    /**
     * time left for test item1
     */
    private static final int TIME_LEFT_1 = 10;
    /**
     * start bidding day for test item1
     */
    private static final int START_BIDDING_DATE_DAY_1 = 1;
    /**
     * start bidding month for test item1
     */
    private static final int START_BIDDING_DATE_MONTH_1 = 3;
    /**
     * start bidding year for test item1
     */
    private static final int START_BIDDING_DATE_YEAR_1 = 2012;
    /**
     * buy it now for item1
     */
    private static final boolean BUY_IT_NOW_1 = false;
    /**
     * bid increment for item1
     */
    private static final float BID_INCREMENT_1 = 1;
    /**
     * seller id for test item2
     */
    private static final int SELLER_ID_2 = 2;
    /**
     * title for test item2
     */
    private static final String TITLE_2 = "shkaf";
    /**
     * description for test item2
     */
    private static final String DESCRIPTION_2 = "Bolshoy i vmestitelniy";
    /**
     * start price for test item2
     */
    private static final float START_PRICE_2 = 100;
    /**
     * time left for test item2
     */
    private static final int TIME_LEFT_2 = 40;
    /**
     * Start bidding day for test item2
     */
    private static final int START_BIDDING_DATE_DAY_2 = 1;
    /**
     * Start bidding month for test item2
     */
    private static final int START_BIDDING_DATE_MONTH_2 = 3;
    /**
     * Start bidding year for test item2
     */
    private static final int START_BIDDING_DATE_YEAR_2 = 2012;
    /**
     * buy it now for test item2
     */
    private static final boolean BUY_IT_NOW_2 = true;
    /**
     * bid increment for test item2
     */
    private static final float BID_INCREMENT_2 = 20;

    /**
     * array with column name
     */
    private static final String[] COL_ITEM_ID = {"Item_ID"};

    private DAOFactory oracleFactory = DAOFactory
            .getDAOFactory(DAOFactory.ORACLE);
    private ItemDAO itemDAO = oracleFactory.getItemDAO();

    private Item item1 = null;
    private Item item2 = null;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        SimpleNamingContextBuilder builder = null;
        OracleConnectionPoolDataSource ds = new OracleConnectionPoolDataSource();
        ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
        ds.setUser("marketplace");
        ds.setPassword("marketplace");
        builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
        builder.bind("java:comp/env/jdbc/myoracle", ds);
        connection = OracleDAOFactory.createConnection();
        item1 = new Item();
        item1.setSellerID(SELLER_ID_1);
        item1.setTitle(TITLE_1);
        item1.setDescription(DESCRIPTION_1);
        item1.setStartPrice(START_PRICE_1);
        item1.setTimeLeft(TIME_LEFT_1);
        Calendar startBiddingDate1 = Calendar.getInstance();
        startBiddingDate1.set(START_BIDDING_DATE_YEAR_1,
                START_BIDDING_DATE_MONTH_1 - 1, START_BIDDING_DATE_DAY_1);
        item1.setStartBiddingDate(startBiddingDate1);
        item1.setBuyItNow(BUY_IT_NOW_1);
        item1.setBidIncrement(BID_INCREMENT_1);
        item1.setCategoryID(1);

        item2 = new Item();
        item2.setSellerID(SELLER_ID_2);
        item2.setTitle(TITLE_2);
        item2.setDescription(DESCRIPTION_2);
        item2.setStartPrice(START_PRICE_2);
        item2.setTimeLeft(TIME_LEFT_2);
        Calendar startBiddingDate2 = Calendar.getInstance();
        startBiddingDate2.set(START_BIDDING_DATE_YEAR_2,
                START_BIDDING_DATE_MONTH_2 - 1, START_BIDDING_DATE_DAY_2);
        item2.setStartBiddingDate(startBiddingDate2);
        item2.setBuyItNow(BUY_IT_NOW_2);
        item2.setBidIncrement(BID_INCREMENT_2);
        item2.setCategoryID(2);

    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        item1 = null;
        item2 = null;
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Test for findItemByUID(int)
     *
     * @throws Exception
     * @see OracleItemDAO#findItemByUID(int)
     */
    public void testFindItemByUID() throws Exception {
        insertItem(item1);
        insertItem(item2);
        Item testItem1 = itemDAO.findItemByUID(item1.getItemID());
        Item testItem2 = itemDAO.findItemByUID(item2.getItemID());
        assertEquals(testItem1, item1);
        assertEquals(testItem2, item2);
        deleteItems();
    }

    /**
     * Test for addItem(Item)
     *
     * @throws java.sql.SQLException
     * @see OracleItemDAO#addItem(marketplace.model.to.Item)
     */
    public void testAddItem() throws SQLException {
        int oldCount = 0;

        String sql = "SELECT COUNT(*) FROM ITEMS WHERE " + "(SELLER_ID = ?)"
                + "AND (TITLE = ?)"

                + "AND (DESCRIPTION = ?)" + "AND (START_PRICE = ? )"
                + "AND (TIME_LEFT = ?) " + " AND (BUY_IT_NOW = ?) AND " + "(BID_INCREMENT = ?) AND (CATEGORY_ID = ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, item1.getSellerID());
        preparedStatement.setString(2, item1.getTitle());
        preparedStatement.setString(3, item1.getDescription());
        preparedStatement.setFloat(4, item1.getStartPrice());
        preparedStatement.setFloat(5, item1.getTimeLeft());
        if (item1.isBuyItNow()) {
            preparedStatement.setString(6, "t");
        } else {
            preparedStatement.setString(6, "f");
        }
        preparedStatement.setFloat(7, item1.getBidIncrement());
        preparedStatement.setInt(8, item1.getCategoryID());

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            oldCount = rs.getInt(1);
        }
        System.out.println(oldCount);
        itemDAO.addItem(item1);
        rs = preparedStatement.executeQuery();
        int newCount = oldCount;
        while (rs.next()) {
            newCount = rs.getInt(1);
        }
        assertTrue(oldCount == newCount - 1);
        deleteItems();
    }

    /**
     * Test for deleteItemByUID(int)
     *
     * @throws SQLException
     * @see OracleItemDAO#deleteItemByUID(int)
     */
    public void testDeleteItemByUID() throws SQLException {
        insertItem(item1);
        String sql = "SELECT * FROM ITEMS WHERE ITEM_ID = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, item1.getItemID());
        ResultSet rs = preparedStatement.executeQuery();
        assertTrue(rs.next());
        itemDAO.deleteItemByUID(item1.getItemID());
        preparedStatement.setInt(1, item1.getItemID());
        rs = preparedStatement.executeQuery();
        assertFalse(rs.next());
    }

    // Insert item in Data Base for test
    private void insertItem(Item item) throws SQLException {
        preparedStatement = connection
                .prepareStatement(SQL_INSERT, COL_ITEM_ID);
        preparedStatement.setInt(1, item.getSellerID());
        preparedStatement.setString(2, item.getTitle());
        preparedStatement.setString(3, item.getDescription());
        preparedStatement.setFloat(4, item.getStartPrice());
        preparedStatement.setDouble(5, item.getTimeLeft());
        preparedStatement.setTimestamp(6, new Timestamp(item.getStartBiddingDate().getTimeInMillis()));
        preparedStatement.setString(7, item.isBuyItNow() ? "t" : "f");
        preparedStatement.setFloat(8, item.getBidIncrement());
        preparedStatement.setInt(9, item.getCategoryID());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            item.setItemID(rs.getInt(1));
        } else {
            throw new SQLException(ERROR_GENERATE_KEY);
        }
        rs.close();
        preparedStatement.close();
        connection.commit();
    }


    // Delete test items from data base. Test Items defined by titles and
    // descriptions.
    private void deleteItems() throws SQLException {
        preparedStatement = connection.prepareStatement(SQL_DELETE);
        preparedStatement.setString(1, TITLE_1);
        preparedStatement.setString(2, DESCRIPTION_1);
        preparedStatement.addBatch();
        preparedStatement.setString(1, TITLE_2);
        preparedStatement.setString(2, DESCRIPTION_2);
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
        preparedStatement.close();
        connection.commit();
    }
}
