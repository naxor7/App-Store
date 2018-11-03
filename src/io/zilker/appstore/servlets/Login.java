package io.zilker.appstore.servlets;

import java.io.IOException;
import javax.servlet.http.HttpSession;  
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.zilker.appstore.controller.*;
import io.zilker.appstore.beans.*;
import io.zilker.appstore.exceptions.*;

@WebServlet(urlPatterns = { "/Login", "/index.html" })
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserController userController;
	static Logger LOGGER;

	public Login() {
		super();
		LOGGER = Logger.getLogger(Login.class.getName());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Entered doGet");
		userController = new UserController();
		try {
			request.setAttribute("LOGIN_MESSAGE", "");
			request.setAttribute("REGISTER_MESSAGE", "");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			request.setAttribute("LOGIN_MESSAGE", e.getMessage());
			request.setAttribute("REGISTER_MESSAGE", "");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
			dispatcher.forward(request, response);
		} finally {
			LOGGER.info("Exited doGet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Entered doPost");
		userController = new UserController();
		try {
			GenericUser user = new GenericUser();
			user.setUserName(request.getParameter("l-username"));
			user.setPassword(request.getParameter("l-password"));
			user = userController.userLogin(user);
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			if (user != null) {
				if (user.getUserPrivilege().compareTo("u") == 0 || user.getUserPrivilege().compareTo("d") == 0) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/user-profile.jsp");
					dispatcher.include(request, response);
				}
				else if (user.getUserPrivilege().compareTo("t") == 0) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("TesterPendingApps");
					dispatcher.include(request, response);
				} 
				else if (user.getUserPrivilege().compareTo("a") == 0) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/admin-profile.jsp");
					dispatcher.include(request, response);
				}
			} else {
				request.setAttribute("LOGIN_MESSAGE", "Username or Password Incorrect");
				request.setAttribute("REGISTER_MESSAGE", "");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
				dispatcher.forward(request, response);
			}
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			request.setAttribute("LOGIN_MESSAGE", e.getMessage());
			request.setAttribute("REGISTER_MESSAGE", "");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			request.setAttribute("LOGIN_MESSAGE", e.getMessage());
			request.setAttribute("REGISTER_MESSAGE", "");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
			dispatcher.forward(request, response);
		} finally {
			LOGGER.info("Exited doPost");
		}
	}

}
