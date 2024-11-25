package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import database.DBQueries;

public class UserDAOImpl implements IUserDAO {

	DBQueries UserQuery;

	public UserDAOImpl() {
		this.UserQuery = new DBQueries("usuarios",
				"idUsuario, email, senha, nome, cpf, endereco, bairro, cidade, uf, cep, telefone", "idUsuario");
	}

	@Override
	public boolean createUser(User user) throws SQLException, Exception {
		String[] values = new String[] { user.getEmail(), user.getSenha(), user.getNome(), user.getCpf(), user.getEndereco(),
				user.getBairro(), user.getCidade(), user.getUf(), user.getCep(), user.getTelefone()};
		try {
			return UserQuery.insert(values) > 0;
		} catch (Exception e) {
	        e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<User> getUser() throws SQLException, Exception {
		List<User> userList = new ArrayList<>();

		try (ResultSet rs = UserQuery.select("")) {
			while (rs.next()) {
				User usuario = new User();
				usuario.setIdUsuario(rs.getString("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setCep(rs.getString("cep"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setBairro(rs.getString("bairro"));
				usuario.setUf(rs.getString("uf"));

				userList.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public boolean updateUser(User user) throws SQLException, Exception {
		String[] update_values = new String[] { user.getNome(), user.getEmail(), user.getSenha(), user.getCpf(),
				user.getEndereco(), user.getBairro(), user.getCidade(), user.getUf(), user.getCep(),
				user.getTelefone() };
		try {
			UserQuery.update(update_values);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) throws SQLException, Exception {
		String[] delete_values = new String[] { user.getIdUsuario(), user.getNome(), user.getEmail(), user.getSenha(), user.getCpf(),
				user.getEndereco(), user.getBairro(), user.getCidade(), user.getUf(), user.getCep(),
				user.getTelefone() };
		try {
			UserQuery.delete(delete_values);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public User loginUser(String email, String senha) throws SQLException, Exception {
		String where = "email = '" + email + "' AND senha = '" + senha + "'";
		ResultSet rs = UserQuery.select(where);
		   
	    if (rs.next()) {
	    	User usuario = new User();
	    	usuario.setNome(rs.getString("nome"));
	    	usuario.setEmail(rs.getString("email"));
	    	usuario.setSenha(rs.getString("senha")); 
	    	usuario.setCpf(rs.getString("cpf"));
	    	usuario.setTelefone(rs.getString("telefone")); 
	    	usuario.setCep(rs.getString("cep"));
	    	usuario.setEndereco(rs.getString("endereco"));
	    	usuario.setCidade(rs.getString("cidade"));
	    	usuario.setBairro(rs.getString("bairro"));
	    	usuario.setUf(rs.getString("uf"));
	    	
			return usuario;
	    }else {
	    	return null;
	    }
	}
	
}
