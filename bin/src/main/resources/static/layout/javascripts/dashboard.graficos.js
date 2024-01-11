var Brewer = Brewer || {};

Brewer.GraficoVendaPorMes = (function() {
	
	function GraficoVendaPorMes() {
		this.ctx = $('#graficoVendasPorMes')[0].getContext('2d');
	}
	
	GraficoVendaPorMes.prototype.iniciar = function() {
		$.ajax({
			url: 'vendas/totalPorMes',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(vendaMes) {
		var meses = [];
		var valores = [];
		vendaMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});
		
		var graficoVendasPorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Vendas por mês',
		    		backgroundColor: "rgba(256,179,0,0.5)",
	                pointBorderColor: "rgba(26,179,0,1)",
	                pointBackgroundColor: "#fff",
	                data: valores
		    	}]
		    },
		    
		});
	}
	
	return GraficoVendaPorMes;
	
}());
Brewer.GraficoVendaPorOrigem = (function() {
	
	function GraficoVendaPorOrigem() {
		this.ctx = $('#graficoVendasPorOrigem')[0].getContext('2d');
	}
	GraficoVendaPorOrigem.prototype.iniciar = function() {
		$.ajax({
			url: 'vendas/porCategoria',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(vendaOrigem) {
		var meses = [];
		var produtos_acessorio = [];
		var produtos_banho = [];
		var produtos_higiene = [];
		var produtos_petisco = [];
		var produtos_racao = [];
		var produtos_remedio = [];	
		
		vendaOrigem.forEach(function(obj) {
			meses.unshift(obj.mes);
			
		produtos_acessorio.unshift(obj.total_acessorio);
		produtos_banho.unshift(obj.total_banho);
		produtos_higiene.unshift(obj.total_higiene);
		produtos_petisco.unshift(obj.total_petisco);
		produtos_racao.unshift(obj.total_racao);
		produtos_remedio.unshift(obj.total_remedio);
		
		});
		
		var graficoVendasPorOrigem = new Chart(this.ctx, {
		    type: 'bar',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Acessório',
		    	    backgroundColor: 'rgba(0, 0, 255, 0.2)',
		    	    borderColor:     'rgba(0, 0, 255, 1)',
	                data: produtos_acessorio,
	                borderWidth: 1
		    	},{
		    		label: 'Banho',
		    		backgroundColor: 'rgba(0, 0.5, 0, 0.2)',
		    		borderColor:     'rgba(0, 0.5, 0,  1)',
	                data: produtos_banho,
	                borderWidth: 1
		    	},{
		    		label: 'Higiene',
		    		backgroundColor: 'rgba(148,0,211, 0.2)',
		    		borderColor:     'rgba(148,0,211, 1)',
	                data: produtos_higiene,
	                borderWidth: 1
		    	},
		    	{		    	    	
		    		label: 'Petisco',
		    		backgroundColor: 'rgba(255, 206, 86, 0.2)',
		    		borderColor:     'rgba(255, 206, 86, 1)',
	                data: produtos_petisco,
	                borderWidth: 1
		    	},
		    	{		    	
		    		label: 'Ração',
		    		backgroundColor: 'rgba(75, 192, 192, 0.2)',
		    		borderColor:     'rgba(75, 192, 192, 1)',
		    		data: produtos_racao,
		    		borderWidth: 1
		    	},{
		    		label: 'Remédio',
		    		backgroundColor: 'rgba(255, 255, 0, 0.2)',
		    		borderColor:     'rgba(255, 255, 0, 1)',
	                data: produtos_remedio,
	                borderWidth: 1
		    	}]
		    	
		    },
		    options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                }
            }
		});
	}
	
	return GraficoVendaPorOrigem;
	
}());


$(function() {
	var graficoVendaPorMes = new Brewer.GraficoVendaPorMes();
	graficoVendaPorMes.iniciar();
	
	var graficoVendaPorOrigem = new Brewer.GraficoVendaPorOrigem();
	graficoVendaPorOrigem.iniciar();
});
