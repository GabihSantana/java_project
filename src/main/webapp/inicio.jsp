<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="Error.jsp" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <title>Dashboard - DATASUS</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="style/main_style.css">
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-sm fixed-top">
  		<div class="container-fluid">
    		<a class="navbar-brand" href="#">Logo</a>
    		<!-- Responsividade -->
    		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
      			<span class="navbar-toggler-icon"></span>
    		</button>
    		<div class="collapse navbar-collapse" id="navbarNav">
      			<ul class="navbar-nav ms-auto">
        			<li class="nav-item">
          				<a class="nav-link" href="login">Início</a>
        			</li>
        			<li class="nav-item">
          				<a class="nav-link" href="#">Gráficos</a>
        			</li>
        			<li class="nav-item">
          				<a class="nav-link" href="#">Tabelas</a>
        			</li>    
      			</ul>
    		</div>
  		</div>
	</nav>
	
	<section>
		<h1>Cadastrar um Usuário</h1>
		<a href="cadastro_user.jsp">Cadastrar</a>
		<h1>Listar Usuários</h1>
		<a href="<%=request.getContextPath()%>/list">Listar</a>
	</section>
	
	
	
</body>
</html>