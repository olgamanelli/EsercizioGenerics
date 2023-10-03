package main;

public class PezzoQuantita {

		private Pezzo pezzo;
		private int quantita;
		
		//costruttore
		
		public PezzoQuantita(Pezzo pezzo, int quantita){
			
			this.pezzo=pezzo;
			this.quantita=quantita;
			
		}

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
