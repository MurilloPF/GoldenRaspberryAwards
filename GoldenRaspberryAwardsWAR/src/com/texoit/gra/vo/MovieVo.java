package com.texoit.gra.vo;

import java.io.Serializable;

public class MovieVo implements Serializable {
	private static final long serialVersionUID = 956855863985L;
	
	private Integer seq;
	private Integer ano;
	private String title;
	private String studios;
	private String producer;
	private String winner;
	
	public Integer getSeq() {
		return seq;
	}
	
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	public Integer getAno() {
		return ano;
	}
	
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStudios() {
		return studios;
	}
	
	public void setStudios(String studios) {
		this.studios = studios;
	}
	
	public String getProducer() {
		return producer;
	}
	
	public void setProducer(String producers) {
		this.producer = producers;
	}
	
	public String getWinner() {
		return winner;
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
	}	
}
