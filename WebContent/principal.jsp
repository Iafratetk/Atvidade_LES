<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page
	import="java.text.SimpleDateFormat, java.util.List, java.util.ArrayList, model.Produto, model.Cliente, model.Usuario, model.Pedido, model.PedidoProduto"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>

</head>
   <nav class="red lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="./principal.jsp" class="brand-logo">Energize Company</a>
      <ul class="right hide-on-med-and-down">
      </ul>
	
      <ul id="nav-mobile" class="side-nav">
        </ul>
      </div>
  </nav>
<body>
<div align="right" valign="top">
	<a class="waves-effect waves-light btn" Style="background:red"  href="remover.jsp">Sair</a>
</div>
	<%
	Usuario u = (Usuario) session.getAttribute("usuarioautenticado");
	String nomeUsuario = u.getLogin();
	if(nomeUsuario==null){
		throw new ServletException("Nenhum Usuario logado");
	}
	%>
	<center>
	
	Seja Bem vindo! <%=nomeUsuario%>
	<h1>logado com sucesso!</h1>
	</div>
	<ul id="nav">
	</center>
	<div align="left" valign="top">
	<table>
	<td>
	<u><a class="waves-effect waves-light" href="./painel.jsp">Escritório</a></u>
	</td>
	<td>
	<a href="./editarlogin.jsp?cmd=editar&id=<%=u.getId()%>&login=<%=u.getLogin()%>&senha=<%=u.getSenha()%>&tipo=<%=u.getTipo()%>&idCliente=<%=u.getIdCliente()%>">Alterar Login</a>
	</td>
	<td>
	<a class="waves-effect waves-light" href="./venda.jsp">Fazer um pedido</a>
	</td>
	</table>
	</ul>
</body>
 <nav class="red lighten-1" role="navigation">
    
      <ul class="right hide-on-med-and-down">
      </ul>
	
      <ul id="nav-mobile" class="side-nav">
        </ul>
      </div>
  </nav>
</html>