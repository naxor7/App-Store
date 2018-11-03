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

import io.zilker.appstore.beans.Applications;
import io.zilker.appstore.beans.GenericUser;
import io.zilker.appstore.beans.StoreUser;
import io.zilker.appstore.controller.AppController;
import io.zilker.appstore.controller.UserController;
import io.zilker.appstore.exceptions.BusinessException;

@WebServlet("/TesterVerify")
public class TesterVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger LOGGER;

	public TesterVerify() {
		super();
		LOGGER = Logger.getLogger(TesterVerify.class.getName());
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
				UserController userController = new UserController();
				AppController appController = new AppController();
				GenericUser user = new GenericUser();
				user = (GenericUser) session.getAttribute("user");
				if (userController.isTester(user) || userController.isAdmin(user)) {
					Applications app = new Applications();
					app.setAppID(Integer.parseInt(request.getParameter("id")));
					appController.updateTesterApproved(app);
					if (request.getParameter("window").compareTo("pendingapps") == 0) {
						RequestDispatcher dispatcher = request.getRequestDispatcher("TesterPendingApps");
						dispatcher.forward(request, response);
					} else if(request.getParameter("window").compareTo("reportedapps") == 0){
						appController.setReportCountToZero(app);
						RequestDispatcher dispatcher = request.getRequestDispatcher("TesterReportedApps");
						dispatcher.forward(request, response);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		} finally {
			LOGGER.info("Exited doGet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
