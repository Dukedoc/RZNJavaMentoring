package marketplace.model.dao.factory;

import marketplace.model.dao.interfaces.BidDAO;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.dao.interfaces.UserDAO;

/**
 * Abstract class for DAOFactory.
 * 
 * @author Roman_Ten
 * 
 */
public abstract class DAOFactory {
    /**
     * The Oracle factory.
     */
    public static final int ORACLE = 1;

    /**
     * Create Data Access Object for Bid.
     * 
     * @return BidDAO is Data Access Object for Bid.
     */
    public abstract BidDAO getBidDAO();

    /**
     * Create Data Access Object for Item.
     * 
     * @return ItemDAO is Data Access Object for Item.
     */
    public abstract ItemDAO getItemDAO();

    /**
     * Create Data Access Object for User.
     * 
     * @return UserDAO is Data Access Object for User.
     */
    public abstract UserDAO getUserDAO();

    /**
     * Create DAOFactory.
     * 
     * @param whichFactory
     *            is name factory
     * @return DAOfactory is DAO Factory
     */
    public static DAOFactory getDAOFactory(int whichFactory) {
	switch (whichFactory) {
	case ORACLE:
	    return new OracleDAOFactory();
	default:
	    return null;
	}
    }
}
