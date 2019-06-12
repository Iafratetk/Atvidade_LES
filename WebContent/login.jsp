<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.text.SimpleDateFormat, java.util.List, java.util.ArrayList, model.Usuario"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type="text/javascript">
	function validarLogin(){
		if(document.formLogin.usuario.value==""){
			alert("campo usuario não informado");
			return false;	
		}
		if(document.formLogin.senha.value==""){
			alert("campo senha não informado");
			return false;
		}
		document.formLogin.submit();
	}
</script>
<title>Insert title here</title>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
  <style>
    body {
      display: flex;
      min-height: 100vh;
      flex-direction: column;
    }

    main {
      flex: 1 0 auto;
    }

    body {
      background: #fff;
    }

    .input-field input[type=date]:focus + label,
    .input-field input[type=text]:focus + label,
    .input-field input[type=email]:focus + label,
    .input-field input[type=password]:focus + label {
      color: #e91e63;
    }

    .input-field input[type=date]:focus,
    .input-field input[type=text]:focus,
    .input-field input[type=email]:focus,
    .input-field input[type=password]:focus {
      border-bottom: 2px solid #e91e63;
      box-shadow: none;
    }
  </style>
</head>
<body>
	<form name="formLogin" action="login" method="post">
  <div class="section"></div>
  <main>
    <center>
      <img class="responsive-img" style="width: 250px;" src="https://media.giphy.com/media/lwhDD4j3IFCKlOhMS8/giphy.gif"/>
      <div class="section"></div>
		<%
		String erro = (String) session.getAttribute("ERROR");
		session.setAttribute("ERROR", "");
		if (erro.equals(null)){
			erro = "";
		}
		%>
		<h7 style="color:red"><%=erro%></h7>
      <h5 class="indigo-text">Faça aqui seu login</h5>
      <div class="section"></div>

      <div class="container">
        <div class="z-depth-1 grey lighten-4 row" style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">

          <form class="col s12" method="post">
            <div class='row'>
              <div class='col s12'>
              </div>
            </div>

            <div class='row'>
              <div class='input-field col s12'>
     <input type="text" name="usuario"
				placeholder="Usuário"></input>

              </div>
            </div>

            <div class='row'>
              <div class='input-field col s12'>
 <input
				type="password" name="senha" placeholder="Senha"></input>

              </div>
              <label style='float: right;'>

							</label>
            </div>

            <br />
            <center>
              <div class='row'>
  			<input class="waves-effect waves-light btn" Style="background:red"  type="button" value="entrar" name="entrar" onclick="validarLogin()"></input>

              </div>
            </center>
          </form>
        </div>
      </div>
      	<a class="waves-effect waves-light"     Style="color:red"    href="./cadastro.jsp">cadastre-se</a>

    </center>

    <div class="section"></div>
    <div class="section"></div>
  </main>

  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
	</form>
</body>
</html>