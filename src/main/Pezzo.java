package main;

import main.enums.TipiPezzi;

public abstract class Pezzo {

	//attributo
	private TipiPezzi tipo;
	
	//costruttore
	public Pezzo(TipiPezzi tipo) {
		this.tipo=tipo;
	}
	
	public TipiPezzi getTipoPezzo() {
		return tipo;
	}

	//Getters e Setters
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//	
	
	
}
