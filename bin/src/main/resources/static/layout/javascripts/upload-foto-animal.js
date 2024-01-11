var PetApp = PetApp || {};

PetApp.UploadFoto = (function() {
	
	function UploadFoto() {
		this.inputNomeFoto = $('input[name=foto]');
		this.inputContentType = $('input[name=contentType]');
		this.novaFoto = $('input[name=novaFoto]');
		
		this.htmlFotoAnimalTemplate = $('#foto-animal').html();
		this.template = Handlebars.compile(this.htmlFotoAnimalTemplate);
		
		this.containerFotoAnimal = $('.js-container-foto-animal');
		
		this.uploadDrop = $('#upload-drop');
	}
	
	UploadFoto.prototype.iniciar = function () {
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: this.containerFotoAnimal.data('url-fotos'),
			complete: onUploadCompleto.bind(this)
			
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);

		if (this.inputNomeFoto.val()) {
			renderizarFoto.call(this, { nome:  this.inputNomeFoto.val(), contentType: this.inputContentType.val()});
		}
	}
	
	function onUploadCompleto(resposta) {
		this.novaFoto.val('true');
		renderizarFoto.call(this, resposta);
	}
	
	function renderizarFoto(resposta){
	    this.inputNomeFoto.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);
		
		this.uploadDrop.addClass('hidden');
		
		var foto = '';
		if(this.novaFoto.val() == 'true'){
		foto = 'temp/';
		}
		foto += resposta.nome;
		
		var htmlFotoAnimal = this.template({foto: foto});
		this.containerFotoAnimal.append(htmlFotoAnimal);
		
		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
	}
	
	function onRemoverFoto() {
		$('.js-foto-animal').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType.val('');
		this.novaFoto.val('false');
	}
	
	return UploadFoto;
	
})();

$(function() {
	var uploadFoto = new PetApp.UploadFoto();
	uploadFoto.iniciar();
});