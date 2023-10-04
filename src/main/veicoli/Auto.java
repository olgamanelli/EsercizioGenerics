package main.veicoli;
import main.enums.TipiVeicoli;
import main.pezzi.SportelliSbagliatiException;

public class Auto extends Veicolo{

		//attributi
		private final TipiVeicoli tipo = TipiVeicoli.AUTO;
		//costruttore
		
		public Auto(int sportelli) throws SportelliSbagliatiException{
			super(4, sportelli);
			if(sportelli != 2 && sportelli != 4) {
				throw new SportelliSbagliatiException("Numero di sportelli sbagliato: devono essere 2 o 4!");
			}
		}
		
		public TipiVeicoli getTipoVeicolo() {
			return this.tipo;
		}
	
}
