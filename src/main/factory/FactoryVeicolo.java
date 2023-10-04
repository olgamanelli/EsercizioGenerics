package main.factory;

import main.enums.TipiVeicoli;
import main.pezzi.SportelliSbagliatiException;
import main.veicoli.Auto;
import main.veicoli.Camion;
import main.veicoli.Moto;
import main.veicoli.Veicolo;
import main.veicoli.VeicoloSconosciutoException;

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
	public static Veicolo getVeicoloFromTipo(TipiVeicoli nomeVeicolo, int sportello, int ruote)  
			throws VeicoloSconosciutoException, SportelliSbagliatiException {
		
		switch(nomeVeicolo) {
			case AUTO: {
				return new Auto(sportello);
			}
			case MOTO: {
				return new Moto();
			}
			case CAMION:{
				return new Camion(ruote, sportello);
			}
			default: throw new VeicoloSconosciutoException("Non esiste un Veicolo di questo tipo!");
		}
	}
}
