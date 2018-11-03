package io.zilker.appstore.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.zilker.appstore.controller.*;
import io.zilker.appstore.dao.UserDao;
import io.zilker.appstore.exceptions.BusinessException;
import io.zilker.appstore.beans.*;

@WebServlet("/UserNameChecker")
public class UserNameChecker extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static Logger LOGGER;

	public UserNameChecker() {
		super();
		LOGGER = Logger.getLogger(UserNameChecker.class.getName());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Entered doGet");
		UserController userController = new UserController();
		try {
			GenericUser user = new GenericUser();
			user.setUserName(request.getParameter("username"));
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			if (userController.checkUserName(user))
				response.getWriter().write("yes");
			else
				response.getWriter().write("no");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Entered doPost");
		try {
			doGet(request, response);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		LOGGER.info("Exited doPost");
	}

}
