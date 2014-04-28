/**
 * 
 */
package marketplace.controller.commands;

import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;

/**
 * Command for registration.
 * 
 * @author Roman_Ten
 * 
 */
public class RegistrationFormCommand extends AbstractCommand {
    /**
     * Constructor.
     * 
     * @param helperFactory
     *            the Helper Factory.
     */
    public RegistrationFormCommand(HelperFactory helperFactory) {
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
