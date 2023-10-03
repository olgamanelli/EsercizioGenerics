package main;

public class Camion extends Veicolo {

		//istanze
	
		//costruttori
		public Camion(int ruote, int sportelli) {
			super(ruote, sportelli);
		}
		
		public String getNome() {
			return "Camion";
		}
}
