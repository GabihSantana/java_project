package control;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.UserDAOImpl;
import model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDAOImpl DAOuser;

	public UserServlet() {
		DAOuser = new UserDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		switch (action) {
		case "/list":
			try {
				listUser(request, response);
			} catch (Exception e) {

			}
		}
	}

	// select, edit

	private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		List<User> userList = DAOuser.getUser();
		request.setAttribute("userList", userList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("listar_user.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		switch (action) {
		case "/new":
			try {
				newUser(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		case "/update":
			try {
				updateUser(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		// insert, update,
	}

	public void newUser(HttpServletRequest request, HttpServletResponse response) {

		String userNome = request.getParameter("nome");
		String userEmail = request.getParameter("email");
		String userSenha = request.getParameter("pwd");
		String userCpf = request.getParameter("cpf");
		String userTelefone = request.getParameter("tel");
		String userCep = request.getParameter("cep");
		String userEndereco = request.getParameter("end");
		String userCidade = request.getParameter("cid");
		String userBairro = request.getParameter("bairro");
		String userUF = request.getParameter("uf");

		User newUser = new User(userNome, userEmail, userSenha, userCpf, userEndereco, userBairro, userCidade, userUF, userCep,
				userTelefone);
		try {
            boolean isCreated = DAOuser.createUser(newUser);
            
            if (isCreated) {
            	response.sendRedirect(request.getContextPath() + "/list");
            } else {
                request.setAttribute("errorMessage", "Erro ao criar o usuário. Tente novamente.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void updateUser(HttpServletRequest request, HttpServletResponse response) {
		String userNome = request.getParameter("nome");
		String userEmail = request.getParameter("email");
		String userSenha = request.getParameter("pwd");
		String userCpf = request.getParameter("cpf");
		String userTelefone = request.getParameter("tel");
		String userCep = request.getParameter("cep");
		String userEndereco = request.getParameter("end");
		String userCidade = request.getParameter("cid");
		String userBairro = request.getParameter("bairro");
		String userUF = request.getParameter("uf");
	}

}
