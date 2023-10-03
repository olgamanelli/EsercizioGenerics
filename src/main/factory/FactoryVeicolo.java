package main.factory;

import main.Veicolo;
import main.Auto;
import main.Camion;
import main.Moto;
import main.Ruota;
import main.Sportello;

/**
 * Factory per istanziare un Veicolo
 * @author Manelli
 *
 */
public class FactoryVeicolo {
	
	//costruttore statico
	/**
	 * 
	 * @param nomeVeicolo, stringa che dice il nome del Veicolo
	 * @return il Veicolo (Ruota o Sportello) con il nome nomeVeicolo
	 * @throws Exception
	 */
	public static Veicolo getVeicoloFromString(String nomeVeicolo, int sportello, int ruote) throws Exception {
		
		switch(nomeVeicolo) {
			case "Auto": {
				return new Auto(sportello);
			}
			case "Moto": {
				return new Moto();
			}
			case "Camion":{
				return new Camion(ruote, sportello);
			}
			default: throw new Exception("Non esiste un Veicolo di questo tipo oooooooooooh!!!");
		}
	}
}
