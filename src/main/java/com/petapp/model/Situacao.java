package com.petapp.model;


public enum Situacao {

	AGENDADO("Agendado"),
    COMPENSADO("Compensado"),
    CANCELADO("Cancelado"),
    PAGAMENTO_NAO_REALIZADO("Pagamento não realizado");

    private String descricao;

    Situacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
