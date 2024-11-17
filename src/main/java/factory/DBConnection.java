package factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private String host;
	private String port;
	private String schema;
	private String user;
	private String password;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private Connection connection = null;
	
	// parâmetros p conectar ao bd
	public DBConnection(String host, String port, String schema, String user, String password) {
		this.setHost(host);
		this.setPort(port);
		this.setSchema(schema);
		this.setUser(user);
		this.setPassword(password);
		this.doConnection();
	}
	
	public DBConnection() {
		this.setHost	("localhost");
		this.setPort	("3306");
		this.setSchema	("dashboard_datasus");
		this.setUser	("root");
		this.setPassword("senha");
		this.doConnection();
	}
	
	// realizar a conexão com o banco
	private void doConnection() {
		// ajustando o fuso
		String timezone = "&useTimezone=true&serverTimezone=UTC";
		String url = "jdbc:mysql://"+this.host+":"+port+"/"+this.schema+"?user="+this.user+"&password="+this.password+timezone;
		
		try {
			// Class.forName -> lê o driver do banco de dados
			Class.forName(driver).getConstructor().newInstance();
			
			// classe que gerencia o driver = cria uma nova instância
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			// estabelece uma sessão com o bd
			this.connection = DriverManager.getConnection(url);
			
		} catch (InstantiationException e){
			e.printStackTrace();
		} catch (IllegalAccessException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	// teste de conexão
	public void testeConexao() {
		try {
			Connection con = this.getConnection();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = ( host.isEmpty() ? "localhost" : host ) ;
	}
	
	public String getPort() {
		return port;
	}
	
	public void setPort(String port) {
		this.port = ( port.isEmpty() ? "3306" : port ) ;
	}
	
	public String getSchema() {
		return schema;
	}
	
	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Connection getConnection() {
		return (this.connection);
	}

}
