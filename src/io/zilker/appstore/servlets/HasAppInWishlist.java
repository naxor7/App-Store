package io.zilker.appstore.servlets;

import java.io.IOException;
import io.zilker.appstore.beans.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;
import io.zilker.appstore.controller.AppController;
import io.zilker.appstore.exceptions.BusinessException;

@WebServlet("/HasAppInWishlist")
public class HasAppInWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static Logger LOGGER;
	
    public HasAppInWishlist() {
        super();
        LOGGER = Logger.getLogger(HasAppInWishlist.class.getName());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Entered doGet");
		AppController appController = new AppController();
		StoreUser user = new StoreUser();
		try {
			HttpSession session = request.getSession(false);
			if (request.getSession(false).getAttribute("user") == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
				dispatcher.forward(request, response);
			} else {
				GenericUser genUser = (GenericUser) session.getAttribute("user");
				user.setGenUser(genUser);
				Applications app = new Applications();
				app.setAppID(Integer.parseInt(request.getParameter("id")));
				if(appController.hasAppInWishlist(app, user))
					response.getWriter().write("yes");
				else
					response.getWriter().write("no");
			}
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			response.getWriter().write(e.getMessage());
		} finally {
			LOGGER.info("Exited doGet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
