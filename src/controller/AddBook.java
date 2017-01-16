package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import connection.MyConnection;
import dao.BookDao;
import model.Books;
import model.User;

/**
 * Servlet implementation class AddBook
 */
//@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private MyConnection con;
	private Connection connection;
	private HttpSession session;
	private Books books;
	private User user;
	private Logger logger;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger = Logger.getLogger(AddBook.class);
		session = request.getSession(true);
		String username=(String) session.getAttribute("username");
		
		String book=request.getParameter("book");
		String description=request.getParameter("description");
		String genre=request.getParameter("genre");
		
		String author=request.getParameter("author");
	
		con=new MyConnection();
		try {
			connection=con.connect();
		} catch (ClassNotFoundException e) {
			
			logger.info(e);
			
		}
		books=new Books();
		books.setTitle(book);
		books.setDescription(description);
		books.setAuthor(author);
		//books.setGenre(genre);
		BookDao bookDao=new BookDao();
		bookDao.getBook(genre,username,books, connection);
		response.sendRedirect("./admin.jsp");
		doGet(request, response);
	}

}
