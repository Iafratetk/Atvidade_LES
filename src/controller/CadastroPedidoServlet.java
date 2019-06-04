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


import model.Pedido;

import persistence.PedidoDao;



/**
 * Servlet implementation class CadastroPedidoServlet
 */
@WebServlet("/cadpedido")
public class CadastroPedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroPedidoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("entrou no get do cadPedido");
		HttpSession session = request.getSession();
		PedidoDao pDao = new PedidoDao();
		List<Pedido> listaPedido = new ArrayList<>();
		String cmd = request.getParameter("cmd");
		if("alterar".equals(cmd)) {
			
		}else if("remover".equals(cmd)) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				pDao.removerPedido(id);
				listaPedido = pDao.pesquisarTodosPedidos();
				session.setAttribute("PEDIDO", listaPedido);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {
		try {
			System.out.println("entrou no try do cadpedido");
			listaPedido = pDao.pesquisarTodosPedidos();
			session.setAttribute("PEDIDO", listaPedido);


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
		int  idCliente = Integer.parseInt(request.getParameter("idCliente"));
		Double valorTotal =Double.parseDouble(request.getParameter("valorTotal"));
		int formaPagamento = Integer.parseInt(request.getParameter("formaPagamento"));
		System.out.println("entrou no post do cadpedido");
		PedidoDao pDao = new PedidoDao();
		Pedido p = new Pedido();
		p.setIdCliente(idCliente);
		p.setValorTotal(valorTotal);
		p.setFormaPagamento(formaPagamento);
		try {
			System.out.println("entrou no try do cadpedido");
			pDao.inserePedidos(p);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./painel.jsp");
	}

}
