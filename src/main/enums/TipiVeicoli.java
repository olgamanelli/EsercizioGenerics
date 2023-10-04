package main.enums;

public enum TipiVeicoli {
	AUTO("Fiat"),
	MOTO("Yamaha"),
	CAMION("DittaTraslocchi");
	
	private String nome;
	
	TipiVeicoli(String nome) {
		this.nome = nome;
	}
}

