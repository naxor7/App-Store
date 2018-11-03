package io.zilker.appstore.servlets;

import java.util.*;
import java.io.IOException;
import java.util.logging.Logger;
import io.zilker.appstore.beans.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import io.zilker.appstore.controller.AppController;
import io.zilker.appstore.exceptions.BusinessException;

@WebServlet("/GetAllApps")
public class GetAllApps extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger LOGGER;

	public GetAllApps() {
		super();
		LOGGER = Logger.getLogger(GetAllApps.class.getName());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Entered doGet");
		AppController appController = new AppController();
		try {
			HttpSession session = request.getSession(false);
			if (request.getSession(false).getAttribute("user") == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
				dispatcher.forward(request, response);
			} else {
				Map<String, ArrayList<Applications>> appMapping = new LinkedHashMap<>();
				ArrayList<Applications> music = new ArrayList<Applications>();
				ArrayList<Applications> games = new ArrayList<Applications>();
				ArrayList<Applications> entertainment = new ArrayList<Applications>();
				ArrayList<Applications> education = new ArrayList<Applications>();
				ArrayList<Applications> politics = new ArrayList<Applications>();
				ArrayList<Applications> other = new ArrayList<Applications>();
				Applications[] allApps = appController.getAllApps();
				for (int i = 0; i < allApps.length; i++) {
					if (allApps[i].getCategory().getCategoryID() == 1) {
						music.add(allApps[i]);
					} else if (allApps[i].getCategory().getCategoryID() == 2) {
						games.add(allApps[i]);
					} else if (allApps[i].getCategory().getCategoryID() == 3) {
						entertainment.add(allApps[i]);
					} else if (allApps[i].getCategory().getCategoryID() == 4) {
						education.add(allApps[i]);
					} else if (allApps[i].getCategory().getCategoryID() == 5) {
						politics.add(allApps[i]);
					} else if (allApps[i].getCategory().getCategoryID() == 6) {
						other.add(allApps[i]);
					}
				}
				String appMappingJson = "[" + new Gson().toJson(music) + "," + new Gson().toJson(games) + ","
						+ new Gson().toJson(entertainment) + "," + new Gson().toJson(education) + ","
						+ new Gson().toJson(politics) + "," + new Gson().toJson(other) + "]";
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(appMappingJson);
			}
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
		doGet(request, response);
		LOGGER.info("Exited doPost");
	}

}
