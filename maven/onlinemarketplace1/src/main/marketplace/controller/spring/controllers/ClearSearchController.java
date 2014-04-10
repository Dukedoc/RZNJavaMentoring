/**
 * 
 */
package marketplace.controller.spring.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.controller.enumerations.AdvancedSearchControllerFields;
import marketplace.model.to.AdvancedSearchTO;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Controller for Clear Search.
 * 
 * @author Roman_Ten
 * 
 */
public class ClearSearchController extends AbstractController {

    private static final String NEXT_VIEW = "advancedSearch";
    private static final String MODEL_NAME = "advancedSearch";
    private Cookie[] cookies;

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	cookies = request.getCookies();
	for (AdvancedSearchControllerFields field : AdvancedSearchControllerFields
		.values()) {
	    Cookie cookie = getFromCookie(field.getValue());
	    cookie.setValue(null);
	    response.addCookie(cookie);
	}

	return new ModelAndView(NEXT_VIEW, MODEL_NAME, new AdvancedSearchTO());
    }

    private Cookie getFromCookie(String value) {
	Cookie cookie = null;
	for (Cookie currentCookie : cookies) {
	    if (currentCookie.getName().equals(value)) {
		cookie = currentCookie;
	    }
	}
	return cookie;
    }
}
