package factory;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import factory.DBConnection;

public class DBQuery {
	private Statement statement =  null;
	
	public DBQuery() {
		try {
			this.statement = new DBConnection().getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// fazer o banco ainda***
	
	
}
