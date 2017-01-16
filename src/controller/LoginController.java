package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.MyConnection;
import constants.BookReviewConstants;
import dao.LoginDao;
import model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private Connection connection;
	private HttpSession session;
	private boolean isValid;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	try {
			connection= new MyConnection().connect();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	request.getServletContext().setAttribute("Connection", connection);
	
	User userBean = new User(request.getParameter("userName"), request.getParameter("password"), null, 0);
	
	isValid = new LoginDao(userBean,connection).authenticate();
	

	if (isValid) {
		session = request.getSession();
		session.setAttribute("username", userBean.getUserName());
		if(BookReviewConstants.USER_TYPE == userBean.getType()){
		
				request.getRequestDispatcher("./userHomePage.jsp").forward(request, response);
		}else{
			
			request.getRequestDispatcher("./admin.jsp").forward(request, response);
		}
	} else {
		
		response.sendRedirect("index.html");
	}
	
	}

}
