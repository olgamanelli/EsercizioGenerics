package main;

import java.util.ArrayList;
import java.util.List;

import main.factory.FactoryPezzo;

public class Robot<T extends Pezzo>{
	
	private String nomePezzo;
	
	//costruttore
	public Robot(String nomePezzo) {
		this.nomePezzo = nomePezzo;
	}
	
	
	
	public String getNomePezzo() {
		return nomePezzo;
	}



	public void setNomePezzo(String nomePezzo) {
		this.nomePezzo = nomePezzo;
	}



	public List<T> apply(int quantita, String nomePezzo) throws Exception{
		
		List<T> pezziCostruiti = new ArrayList<T>();
		
		for(int i=0; i<quantita; i++) {
			T pezzo = (T) FactoryPezzo.getPezzoFromString(nomePezzo);
			pezziCostruiti.add(pezzo);
		}
		
		return pezziCostruiti;
		
	}

}
