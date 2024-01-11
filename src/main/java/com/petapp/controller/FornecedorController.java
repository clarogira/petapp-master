package com.petapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petapp.controller.page.PageWrapper;
import com.petapp.dto.FornecedorDTO;
import com.petapp.model.Fornecedor;
import com.petapp.model.Ramo;
import com.petapp.repository.FornecedorRepository;
import com.petapp.repository.filter.FornecedorFilter;
import com.petapp.service.FornecedorService;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;
import com.petapp.service.exception.NomeFornecedorJaCadastradoException;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorRepository fr;
	
	@Autowired
	private FornecedorService fs;
		
	
		@GetMapping(value = "/nova")
		public ModelAndView nova(Fornecedor fornecedor) {
			ModelAndView mv = new ModelAndView("/fornecedores/cadastroFornecedor");
			Iterable<Fornecedor> fornecedores = fr.findAll();
			mv.addObject("Fornecedores", fornecedores);
			mv.addObject("ramos", Ramo.values());
	        return mv;
		}
		@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
		public ModelAndView salvar(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attributes) {
			if (result.hasErrors()) {
				attributes.addFlashAttribute("mensagem", "Verifique os campos!");
				return nova(fornecedor);
			}
			try {
				fs.salvar(fornecedor);
			} catch (NomeFornecedorJaCadastradoException e) {
				result.rejectValue("nome",e.getMessage(), e.getMessage());
				return nova(fornecedor);
			}
				attributes.addFlashAttribute("mensagem", "Fornecedor cadastrado com sucesso!");
				return new ModelAndView("redirect:/fornecedores");
			}
		
		@RequestMapping(value = "/novoFornecedorModal", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
		public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Fornecedor fornecedor, BindingResult result) {
			if (result.hasErrors()) {
				
				return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
			}
			
			try {
				fs.salvar(fornecedor);
			} catch (NomeFornecedorJaCadastradoException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
			return ResponseEntity.ok(fornecedor);
		}
		
	
		@GetMapping
		public ModelAndView pesquisar(FornecedorFilter fornecedorFilter, @PageableDefault(size=50, sort= {"nome"})Pageable pageable, HttpServletRequest httpServletRequest) {
			ModelAndView mv = new ModelAndView("fornecedores/pesquisaFornecedor");
			mv.addObject("ramos", Ramo.values());	
			PageWrapper<Fornecedor> paginaWrapper = new PageWrapper<> (fr.filtrar(fornecedorFilter, pageable), httpServletRequest);
			mv.addObject("pagina", paginaWrapper);
		
			return mv;
		}

		@DeleteMapping("/{codigo}")
		public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Fornecedor fornecedor) {
			try {
				fs.excluir(fornecedor);
			} catch (ImpossivelExcluirEntidadeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			return ResponseEntity.ok().build();
		}
		@GetMapping("/{codigo}")
		public ModelAndView editar(@PathVariable("codigo") Fornecedor fornecedor) {
			ModelAndView mv = nova(fornecedor);
			mv.addObject(fornecedor);
			return mv;
		}
		
		@RequestMapping("/filtro")
	     public @ResponseBody
	     List<FornecedorDTO> filtradas(String nome) {
			        return fr.filtradas(nome.toLowerCase());
			    }
	}
	

