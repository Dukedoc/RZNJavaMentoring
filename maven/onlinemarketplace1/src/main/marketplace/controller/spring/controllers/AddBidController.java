package marketplace.controller.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.controller.helpers.HelperFactory;
import marketplace.controller.utils.BidError;
import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.BidDAO;
import marketplace.model.to.Bid;
import marketplace.model.to.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller for Add Bid.
 * 
 * @author Roman_Ten
 * 
 */
public class AddBidController extends AbstractController {

    private static final String ERROR_MSG_ATTRIBUTE = "errorMsg";
    private User user;
    private String successView;

    /**
     * Set successView.
     * 
     * @param successView
     *            the successView to set.
     */
    public void setSuccessView(String successView) {
	this.successView = successView;
    }

    /**
     * Set user.
     * 
     * @param user
     *            the User.
     */
    public void setUser(User user) {
	this.user = user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	HelperFactory helperFactory = HelperFactory.getHelperFactory(
		HelperFactory.WEB, request);
	int itemID = helperFactory.getBidHelper().getItemID();
	float bidValue = helperFactory.getBidHelper().getBidValue();
	Bid bid = new Bid();
	bid.setBid(bidValue);
	bid.setBidderID(user.getUserID());
	bid.setItemID(itemID);
	BidDAO bidDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE).getBidDAO();
	int bidID = bidDAO.bidAdd(bid);
	ModelAndView modelAndView = new ModelAndView(new RedirectView(
		successView));
	if (bidID <= 0) {
	    modelAndView.addObject(ERROR_MSG_ATTRIBUTE, BidError
		    .parseToCommandResult(bidID));
	}
	return modelAndView;
    }
}