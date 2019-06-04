package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import model.Usuario;
import persistence.ClienteDao;
import persistence.LoginDao;

/**
 * Servlet implementation class cadastreSeServlet
 */
@WebServlet("/cadastreSeServlet")
public class cadastreSeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cadastreSeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=0;
		HttpSession session = request.getSession();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String nome = request.getParameter("nome");
		String telefone =request.getParameter("telefone");
		String endereco = request.getParameter("endereco");
		System.out.println(nome + telefone + endereco);
		String str = "";
		ClienteDao cDao = new ClienteDao();
		Cliente c = new Cliente();
		List<Cliente> listaCliente = new ArrayList<>();
		
		c.setNome(nome);
		c.setTelefone(telefone);
		c.setEndereco(endereco);
		

		try {
			cDao.insereClientes(c);
			listaCliente = cDao.pesquisarTodosClientes();
			for (Cliente cD : listaCliente) {
				id = cD.getId();
			}
			int idCliente = id;
			String login =request.getParameter("login");
			String senha = request.getParameter("senha");
			int tipo = 1;
			
			LoginDao uDao = new LoginDao();
			Usuario u = new Usuario();
			
			u.setIdCliente(idCliente);
			u.setLogin(login);
			u.setSenha(senha);
			u.setTipo(tipo);
			
			uDao.insereUsuario(u);
			str = "cadastrado concluido com sucesso!";
			session.setAttribute("CADASTRADO", str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("./remover.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
