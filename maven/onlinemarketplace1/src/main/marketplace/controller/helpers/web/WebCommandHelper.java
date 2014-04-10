/**
 * 
 */
package marketplace.controller.helpers.web;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.CommandHelper; //import marketplace.controller.listeners.FrontControllerListener;
import marketplace.model.to.ItemDetails;

/**
 * Command helper for web application.
 * 
 * @author Roman_Ten
 * 
 */
public class WebCommandHelper implements CommandHelper {

    private static final String ERROR_MESSAGE_PARAMETER = "errorMsg";
    private static final String ERROR_PARAMETER = "error";
    private static final String COMMAND_RESULT_ATTRIBUTE = "commandResult";
    private WebHelperFactory factory;

    /**
     * Constructor.
     * 
     * @param webHelperFactory
     *            the helper factory for web application.
     */
    public WebCommandHelper(WebHelperFactory webHelperFactory) {
	this.factory = webHelperFactory;
    }

    /**
     * {@inheritDoc}
     */
    public String getCommandName() {
	return factory.getRequest().getParameter("action");
    }

    /**
     * {@inheritDoc}
     */
    public CommandResultEnum getCommandResult() {
	return (CommandResultEnum) factory.getRequest().getAttribute(
		COMMAND_RESULT_ATTRIBUTE);
    }

    /**
     * {@inheritDoc}
     */
    public void setCommandResult(CommandResultEnum commandResult) {
	factory.getRequest().setAttribute(COMMAND_RESULT_ATTRIBUTE,
		commandResult);
    }

    /**
     * {@inheritDoc}
     */
    public String getError() {
	return factory.getRequest().getParameter(ERROR_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    public void addTimerSchedule(ItemDetails itemDetails) {
	// FrontControllerListener.addSchedule(itemDetails);

    }

    /**
     * {@inheritDoc}
     */
    public void changeTimerSchedule(ItemDetails itemDetails) {
	// FrontControllerListener.removeSchedule(itemDetails.getItemID());
	// FrontControllerListener.addSchedule(itemDetails);

    }

    /**
     * {@inheritDoc}
     */
    public void setErrorMessage(String errorMessage) {
	factory.getRequest()
		.setAttribute(ERROR_MESSAGE_PARAMETER, errorMessage);

    }

}
