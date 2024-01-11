package com.petapp.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petapp.controller.page.PageWrapper;
import com.petapp.model.Animal;
import com.petapp.model.Cor;
import com.petapp.model.Raca;
import com.petapp.repository.AnimalRepository;
import com.petapp.repository.ClienteRepository;
import com.petapp.repository.filter.AnimalFilter;
import com.petapp.service.AnimalService;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/animais")
public class AnimalController {
	@Autowired
	private ClienteRepository cr;  
	@Autowired
	private AnimalRepository ar; 
	@Autowired
	private AnimalService as;
	
	@RequestMapping(value = "/nova")
    public ModelAndView nova(Animal animal) {
		ModelAndView mv = new ModelAndView("animais/cadastroAnimal");
		mv.addObject("clientes", cr.findAllByOrderByNomeAsc());
		mv.addObject("racas", Raca.values());
		mv.addObject("cores", Cor.values());
        return mv;
    }

	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Animal animal, BindingResult result,Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return nova(animal);
		}
		
		as.salvar(animal);
		attributes.addFlashAttribute("mensagem", "Animal cadastrado com sucesso!");
		return new ModelAndView("redirect:/animais");
	}

	@GetMapping
	public ModelAndView pesquisar(AnimalFilter animalFilter, @PageableDefault(size=50, sort= {"cliente"})Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("animais/pesquisaAnimal");
		
		mv.addObject("clientes", cr.findAll());
		mv.addObject("racas", Raca.values());
		mv.addObject("cores", Cor.values());
		PageWrapper<Animal> paginaWrapper = new PageWrapper<> (ar.filtrar(animalFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}

	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Animal animal) {
		try {
			as.excluir(animal);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Animal animal) {
		ModelAndView mv = nova(animal);
		mv.addObject(animal);
		return mv;
	}
	
	@GetMapping(value = "/pesquisaAnimalRapido")
	public ModelAndView pesquisarRapido(AnimalFilter animalFilter, @PageableDefault(size=20, sort= {"cliente"})Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("animais/PesquisaRapidaAnimais");
		
		mv.addObject("clientes", cr.findAll());
		mv.addObject("racas", Raca.values());
		mv.addObject("cores", Cor.values());
		PageWrapper<Animal> paginaWrapper = new PageWrapper<> (ar.filtrar(animalFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
}
