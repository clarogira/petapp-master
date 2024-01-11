package com.petapp.controller.validator;


import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.petapp.model.Venda;


@Component
public class VendaValidator implements Validator {
	Venda venda;

	@Override
	public boolean supports(Class<?> clazz) {
		return Venda.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "cliente.codigo", "", "Selecione um cliente na pesquisa rápida");
		ValidationUtils.rejectIfEmpty(errors, "formaDePagamento", "", "Informe uma forma de pagamento");

		Venda venda = (Venda) target;
		validarSeInformouItens(errors, venda);
		validarValorTotalNegativo(errors, venda);
		validarPagamento(errors, venda);
	}


	private void validarValorTotalNegativo(Errors errors, Venda venda) {
		if (venda.getValorTotal().compareTo(BigDecimal.ZERO) < 0) {
			errors.reject("", "Valor total não pode ser negativo");
		}
	}

	private void validarSeInformouItens(Errors errors, Venda venda) {
		if (venda.getItens().isEmpty()) {
			errors.reject("", "Adicione pelo menos um produto na venda");
		}
	}

	private void validarPagamento(Errors errors, Venda venda) {
		String pagamento = venda.getFormaDePagamento().toString();
		System.out.println(pagamento);
		if(pagamento  == "ZERO") {
			errors.reject("", "Adicione forma de pagamento");
		}
	}

}
