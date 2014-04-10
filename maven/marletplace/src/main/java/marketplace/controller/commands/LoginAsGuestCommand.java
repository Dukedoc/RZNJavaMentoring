/**
 * 
 */
package marketplace.controller.commands;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;

/**
 * Command for Login as Guest.
 * 
 * @author Roman_Ten
 * 
 */
public class LoginAsGuestCommand extends AbstractCommand {

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public LoginAsGuestCommand(HelperFactory helperFactory) {
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
