/**
 * 
 */
package marketplace.controller.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.controller.helpers.HelperFactory;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.BidDAO;
import marketplace.model.to.Bid;
import marketplace.model.to.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller for bid add action.
 * 
 * @author Roman_Ten
 * 
 */
public class BidAddController extends AbstractController {
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMsg";
    private static final String REDIRECT_VIEW = "showItems.htm";
    private User user;
    private DAOFactory daoFactory;

    /**
     * Set user.
     * 
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
	this.user = user;
    }

    /**
     * Set DAOFactory.
     * 
     * @param daoFactory
     *            the daoFactory to set
     */
    public void setDaoFactory(DAOFactory daoFactory) {
	this.daoFactory = daoFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	Bid bid = getBidFromRequest(request);
	BidDAO bidDAO = daoFactory.getBidDAO();
	int errorCode = bidDAO.bidAdd(bid);
	ModelAndView modelAndView = new ModelAndView(new RedirectView(
		REDIRECT_VIEW));
	if (errorCode <= 0) {
	    modelAndView.addObject(ERROR_MESSAGE_ATTRIBUTE, errorCode);
	}
	return modelAndView;
    }

    private Bid getBidFromRequest(HttpServletRequest request) {
	HelperFactory helperFactory = HelperFactory.getHelperFactory(
		HelperFactory.WEB, request);
	Bid bid = new Bid();
	bid.setBidderID(user.getUserID());
	bid.setItemID(helperFactory.getBidHelper().getItemID());
	bid.setBid(helperFactory.getBidHelper().getBidValue());
	return bid;
    }

}
