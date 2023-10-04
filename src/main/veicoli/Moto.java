package main.veicoli;

import main.enums.TipiVeicoli;

public class Moto extends Veicolo {
	
	//istanze
	private final TipiVeicoli tipo = TipiVeicoli.MOTO;
	
	//costruttori
	public Moto() {
		super(2,0);
	}
	
	public TipiVeicoli getTipoVeicolo() {
		return TipiVeicoli.MOTO;
	}

}
