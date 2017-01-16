package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Books;

public class BookSearchDAO {
	private Connection connect;
	private String bookName;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private Books book;

	public BookSearchDAO(Connection connect, String bookName) {

		this.connect = connect;
		this.bookName = bookName.toLowerCase();

	}

	public Books searchName() throws SQLException {

		preparedStatement = connect
				.prepareStatement("Select * from def_books natural join def_master where title=?");
		preparedStatement.setString(1, bookName);

		resultSet = preparedStatement.executeQuery();

		return new Books(resultSet.getInt("bookid"), resultSet.getString("title"), resultSet.getString("author"), resultSet.getInt("genre"), resultSet.getInt("status"), resultSet.getString("creationDate"), resultSet.getInt("createdBy"), resultSet.getString("modificationDate"), resultSet.getInt("modifiedBy"), resultSet.getString("description"));

	}

}
