$(function() {
	
	var modal = $('#modalCadastroRapidoFornecedor');
	var botaoSalvar = modal.find('.js-modal-cadastro-fornecedor-salvar-btn');
	var form = modal.find('form');
	form.on('submit', function(event) { event.preventDefault() });
	var url = form.attr('action');
	var inputNomeFornecedor = $('#nomeFornecedor');
	var inputTelefoneFornecedor= $('#telefoneFornecedor');
	var inputRepresentanteFornecedor = $('#representanteFornecedor');
	var inputTelefoneRepresentanteFornecedor = $('#telefoneRepresentanteFornecedor');
	var inputRamoFornecedor = $('#ramoFornecedor');
	var inputCnpjFornecedor = $('#cnpjFornecedor');
	var inputObservacaoFornecedor = $('#observacaoFornecedor');
	
	var containerMensagemErro = $('.js-mensagem-cadastro-rapido-fornecedor');
	
	modal.on('shown.bs.modal', onModalShow);
	modal.on('hide.bs.modal', onModalClose)
	botaoSalvar.on('click', onBotaoSalvarClick);
	
	function onModalShow() {
		inputNomeFornecedor.focus();
	}
	
	function onModalClose() {
		inputNomeFornecedor.val('');
		inputTelefoneFornecedor.val('');
		inputRepresentanteFornecedor.val('');
		inputTelefoneRepresentanteFornecedor.val('');
		inputRamoFornecedor.val('');
		inputCnpjFornecedor.val('');
		inputObservacaoFornecedor.val('');
		
		containerMensagemErro.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeFornecedor = inputNomeFornecedor.val().trim();
		var telefoneFornecedor = inputTelefoneFornecedor.val().trim();
		var representanteFornecedor = inputRepresentanteFornecedor.val().trim();
		var telefoneRepresentanteFornecedor = inputTelefoneRepresentanteFornecedor.val().trim();
		var ramoFornecedor = inputRamoFornecedor.val().trim();
		var cnpjFornecedor = inputCnpjFornecedor.val().trim();
		var observacaoFornecedor = inputObservacaoFornecedor.val().trim();
		
		console.log('nome fornecedor, nomeFornecedor')
		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({nome: nomeFornecedor, telefone:telefoneFornecedor, representante:representanteFornecedor, telefoneRepresentante:telefoneRepresentanteFornecedor, ramo:ramoFornecedor, cnpj:cnpjFornecedor, observacao:observacaoFornecedor}),
			error: onErroSalvandoFornecedor,
			success: onFornecedorSalvo
		});
	}
	
	function onErroSalvandoFornecedor(obj) {
		var mensagemErro = obj.responseText;
		containerMensagemErro.removeClass('hidden');
		containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		form.find('.form-group').addClass('has-error');
	}
	
	function onFornecedorSalvo(fornecedor) {
		var comboFornecedor = $('#fornecedor');
		comboFornecedor.append('<option value=' + fornecedor.codigo + '>' + fornecedor.nome + '</option>');
		comboFornecedor.val(fornecedor.codigo);
		modal.modal('hide');
	}
	
});
