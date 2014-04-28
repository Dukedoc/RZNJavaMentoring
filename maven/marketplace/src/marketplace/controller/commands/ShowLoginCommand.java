/**
 * 
 */
package marketplace.controller.commands;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;

/**
 * 
 * Command for Show Login.
 * 
 * @author Roman_Ten
 * 
 */
public class ShowLoginCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public ShowLoginCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	getHelperFactory().getCommandHelper().setCommandResult(
		CommandResultEnum.TRUE);
    }

}
