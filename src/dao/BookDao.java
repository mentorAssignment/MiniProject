package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import constants.BookReviewConstants;

import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import connection.MyConnection;
import model.Books;

public class BookDao {
	private Books book;
	private Connection connection;
	private PreparedStatement statement;
	private PreparedStatement statement1;
	private PreparedStatement statement3;
	private ResultSet resultSet;
	private ResultSet resultSet2;
	private DateFormat dateFormat;
	private DateFormat df;
	private Date today;
	private String reportDate;
	private static ArrayList<String> genreMapping=null;
	final static Logger logger=Logger.getLogger(BookDao.class);
	
	public BookDao(){
		
	}
	
	public Books getBook(String genre,String username,Books book,Connection connection){
		this.book=book;
		this.connection=connection;
		try {
			
			 df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			today = Calendar.getInstance().getTime();
			reportDate = df.format(today);
			
			statement3=connection.prepareStatement("select userId from def_users where userName=?");
			statement3.setString(1, username);
			resultSet2=statement3.executeQuery();
			resultSet2.next();
				
			statement1=connection.prepareStatement("select masterid from def_master where name=?");
			statement1.setString(1,genre);
			resultSet=statement1.executeQuery();
			resultSet.next();
			book.setGenre(resultSet.getInt(1));
			
			
			statement=connection.prepareStatement("insert into def_books(title,author,masterid,description,status,createdBy,creationDate,modifiedBy,modificationDate) values(?,?,?,?,?,?,?,?,?)");
			
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(3, book.getGenre());
			statement.setString(4, book.getDescription());
			statement.setString(5, BookReviewConstants.STATUS_ACTIVE);
			statement.setInt(6, resultSet2.getInt(1));
			statement.setString(7, reportDate);
			statement.setInt(8, resultSet2.getInt(1));
			statement.setString(9, reportDate);
			statement.executeUpdate();
		} catch (SQLException e) {
			
			logger.error(e);
		}
		
		return book;
	}
	
	public boolean updateBook(Books book,Connection connection)
	{
		this.book=book;
		this.connection=connection;
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String modificationDate=dateFormat.format(date);
		try
		{
			statement=connection.prepareStatement("update def_books set title=?, author=?, modificationDate=?, modifiedBy=?, description=? where bookid=?");
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setString(3, modificationDate);
			statement.setInt(4, 1);
			statement.setString(5, book.getDescription());
			statement.setInt(6, book.getBookid());
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			logger.error("Exception:",e);
			return false;
		}
			return true;
	}
	public String[] getGenre(Connection connection)
	{
	
		int i=0;
		this.connection=connection;
		String a[]=new String[10];
		try
		{
			statement=connection.prepareStatement("select name from def_master where description=?");
			statement.setString(1,"genre");
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				a[i]=resultSet.getString(1);
				i++;
			}
		}
		catch(SQLException e)
		{
			logger.error("Exception:",e);
		}
		return a;
	}
	public ArrayList<Books> getBooksByGenre(String genre,Connection connection)
	{	
		BasicConfigurator.configure();
		ArrayList<Books> s=new ArrayList<>();
		this.connection=connection;
		try
		{
			
			statement=connection.prepareStatement("SELECT bookid, title, author,description, masterid FROM def_books WHERE masterid =(SELECT masterid FROM def_master WHERE NAME=?)AND STATUS=?");
			statement.setString(1,genre);
			statement.setString(2,"active");
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				s.add(new Books(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4), genreMapping.get(resultSet.getInt(5)-4) ));
			}
		}
		catch(SQLException e)
		{
			logger.error("Exception:",e);
		}
		return s;
	}
	
	public ArrayList<String> getGenreMapping()
	{
		BasicConfigurator.configure();
		if(genreMapping==null)
		{
			int i=1;
			genreMapping = new ArrayList<String>();
			genreMapping.add("dummy genre");
			try
			{
				connection = (new MyConnection()).connect();
				statement=connection.prepareStatement("select name from def_master where description=?");
				statement.setString(1,"genre");
				resultSet=statement.executeQuery();
				while(resultSet.next())
				{
					genreMapping.add(resultSet.getString(1));
				}
			}
			catch(SQLException | ClassNotFoundException e)
			{
				logger.error("Exception:",e);
			}
		}
		
		return genreMapping;
	}
	public boolean deleteBook(Books book)
	{
		BasicConfigurator.configure();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String modificationDate=dateFormat.format(date);
		try
		{
			connection = (new MyConnection()).connect();
			statement=connection.prepareStatement("UPDATE def_books SET STATUS=?, modificationDate=?, modifiedBy=? WHERE bookid=?");
			statement.setString(1, "deactive");
			statement.setString(2, modificationDate);
			statement.setInt(3, 1);
			statement.setInt(4, book.getBookid());
			statement.executeUpdate();
		}
		catch(SQLException | ClassNotFoundException e)
		{
			logger.error("Exception:",e);
			return false;
		}
		return true;
	}
}
