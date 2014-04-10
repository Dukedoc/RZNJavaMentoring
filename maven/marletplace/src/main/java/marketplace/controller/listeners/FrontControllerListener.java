/**
 * 
 */
package marketplace.controller.listeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import marketplace.controller.timers.ItemComplete;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.to.ItemDetails;

/**
 * Listener for application.
 * 
 * @author Roman_Ten
 * 
 */
public class FrontControllerListener implements ServletContextListener {
    // Map with task. Key is item_id, value is itemComplete with this item.
    private static Map<Integer, ItemComplete> itemCompleteList = new HashMap<Integer, ItemComplete>();

    // Timer for items.
    private static Timer timer;

    /**
     * {@inheritDoc}
     */
    public void contextDestroyed(ServletContextEvent arg0) {
	timer = null;
	itemCompleteList = null;
    }

    /**
     * {@inheritDoc}
     */
    public void contextInitialized(ServletContextEvent arg0) {
	timer = new Timer();
	addScheduleList();
    }

    // Add to the timer items with a known stop date.
    private void addScheduleList() {
	ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getItemDAO();
	List<ItemDetails> itemDetailsList = itemDAO.getItemDetailsList();
	for (ItemDetails itemDetails : itemDetailsList) {
	    if ((itemDetails.getStopDate() != null)
		    && (!itemDetails.isSendEmail())) {
		addItemCompleteTimerTask(itemDetails);
	    }
	}
    }

    /**
     * Add task to schedule.
     * 
     * @param itemDetails
     *            the itemDetails.
     */
    public static void addSchedule(ItemDetails itemDetails) {
	if (itemDetails.getStopDate() != null) {
	    addItemCompleteTimerTask(itemDetails);
	}
    }

    /**
     * Remove task from schedule.
     * 
     * @param itemID
     *            the Item ID.
     */
    public static void removeSchedule(int itemID) {
	if (itemCompleteList.containsKey(itemID)) {
	    itemCompleteList.get(itemID).cancel();
	    itemCompleteList.remove(itemID);
	}
    }

    // Add item to timer task.
    private static void addItemCompleteTimerTask(ItemDetails itemDetails) {
	ItemComplete itemComplete = new ItemComplete(itemDetails.getItemID());
	itemCompleteList.put(itemDetails.getItemID(), itemComplete);
	timer.schedule(itemComplete, itemDetails.getStopDate().getTime());

    }

}
