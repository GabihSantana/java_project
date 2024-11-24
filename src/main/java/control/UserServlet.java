package control;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBQueries;
import model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBQueries UserQuery;
	User usuario = new User();

	public UserServlet() {
		this.UserQuery = new DBQueries("usuarios", "idUsuario, email, senha, nome, cpf, endereco, bairro, cidade, uf, cep, telefone", "idUsuario");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {
				insertUser(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteUser(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				selectUser(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateUser(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/list":
			try {
		        listUser(request, response);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			break;
		default:
		    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Página não encontrada");
		    break;
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro_user.jsp");
		dispatcher.forward(request, response);
	}

	public static String[] extractValues(User usuario) {
		return new String[] { usuario.getEmail(), usuario.getSenha(), usuario.getNome(), usuario.getCpf(),
				usuario.getEndereco(), usuario.getBairro(), usuario.getCidade(),
				 usuario.getUf(), usuario.getCep(), usuario.getTelefone() };
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("pwd"));
		usuario.setCpf(request.getParameter("cpf"));
		usuario.setTelefone(request.getParameter("tel"));
		usuario.setCep(request.getParameter("cep"));
		usuario.setEndereco(request.getParameter("end"));
		usuario.setCidade(request.getParameter("cid"));
		usuario.setBairro(request.getParameter("bairro"));
		usuario.setUf(request.getParameter("uf"));

		int result = UserQuery.insert(extractValues(usuario));

		if (result > 0) {
		    response.sendRedirect("inicio.jsp");
		} else {
		    response.getWriter().println("Erro ao inserir o usuário.");
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserQuery.delete(extractValues(usuario));
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("pwd"));
		usuario.setCpf(request.getParameter("cpf"));
		usuario.setTelefone(request.getParameter("tel"));
		usuario.setCep(request.getParameter("cep"));
		usuario.setEndereco(request.getParameter("end"));
		usuario.setCidade(request.getParameter("cid"));
		usuario.setBairro(request.getParameter("bairro"));
		usuario.setUf(request.getParameter("uf"));

		UserQuery.update(extractValues(usuario));
		response.sendRedirect("list");
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
	    List<User> userList = new ArrayList<>(); // lista p armazenar o retorno

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
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados.");
	        return; 
	    }
	    // atributo da requisição
	    request.setAttribute("userList", userList);
	    // Encaminha para o JSP
	    RequestDispatcher dispatcher = request.getRequestDispatcher("listar_user.jsp");
	    dispatcher.forward(request, response);
	}
	
	private void selectUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultSet rs = UserQuery.select("");
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro_user.jsp");
		response.sendRedirect("list");
		request.setAttribute("user", rs);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
