package DAO;
import java.sql.SQLException;
import java.util.List;

import model.User;

public interface IUserDAO {
	public boolean createUser(User user) throws SQLException, Exception;
	public List<User> getUser() throws SQLException, Exception;
	public boolean updateUser(User user) throws SQLException, Exception;
	public boolean deleteUser(User user) throws SQLException, Exception;
	public User loginUser(String email, String senha) throws SQLException, Exception;
}
