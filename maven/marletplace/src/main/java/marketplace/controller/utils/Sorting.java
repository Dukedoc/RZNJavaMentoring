/**
 * 
 */
package marketplace.controller.utils;

import java.util.Collections;
import java.util.List;

import marketplace.controller.enumerations.SortFieldEnum;
import marketplace.controller.helpers.HelperFactory;
import marketplace.model.to.ItemDetails;
import marketplace.model.to.ItemDetailsSort;

/**
 * Sorting.
 * 
 * @author Roman_Ten
 * 
 */
public class Sorting {
    private static final String DESC_ORDER = "DESC";
    private static final String DEFAULT_SORT_DIRECTION = "ASC";
    private static final SortFieldEnum DEFAULT_SORT_FIELD = SortFieldEnum.ITEM_ID;
    private HelperFactory helperFactory;
    private List<ItemDetails> list;

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the helper factory.
     * @param list
     *            list for sorting.
     */
    public Sorting(HelperFactory helperFactory, List<ItemDetails> list) {
	this.helperFactory = helperFactory;
	this.list = list;
    }

    /**
     * To sort.
     * 
     * @return a sorted list.
     */
    public List<ItemDetails> sort() {
	SortFieldEnum sortField = null;

	if (getHelperFactory().getItemHelper().getSortField() != null
		&& !getHelperFactory().getItemHelper().getSortField()
			.equals("")) {
	    sortField = SortFieldEnum.valueOf(getHelperFactory()
		    .getItemHelper().getSortField().toUpperCase());

	} else {
	    sortField = DEFAULT_SORT_FIELD;
	}
	String sortDirection = null;
	if (getHelperFactory().getItemHelper().getSortDirection() != null
		&& !getHelperFactory().getItemHelper().getSortDirection()
			.equals("")) {
	    sortDirection = getHelperFactory().getItemHelper()
		    .getSortDirection().toLowerCase();
	} else {
	    // values by default.
	    sortDirection = DEFAULT_SORT_DIRECTION;
	}
	Collections.sort(list, new ItemDetailsSort(sortField));
	if (sortDirection.toUpperCase().equals(DESC_ORDER)) {
	    Collections.reverse(list);
	}
	return list;

    }

    /**
     * Get helper factory.
     * 
     * @return the helperFactory
     */
    private HelperFactory getHelperFactory() {
	return helperFactory;
    }

}
