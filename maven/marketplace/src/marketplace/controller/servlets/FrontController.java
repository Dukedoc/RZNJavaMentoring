package marketplace.controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketplace.controller.commands.Command;
import marketplace.controller.commands.CommandFactory;
import marketplace.controller.dispatchers.Dispatcher;
import marketplace.controller.dispatchers.WebDispatcher;
import marketplace.controller.helpers.HelperFactory;

/**
 * Servlet implementation class FrontController.
 */
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Override doGet.
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Override doPost.
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws ServletException {
	super.init();
    }

    /*
     * Processing of requests for both HTTP Methods <code>GET</ code> and
     * <code>POST</ code>.
     * 
     * @param request servlet response
     * 
     * @param response servlet response
     */
    private void processRequest(HttpServletRequest request,
	    HttpServletResponse response) {
	HelperFactory helperFactory = HelperFactory.getHelperFactory(
		HelperFactory.WEB, request);
	Command command = CommandFactory.getCommand(helperFactory);
	command.execute();
	Dispatcher dispatcher = new WebDispatcher(helperFactory, request,
		response, getServletContext());
	dispatcher.dispatch();

    }

}
