package io.zilker.appstore.servlets;

import java.io.IOException;
import io.zilker.appstore.beans.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import io.zilker.appstore.beans.Applications;
import io.zilker.appstore.controller.AppController;
import io.zilker.appstore.controller.UserController;
import io.zilker.appstore.exceptions.BusinessException;

@WebServlet("/TesterReportedApps")
public class TesterReportedApps extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger LOGGER;

	public TesterReportedApps() {
		super();
		LOGGER = Logger.getLogger(TesterReportedApps.class.getName());
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
				AppController appController = new AppController();
				UserController userController = new UserController();
				Applications[] reportedApps = appController.getReportedApps();
				if (reportedApps != null) {
					int[] sameName = new int[reportedApps.length];
					float[] userRating = new float[reportedApps.length];
					int[] userApps = new int[reportedApps.length];
					int[] sameCategoryApps = new int[reportedApps.length];
					int[] sameDescription = new int[reportedApps.length];
					for (int i = 0; i < reportedApps.length; i++) {
						sameName[i] = appController.findAppsWithAppName(reportedApps[i]);
						GenericUser user = new GenericUser();
						user.setUserID(reportedApps[i].getUserID());
						userRating[i] = userController.averageUserReviews(user);
						userApps[i] = userController.userPublishedAppCount(user);
						sameCategoryApps[i] = appController.findAppsWithAppNameSameCategory(reportedApps[i]);
						sameDescription[i] = appController.findAppsWithDescription(reportedApps[i]);
					}
					request.setAttribute("reportedApps", reportedApps);
					request.setAttribute("sameName", sameName);
					request.setAttribute("userRating", userRating);
					request.setAttribute("userApps", userApps);
					request.setAttribute("sameCategoryApps", sameCategoryApps);
					request.setAttribute("sameDescription", sameDescription);
				}
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/WEB-INF/pages/tester-profile-reportedapps.jsp");
				dispatcher.include(request, response);
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
		doGet(request, response);
	}

}
