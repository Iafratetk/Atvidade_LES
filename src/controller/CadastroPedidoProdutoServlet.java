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

import model.PedidoProduto;

import persistence.PedidoProdutoDao;

/**
 * Servlet implementation class CadastroPedidoProdutoServlet
 */
@WebServlet("/cadpedidoproduto")
public class CadastroPedidoProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CadastroPedidoProdutoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd = request.getParameter("cmd");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("entrou no get do cadPedidoProduto");
		HttpSession session = request.getSession();
		PedidoProdutoDao pDao = new PedidoProdutoDao();
		List<PedidoProduto> listaPedidoProduto = new ArrayList<>();
		if ("editar".equals(cmd)) {
			System.out.println(cmd);
		} else if ("remover".equals(cmd)) {
			System.out.println(cmd);
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				pDao.removerPedidoProduto(id);
				listaPedidoProduto = pDao.pesquisarTodosPedidosProdutos();
				session.setAttribute("PEDIDOPRODUTO", listaPedidoProduto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try {
				System.out.println("entrou no try do cadpedidoProduto");
				listaPedidoProduto = pDao.pesquisarTodosPedidosProdutos();
				session.setAttribute("PEDIDOPRODUTO", listaPedidoProduto);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("./painel.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idPedido = Integer.parseInt(request.getParameter("idPedido"));
		int idProduto = Integer.parseInt(request.getParameter("idProduto"));
		Double valorProduto = Double.parseDouble(request.getParameter("valorProduto"));
		int quantidade = Integer.parseInt(request.getParameter("quantidadeProduto"));
		System.out.println("entrou no post do cadpedidoproduto");
		PedidoProdutoDao pDao = new PedidoProdutoDao();
		PedidoProduto p = new PedidoProduto();
		p.setIdPedido(idPedido);
		p.setIdProduto(idProduto);
		p.setValorProduto(valorProduto);
		p.setQuantidade(quantidade);
		try {
			System.out.println("entrou no try do cadpedido");
			pDao.inserePedidoProduto(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./painel.jsp");
	}

}
