PetApp = PetApp || {};

PetApp.PesquisaRapidaAnimal = (function() {
	
	function PesquisaRapidaAnimal() {
		this.pesquisaRapidaAnimaisModal = $('#pesquisaRapidaAnimais');
		this.nomeInput = $('#nomeAnimalModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-Animais-btn'); 
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaAnimais');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-animal').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaAnimal.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaAnimaisModal.on('shown.bs.modal', onModalShow.bind(this));

	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaAnimaisModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			}, 
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.addClass('hidden');
		console.log(resultado);
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaAnimalPesquisaRapida = new PetApp.TabelaAnimalPesquisaRapida(this.pesquisaRapidaAnimaisModal);
		tabelaAnimalPesquisaRapida.iniciar();
	} 
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaAnimal;
	
}());

PetApp.TabelaAnimalPesquisaRapida = (function() {
	
	function TabelaAnimalPesquisaRapida(modal) {
		this.modalAnimal = modal;
		this.animal = $('.js-animal-pesquisa-rapida');
	}
	
	TabelaAnimalPesquisaRapida.prototype.iniciar = function() {
		this.animal.on('click', onAnimalSelecionado.bind(this));
	}
	
	function onAnimalSelecionado(evento) {
		this.modalAnimal.modal('hide');
		
		var animalSelecionado = $(evento.currentTarget);
		$('#nomeAnimal').val(animalSelecionado.data('nome'));
		$('#codigoAnimal').val(animalSelecionado.data('codigo'));
		console.log('codigo', animalSelecionado.data('codigo'));

	}
	
	return TabelaAnimalPesquisaRapida;
	
}());

$(function() {
	var pesquisaRapidaAnimal = new PetApp.PesquisaRapidaAnimal();
	pesquisaRapidaAnimal.iniciar();
});