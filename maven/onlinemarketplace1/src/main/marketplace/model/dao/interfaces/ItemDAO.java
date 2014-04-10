package marketplace.model.dao.interfaces;

import java.util.List;

import marketplace.model.to.AdvancedSearchTO;
import marketplace.model.to.Category;
import marketplace.model.to.Item;
import marketplace.model.to.ItemDetails;

/**
 * Data Access Object for Item.
 * 
 * @author Roman_Ten
 * 
 */
public interface ItemDAO {
    /**
     * Create Item's list.
     * 
     * @return List<itemDetails> is list of all items
     */
    List<ItemDetails> getItemDetailsList();

    /**
     * Find item by his name.
     * 
     * @param name
     *            is name of item
     * @return itemDetails is transfer object item
     */
    List<ItemDetails> findItemDetailsByName(String name);

    /**
     * Search by item description.
     * 
     * @param description
     *            is item description
     * @return itemDetails is transfer object item
     */
    List<ItemDetails> findItemDetailsByDescription(String description);

    /**
     * Search by item uid.
     * 
     * @param uid
     *            is id item
     * @return itemDetails is transfer object item
     */
    ItemDetails findItemDetailsByUID(int uid);

    /**
     * Search by item uid.
     * 
     * @param uid
     *            is id item
     * @return Item is transfer object item
     */
    Item findItemByUID(int uid);

    /**
     * Add item.
     * 
     * @param item
     *            is transfer object item
     * @return either is itemID or -1 for error.
     */
    int addItem(Item item);

    /**
     * Delete item by uid.
     * 
     * @param uid
     *            is item id
     * @return either (1) the row count or (2) 0 for SQL statements that return
     *         nothing
     */
    int deleteItemByUID(int uid);

    /**
     * Edit item.
     * 
     * @param itemID
     *            is ID of chargeable item
     * @param item
     *            is item that replaced
     */
    void editItem(int itemID, Item item);

    /**
     * Get categories list.
     * 
     * @return Categories list.
     */
    List<Category> getCategoriesList();

    /**
     * Search items by category.
     * 
     * @param category
     *            name of category
     * @return itemDetails list where category equals category parameter.
     */
    List<ItemDetails> findItemsByCategory(String category);

    /**
     * Set flag send e-mail.
     * 
     * @param itemID
     *            item id.
     */
    void setSendEmail(int itemID);

    /**
     * Get bids history.
     * 
     * @param itemID
     *            the Item ID.
     * @return bids history list.
     */
    List<ItemDetails> getBidsHistoryList(int itemID);

    /**
     * Advanced Search.
     * 
     * @param advancedSearchTO
     *            the Advanced Search transfer object.
     * @return result list.
     */
    List<ItemDetails> advancedSearch(AdvancedSearchTO advancedSearchTO);

}
