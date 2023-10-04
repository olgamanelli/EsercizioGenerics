package main.veicoli;

import java.util.List;

import main.pezzi.Ruota;
import main.pezzi.Sportello;

public class Veicolo {

		//attributi
		private int numeroRuote;
		private int numeroSportelli;
		private volatile boolean  isBuilt = false;
		private List<Ruota> listaRuote;
		private List<Sportello> listaSportelli;
		
	

		//costruttore
		public Veicolo(int ruote, int sportelli) {
			this.numeroRuote = ruote;
			this.numeroSportelli = sportelli;
		}

		public int getNumeroRuote() {
			return numeroRuote;
		}

		public void setNumeroRuote(int numeroRuote) {
			this.numeroRuote = numeroRuote;
		}

		public int getNumeroSportelli() {
			return numeroSportelli;
		}

		public void setNumeroSportelli(int numeroSportelli) {
			this.numeroSportelli = numeroSportelli;
		}

		public synchronized boolean getBuilt() {
			return isBuilt;
		}

		public void setBuilt(boolean isBuilt) {
			this.isBuilt = isBuilt;
		}
		
		public List<Ruota> getListaRuote() {
			return listaRuote;
		}

		public void setListaRuote(List<Ruota> listaRuote) {
			this.listaRuote = listaRuote;
		}

		public List<Sportello> getListaSportelli() {
			return listaSportelli;
		}

		public void setListaSportelli(List<Sportello> listaSportelli) {
			this.listaSportelli = listaSportelli;
		}
		
		
		
		
	
}
