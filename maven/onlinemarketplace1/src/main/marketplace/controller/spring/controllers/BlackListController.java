package marketplace.controller.spring.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.model.dao.factory.DAOFactory;
import marketplace.model.dao.interfaces.UserDAO;
import marketplace.model.to.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Controller for BlackList.
 * 
 * @author Roman_Ten
 * 
 */
public class BlackListController extends AbstractController {
    private static final String USERS_LIST_ATTRIBUTE = "usersList";
    private static final String USER_ATTRIBUTE = "user";
    private User user;

    /**
     * Set User.
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
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
	    HttpServletResponse arg1) throws Exception {
	UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.ORACLE)
		.getUserDAO();
	List<User> blackList = userDAO.getBlackList(user);
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject(USER_ATTRIBUTE, user);
	modelAndView.addObject(USERS_LIST_ATTRIBUTE, blackList);
	return modelAndView;
    }

}
