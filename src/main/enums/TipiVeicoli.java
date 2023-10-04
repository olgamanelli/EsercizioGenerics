package main.enums;

public enum TipiVeicoli {
	AUTO("Fiat"),
	Moto("Yamaha"),
	Camion("DittaTraslocchi");
	
	private String nome;
	
	TipiVeicoli(String nome) {
		this.nome = nome;
	}
}

