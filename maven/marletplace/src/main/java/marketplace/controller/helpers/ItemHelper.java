package marketplace.controller.helpers;

import java.util.List;

import marketplace.model.exception.ValidatorException;
import marketplace.model.to.Category;
import marketplace.model.to.Item;
import marketplace.model.to.ItemDetails;

/**
 * Interface for Item Helper.
 * 
 * @author Roman_Ten
 * 
 */
public interface ItemHelper {

    /**
     * Set item list to request.
     * 
     * @param itemList
     *            the item list.
     */
    void setItemList(List<ItemDetails> itemList);

    /**
     * Get title for request.
     * 
     * @return title.
     */
    String getTitle();

    /**
     * Get description.
     * 
     * @return description.
     */
    String getDescription();

    /**
     * Get Start Price.
     * 
     * @return start price.
     * @throws ValidatorException
     *             if start price is not valid.
     */

    float getStartPrice() throws ValidatorException;

    /**
     * Get time left.
     * 
     * @return time left if time left is not valid.
     * @throws ValidatorException
     *             if time left is not valid.
     */
    float getTimeLeft() throws ValidatorException;

    /**
     * Get buy it now value.
     * 
     * @return buy it now.
     */
    boolean isBuyItNow();

    /**
     * Get Bid Increment.
     * 
     * @return bid increment.
     * @throws ValidatorException
     *             if bid increment is not valid.
     */
    float getBidIncrement() throws ValidatorException;

    /**
     * Get itemID from parameters.
     * 
     * @return the itemID.
     */
    int getItemID();

    /**
     * Set item.
     * 
     * @param item
     *            the item transfer object.
     */
    void setItem(Item item);

    /**
     * Set Categories List.
     * 
     * @param categoriesList
     *            the categories list.
     */
    void setCategoriesList(List<Category> categoriesList);

    /**
     * Get category ID.
     * 
     * @return the category ID.
     */
    int getCategoryID();

    /**
     * Get List Number.
     * 
     * @return list number.
     */
    int getListNumber();

    /**
     * Set page Count.
     * 
     * @param pageCount
     *            the page count.
     */
    void setPageCount(int pageCount);

    /**
     * Get Sort field.
     * 
     * @return field for sort.
     */
    String getSortField();

    /**
     * Get sort direction.
     * 
     * @return direction for sort.
     */
    String getSortDirection();

    /**
     * Set bids history to request.
     * 
     * @param bidHistoryList
     *            list of bids history.
     */
    void setBidsHistory(List<ItemDetails> bidHistoryList);

    /**
     * Set error message to request.
     * 
     * @param message
     *            the message.
     */
    void setErrorMessage(String message);

}
