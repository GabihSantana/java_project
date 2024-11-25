package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class Authenticator {
    private DBQueries query;

    public Authenticator(DBQueries query) {
        this.query = query;
    }

    public User authenticator(String email, String psswd) throws SQLException {
        String where = "email = '" + email + "' AND senha = '" + psswd + "'";
        ResultSet rs = query.select(where);

        if (rs.next()) {
            User user = new User();

            user.setNome(rs.getString("nome"));
            user.setEmail(rs.getString("email"));
            user.setSenha(rs.getString("senha")); 
            user.setCpf(rs.getString("cpf"));
            user.setTelefone(rs.getString("telefone")); 
            user.setCep(rs.getString("cep"));
            user.setEndereco(rs.getString("endereco"));
            user.setCidade(rs.getString("cidade"));
            user.setBairro(rs.getString("bairro"));
            user.setUf(rs.getString("uf"));

            return user;
        }
        return null; // Nenhum usuário encontrado
    }
}
