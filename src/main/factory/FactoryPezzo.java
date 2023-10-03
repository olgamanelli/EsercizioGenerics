package main.factory;

import main.Pezzo;
import main.Ruota;
import main.Sportello;

/**
 * Factory per istanziare un pezzo
 * @author Manelli
 *
 */
public class FactoryPezzo {
	
	//costruttore statico
	/**
	 * 
	 * @param nomePezzo, stringa che dice il nome del pezzo
	 * @return il Pezzo (Ruota o Sportello) con il nome nomePezzo
	 * @throws Exception
	 */
	public static Pezzo getPezzoFromString(String nomePezzo) throws Exception {
		
		switch(nomePezzo) {
			case "Ruota": {
				return new Ruota(nomePezzo);
			}
			case "Sportello": {
				return new Sportello(nomePezzo);
			}
			default: throw new Exception("Non esiste un pezzo di questo tipo oooooooooooh!!!");
		}
	}
}
