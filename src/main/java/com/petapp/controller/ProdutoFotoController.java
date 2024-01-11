package com.petapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.petapp.dto.FotoDTO;
import com.petapp.storage.FotoStorage;
import com.petapp.storage.FotoStorageRunnable;

@RestController
@RequestMapping("/img/produtos")
public class ProdutoFotoController {
	
//18-07
	@Autowired
	private FotoStorage fotostorage;
	@PostMapping
	public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<FotoDTO> resultado = new DeferredResult<>();

		Thread thread = new Thread(new FotoStorageRunnable(files, resultado, fotostorage));
		thread.start();
		
		return resultado;
	}
	@GetMapping("/temp/{nome}")
	public byte[] recuperarFotoTemporaria(@PathVariable String nome) {
		return fotostorage.recuperarFotoTemporaria(nome);
	}
}
	

