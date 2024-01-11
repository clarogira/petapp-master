package com.petapp.controller;



import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petapp.controller.page.PageWrapper;
import com.petapp.controller.validator.VendaValidator;
import com.petapp.dto.VendaCategoria;
import com.petapp.dto.VendaMes;
import com.petapp.model.FormaDePagamento;
import com.petapp.model.ItemVenda;
import com.petapp.model.Produto;
import com.petapp.model.Status;
import com.petapp.model.Venda;
import com.petapp.repository.ProdutoRepository;
import com.petapp.repository.VendaRepository;
import com.petapp.repository.filter.VendaFilter;
import com.petapp.service.VendaService;
import com.petapp.session.TabelasItensSession;


@Controller
@RequestMapping("/vendas")
public class VendaController {
	
	@Autowired
	private ProdutoRepository pr;
	
	@Autowired
	private TabelasItensSession tabelaItens;
	
	@Autowired
	private VendaService vs;
	
	@Autowired
	private VendaRepository vr;
	
	@Autowired
	private VendaValidator vendaValidator;
	

	@GetMapping("/nova")
	public ModelAndView nova(Venda venda) {
		ModelAndView mv = new ModelAndView("vendas/cadastroVenda");
		
		setUuid(venda);
		
		mv.addObject("itens", venda.getItens());
		mv.addObject("valorFrete", venda.getValorFrete());
		mv.addObject("valorDesconto", venda.getValorDesconto());
		mv.addObject("valorTotalItens", tabelaItens.getValorTotal(venda.getUuid()));
		mv.addObject("formasDePagamento", FormaDePagamento.values());
        
		return mv;
	}

	private void setUuid(Venda venda) {
		if (StringUtils.isEmpty(venda.getUuid())) {
			venda.setUuid(UUID.randomUUID().toString());
		}
	}
	
	@PostMapping(value = "/nova", params = "salvar")
	public ModelAndView salvar(Venda venda, BindingResult result, RedirectAttributes attributes) {
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}
				
		vs.salvar(venda);
		attributes.addFlashAttribute("mensagem", "Venda salva com sucesso");
		return new ModelAndView("redirect:/vendas");
	}

	@PostMapping(value = "/nova", params = "fechar")
	public ModelAndView emitir(Venda venda, BindingResult result, RedirectAttributes attributes) {
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}
		
		
		vs.salvarFechar(venda);
		attributes.addFlashAttribute("mensagem", "Venda fechada com sucesso");
		return new ModelAndView("redirect:/vendas");
	}
	
	@PostMapping(value = "/nova", params = "cancelar")
	public ModelAndView cancelar(Venda venda, BindingResult result
				, RedirectAttributes attributes) {
		
		vs.cancelar(venda);
		attributes.addFlashAttribute("mensagem", "Venda cancelada com sucesso");
		return new ModelAndView("redirect:/vendas/" + venda.getCodigo());
	}
	
	@GetMapping("/totalPorMes")
	public @ResponseBody List<VendaMes> listarTotalVendaPorMes() {
		return vr.totalPorMes();
	}
	
	@GetMapping("/porCategoria")
	public @ResponseBody List<VendaCategoria> vendasPorCategoria() {
		return  vr.totalPorCategoria();
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoProduto, String uuid) {
		Produto produto = pr.findByCodigo(codigoProduto);
		tabelaItens.adicionarItem(uuid, produto, 1);
		return mvTabelaItensVenda(uuid);
	}
	
	@PutMapping("/item/{codigoProduto}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoProduto") Produto Produto
			, Integer quantidade, String uuid) {
		tabelaItens.alterarQuantidadeItens(uuid, Produto, quantidade);
		return mvTabelaItensVenda(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoProduto}")
	public ModelAndView excluirItem(@PathVariable("codigoProduto") Produto Produto
			, @PathVariable String uuid) {
		tabelaItens.excluirItem(uuid, Produto);
		return mvTabelaItensVenda(uuid);
	}
	
	@GetMapping
	public ModelAndView pesquisar(VendaFilter vendaFilter,
			@PageableDefault(size = 50, sort= {"codigo"}, direction=Direction.DESC) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("vendas/PesquisaVendas");
		mv.addObject("todosStatus", Status.values());
		mv.addObject("formasDePagamento", FormaDePagamento.values());
		PageWrapper<Venda> paginaWrapper = new PageWrapper<>(vr.filtrar(vendaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Venda venda = vr.buscarComItens(codigo);
		
		setUuid(venda);
		for (ItemVenda item : venda.getItens()) {
			tabelaItens.adicionarItem(venda.getUuid(), item.getProduto(), item.getQuantidade());
		}
		
		ModelAndView mv = nova(venda);
		mv.addObject(venda);
		return mv;
	}
	
	private ModelAndView mvTabelaItensVenda(String uuid) {
		ModelAndView mv = new ModelAndView("vendas/TabelaItensVenda");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		mv.addObject("valorTotal", tabelaItens.getValorTotal(uuid));
		return mv;
	}
	
	private void validarVenda(Venda venda, BindingResult result) {
		venda.adicionarItens(tabelaItens.getItens(venda.getUuid()));
		venda.calcularValorTotal();
		venda.calcularLucroTotal();
		
		vendaValidator.validate(venda, result);
	}

}
