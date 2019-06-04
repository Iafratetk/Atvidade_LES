package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import persistence.LoginDao;

/**
 * Servlet implementation class EditarLoginServlet
 */
@WebServlet("/editalogin")
public class EditarLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		int tipo = Integer.parseInt(request.getParameter("tipo"));
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		Usuario u = new Usuario();
		LoginDao uDao = new LoginDao();
		u.setId(id);
		u.setLogin(login);
		u.setSenha(senha);
		u.setTipo(tipo);
		u.setIdCliente(idCliente);
		try {
			uDao.atualizaUsuario(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./login.jsp");
	}

}
