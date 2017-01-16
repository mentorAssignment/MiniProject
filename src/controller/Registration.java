package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import connection.MyConnection;
import constants.BookReviewConstants;
import dao.RegistrationDAO;
import model.User;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	
	private Boolean isInserted = false;
	private PrintWriter out;
	private Logger logger;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init() throws ServletException {
		
	}
	public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger = Logger.getLogger(Registration.class);
		out = response.getWriter();
		try {
			isInserted = new RegistrationDAO(new User
													(request.getParameter("userName"),
													request.getParameter("password"),
													request.getParameter("name"), 
													/*BookReviewConstants.USER_TYPE)*/2)).register();
		} catch (ClassNotFoundException |SQLException e) {
			
			logger.info(e);
		}
		
		
		if(isInserted){
			response.sendRedirect("index.html");
		}else{
			out.println("Registration Unsuccessfull");
			
		}
			
		
	}

}
