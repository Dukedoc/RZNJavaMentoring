/**
 * 
 */
package marketplace.controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import marketplace.controller.enumerations.CommandEnumeration;
import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.ItemDAO;
import marketplace.model.to.Item;
import marketplace.model.to.User;

/**
 * Authorization filter.
 * 
 * @author Roman_Ten
 */
public class AuthorizationFilter implements Filter {

    private static final String JSP = ".jsp";
    private static final String WEB_INF = "/WEB-INF/";

    /**
     * {@inheritDoc}
     */
    public void destroy() {

    }

    /**
     * {@inheritDoc}
     */
    public void doFilter(ServletRequest servletRequest,
	    ServletResponse servletResponse, FilterChain filterChain)
	    throws IOException, ServletException {
	HelperFactory helperFactory = HelperFactory.getHelperFactory(
		HelperFactory.WEB, (HttpServletRequest) servletRequest);
	User user = helperFactory.getUserHelper().getUserFromSession();
	CommandEnumeration commandName = CommandEnumeration
		.fromString(helperFactory.getCommandHelper().getCommandName());
	if (user == null) {
	    switch (commandName) {
	    case ADD_TO_BLACK_LIST:
	    case BIDDING:
	    case BLACK_LIST:
	    case BUY_IT_NOW:
	    case DELETE_ITEM:
	    case EDIT_ITEM:
	    case LOGOUT:
	    case PUBLISH_ITEM:
	    case REMOVE_FROM_BLACK_LIST:
	    case SHOW_MY_ITEMS:
	    case SELL:
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(WEB_INF);
		stringBuilder.append(CommandEnumeration.NOT_AUTHORIZED
			.getText());
		stringBuilder.append(JSP);
		RequestDispatcher dispatcher = ((HttpServletRequest) servletRequest)
			.getRequestDispatcher(stringBuilder.toString());
		dispatcher.forward(servletRequest, servletResponse);
		break;
	    default:
		filterChain.doFilter(servletRequest, servletResponse);
		break;
	    }
	} else {
	    if (commandName == CommandEnumeration.REGISTRATION) {
		return;
	    }
	    if (commandName == CommandEnumeration.EDIT_ITEM
		    || commandName == CommandEnumeration.DELETE_ITEM) {
		int itemID = helperFactory.getItemHelper().getItemID();
		ItemDAO itemDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
			.getItemDAO();
		Item item = itemDAO.findItemByUID(itemID);
		if (item != null) {
		    if (item.getSellerID() == user.getUserID()) {
			filterChain.doFilter(servletRequest, servletResponse);
		    }
		}
	    } else {
		filterChain.doFilter(servletRequest, servletResponse);
	    }
	}

    }

    /**
     * {@inheritDoc}
     */
    public void init(FilterConfig arg0) throws ServletException {

    }

}
