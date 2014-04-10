package marketplace.controller.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Controller for not authorized.
 * 
 * @author Roman_Ten
 * 
 */
public class NotAuthorizedController extends AbstractController {

    private static final String NEXT_VIEW = "notAuthorized";

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
	    HttpServletResponse arg1) throws Exception {
	return new ModelAndView(NEXT_VIEW);
    }

}
