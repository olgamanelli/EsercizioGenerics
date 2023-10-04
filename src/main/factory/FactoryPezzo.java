package main.factory;

import main.Pezzo;
import main.PezzoSconosciutoException;
import main.Ruota;
import main.Sportello;
import main.enums.TipiPezzi;

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
	
	public static Pezzo getPezzoFromString(TipiPezzi tipoPezzo) throws PezzoSconosciutoException {
		
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
