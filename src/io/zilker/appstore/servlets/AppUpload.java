package io.zilker.appstore.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.zilker.appstore.controller.UserController;

@WebServlet("/AppUpload")
public class AppUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger LOGGER;

	public AppUpload() {
		super();
		LOGGER = Logger.getLogger(AppUpload.class.getName());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Entered doGet");
		try {
			HttpSession session = request.getSession(false);
			if (request.getSession(false).getAttribute("user") == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/appupload.jsp");
				dispatcher.forward(request, response);
			}
		} catch(Exception e) {
			response.getWriter().write(e.getMessage());
		} finally {
			LOGGER.info("Exited doGet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
