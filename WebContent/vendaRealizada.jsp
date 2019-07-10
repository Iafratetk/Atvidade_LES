<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page
	import="java.text.SimpleDateFormat, java.util.List, java.util.ArrayList, model.Produto, persistence.ProdutoDao, model.Usuario"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Venda Realizada Com Sucesso!</title>
</head>
	<%
	Usuario u = (Usuario) session.getAttribute("usuarioautenticado");
	String nomeUsuario = u.getLogin();
	if(nomeUsuario==null){
		throw new ServletException("Nenhum Usuario logado");
	}
	%>
	<%
		int numPedido =(int) session.getAttribute("FINAL1");
		double valorTotal = (double) session.getAttribute("FINAL2");
		String nome = (String) session.getAttribute("FINAL3");
	%>
	<nav class="red lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="./index.jsp" class="brand-logo">Energize Company</a>
      <ul class="right hide-on-med-and-down">
      </ul>
	
      <ul id="nav-mobile" class="side-nav">
        </ul>
      </div>
  </nav>
  <div align="right" valign="top">
	<a class="waves-effect waves-light btn" Style="background:red"  href="remover.jsp">Sair</a>
</div>
<body>
	<center>
	<h3 style="color:red">O seu pedido Nº <%=numPedido%> foi finalizado com sucesso!</h3>
	<h4 style="color:red">no valor de <%=valorTotal%></h4>
	<h4 style="color:red"> pedido relizado por <%=nome %></h4>
	<u><a class="waves-effect waves-light btn" Style="background:red" href="./principal.jsp">Voltar para a pagina principal</a></u>
	</center>
</body>
</html>