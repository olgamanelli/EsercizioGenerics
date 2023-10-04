package main;

import main.enums.TipiVeicoli;

public class Camion extends Veicolo {

		//istanze
		private final TipiVeicoli tipo = TipiVeicoli.Camion;
	
		//costruttori
		public Camion(int ruote, int sportelli) {
			super(ruote, sportelli);
			}
		
		public TipiVeicoli getTipoVeicolo() {
			return TipiVeicoli.Camion;
		}
}
