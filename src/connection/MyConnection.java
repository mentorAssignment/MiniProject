package connection;
import java.sql.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class MyConnection {
	Connection connection;
	static Logger log=Logger.getLogger(MyConnection.class);
	public Connection connect() throws ClassNotFoundException {
		BasicConfigurator.configure();
		Class.forName("com.mysql.jdbc.Driver");
		try{
		 connection=DriverManager.getConnection("jdbc:mysql://localhost/miniproject","root","root");
		 log.info("connection established");
		}
		catch(SQLException e)
		{
			log.error(e);
		}
		return connection;
		
	}

}
