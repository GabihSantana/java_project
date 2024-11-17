package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import factory.DBQuery;
import model.User;

@WebServlet(urlPatterns = { "/Controller", "/inicio", "/insert_user" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBQuery db = new DBQuery();
	User usuario = new User();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/inicio")) {
			inicio(request, response);
		} else if (action.equals("/insert_user")) {
			novoUser(request, response);
		} else {
			response.sendRedirect("login.jsp");
		}
	}

	// Ir para tela inicio
	protected void inicio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("inicio.jsp");
	}

	// Novo usuário
	protected void novoUser(HttpServletRequest request, HttpServletResponse response)
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
		
		// Inserir passando o obj usuario
		db.inserir(usuario);
		// redireciona
		response.sendRedirect("inicio");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
