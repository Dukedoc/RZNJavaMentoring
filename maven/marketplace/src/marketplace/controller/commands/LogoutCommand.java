/**
 * 
 */
package marketplace.controller.commands;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;

/**
 * Command for log out.
 * 
 * @author Roman_Ten
 * 
 */
public class LogoutCommand extends AbstractCommand {

    /**
     * Constructor for logout command.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public LogoutCommand(HelperFactory helperFactory) {
	super(helperFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
	getHelperFactory().getUserHelper().logout();
	getHelperFactory().getCommandHelper().setCommandResult(
		CommandResultEnum.TRUE);
    }

}
