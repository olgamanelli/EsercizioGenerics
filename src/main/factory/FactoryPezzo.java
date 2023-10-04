package main.factory;

import main.enums.TipiPezzi;
import main.pezzi.Pezzo;
import main.pezzi.PezzoSconosciutoException;
import main.pezzi.Ruota;
import main.pezzi.Sportello;

/**
 * Factory per istanziare un pezzo
 * @author Manelli
 *
 */
public class FactoryPezzo {
	
	//costruttore statico
	/**
	 * 
	 * @param tipoPezzo
	 * @return
	 * @throws PezzoSconosciutoException
	 */
	
	public static Pezzo getPezzoFromTipo(TipiPezzi tipoPezzo) throws PezzoSconosciutoException {
		
		switch(tipoPezzo) {
			case Ruota: {
				return new Ruota();
			}
			case Sportello: {
				return new Sportello();
			}
			default: throw new PezzoSconosciutoException("Non esiste un pezzo di questo tipo!");
		}
	}
}
