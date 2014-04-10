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
 * Controller for buy it now action.
 * 
 * @author Roman_Ten
 * 
 */
public class BuyItNowController extends AbstractController {
    private static final String REDIRECT_VIEW = "showItems.htm";
    private DAOFactory daoFactory;
    private User user;

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
     * Set user.
     * 
     * @param user
     *            the user to set
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
	ModelAndView modelAndView = new ModelAndView(new RedirectView(
		REDIRECT_VIEW));
	HelperFactory helperFactory = HelperFactory.getHelperFactory(
		HelperFactory.WEB, request);
	int itemID = helperFactory.getBidHelper().getItemID();
	float price = helperFactory.getBidHelper().getBidValue();
	Bid bid = new Bid();
	bid.setItemID(itemID);
	bid.setBidderID(user.getUserID());
	bid.setBid(price);
	BidDAO bidDAO = daoFactory.getBidDAO();
	int bidID = bidDAO.buyItNow(bid);
	if (bidID < 0) {
	    modelAndView.addObject("errorMsg", bidID);
	}

	return modelAndView;
    }

}
