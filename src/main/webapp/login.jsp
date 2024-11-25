<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html lang="pt-br">
<script type="text/javascript">
        <% if (request.getAttribute("loginError") != null) { %>
            alert('<%= request.getAttribute("loginError") %>');
        <% } %>
    </script>
<head>
<link rel="icon" href="img/login_img.webp" type="image/x-icon">
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="style/login_style.css">
</head>
<body>
	<div class="container rounded-3 mt-5 mx-auto p-4">
		<br />
		<h2 class="text-center">Login</h2>
		<form action="login" method="post">
			<div class="mb-3 mt-3">
				<label for="email">Email:</label> <input type="email"
					class="form-control" id="email" placeholder="Insira o email"
					name="email">
			</div>
			<div class="mb-3">
				<label for="pwd">Senha:</label> <input type="password"
					class="form-control" id="pwd" placeholder="Insira a senha"
					name="pwd">
			</div>
			<div class="form-check mb-3">
				<label class="form-check-label"> <input
					class="form-check-input" type="checkbox" name="remember">
					Remember me
				</label>
			</div>
			<br />
			<div class="d-flex justify-content-between">
				<a href="" class="btn bg-light">Cadastrar</a>
				<button type="submit" class="btn btn-primary">Entrar</button>
			</div>
		</form>
	</div>
</body>
</html>