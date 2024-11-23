<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<title>Cadastrar Usuário</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- Adicionando JQuery API de CEP -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="style/style_main.css">
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-sm fixed-top">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Logo</a>
			<!-- Responsividade -->
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link" href="login">Início</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Gráficos</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Tabelas</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<section>
		<div class="container rounded-3 mt-5 mx-auto p-4">
			<br />
			<h2 class="text-center">Cadastrar um Usuário</h2>
			<form id="form_cad" action="<%=request.getContextPath()%>/insert"
				method="post">
				<div class="mb-3 mt-3">
					<label for="nome">Nome:</label> <input type="text"
						class="form-control" id="nome" placeholder="Insira o nome"
						name="nome" maxlength="90" autocomplete="off">
				</div>
				<div class="mb-3 mt-3">
					<label for="email">Email:</label> <input type="email"
						class="form-control" id="email" placeholder="Insira o email"
						name="email" maxlength="100" autocomplete="off">
				</div>
				<div class="mb-3">
					<label for="pwd">Senha:</label> <input type="password"
						class="form-control" id="pwd" placeholder="Insira a senha"
						name="pwd" maxlength="255">
				</div>

				<div class="mb-3 mt-3">
					<label for="cpf">CPF:</label> <input type="text"
						class="form-control" id="cpf" placeholder="Insira o CPF"
						name="cpf" maxlength="14" autocomplete="off">
				</div>

				<div class="mb-3 mt-3">
					<label for="tel">Telefone:</label> <input type="text"
						class="form-control" id="tel" placeholder="Insira o Telefone"
						name="tel" maxlength="15" autocomplete="off">
				</div>

				<div class="mb-3 mt-3">
					<label for="cep">CEP:</label> <input type="text"
						class="form-control" id="cep" placeholder="Insira o CEP"
						name="cep" maxlength="9" autocomplete="off">
				</div>

				<div class="mb-3 mt-3">
					<label for="end">Endereço:</label> <input type="text"
						class="form-control" id="end" placeholder="Insira o Endereço"
						name="end" maxlength="50">
				</div>

				<div class="mb-3 mt-3">
					<label for="cid">Cidade:</label> <input type="text"
						class="form-control" id="cid" placeholder="Insira a Cidade"
						name="cid" maxlength="50">
				</div>

				<div class="mb-3 mt-3">
					<label for="bairro">Bairro:</label> <input type="text"
						class="form-control" id="bairro" placeholder="Insira o Bairro"
						name="bairro" maxlength="30">
				</div>

				<div class="mb-3 mt-3">
					<label for="uf">UF:</label> <input type="text" class="form-control"
						id="uf" placeholder="UF" name="uf" maxlength="2">
				</div>

				<br />
				<div class="d-flex justify-content-between">
					<button type="submit" class="btn btn-primary">Cadastrar</button>
				</div>
			</form>
		</div>
	</section>

	<script src="scripts/input_cadastro.js"></script>


</body>
</html>