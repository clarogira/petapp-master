package com.petapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.petapp.repository.ClienteRepository;
import com.petapp.repository.ProdutoRepository;
import com.petapp.repository.VendaRepository;

@Controller
public class DashboardController {
	
@Autowired
VendaRepository vr;

@Autowired
ProdutoRepository pr;
@Autowired
ClienteRepository cr;

	@GetMapping("/dashboardVenda")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("dashboardVenda");
		mv.addObject("vendasNoAno", vr.valorTotalNoAno());
		mv.addObject("vendasNoMes", vr.valorTotalNoMes());
		mv.addObject("ticketMedio", vr.valorTicketMedioNoAno());
		mv.addObject("vendasNoDia", vr.valorTotalNoDia());
		mv.addObject("valorItensEstoque", pr.valorItensEstoque());
		mv.addObject("totalClientes", cr.count());
		mv.addObject("lucroNoMes", vr.lucroTotalNoMes());

		return mv;
	}
	
}