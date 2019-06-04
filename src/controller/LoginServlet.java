package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import exception.InvalidUserException;
import model.Usuario;
import persistence.LoginDao;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		validaUsuarioESenha(req);
		RequestDispatcher rd = req.getRequestDispatcher("principal.jsp");
		rd.forward(req, resp);
	}

	private void validaUsuarioESenha(HttpServletRequest req) throws ServletException {
		// TODO Auto-generated method stub
		String nomeUsuario = req.getParameter("usuario");
		String senhaUsuario = req.getParameter("senha");	
		boolean validar = false;
		List<Usuario> lu = new ArrayList();
		Usuario user = new Usuario();
		LoginDao uDao = new LoginDao();
		try {
			lu = uDao.pesquisarTodosUsuarios();
			for (Usuario u : lu) {
				System.out.println(u.getLogin());
				if((nomeUsuario.trim().equals(u.getLogin())) && (senhaUsuario.trim().equals(u.getSenha()))) {
					validar = true;
					user.setId(u.getId());
					user.setLogin(u.getLogin());
					user.setSenha(u.getSenha());
					user.setTipo(u.getTipo());
					user.setIdCliente(u.getIdCliente());
					System.out.println("usuario Valido");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if (validar == false)
			throw new InvalidUserException ("Login ou Senha inválido");
			HttpSession session = req.getSession();
			session.setAttribute("usuarioautenticado", user);
	}

}
