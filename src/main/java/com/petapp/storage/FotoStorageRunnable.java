package com.petapp.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.petapp.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<FotoDTO> resultado;
	//18-07
	private FotoStorage  fotostorage;
	
	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado, FotoStorage fotostorage) {
		this.files = files;
		this.resultado = resultado;
		this.fotostorage=fotostorage;
		
	}

	@Override
	public void run() {
		String novoNome = this.fotostorage.salvarTemporariamente(files);
		
		String nomeFoto = novoNome;
		
		resultado.setResult(new FotoDTO(nomeFoto));
	}

}
