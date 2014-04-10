/**
 * 
 */
package marketplace.controller.utils;

import java.util.List;

import marketplace.controller.helpers.HelperFactory;
import marketplace.model.to.ItemDetails;

/**
 * Paginal output.
 * 
 * @author Roman_Ten
 * 
 */
public class PaginalOutput {
    private static final int MAX_ITEMS_ON_LIST = 10;
    private HelperFactory helperFactory;
    private List<ItemDetails> itemsList;

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the helper factory.
     * @param itemsList
     *            paginated list.
     */
    public PaginalOutput(HelperFactory helperFactory,
	    List<ItemDetails> itemsList) {
	this.helperFactory = helperFactory;
	this.itemsList = itemsList;
    }

    /**
     * Paging.
     * 
     * @return a split list.
     */
    public List<ItemDetails> paging() {
	List<ItemDetails> itemList = null;

	int listNumber = getHelperFactory().getItemHelper().getListNumber();
	if (listNumber == 0) {
	    listNumber = 1;
	}
	int startIndex = (listNumber - 1) * MAX_ITEMS_ON_LIST;
	int stopIndex = startIndex + MAX_ITEMS_ON_LIST;
	int countList = itemsList.size();
	if (stopIndex > countList) {
	    itemList = itemsList.subList(startIndex, countList);
	} else {
	    itemList = itemsList.subList(startIndex, stopIndex);
	}
	int pageCount = (int) Math.ceil((float) countList / MAX_ITEMS_ON_LIST);
	getHelperFactory().getItemHelper().setPageCount(pageCount);
	return itemList;
    }

    /**
     * Get Helper Factory.
     * 
     * @return the helperFactory
     */
    private HelperFactory getHelperFactory() {
	return helperFactory;
    }

}
