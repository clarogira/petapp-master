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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petapp.controller.page.PageWrapper;
import com.petapp.dto.ProdutoDTO;
import com.petapp.model.Categoria;
import com.petapp.model.Produto;
import com.petapp.model.Ramo;
import com.petapp.repository.FornecedorRepository;
import com.petapp.repository.ProdutoRepository;
import com.petapp.repository.filter.ProdutoFilter;
import com.petapp.service.ProdutoService;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;
import com.petapp.service.exception.NomeProdutoJaCadastradoException;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoRepository pr;
	@Autowired
	private FornecedorRepository fr;
	@Autowired
	private ProdutoService ps;

	
	
	@RequestMapping(value ="/nova")
	public ModelAndView nova(Produto produto) {
		ModelAndView mv = new ModelAndView("produtos/cadastroProduto");
		mv.addObject("todosFornecedores", fr.findAllByOrderByNomeAsc());
		mv.addObject("categorias", Categoria.values());
		mv.addObject("ramos", Ramo.values());
		
		return mv;
	}
	
	
	
	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
				
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return nova(produto);
		}
		try {
			
			ps.salvar(produto);
			
		} catch (NomeProdutoJaCadastradoException e) {
			result.rejectValue("nome",e.getMessage(), e.getMessage());
			return nova(produto);
		}
		attributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!");
		return new ModelAndView("redirect:/produtos");
	}
	
	
@GetMapping
public ModelAndView pesquisar(ProdutoFilter produtoFilter, @PageableDefault(size=50, sort= {"nome"})Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("produtos/pesquisaProduto");
		
		mv.addObject("fornecedores", fr.findAll());
		mv.addObject("categorias", Categoria.values());
		mv.addObject("ramos", Ramo.values());
		mv.addObject("todosFornecedores", fr.findAll());
		PageWrapper<Produto> paginaWrapper = new PageWrapper<> (pr.filtrar(produtoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		
		return mv;
	}

@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody List<ProdutoDTO> pesquisar(String pesquisaPorNome) {
	

	return pr.porNome(pesquisaPorNome);
}

@DeleteMapping("/{codigo}")
public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Produto produto) {
	try {
		ps.excluir(produto);
	} catch (ImpossivelExcluirEntidadeException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	return ResponseEntity.ok().build();
}
	
@GetMapping("/{codigo}")
public ModelAndView editar(@PathVariable("codigo") Produto produto) {
	ModelAndView mv = nova(produto);
	mv.addObject(produto);
	return mv;
}
	
	
	
}
