package marketplace.controller.commands;

import marketplace.controller.helpers.HelperFactory;

/**
 * Abstract command.
 * 
 * @author Roman_Ten
 */
public abstract class AbstractCommand implements Command {

    private HelperFactory helperFactory;

    /**
     * Constructor for Command.
     * 
     * @param helperFactory
     *            the helperFactory.
     */
    public AbstractCommand(final HelperFactory helperFactory) {
	this.helperFactory = helperFactory;
    }

    /**
     * {@inheritDoc}
     */
    public abstract void execute();

    /**
     * Getter helper factory.
     * 
     * @return the helperFactory.
     */
    public final HelperFactory getHelperFactory() {
	return helperFactory;
    }
}
