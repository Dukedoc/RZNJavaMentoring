package marketplace.model.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import marketplace.model.dao.interfaces.BidDAO;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.dao.oracle.OracleBidDAO;
import marketplace.model.dao.oracle.OracleItemDAO;
import marketplace.model.dao.oracle.OracleUserDAO;

/**
 * DAO Factory for Oracle.
 * 
 * @author Roman_Ten
 * 
 */
public class OracleDAOFactory extends DAOFactory {
    private static final String JDBC_MYORACLE = "java:comp/env/jdbc/myoracle";

    /**
     * Create connection pool.
     * 
     * @return connection is Connection.
     * @throws SQLException
     *             An exception that provides information on a database access
     *             error or other errors.
     */
    public static synchronized Connection createConnection()
	    throws SQLException {
	Context initContext;
	Connection conn = null;
	Locale.setDefault(Locale.ENGLISH);
	try {
	    initContext = new InitialContext();
	    DataSource ds = (DataSource) initContext.lookup(JDBC_MYORACLE);
	    conn = ds.getConnection();
	} catch (NamingException e) {
	    e.printStackTrace();
	}
	return conn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BidDAO getBidDAO() {
	return new OracleBidDAO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemDAO getItemDAO() {
	return new OracleItemDAO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDAO getUserDAO() {
	return new OracleUserDAO();
    }

}
