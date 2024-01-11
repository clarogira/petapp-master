package com.petapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.petapp.model.Servico;
import com.petapp.repository.ServicoRepository;
import com.petapp.repository.filter.ServicoFilter;
import com.petapp.service.ServicoService;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;
import com.petapp.service.exception.NomeServicoJaCadastradoException;

@Controller
@RequestMapping("/servicos")
public class ServicoController {
			
		@Autowired
		private ServicoRepository sr;
		
		@Autowired
		private ServicoService ss;
			
		
			@GetMapping(value = "/nova")
			public ModelAndView nova(Servico servico) {
				ModelAndView mv = new ModelAndView("servicos/cadastroServico");
				Iterable<Servico> servicos = sr.findAll();
				mv.addObject("Servicos", servicos);
		        return mv;
			}
			@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
			public ModelAndView salvar(@Valid Servico servico, BindingResult result, RedirectAttributes attributes) {
				if (result.hasErrors()) {
					attributes.addFlashAttribute("mensagem", "Verifique os campos!");
					return nova(servico);
				}
			try {
				ss.salvar(servico);
				
			} catch (NomeServicoJaCadastradoException e) {
				result.rejectValue("nome",e.getMessage(), e.getMessage());
				return nova(servico);
			}
				attributes.addFlashAttribute("mensagem", "Servico cadastrado com sucesso!");
				return new ModelAndView("redirect:/servicos");
			}

			@GetMapping
			public ModelAndView pesquisar(ServicoFilter servicoFilter, 
					@PageableDefault(size=10, sort= {"nome"})Pageable pageable, HttpServletRequest httpServletRequest) {
				ModelAndView mv = new ModelAndView("servicos/pesquisaServico");
						
				PageWrapper<Servico> paginaWrapper = new PageWrapper<> (sr.filtrar(servicoFilter, pageable), httpServletRequest);
				mv.addObject("pagina", paginaWrapper);
				
				return mv;
			}
			@DeleteMapping("/{codigo}")
			public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Servico servico) {
				try {
					ss.excluir(servico);
				} catch (ImpossivelExcluirEntidadeException e) {
					return ResponseEntity.badRequest().body(e.getMessage());
				}
				return ResponseEntity.ok().build();
			}
			@GetMapping("/{codigo}")
			public ModelAndView editar(@PathVariable("codigo") Servico servico) {
				ModelAndView mv = nova(servico);
				mv.addObject(servico);
				return mv;
			}
			
}


