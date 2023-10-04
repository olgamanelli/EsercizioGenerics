package main.pezzi;

public class PezzoQuantita {

		//Attributi
		private Pezzo pezzo;
		private int quantita;
		
		//Costruttore
		public PezzoQuantita(Pezzo pezzo, int quantita){
			this.pezzo=pezzo;
			this.quantita=quantita;
		}

		//Getters e Setters 
		public Pezzo getPezzo() {
			return pezzo;
		}

		public void setPezzo(Pezzo pezzo) {
			this.pezzo = pezzo;
		}

		public int getQuantita() {
			return quantita;
		}

		public void setQuantita(int quantita) {
			this.quantita = quantita;
		}
}
