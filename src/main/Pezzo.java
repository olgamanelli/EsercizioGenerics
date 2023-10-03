package main;

public abstract class Pezzo {

	//attributo
	
	private String nome;
	
	//costruttore
	public Pezzo(String nome) {
		this.nome=nome;
	}

	//Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
