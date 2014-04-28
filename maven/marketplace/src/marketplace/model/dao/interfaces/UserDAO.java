package marketplace.model.dao.interfaces;

import java.util.List;

import marketplace.model.to.ItemDetails;
import marketplace.model.to.User;

/**
 * Data Access Object for User.
 * 
 * @author Roman_Ten
 * 
 */
public interface UserDAO {
    /**
     * Return value for sql error.
     */
    int SQL_ERROR = -1;
    /**
     * Return value for login exists.
     */
    int LOGIN_EXIST = -2;

    /**
     * Get User Transfer Object by Login.
     * 
     * @param login
     *            user login.
     * @return user.
     */
    User getUserByLogin(String login);

    /**
     * Get user's item list.
     * 
     * @param user
     *            user transfer object.
     * @return user's item list.
     */
    List<ItemDetails> getUserItems(User user);

    /**
     * Add user to db.
     * 
     * @param user
     *            is User TO
     * @return USER_ID or -1 if error. -2 if login exist.
     */
    int addUser(User user);

    /**
     * Add user to blacklist.
     * 
     * @param sellerID
     *            the seller ID.
     * @param bidderID
     *            the bidder ID.
     * @return 1 if successful or 0 for error.
     */
    int addToBlackList(int sellerID, int bidderID);

    /**
     * Get user's black List.
     * 
     * @param user
     *            is User TO.
     * @return user's black list.
     */
    List<User> getBlackList(User user);

    /**
     * Remove from blackList.
     * 
     * @param sellerID
     *            the Seller ID.
     * @param bidderID
     *            the Bidder ID.
     */
    void removeFromBlackList(int sellerID, int bidderID);
}
