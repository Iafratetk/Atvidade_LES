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
import model.Pedido;
import model.Produto;
import model.Usuario;
import persistence.ClienteDao;
import persistence.PedidoDao;
import persistence.ProdutoDao;

/**
 * Servlet implementation class SacolServlet
 */
@WebServlet("/sacola")
public class SacolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public List<Produto> listaSacola = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SacolServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String cmd = request.getParameter("cmd");
		int id = Integer.parseInt(request.getParameter("produtosSlc"));
		System.out.println(id);
		List<Produto> listaProduto = new ArrayList<>();
		
		//listaSacola = (List<Produto>) session.getAttribute("LISTASACOLA");
		ProdutoDao pDao = new ProdutoDao();
		try {
			listaProduto = pDao.pesquisarTodos();
			for (Produto p : listaProduto) {
				if (p.getId() == id) {
					listaSacola.add(p);
				}
			}

			session.setAttribute("LISTAPRODUTO", listaSacola);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./venda.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String cmd = request.getParameter("cmd");
		int qtd = 0;
		ProdutoDao pDao = new ProdutoDao();
		Produto p = new Produto();
		Pedido ped = new Pedido();
		Usuario u = new Usuario();
		Cliente c = new Cliente();
		u = (Usuario) session.getAttribute("usuarioautenticado");
		int idCliente = u.getIdCliente();
		double valorTotal= 0;
		for (Produto pro : listaSacola) {
			pro.getId();
			pro.getNome();
			qtd = pro.getQuantidade() - 1;
			pro.getValor();
			try {
				pDao.atualizaProdutos(pro);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			valorTotal = pro.getValor() + valorTotal;
		}
		int formaPagamento = Integer.parseInt(request.getParameter("formaSlc"));
		PedidoDao pedDao = new PedidoDao();
		ped.setIdCliente(idCliente);
		ped.setValorTotal(valorTotal);
		ped.setFormaPagamento(formaPagamento);
		try {
			pedDao.inserePedidos(ped);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Pedido> listaPedido = new ArrayList();
		try {
			listaPedido = pedDao.pesquisarTodosPedidos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numPedido = 0;
		for (Pedido pedido : listaPedido) {
			numPedido = pedido.getId();
		}
		String nome = "";
		List<Cliente> listaCli = new ArrayList();
		ClienteDao cDao = new ClienteDao();
		try {
			listaCli = cDao.pesquisarTodosClientes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Cliente cli : listaCli) {
			if(idCliente == cli.getId()) {
				nome = cli.getNome();
			}
		}
		session.setAttribute("FINAL1", numPedido);
		session.setAttribute("FINAL2", valorTotal);
		session.setAttribute("FINAL3", nome);
		listaSacola = new ArrayList();
		session.removeAttribute("LISTAPRODUTO");
		response.sendRedirect("./vendaRealizada.jsp");
		
		
		
	}

}
