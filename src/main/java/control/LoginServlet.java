package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import DAO.UserDAOImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDAOImpl UserDAO;
	
    public LoginServlet() {
    	UserDAO = new UserDAOImpl();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userMail = request.getParameter("email");
		String password = request.getParameter("pwd");
				
		if (userMail != null && password != null) {
			try {
				User user = UserDAO.loginUser(userMail, password);
				if(user != null) {
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("inicio.jsp");
                    
				}else {
					 request.setAttribute("loginError", "Credenciais inválidas!");
	                 RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	                 dispatcher.forward(request, response);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros de login inválidos.");
        }
	}

}
