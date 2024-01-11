package com.petapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.petapp.dto.PeriodoRelatorioFinanceiro;
import com.petapp.model.Situacao;
import com.petapp.model.Tipo;
import com.petapp.dto.PeriodoRelatorio;
import com.petapp.service.RelatorioService;
@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	
	@Autowired
	private RelatorioService relatorioService;
	
	
	//vendas
	@GetMapping("/vendasEmitidas")
	public ModelAndView relatorioVendasEmitidas() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioVendasEmitidas");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}
	
	@PostMapping("/vendasEmitidas")
	public ResponseEntity<byte[]> gerarRelatorioVendasEmitidas(PeriodoRelatorio periodoRelatorio) throws Exception {
		byte[] relatorio = relatorioService.gerarRelatorioVendasEmitidas(periodoRelatorio); 
		
		return ResponseEntity.ok()
				.header(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	//produtos
	
	@GetMapping("/produtos")
	public ModelAndView relatorioProdutos() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioProdutos");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}
	@PostMapping("/produtos")
	public ResponseEntity<byte[]> gerarRelatorioProdutos(PeriodoRelatorio periodoRelatorio) throws Exception {
		byte[] relatorio = relatorioService.gerarRelatorioProdutos(periodoRelatorio); 
		
		return ResponseEntity.ok()
				.header(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	 /**
     * Contas à receber     *     */
    @GetMapping("/contasAReceber")
    public ModelAndView relatorioContasAReceber() {
        ModelAndView mv = new ModelAndView("relatorio/ContasAReceber");
        mv.addObject(new PeriodoRelatorioFinanceiro());
        return mv;
    }

    @PostMapping("/contasAReceber")
   	public ResponseEntity<byte[]> RelatorioContasAReceber(PeriodoRelatorioFinanceiro periodoRelatorioFinanceiro) throws Exception {
    		byte[] relatorio = relatorioService.gerarRelatorioContasAReceber(periodoRelatorioFinanceiro);
    		
    		return ResponseEntity.ok()
    				.header(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
    				.body(relatorio);
    	}

    /**
     * Contas à pagar
     *
     */

    @GetMapping("/contasAPagar")
    public ModelAndView relatorioContasAPagar() {    	
        ModelAndView mv = new ModelAndView("relatorio/ContasAPagar");
        mv.addObject(new PeriodoRelatorioFinanceiro());
        mv.addObject("todosOsTipos", Tipo.values());
        mv.addObject("todasAsSituacoes", Situacao.values());
        return mv;
    }

    @PostMapping("/contasAPagar")
   	public ResponseEntity<byte[]> RelatorioContasAPagar(PeriodoRelatorioFinanceiro periodoRelatorioFinanceiro) throws Exception {
    		
   		byte[] relatorio = relatorioService.gerarRelatorioContasAPagar(periodoRelatorioFinanceiro); 
    		
    		return ResponseEntity.ok()
    				.header(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
    				.body(relatorio);
    	}

}