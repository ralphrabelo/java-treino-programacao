package br.com.coruja.service;

import br.com.bank.gateway.Bacen;
import br.com.coruja.model.Banco;

public class SistemaBancario {

	private Bacen bacen;

	public SistemaBancario(Bacen bacen) {
		super();
		this.bacen = bacen;
	}

	public long registrarBanco(Banco banco) {
		return bacen.cadastrarBanco(banco);
	}


}
