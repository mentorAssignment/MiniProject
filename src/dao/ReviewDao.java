package dao;
import java.util.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import connection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Books;
import model.Review;

public class ReviewDao {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private Logger logger;
	public ArrayList<Review> getReviews(Books book)
	{
		BasicConfigurator.configure();
		ArrayList<Review> reviews=new ArrayList<>();
		
		try {
			connection=new MyConnection().connect();
		} catch (ClassNotFoundException e) {
			logger.error("Exception:",e);
		}
		try {
			statement=connection.prepareStatement("select reviewId,rating,comment from def_bookreview where bookId=?");
			statement.setInt(1, book.getBookid());
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				Review review=new Review(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
				reviews.add(review);
			}
		} catch (SQLException e) {
			
			logger.error("Exception:",e);
		}
		return reviews;
	}
}
