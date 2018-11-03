package io.zilker.appstore.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import org.json.*;
import javax.servlet.http.HttpSession;
import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.Errors;
import io.zilker.appstore.constants.TemplateStrings;
import io.zilker.appstore.controller.*;
import io.zilker.appstore.exceptions.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@WebServlet("/ChangeInfo")
public class ChangeInfo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Logger LOGGER;

	public ChangeInfo() {
		super();
		LOGGER = Logger.getLogger(ChangeInfo.class.getName());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Entered doPost");
		UserController userController = new UserController();
		try {
			HttpSession session = request.getSession(false);
			if (request.getSession(false).getAttribute("user") == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
				dispatcher.forward(request, response);
			} else {
				GenericUser user = (GenericUser) session.getAttribute("user");
				GenericUser tempUser = new GenericUser();
				StringBuilder jsonBuffer = new StringBuilder();
				String line = null;
				BufferedReader reader = request.getReader();
				while ((line = reader.readLine()) != null) {
					jsonBuffer.append(line);
				}
				JSONObject jsonObject = new JSONObject(jsonBuffer.toString());
				String field = jsonObject.getString("field");
				String value = jsonObject.getString("value");
				String password = jsonObject.getString("password");
				tempUser.setUserID(user.getUserID());
				tempUser.setUserName(user.getUserName());
				tempUser.setPassword(password);
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				if (field.compareTo("email") == 0) {
					if(user.getEmail().compareTo(value) == 0) {
						throw new BusinessException(TemplateStrings.SAME_EMAIL);
					}
					tempUser.setEmail(value);
					if (userController.changeEmail(tempUser)) {
						user.setEmail(tempUser.getEmail());
						response.getWriter().write(TemplateStrings.EMAIL_CHANGED);
					} else {
						response.getWriter().write(Errors.SOMETHING_WRONG_WITH_SERVER);
					}
				} else if (field.compareTo("fullname") == 0) {
					if(user.getFullName().compareTo(value) == 0) {
						throw new BusinessException(TemplateStrings.SAME_NAME);
					}
					tempUser.setFullName(value);
					if (userController.changeFullName(tempUser)) {
						user.setFullName(tempUser.getFullName());
						response.getWriter().write(TemplateStrings.FULLNAME_CHANGED);
					} else {
						response.getWriter().write(Errors.SOMETHING_WRONG_WITH_SERVER);
					}
				} else if (field.compareTo("username") == 0) {
					if(user.getUserName().compareTo(value) == 0) {
						throw new BusinessException(TemplateStrings.SAME_USERNAME);
					}
					if (userController.isUser(tempUser)) {
						tempUser.setUserName(value);
						if (userController.changeUserName(tempUser)) {
							user.setUserName(tempUser.getUserName());
							response.getWriter().write(TemplateStrings.USERNAME_CHANGED);
						} else {
							response.getWriter().write(Errors.SOMETHING_WRONG_WITH_SERVER);
						}
					}
				} else if (field.compareTo("password") == 0) {
					if (userController.isUser(tempUser)) {
						tempUser.setPassword(value);
						if (userController.changePassword(tempUser)) {
							user.setPassword(tempUser.getPassword());
							response.getWriter().write(TemplateStrings.PASSWORD_CHANGED);
						} else {
							response.getWriter().write(Errors.SOMETHING_WRONG_WITH_SERVER);
						}
					}
				} else if (field.compareTo("accounttype") == 0) {
					tempUser.setUserPrivilege(value);
					if (userController.changeUserPrivilege(tempUser)) {
						user.setUserPrivilege(tempUser.getUserPrivilege());
						response.getWriter().write(TemplateStrings.ACCOUNT_TYPE_CHANGED);
					} else {
						response.getWriter().write(Errors.SOMETHING_WRONG_WITH_SERVER);
					}
				}
			}
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			response.getWriter().write(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			response.getWriter().write(Errors.SOMETHING_WRONG_WITH_SERVER);
		} finally {
			LOGGER.info("Exited doPost");
		}
	}

}
