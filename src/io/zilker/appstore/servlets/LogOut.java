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


@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static Logger LOGGER;
	
    public LogOut() {
        super();
        LOGGER = Logger.getLogger(LogOut.class.getName());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Entered doGet");
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			} 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
		} finally {
			LOGGER.info("Exited doGet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
