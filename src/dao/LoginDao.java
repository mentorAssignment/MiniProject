package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import constants.BookReviewConstants;
import model.User;

public class LoginDao {
	private User user;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private Logger logger;

	public LoginDao(User user, Connection connection) {
		logger = Logger.getLogger(LoginDao.class);
		if (user != null && connection != null) {

			this.user = user;
			this.connection = connection;

			try {
				//have to change this query because natural join returning nothing
				preparedStatement = connection.prepareStatement(
						"select masterid from def_users where username = ? and password = ?");
				preparedStatement.setString(1, user.getUserName());
				preparedStatement.setString(2, user.getPassword());

			} catch (SQLException e) {

				logger.error(e);
			}
		} else {

		}

	}

	public boolean authenticate() {
		try {
			resultSet = preparedStatement.executeQuery();
			
			
			if (resultSet.next()) {
				
				if (BookReviewConstants.USER_TYPE == resultSet.getInt("masterid")) {
					user.setType(BookReviewConstants.USER_TYPE);
				} else {
					user.setType(BookReviewConstants.ADMIN_TYPE);
				}
				return true;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return false;
	}

}
