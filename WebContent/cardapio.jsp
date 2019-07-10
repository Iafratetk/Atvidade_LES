<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page
	import="java.text.SimpleDateFormat, java.util.List, java.util.ArrayList, model.Produto"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Cardápio</title>
</head>
   <nav class="red lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="./index.jsp" class="brand-logo">Energize Company</a>
      <ul class="right hide-on-med-and-down">
      </ul>
	
      <ul id="nav-mobile" class="side-nav">
        </ul>
      </div>
  </nav>
<body>
	<div align="right" valign="top">
	<form>
		<u><a class="waves-effect waves-light"  Style="color:red" href="./login.jsp">Login</a></u>
		 <a class="waves-effect waves-light btn"  Style="background:red" href="./cadastro.jsp">cadastre-se</a>
		 </form>
	</div>
	
	<center>
	<h2 Style="color:red"><u><td>CARDÁPIO!</td></u></h2>
	<div>	
	<form action="cardapio" method="get">
	
			
		 <button class="btn waves-effect waves-light" type="submit" name="action">Ver
   		 <i class="material-icons right">V</i>
 		 </button>
	
		<%
			List<Produto> lp = new ArrayList<>();
			lp = (List<Produto>) session.getAttribute("CARDAPIO");
			if (lp != null) {
				for (Produto p : lp) {
		%>
		<table border="1">
			<tr>
				<th>id:</th>
				<td><%=p.getId()%></td>
				<th>nome:</th>
				<td><%=p.getNome()%></td>
				<th>quantidade:</th>
				<td><%=p.getQuantidade()%></td>
				<th>valor:</th>
				<td><%=p.getValor()%></td>
				<td>
				</td>
			</tr>
		</table>
		<%
			System.out.println(p.getNome());
				}
			} else {
			
			}
		%>

	</form>
	</div>
	</center>
</body>
 <nav class="red lighten-1" role="navigation">
    
      <ul class="right hide-on-med-and-down">
      </ul>
	
      <ul id="nav-mobile" class="side-nav">
        </ul>
      </div>
  </nav>
</html>