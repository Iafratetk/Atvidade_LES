package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Usuario;
import persistence.LoginDao;



/**
 * Servlet implementation class CadastroUsuario
 */
@WebServlet(name = "CadastroUsuarioServlet", urlPatterns = { "/cadusuario" })
public class CadastroUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		System.out.println("entrou no get do cadusuario");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		LoginDao uDao = new LoginDao();
		List<Usuario> listaUsuarios = new ArrayList<>();
		String cmd = request.getParameter("cmd");
		if ("alterar".equals(cmd)) {
			
		}else if("remover".equals(cmd)) {
			
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				uDao.removerUsuario(id);
				listaUsuarios = uDao.pesquisarTodosUsuarios();
				session.setAttribute("USUARIO", listaUsuarios);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}else {
		try {
			System.out.println("entrou no try do cadusuario");
			listaUsuarios = uDao.pesquisarTodosUsuarios();
			session.setAttribute("USUARIO", listaUsuarios);


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		response.sendRedirect("./painel.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		String login =request.getParameter("login");
		String senha = request.getParameter("senha");
		int tipo = Integer.parseInt(request.getParameter("tipo"));
		System.out.println("entrou no post do cadproduto");
		LoginDao uDao = new LoginDao();
		Usuario u = new Usuario();
		u.setIdCliente(idCliente);
		u.setLogin(login);
		u.setSenha(senha);
		u.setTipo(tipo);
		
		try {
			System.out.println("entrou no try do cadproduto");
			uDao.insereUsuario(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./painel.jsp");
	}

}
