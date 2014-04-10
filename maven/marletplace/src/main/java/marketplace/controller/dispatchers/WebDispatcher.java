/**
 * 
 */
package marketplace.controller.dispatchers;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.controller.enumerations.CommandEnumeration;
import marketplace.controller.enumerations.CommandResultEnum;
import marketplace.controller.helpers.HelperFactory;

/**
 * Dispatcher for Web Application.
 * 
 * @author Roman_Ten
 * 
 */
public class WebDispatcher implements Dispatcher {
    private static final String ERROR_PARAMETER = "&error=";
    private static final String REDIRECT_STRING = "FrontController?action=";
    private static final String FORWARD_STRING = "/WEB-INF/{0}.jsp";
    private HelperFactory helperFactory;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext servletContext;

    /**
     * Constructor.
     * 
     * @param helperFactory
     *            is HelperFactory for this Web Dispatcher
     * @param request
     *            the HttpServletRequest.
     * @param response
     *            the HttpServletResponse.
     * @param servletContext
     *            the ServletContext.
     */
    public WebDispatcher(HelperFactory helperFactory,
	    HttpServletRequest request, HttpServletResponse response,
	    ServletContext servletContext) {
	this.helperFactory = helperFactory;
	this.request = request;
	this.response = response;
	this.servletContext = servletContext;
    }

    /**
     * {@inheritDoc}
     */
    public void dispatch() {
	CommandEnumeration commandName = CommandEnumeration
		.fromString(helperFactory.getCommandHelper().getCommandName());
	CommandResultEnum commandResult = helperFactory.getCommandHelper()
		.getCommandResult();

	switch (commandName) {
	case SHOW_ITEMS:
	    forward(CommandEnumeration.SHOW_ITEMS);
	    break;
	case SEARCH:
	    forward(CommandEnumeration.SHOW_ITEMS);
	    break;
	case SHOW_LOGIN:
	    forward(CommandEnumeration.LOGIN);
	    break;
	case LOGIN:
	    if (commandResult == CommandResultEnum.TRUE) {
		redirect(CommandEnumeration.SHOW_ITEMS);
	    } else {
		forward(CommandEnumeration.LOGIN);
	    }
	    break;
	case SHOW_MY_ITEMS:
	    if (commandResult == CommandResultEnum.TRUE) {
		forward(CommandEnumeration.SHOW_MY_ITEMS);
	    } else {
		forward(CommandEnumeration.NOT_AUTHORIZED);
	    }
	    break;
	case SELL:
	    if (commandResult == CommandResultEnum.TRUE) {
		forward(CommandEnumeration.EDIT_ITEM);
	    } else {
		forward(CommandEnumeration.NOT_AUTHORIZED);
	    }
	    break;
	case PUBLISH_ITEM:
	    if (commandResult == CommandResultEnum.TRUE) {
		redirect(CommandEnumeration.SHOW_ITEMS);
	    } else {
		forward(CommandEnumeration.EDIT_ITEM);
	    }
	    break;
	case LOGIN_AS_GUEST:
	    redirect(CommandEnumeration.SHOW_ITEMS);
	    break;
	case LOGOUT:
	    forward(CommandEnumeration.LOGIN);
	    break;
	case REGISTRATION:
	    forward(CommandEnumeration.REGISTRATION);
	    break;
	case ADD_USER:
	    if (commandResult == CommandResultEnum.LOGIN_EXIST) {
		forward(CommandEnumeration.REGISTRATION);
	    } else if (commandResult == CommandResultEnum.TRUE) {
		redirect(CommandEnumeration.SHOW_ITEMS);
	    } else if (commandResult == CommandResultEnum.FORM_NOT_VALID) {
		forward(CommandEnumeration.REGISTRATION);
	    } else {
		redirect(CommandEnumeration.REGISTRATION,
			CommandResultEnum.SQL_EXCEPTION);
	    }
	    break;
	case BIDDING:
	    if (commandResult == CommandResultEnum.TRUE) {
		redirect(CommandEnumeration.SHOW_ITEMS);
	    } else {
		redirect(CommandEnumeration.SHOW_ITEMS, commandResult);
	    }
	    break;
	case DELETE_ITEM:
	    redirect(CommandEnumeration.SHOW_MY_ITEMS);
	    break;
	case EDIT_ITEM:
	    forward(CommandEnumeration.EDIT_ITEM);
	    break;
	case BUY_IT_NOW:
	    if (commandResult == CommandResultEnum.TRUE) {
		redirect(CommandEnumeration.SHOW_ITEMS);
	    } else {
		redirect(CommandEnumeration.SHOW_ITEMS, commandResult);
	    }
	    break;
	case SHOW_HISTORY:
	    forward(CommandEnumeration.SHOW_HISTORY);
	    break;
	case BLACK_LIST:
	    if (commandResult == CommandResultEnum.TRUE) {
		forward(CommandEnumeration.BLACK_LIST);
	    } else {
		forward(CommandEnumeration.NOT_AUTHORIZED);
	    }
	    break;
	case REMOVE_FROM_BLACK_LIST:
	    if (commandResult == CommandResultEnum.TRUE) {
		redirect(CommandEnumeration.BLACK_LIST);
	    } else {
		forward(CommandEnumeration.NOT_AUTHORIZED);
	    }
	case ADD_TO_BLACK_LIST:
	    if (commandResult == CommandResultEnum.NOT_AUTORIZATED) {
		forward(CommandEnumeration.NOT_AUTHORIZED);
	    } else if (commandResult == CommandResultEnum.BLACK_LIST_ADD_USER) {
		redirect(CommandEnumeration.SHOW_HISTORY.getText() + "&itemID="
			+ request.getParameter("itemID") + ERROR_PARAMETER
			+ CommandResultEnum.BLACK_LIST_ADD_USER);
	    } else if (commandResult == CommandResultEnum.BLACK_LIST_ERROR_DUPLICATE) {
		redirect(CommandEnumeration.SHOW_HISTORY.getText() + "&itemID="
			+ request.getParameter("itemID") + ERROR_PARAMETER
			+ CommandResultEnum.BLACK_LIST_ERROR_DUPLICATE);
	    }
	default:
	    break;
	}
    }

    /**
     * Forward.
     * 
     * @param commandEnumeration
     *            the commandEnumeration for forward.
     */
    private void forward(CommandEnumeration commandEnumeration) {
	RequestDispatcher requestDispatcher = servletContext
		.getRequestDispatcher(MessageFormat.format(FORWARD_STRING,
			commandEnumeration.getText()));
	try {
	    requestDispatcher.forward(request, response);
	} catch (ServletException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Redirect.
     * 
     * @param string
     *            for redirect.
     */
    private void redirect(String string) {
	try {
	    response.sendRedirect(REDIRECT_STRING + string);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Redirect.
     * 
     * @param commandEnumeration
     *            commandEnumeration for redirect.
     */
    private void redirect(CommandEnumeration commandEnumeration) {
	try {
	    response.sendRedirect(REDIRECT_STRING
		    + commandEnumeration.getText());
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Redirect.
     * 
     * @param commandEnumeration
     *            the commandEnumeration for redirect.
     * @param commandResult
     *            CommandResultEnum for message.
     */
    private void redirect(CommandEnumeration commandEnumeration,
	    CommandResultEnum commandResult) {
	try {
	    response.sendRedirect(REDIRECT_STRING
		    + commandEnumeration.getText() + ERROR_PARAMETER
		    + commandResult);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
