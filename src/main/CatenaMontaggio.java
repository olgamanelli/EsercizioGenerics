package main;

import java.util.ArrayList;
import java.util.List;
import main.enums.*;
import main.factory.FactoryVeicolo;

public class CatenaMontaggio<T extends Veicolo> {
	
	//attributi
	private List<Robot> listaRobot;
	
	//costruttore
	public CatenaMontaggio(List<Robot> listaRobot) {
		this.listaRobot = listaRobot;
	}
	
	
	/**
	 * 
	 * @param listaPezziDisponibili
	 * @param ruote
	 * @param sportelli
	 * @param tipoVeicolo
	 * @return T, veicolo creato
	 * @throws PezziTerminatiException
	 * @throws VeicoloSconosciutoException
	 * @throws PezzoSconosciutoException
	 * @throws SportelliSbagliatiException
	 */
	public T apply(List<PezzoQuantita> listaPezziDisponibili, int ruote, int sportelli, TipiVeicoli tipoVeicolo) 
			throws PezziTerminatiException, VeicoloSconosciutoException, PezzoSconosciutoException, SportelliSbagliatiException {
		
		//Crea veicolo
		T veicolo = (T) FactoryVeicolo.getVeicoloFromString(tipoVeicolo, sportelli, ruote);

		//Si aggiorna la lista dei pezzi disponibili, togliendo quelli usati per la costruzione del veicolo
		for(PezzoQuantita pezzoQuantita : listaPezziDisponibili) {
			
			if(pezzoQuantita.getPezzo() instanceof Ruota) {
				pezzoQuantita.setQuantita(pezzoQuantita.getQuantita()-veicolo.getNumeroRuote());
				if(pezzoQuantita.getQuantita() < 0) {
					throw new PezziTerminatiException("Errore: Sono terminate le ruote a disposizione!");
				}
			} else if(pezzoQuantita.getPezzo() instanceof Sportello){
				pezzoQuantita.setQuantita(pezzoQuantita.getQuantita()-veicolo.getNumeroSportelli());
				if(pezzoQuantita.getQuantita() < 0) {
					throw new PezziTerminatiException("Errore: Sono terminati gli sportelli a disposizione!");
				}
			}
		}
		
		
		//Si scorre la lista dei robot, ogni robot costruisce una certa quantita di un certo pezzo
		//Si aggiornano listaRuote e listaSportelli
		for(int i=0; i<listaRobot.size(); i++) {
			if(listaRobot.get(i).getTipoPezzo().equals(TipiPezzi.Ruota)) {
				List<Ruota> listaRuote = new ArrayList<>();
				listaRuote = listaRobot.get(i).apply(ruote, TipiPezzi.Ruota);
				veicolo.setListaRuote(listaRuote);
			} else if(listaRobot.get(i).getTipoPezzo().equals(TipiPezzi.Sportello)){
				List<Sportello> listaSportelli = new ArrayList<>();
				listaSportelli = listaRobot.get(i).apply(sportelli, TipiPezzi.Sportello);
				veicolo.setListaSportelli(listaSportelli);	
			} else {
				throw new PezzoSconosciutoException("Questo pezzo non esiste!");
			}
		}
		
		// se tutto ok si mette isBuilt = true nel veicolo
			if(veicolo.getListaSportelli().size() == veicolo.getNumeroSportelli() &&
					veicolo.getListaRuote().size() == veicolo.getNumeroRuote() ) {
				veicolo.setBuilt(true);
			}
			
		return veicolo;
	}
	
}
