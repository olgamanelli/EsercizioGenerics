package main;

import java.util.ArrayList;
import java.util.List;
import main.enums.*;
import main.factory.FactoryPezzo;

public class Robot<T extends Pezzo>{
	
	//Attributi
	private TipiPezzi tipoPezzo;
	
	//Costruttore
	public Robot(TipiPezzi tipoPezzo) {
		this.tipoPezzo = tipoPezzo;
	}
	
	//Metodi
	
	//Getters Setters
	public TipiPezzi getTipoPezzo() {
		return tipoPezzo;
	}

	public void setTipoPezzo(TipiPezzi tipoPezzo) {
		this.tipoPezzo = tipoPezzo;
	}

	//Il robot crea i pezzi necessari
	public List<T> apply(int quantita, TipiPezzi tipoPezzo) throws PezzoSconosciutoException{
		
		List<T> pezziCostruiti = new ArrayList<T>();
		
		for(int i=0; i<quantita; i++) {
			T pezzo = (T) FactoryPezzo.getPezzoFromString(tipoPezzo);
			pezziCostruiti.add(pezzo);
		}
		
		return pezziCostruiti;
		
	}

}
