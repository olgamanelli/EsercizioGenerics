package main.factory;

import main.Veicolo;
import main.VeicoloSconosciutoException;
import main.SportelliSbagliatiException;
import main.Auto;
import main.Camion;
import main.Moto;
import main.enums.TipiVeicoli;

/**
 * Factory per istanziare un Veicolo
 * @author Manelli
 *
 */
public class FactoryVeicolo {
	
	//Costruttore statico
	/**
	 * 
	 * @param nomeVeicolo
	 * @param sportello
	 * @param ruote
	 * @return
	 * @throws VeicoloSconosciutoException
	 * @throws SportelliSbagliatiException
	 */
	public static Veicolo getVeicoloFromString(TipiVeicoli nomeVeicolo, int sportello, int ruote)  
			throws VeicoloSconosciutoException, SportelliSbagliatiException {
		
		switch(nomeVeicolo) {
			case AUTO: {
				return new Auto(sportello);
			}
			case Moto: {
				return new Moto();
			}
			case Camion:{
				return new Camion(ruote, sportello);
			}
			default: throw new VeicoloSconosciutoException("Non esiste un Veicolo di questo tipo!");
		}
	}
}
