package main.veicoli;

import main.enums.TipiVeicoli;

public class Camion extends Veicolo {

		//istanze
		private final TipiVeicoli tipo = TipiVeicoli.CAMION;
	
		//costruttori
		public Camion(int ruote, int sportelli) {
			super(ruote, sportelli);
			}
		
		public TipiVeicoli getTipoVeicolo() {
			return TipiVeicoli.CAMION;
		}
}
