package main;

import main.enums.TipiVeicoli;

public class Moto extends Veicolo {
	
	//istanze
	private final TipiVeicoli tipo = TipiVeicoli.Moto;
	
	//costruttori
	public Moto() {
		super(2,0);
	}
	
	public TipiVeicoli getTipoVeicolo() {
		return TipiVeicoli.Moto;
	}

}
