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

import org.apache.log4j.Logger;

import dao.BookSearchDAO;
import model.Books;

/**
 * Servlet implementation class SearchBook
 */
@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	private Connection connect;
	private Books book;
	private HttpSession session;
	private Logger logger;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBook() {
        super();
       
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connect = (Connection) request.getServletContext().getAttribute("Connection");
		logger = Logger.getLogger(SearchBook.class);
		try {
			book = new BookSearchDAO(connect, request.getParameter("searchBar")).searchName();
		} catch (SQLException e) {
			
			logger.error(e);
		}
		if(book!=null){
		request.setAttribute("book", book);	
		request.getRequestDispatcher("./userHomePage.jsp").forward(request, response);
		}else{
			response.sendRedirect("./userHomePage.jsp");
		}
	}

}
