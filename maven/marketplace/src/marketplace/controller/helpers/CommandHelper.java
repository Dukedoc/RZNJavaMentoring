/**
 * 
 */
package marketplace.controller.helpers;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.model.to.ItemDetails;

/**
 * Interface Command Helper.
 * 
 * @author Roman_Ten
 * 
 */
public interface CommandHelper {

    /**
     * Get command name from request.
     * 
     * @return command name.
     */
    String getCommandName();

    /**
     * get command result from request.
     * 
     * @return command result.
     */
    CommandResultEnum getCommandResult();

    /**
     * Set command result to request.
     * 
     * @param commandResult
     *            the command result.
     */
    void setCommandResult(CommandResultEnum commandResult);

    /**
     * Get error from parameters.
     * 
     * @return the error.
     */
    String getError();

    /**
     * Add schedule to timer.
     * 
     * @param itemDetails
     *            itemdetails for timer.
     */
    void addTimerSchedule(ItemDetails itemDetails);

    /**
     * change timer schedule.
     * 
     * @param itemDetails
     *            item details for timer.
     */
    void changeTimerSchedule(ItemDetails itemDetails);

    /**
     * Set Error message.
     * 
     * @param message
     *            the error message.
     */
    void setErrorMessage(String message);

}
