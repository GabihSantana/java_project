$(document).ready(function() {

	const $inputCpf = $("#cpf");
	const $inputFone = $("#tel");

	  // eventos de input para mascarar os campos
	  $inputCpf.on("input", function () {
	    let valor = $(this).val().replace(/[^\d.-]/g, ""); // regex

	    if (valor.length === 3 || valor.length === 7) {
	      valor += ".";
	    } else if (valor.length === 11) {
	      valor += "-";
	    }

	    $(this).val(valor); // atualiza o valor 
	  });

	  $inputFone.on("input", function () {
	    let valor = $(this).val().replace(/[^\d() -]/g, ""); // regex

	    if (valor.length === 1) {
	      valor = "(" + valor;
	    }
	    if (valor.length === 3) {
	      valor += ") ";
	    }
	    if (valor.length === 10) {
	      valor += "-";
	    }

	    $(this).val(valor); // atualiza o valor
	  });
	
	// validar os inputs do formulário
	$( "#form_cad" ).on( "submit", function( event ) {
		var nome = $('#nome').val().trim();
		var email = $('#email').val().trim();
		var pwd = $('#pwd').val().trim();
		var cpf = $('#cpf').val().trim();
		var tel = $('#tel').val().trim();
		
		let nulos = nome === '' || email === '' || cpf === '' || pwd === '' || tel === ''

		if (nulos) {
		    alert('Por favor, preencha todos os campos!');
		    event.preventDefault();
		  } 
	});
	
	// API de CEP
	
	function limpa_formulário_cep() {
		// Limpa valores do formulário de cep.
		$("#end").val("");
		$("#bairro").val("");
		$("#cid").val("");
		$("#uf").val("");
	}

	//Quando o campo cep perde o foco.
	$("#cep").blur(function() {

		//Nova variável "cep" somente com dígitos.
		var cep = $(this).val().replace(/\D/g, '');

		//Verifica se campo cep possui valor informado.
		if (cep != "") {

			//Expressão regular para validar o CEP.
			var validacep = /^[0-9]{8}$/;

			//Valida o formato do CEP.
			if (validacep.test(cep)) {

				//Preenche os campos com "..." enquanto consulta webservice.
				$("#end").val("...");
				$("#bairro").val("...");
				$("#cid").val("...");
				$("#uf").val("...");

				//Consulta o webservice viacep.com.br/
				$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function(dados) {

					if (!("erro" in dados)) {
						//Atualiza os campos com os valores da consulta.
						$("#end").val(dados.logradouro);
						$("#bairro").val(dados.bairro);
						$("#cid").val(dados.localidade);
						$("#uf").val(dados.uf);
					} //end if.
					else {
						//CEP pesquisado não foi encontrado.
						limpa_formulário_cep();
						alert("CEP não encontrado.");
					}
				});
			} //end if.
			else {
				//cep é inválido.
				limpa_formulário_cep();
				alert("Formato de CEP inválido.");
			}
		} //end if.
		else {
			//cep sem valor, limpa formulário.
			limpa_formulário_cep();
		}
	});
});