package com.petapp.controller;

import com.petapp.controller.page.PageWrapper;
import com.petapp.model.Situacao;
import com.petapp.model.Tipo;
import com.petapp.model.TipoDePagamento;
import com.petapp.model.Titulo;
import com.petapp.repository.FornecedorRepository;
import com.petapp.repository.TiposDePagamento;
import com.petapp.repository.TituloRepository;
import com.petapp.repository.filter.TituloFilter;
import com.petapp.service.TitulosService;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping("/titulos")
public class TitulosController {

    private final String INDEX = "titulo/CadastroTitulo";

    @Autowired
    private FornecedorRepository fornecedores;

    @Autowired
    private TitulosService titulosService;

    @Autowired
    private TiposDePagamento tiposDePagamento;

    @Autowired
    private TituloRepository titulos;

    @RequestMapping(value = "/novo")
    public ModelAndView novo(Titulo titulo) {
        ModelAndView mv = new ModelAndView(INDEX);
        mv.addObject("todosOsTipos", Tipo.values());
        mv.addObject("todasAsSituacoes", Situacao.values());
        mv.addObject("tiposDePagamento", tiposDePagamento.findAll());
        return mv;
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public ModelAndView salvar(@Valid Titulo titulo, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            return novo(titulo);
        }

        titulosService.salvar(titulo);
        attributes.addFlashAttribute("mensagem", "Título salvo com sucesso");
        return new ModelAndView("redirect:/titulos/novo");
    }

    @GetMapping
    public ModelAndView pesquisar(TituloFilter tituloFilter, @PageableDefault(size=50, sort= {"descricao"})Pageable pageable, HttpServletRequest httpServletRequest) {
    		ModelAndView mv = new ModelAndView("titulo/PesquisarTitulos");
    		
    		mv.addObject("todasAsSituacoes", Situacao.values());
    		 mv.addObject("todosOsTipos", Tipo.values());
    		mv.addObject("tiposDePagamento", tiposDePagamento.findAll());
    		mv.addObject("listaDeFornecedores", titulos.fornecedoresDosTitulos());
    		PageWrapper<Titulo> paginaWrapper = new PageWrapper<> (titulos.filtrar(tituloFilter, pageable), httpServletRequest);
    		mv.addObject("pagina", paginaWrapper);
    		
    		
    		return mv;
    	}

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<?> novoTipoDePagamento(@RequestBody @Valid TipoDePagamento tipoDePagamento,
                                                               BindingResult result){

        if (result.hasErrors()){
          return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
        }

        tipoDePagamento = tiposDePagamento.save(tipoDePagamento);
        return ResponseEntity.ok(tipoDePagamento);
    }

    @GetMapping("/{codigo}")
    public ModelAndView editar(@PathVariable("codigo") Titulo titulo){
        ModelAndView mv = new ModelAndView(INDEX);
        mv.addObject("listaDeFornecedores", fornecedores.findAll());
        mv.addObject("todosOsTipos", Tipo.values());
        mv.addObject("todasAsSituacoes", Situacao.values());
        mv.addObject("tiposDePagamento", tiposDePagamento.findAll());
        mv.addObject(titulo);
        return mv;
    }

  
    @DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Titulo titulo) {
		try {
			titulosService.excluir(titulo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}


}
