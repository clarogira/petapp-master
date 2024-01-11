$(function() {
	
	var modal = $('#modalCadastroRapidoCliente');
	var botaoSalvar = modal.find('.js-modal-cadastro-cliente-salvar-btn');
	var form = modal.find('form');
	form.on('submit', function(event) { event.preventDefault() });
	var url = form.attr('action');
	var inputNomeCliente = $('#nomeCliente');
	var inputemailCliente = $('#emailCliente');
	var inputtelefoneFixoCliente = $('#telefoneFixoCliente');
	var inputtelefoneCelularCliente = $('#telefoneCelularCliente');
	var inputObservacoesCliente = $('#ObservacoesCliente');
	var inputruaCliente = $('#ruaCliente');
	var inputcomplementoCliente = $('#complementoCliente');
	var inputbairroCliente = $('#bairroCliente');
	var inputcepCliente = $('#cepCliente');
	
	
	var containerMensagemErro = $('.js-mensagem-cadastro-rapido-cliente');
	
	modal.on('shown.bs.modal', onModalShow);
	modal.on('hide.bs.modal', onModalClose)
	botaoSalvar.on('click', onBotaoSalvarClick);
	
	function onModalShow() {
		inputNomeCliente.focus();
	}
	
	function onModalClose() {
		inputNomeCliente.val('');
		inputemailCliente.val('');
		inputtelefoneFixoCliente.val('');
		inputtelefoneCelularCliente.val(''); 
		inputObservacoesCliente.val(''); 
		inputruaCliente.val('');
		inputcomplementoCliente.val(''); 
		inputbairroCliente.val(''); 
		inputcepCliente.val(''); 
		containerMensagemErro.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeCliente = inputNomeCliente.val().trim();
		var emailCliente = inputemailCliente.val().trim();
		var telefoneFixoCliente = inputtelefoneFixoCliente.val().trim();
		var telefoneCelularCliente = inputtelefoneCelularCliente.val().trim();
		var ObservacoesCliente = inputObservacoesCliente.val().trim();
		var ruaCliente = inputruaCliente.val().trim();
		var complementoCliente = inputcomplementoCliente.val().trim();
		var bairroCliente = inputbairroCliente.val().trim();
		var cepCliente = inputcepCliente.val().trim();
			
		console.log('nome cliente, nomeCliente')
		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({nome: nomeCliente, telefone_fixo: telefoneFixoCliente, telefone_celular:telefoneCelularCliente, email:emailCliente, observacao:ObservacoesCliente, rua:ruaCliente, complemento:complementoCliente, bairro:bairroCliente,cep:cepCliente}),
			
			error: onErroSalvandoCliente,
			success: onClienteSalvo
		});
	}
	
	function onErroSalvandoCliente(obj) {
		var mensagemErro = obj.responseText;
		containerMensagemErro.removeClass('hidden');
		containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		form.find('.form-group').addClass('has-error');
	}
	
	function onClienteSalvo(cliente) {
		var comboCliente = $('#cliente');
		comboCliente.append('<option value=' + cliente.codigo + '>' + cliente.nome + '</option>');
		comboCliente.val(cliente.codigo);
		modal.modal('hide');
	}
	
});
