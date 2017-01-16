package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import dao.BookDao;
import dao.ReviewDao;
import model.Books;
import model.Review;

/**
 * Servlet implementation class ProcessInput
 */
@WebServlet("/ProcessInput")
public class ProcessInput extends HttpServlet {
	Logger logger;
	HttpSession session;
	Books processBook;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessInput() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger=Logger.getLogger(ProcessInput.class);
		BasicConfigurator.configure();
		session = request.getSession(true);
		ArrayList<Review> reviews=new ArrayList<>();
		int processBookId = 0;
		String parame = "";
		parame=request.getParameter("hidden");
		logger.info("parame value:"+parame);
		
		processBookId = Integer.parseInt(parame.split("-")[1]);
		logger.info("yo man!!!"+processBookId);
		
		processBook = new Books();
		
		ArrayList<Books> bookList = (ArrayList<Books>)session.getAttribute("history");
		for(int i=0;i<bookList.size();i++)
		{
			if(bookList.get(i).getBookid()==processBookId)
			{
				processBook = bookList.get(i);
			}
		}
		reviews=new ReviewDao().getReviews(processBook);
		session.setAttribute("processBook", processBook);
		session.setAttribute("reviews", reviews);
		
		if(parame.startsWith("update")) {
			
			response.sendRedirect("UpdateForm.jsp");
		}
		else if(parame.startsWith("view")) {
			
			response.sendRedirect("ViewBook.jsp");
		}
		else if(parame.startsWith("delete")) 
		{
			//WRITE CODE FOR DELETE IN DB
			
			if(new BookDao().deleteBook(processBook))
			{
				
				logger.info("deleted");
				bookList.remove(processBook);
				session.setAttribute("history", bookList);
				
				response.sendRedirect("AdminUpdate.jsp");
			}
			else
			{
				logger.info("Cannot delete");
			}
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
