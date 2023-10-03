package main;

import java.util.ArrayList;
import java.util.List;

import main.factory.FactoryVeicolo;

public class CatenaMontaggio<T extends Veicolo> {
	
	//attributo
	private List<Robot> listaRobot;
	
	//costruttore
	public CatenaMontaggio(List<Robot> listaRobot) {
		this.listaRobot = listaRobot;
	}
	
	
	/**
	 * 
	 * @param listaPezziDisponibili, lista dei pezzi a disposizione, da modificare una volta costruito veicolo
	 * @param ruote, numero ruote del veicolo da costruire
	 * @param sportelli, numero sportelli del veicolo da costruire
	 * @param nomeVeicolo
	 * @return T, veicolo costruito
	 * @throws Exception
	 */
	public T apply(List<PezzoQuantita> listaPezziDisponibili, int ruote, int sportelli, String nomeVeicolo) throws Exception {
		
		T veicolo = (T) FactoryVeicolo.getVeicoloFromString(nomeVeicolo, sportelli, ruote);
		for(PezzoQuantita pezzoQuantita : listaPezziDisponibili) {
			
			if(pezzoQuantita.getPezzo().getNome().equals("Ruota")) {
				pezzoQuantita.setQuantita(pezzoQuantita.getQuantita()-veicolo.getNumeroRuote());
			} else if(pezzoQuantita.getPezzo().getNome().equals("Sportello")){
				pezzoQuantita.setQuantita(pezzoQuantita.getQuantita()-veicolo.getNumeroSportelli());
			}
		}
		
		//TODO: devo costruire il veicolo
		
		//al robot dico quantità di pezzi e nome pezzo e gli faccio costruire il veicolo, ritrona una lista dei pezzi costruiti
		
		for(int i=0; i<listaRobot.size(); i++) {
			
			if(listaRobot.get(i).getNomePezzo().equals("Ruota")) {
				List<Ruota> listaRuote = new ArrayList<>();
				listaRuote = listaRobot.get(i).apply(ruote, "Ruota");
				veicolo.setListaRuote(listaRuote);
			} else if(listaRobot.get(i).getNomePezzo().equals("Sportello")){
				List<Sportello> listaSportelli = new ArrayList<>();
				listaSportelli = listaRobot.get(i).apply(sportelli, "Sportello");
				veicolo.setListaSportelli(listaSportelli);	
			}
		}
		
		// se tutto ok metto isBuilt = true nel veicolo
			if(veicolo.getListaSportelli().size() == veicolo.getNumeroSportelli() &&
					veicolo.getListaRuote().size() == veicolo.getNumeroRuote() ) {
				veicolo.setBuilt(true);
			}
			
		return veicolo;
	}
	
}
