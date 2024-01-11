package com.petapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petapp.controller.page.PageWrapper;
import com.petapp.model.Cliente;
import com.petapp.repository.ClienteRepository;
import com.petapp.repository.filter.ClienteFilter;
import com.petapp.service.ClienteService;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;
import com.petapp.service.exception.NomeClienteJaCadastradoException;

@Controller
@RequestMapping("/clientes")

public class ClienteController {
	
	
	@Autowired
	private ClienteRepository cr;

	@Autowired
	private ClienteService cs;
	
	@RequestMapping(value = "/nova")
    public ModelAndView nova(Cliente cliente) {
		ModelAndView mv = new ModelAndView("clientes/cadastroCliente");
		
        return mv;
    }

	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return nova(cliente);
		}
	try {
		cs.salvar(cliente);
	} catch (NomeClienteJaCadastradoException e) {
		result.rejectValue("nome",e.getMessage(), e.getMessage());
		return nova(cliente);
	}
		attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
		return new ModelAndView("redirect:/clientes");
	}

	@RequestMapping(value = "/novoClienteModal", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		try {
			cs.salvar(cliente);
		} catch (NomeClienteJaCadastradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok(cliente);
	}
	
	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter, @PageableDefault(size=50, sort= {"nome"}, direction=Direction.ASC)Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("clientes/pesquisaCliente");
				
		PageWrapper<Cliente> paginaWrapper = new PageWrapper<> (cr.filtrar(clienteFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@RequestMapping(value = "/pesquisaClienteRapido", method = RequestMethod.GET, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Cliente> pesquisar(String nome) {
		System.out.println(nome);
		
		validarTamanhoNome(nome);
	
		return cr.findByNomeContainingIgnoreCase(nome);
	}

	private void validarTamanhoNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Cliente cliente) {
		try {
			cs.excluir(cliente);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Cliente cliente) {
		ModelAndView mv = nova(cliente);
		mv.addObject(cliente);
		return mv;
	}
}
