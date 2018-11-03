package io.zilker.appstore.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import io.zilker.appstore.beans.Applications;
import io.zilker.appstore.beans.GenericUser;
import io.zilker.appstore.controller.AppController;
import io.zilker.appstore.controller.UserController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadApp")
public class DownloadApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger LOGGER;

	public DownloadApp() {
		super();
		LOGGER = Logger.getLogger(DownloadApp.class.getName());
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
				String filePath = "/home/ztech/storefiles/"+user.getUserName()+"/"+app.getAppName();
				File downloadFile = new File(filePath);
				FileInputStream inStream = new FileInputStream(downloadFile);
				String relativePath = getServletContext().getRealPath("");
				System.out.println("relativePath = " + relativePath);
				ServletContext context = getServletContext();
				String mimeType = context.getMimeType(filePath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				System.out.println("MIME type: " + mimeType);
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
				OutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				while ((bytesRead = inStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inStream.close();
				outStream.close();
			}
		} catch (Exception e) {
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
