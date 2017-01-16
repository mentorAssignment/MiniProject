package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import connection.MyConnection;
import constants.BookReviewConstants;
import model.User;

public class RegistrationDAO {
		private User user;
		private PreparedStatement preparedStatement;
		private Connection connection;
		private DateFormat df;
		private Date dateobj;
		public RegistrationDAO(User user) throws ClassNotFoundException{
			
			this.user = user;
			
		}

		public boolean register() throws SQLException, ClassNotFoundException {
			

			connection = new MyConnection().connect();
			
			preparedStatement = connection.prepareStatement("Insert into def_users ("+BookReviewConstants.MASTER_ID+","
			+BookReviewConstants.USER_NAME+","
			+BookReviewConstants.PASSWORD+","
			+BookReviewConstants.NAME+",status,"
			+BookReviewConstants.CREATION_DATE+") values(?,?,?,?,?,?) ");
			
			preparedStatement.setInt(1, BookReviewConstants.USER_TYPE);
			preparedStatement.setString(2, user.getUserName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getName());
			preparedStatement.setString(5, BookReviewConstants.STATUS_ACTIVE );
			
			df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			dateobj = new Date();
			
			preparedStatement.setString(6, df.format(dateobj));
			
			
			
			return preparedStatement.executeUpdate()>0;
			
		}
}
