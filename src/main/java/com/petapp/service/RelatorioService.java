package com.petapp.service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petapp.model.Situacao;
import com.petapp.model.Tipo;
import com.petapp.dto.PeriodoRelatorio;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class RelatorioService {
	
	@Autowired
	private DataSource dataSource;
	java.util.Date dataInicio;
	java.util.Date dataFim; 
	Integer min;
	Integer max;
	Tipo tipo;
	Situacao situacao;
	
	public byte[] gerarRelatorioVendasEmitidas(PeriodoRelatorio periodoRelatorio) throws Exception {
		
		dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		
		
		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorios/relatorio_vendas_emitidas.jasper");
		
		Connection con = this.dataSource.getConnection();
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, con);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {
			con.close();
		}
	}

	public byte[] gerarRelatorioProdutos(PeriodoRelatorio periodoRelatorio) throws Exception {
		dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		min = periodoRelatorio.getMin();
		max = periodoRelatorio.getMax();
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		parametros.put("min", min);
		parametros.put("max", max);
		
		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorios/Flower.jasper");
		
		Connection con = this.dataSource.getConnection();
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, con);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {
			con.close();
		}
	}



public byte[] gerarRelatorioContasAPagar(com.petapp.dto.PeriodoRelatorioFinanceiro periodoRelatorioFinanceiro) throws Exception {
	
	dataInicio = Date.from(LocalDateTime.of(periodoRelatorioFinanceiro.getDataDe(), LocalTime.of(0, 0, 0))
			.atZone(ZoneId.systemDefault()).toInstant());
	dataFim = Date.from(LocalDateTime.of(periodoRelatorioFinanceiro.getDataAte(), LocalTime.of(23, 59, 59))
			.atZone(ZoneId.systemDefault()).toInstant());
	tipo = periodoRelatorioFinanceiro.getTipo();
	situacao = periodoRelatorioFinanceiro.getSituacao();
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("format", "pdf");
		parametros.put("dataDe", dataInicio);
		parametros.put("dataAte", dataFim);
		parametros.put("situacao", situacao);
		parametros.put("tipo", tipo);
		
		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorios/relatorio_contas_agendadas.jasper");
		
		Connection con = this.dataSource.getConnection();
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, con);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {
			con.close();
		}
	}

public byte[] gerarRelatorioContasAReceber(com.petapp.dto.PeriodoRelatorioFinanceiro periodoRelatorioFinanceiro) throws Exception {
		
	dataInicio = Date.from(LocalDateTime.of(periodoRelatorioFinanceiro.getDataDe(), LocalTime.of(0, 0, 0))
			.atZone(ZoneId.systemDefault()).toInstant());
	dataFim = Date.from(LocalDateTime.of(periodoRelatorioFinanceiro.getDataAte(), LocalTime.of(23, 59, 59))
			.atZone(ZoneId.systemDefault()).toInstant());
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("format", "pdf");
		parametros.put("dataDe", dataInicio);
		parametros.put("dataAte", dataFim);
		
		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorios/relatorio_contas_a_pagar.jasper");
		
		Connection con = this.dataSource.getConnection();
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, con);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {
			con.close();
		}
	}
}








