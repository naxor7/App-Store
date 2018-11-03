package io.zilker.appstore.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import io.zilker.appstore.constants.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import io.zilker.appstore.beans.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.zilker.appstore.controller.*;
import io.zilker.appstore.exceptions.*;

@WebServlet("/AppUploadServlet")
@MultipartConfig(fileSizeThreshold = 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 100)
public class AppUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger LOGGER;

	public AppUploadServlet() {
		super();
		LOGGER = Logger.getLogger(AppUploadServlet.class.getName());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Entered doPost");
		try {
			HttpSession session = request.getSession(false);
			if (request.getSession(false).getAttribute("user") == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
				dispatcher.forward(request, response);
			} else {
				AppController appController = new AppController();
				boolean gotPic = false;
				ServletValidator validator = new ServletValidator();
				validator.checkName(request.getParameter("appname"));
				validator.checkDescription(request.getParameter("appdescription"));
				validator.checkCost(request.getParameter("appcost"));
				GenericUser user = (GenericUser) session.getAttribute("user");
				StoreUser storeUser = new StoreUser();
				storeUser.setGenUser(user);
				Applications app = populateApp(request);
				if(appController.hasAppWithSameName(app, storeUser)) {
					throw new BusinessException(Errors.SAME_NAME_APP_NOT_ALLOWED);
				}
				String uploadFilePath = "/home/ztech/storefiles/"+user.getUserName();
				File fileSaveDir = new File(uploadFilePath);
				if (!fileSaveDir.exists()) {
					fileSaveDir.mkdirs();
				}
				String fileName = null;
				for (Part part : request.getParts()) {
					if (part.getSubmittedFileName() != null) {
						if(!gotPic) {
							fileName = request.getParameter("appname")+"logo";
							gotPic = true;
						}
						else {
							fileName = request.getParameter("appname");
						}
						part.write(uploadFilePath + File.separator + fileName);
					}
				}
				appController.addApps(app, storeUser);
				request.setAttribute("message", fileName + " File uploaded successfully!");
				response.getWriter().write("Your app has been uploaded successfully. Expect it soon in the AppStore. Thank You");
			}
		} catch (Exception e) {
			response.getWriter().write(e.getMessage()+" Please close this window and retry");
		} finally {
			LOGGER.info("Exited doPost");
		}
	}

	private Applications populateApp(HttpServletRequest request) {
		Applications app = new Applications();
		app.setAppName(request.getParameter("appname"));
		Category category = new Category();
		category.setCategoryID(6);
		app.setCategory(category);
		app.setDescription(request.getParameter("appdescription"));
		app.setCost(Integer.parseInt(request.getParameter("appcost")));
		return app;
	}
	
}
