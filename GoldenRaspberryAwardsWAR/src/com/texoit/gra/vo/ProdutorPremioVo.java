package com.texoit.gra.vo;

import java.io.Serializable;

public class ProdutorPremioVo implements Serializable {
	private static final long serialVersionUID = 956855863985L;
	
	private String producer;
	private Integer Intervalo;
	private Integer primeiroAno;
	private Integer segundoAno;
	
	public String getProducer() {
		return producer;
	}
	
	public void setProducer(String producers) {
		this.producer = producers;
	}

	public Integer getIntervalo() {
		return Intervalo;
	}

	public void setIntervalo(Integer intervalo) {
		Intervalo = intervalo;
	}

	public Integer getPrimeiroAno() {
		return primeiroAno;
	}

	public void setPrimeiroAno(Integer primeiroAno) {
		this.primeiroAno = primeiroAno;
	}

	public Integer getSegundoAno() {
		return segundoAno;
	}

	public void setSegundoAno(Integer segundoAno) {
		this.segundoAno = segundoAno;
	}
}
