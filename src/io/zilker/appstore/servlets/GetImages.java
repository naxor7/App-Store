package io.zilker.appstore.servlets;

import java.io.*;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import io.zilker.appstore.beans.Applications;
import io.zilker.appstore.beans.GenericUser;
import io.zilker.appstore.controller.AppController;
import io.zilker.appstore.controller.UserController;

@WebServlet("/GetImages")
public class GetImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger LOGGER;

	public GetImages() {
		super();
		LOGGER = Logger.getLogger(GetImages.class.getName());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Entered doGet");
		try {
			HttpSession session = request.getSession(false);
			if (request.getSession(false).getAttribute("user") == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
				dispatcher.forward(request, response);
			} else {
				ServletValidator validator = new ServletValidator();
				validator.checkID(request.getParameter("id"));
				AppController appController = new AppController();
				UserController userController = new UserController();
				GenericUser user = new GenericUser();
				Applications app = new Applications();
				app.setAppID(Integer.parseInt(request.getParameter("id")));
				app = appController.getAppInfo(app);
				user.setUserID(app.getUserID());
				user = userController.getUserInfo(user);
				File f = new File("/home/ztech/storefiles/"+user.getUserName()+"/"+app.getAppName()+"logo");
				FileInputStream fin = new FileInputStream(f);
				ServletOutputStream outStream = response.getOutputStream();
				response.setContentType("image/jpg");
				int i = 0;
				while (i != -1) {
					i = fin.read();
					outStream.write(i);
				}
				fin.close();
			}
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
		doGet(request, response);
		LOGGER.info("Exiting doPost");
	}

}
