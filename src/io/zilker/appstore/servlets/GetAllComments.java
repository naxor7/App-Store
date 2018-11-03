package io.zilker.appstore.servlets;

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
import io.zilker.appstore.beans.Applications;
import io.zilker.appstore.controller.AppController;
import io.zilker.appstore.exceptions.BusinessException;

@WebServlet("/GetAllComments")
public class GetAllComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger LOGGER;   
	
    public GetAllComments() {
        super();
        LOGGER = Logger.getLogger(GetAllComments.class.getName());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Entered doGet");
		AppController appController = new AppController();
		try {
			HttpSession session = request.getSession(false);
			if (request.getSession(false).getAttribute("user") == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
				dispatcher.forward(request, response);
			} else {
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				Applications app = new Applications();
				app.setAppID(Integer.parseInt(request.getParameter("id")));
				Comments[] comments = appController.getAllComments(app);
				String jsonText = new Gson().toJson(comments);
				response.getWriter().write(jsonText);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
