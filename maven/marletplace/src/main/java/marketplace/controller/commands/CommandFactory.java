package marketplace.controller.commands;

import marketplace.controller.enumerations.CommandEnumeration;
import marketplace.controller.helpers.HelperFactory;

/**
 * Command factory.
 * 
 * @author Roman_Ten
 * 
 */
public abstract class CommandFactory {

    /**
     * Create Command.
     * 
     * @param helperFactory
     *            the helper factory.
     * @return Command.
     */
    public static AbstractCommand getCommand(HelperFactory helperFactory) {
	CommandEnumeration commandName = CommandEnumeration
		.fromString(helperFactory.getCommandHelper().getCommandName());
	switch (commandName) {
	case SHOW_ITEMS:
	    return new ShowItemsCommand(helperFactory);
	case SEARCH:
	    return new SearchCommand(helperFactory);
	case SHOW_LOGIN:
	    return new ShowLoginCommand(helperFactory);
	case LOGIN:
	    return new LoginCommand(helperFactory);
	case SHOW_MY_ITEMS:
	    return new ShowMyItemsCommand(helperFactory);
	case SELL:
	    return new SellCommand(helperFactory);
	case PUBLISH_ITEM:
	    return new SellItemCommand(helperFactory);
	case LOGIN_AS_GUEST:
	    return new LoginAsGuestCommand(helperFactory);
	case LOGOUT:
	    return new LogoutCommand(helperFactory);
	case REGISTRATION:
	    return new RegistrationFormCommand(helperFactory);
	case ADD_USER:
	    return new AddUserCommand(helperFactory);
	case BIDDING:
	    return new BiddingCommand(helperFactory);
	case DELETE_ITEM:
	    return new DeleteItemCommand(helperFactory);
	case EDIT_ITEM:
	    return new EditItemCommand(helperFactory);
	case BUY_IT_NOW:
	    return new BuyItNowCommand(helperFactory);
	case SHOW_HISTORY:
	    return new ShowHistoryCommand(helperFactory);
	case ADD_TO_BLACK_LIST:
	    return new AddToBlackListCommand(helperFactory);
	case BLACK_LIST:
	    return new BlackListCommand(helperFactory);
	case REMOVE_FROM_BLACK_LIST:
	    return new RemoveFromBlackListCommand(helperFactory);
	default:
	    return null;
	}
    }
}
