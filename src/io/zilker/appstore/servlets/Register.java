package io.zilker.appstore.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.zilker.appstore.exceptions.*;

import io.zilker.appstore.beans.GenericUser;
import io.zilker.appstore.controller.UserController;
import io.zilker.appstore.dao.UserDao;

@WebServlet("/Register")
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private GenericUser user;
	static Logger LOGGER;

	public Register() {
		super();
		LOGGER = Logger.getLogger(Register.class.getName());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Entered doGet");
		user = new GenericUser();
		UserController userController = new UserController();
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
		user = new GenericUser();
		UserController userController = new UserController();
		try {
			user.setFullName(request.getParameter("r-fullname"));
			user.setEmail(request.getParameter("r-email"));
			user.setUserName(request.getParameter("r-username"));
			user.setPassword(request.getParameter("r-password"));
			boolean register = userController.userRegister(user);
			if (register) {
				request.setAttribute("LOGIN_MESSAGE", "You're Registered. Login to continue");
				request.setAttribute("REGISTER_MESSAGE", "");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
				dispatcher.forward(request, response);
			}
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			request.setAttribute("LOGIN_MESSAGE", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			request.setAttribute("LOGIN_MESSAGE", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
			dispatcher.forward(request, response);
		} finally {
			LOGGER.info("Exited doPost");
		}
	}

}
