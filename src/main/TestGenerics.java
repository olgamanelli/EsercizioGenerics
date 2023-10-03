package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import main.constant.Constant;
import main.factory.FactoryPezzo;

/**
 * Programma che testa il funzionamento delle classi Pezzo, Veicolo e delle sottoclassi
 * @author Manelli
 *
 */
public class TestGenerics {

	/**
	 * Nel main viene letto un file con pezzi e quantità disponibili, che vengono assegnati ai veicoli creati
	 * tramite catene di montaggio
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		//creazione lista pezzi disponibili
		List<PezzoQuantita> listaPezzi = new ArrayList<>();
		
		//leggo lista da file txt tramite l'apposita funzione
		listaPezzi = readFromCsv(Constant.PATH_READER);
		
		//se lista pezzi è vuota stampo errore, altrimenti stampo nome dei pezzi e quantità disponibili
		if(listaPezzi.size() == 0) {
			System.out.println("Non sono risucito a caricare nessun pezzo.");
		}
		else {
			for(PezzoQuantita pezzoQuantita : listaPezzi) {
				System.out.println(pezzoQuantita.getPezzo().getNome() + " " + pezzoQuantita.getQuantita() );
			}
		}
		
		// TODO 
		//Creazione lista dei robot
		List<Robot> listaRobot = new ArrayList<>();
		Robot<Ruota> robot1 = new Robot<Ruota>("Ruota");
		Robot<Sportello> robot2 = new Robot<Sportello>("Sportello");
		listaRobot.add(robot1);
		listaRobot.add(robot2);
		
		/**
		 * TODO
		 * STEP 1
		 * CONSTANT fare per tutte le costanti
		 * gestire le eccezioni custom
		 * warning eclipse
		 * l'obiettivo è prima creare le 3 catene di montaggio e poi fare l'apply di tutte le auto
		 * cambiamo nomi (es catena 1 diventa catenaMontaggioAuto
		 * gestione eccezione custom nel caso finiscano i pezzi
		 * una volta terminato tutto il main, aggiornare il file txt
		 * Check delle porte e delle ruote (per esempio un check che gli sportelli della macchina possono essere solo X o Y, ovviamente da fare dentro la classe AUTO)
		 * Pensare a come modificare il codice in modo da evitare di portarsi dietro sempre il nome del pezzo o del veicolo (potresti utilizzare il get class e sfruttare l'enumeratore per mappare le classi nei factory)
		 * creare N package in cui mettiamo il package pezzi, il package veicoli
		 * commenti
		 * 
		 * 
		 * STEP 2 
		 * lavorare con i multi thread
		 * Avviamo un thread per ogni catena di montaggio
		 * e poi facciamo la solita esecuzione di apply auto, camion e moto in modo parallelo (threadpool)(Future)
		 * CONCORRENZA
		 */
		//Per ogni veicolo che si vuole costruire si chiama la catena di montaggio dello specifico veicolo
		Auto auto = new Auto(2);
		CatenaMontaggio<Auto> catena1= new CatenaMontaggio<Auto>(listaRobot);
		auto = catena1.apply(listaPezzi, auto.getNumeroRuote(), auto.getNumeroSportelli(), auto.getNome());
		
		Moto moto = new Moto();
		CatenaMontaggio<Moto> catena2 = new CatenaMontaggio<Moto>(listaRobot);
		moto = catena2.apply(listaPezzi, moto.getNumeroRuote(), moto.getNumeroSportelli(), moto.getNome());
		
		Camion camion = new Camion(8,2);
		CatenaMontaggio<Camion> catena3 = new CatenaMontaggio<Camion>(listaRobot);
		camion = catena3.apply(listaPezzi, camion.getNumeroRuote(), camion.getNumeroSportelli(), camion.getNome());
		
		//check
		for(PezzoQuantita pezzoQuantita : listaPezzi) {
			System.out.println(pezzoQuantita.getPezzo().getNome() + " " + pezzoQuantita.getQuantita() );
		}
		
		// TODO pulizia commenti
		//Ogni volta che si costruisce un veicolo si tolgono dalla listaPezzi i pezzi che servono per costruirlo
		
		
		
		
	}
	
	/**
	 * 
	 * @param filePath, path del file in entrata
	 * @return la lista List<PezzoQuantita> letta dal file
	 * @throws Exception
	 */
	// TODO diventa readFromTxt
	private static List<PezzoQuantita> readFromCsv(String filePath) throws Exception{
		
		//La lista di PezzoQUantita che viene restituita
		List<PezzoQuantita> listapezzi = new ArrayList<>();
		
		
		try(	//lettura del file tramite FilePath
				FileReader fr = new FileReader(filePath, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(fr);
			) {
			
			//Si legge una linea del file
			String line = br.readLine();
			
			//Finchè la linea letta non è vuota la si divide in nome pezzo e quantità e si riempie la lista listapezzi 
			while(line != null) {
				//Array di oggetti che contiene gli elementi della riga
				Object[] attributes = line.split(",");
				
				//Estraggo il nome del pezzo (come stringa) e la quantità disponibile dall'array
				String nomePezzo = attributes[0].toString();
				int quantita = Integer.valueOf((String) attributes[1]);
				
				//Trasformo il nome del pezzo nel pezzo vero e proprio tramite la classe FactoryPezzo
				// (non si pu istanziare direttamente un pezzo perchè classe astratta)
				Pezzo pezzo = FactoryPezzo.getPezzoFromString(nomePezzo);
				
				//Creo il il PezzoQuantita e lo aggiungo nella lista listpezzi
				PezzoQuantita pezzoquantita = new PezzoQuantita(pezzo, quantita);
				listapezzi.add(pezzoquantita);
				
				//Si legge la linea successiva
				line = br.readLine();
			}
		} catch (Exception e) {
			//Se il file non esiste: Eccezione
			System.out.println("Eccezione perche non ho trovato il file: " + e.getMessage());
		}
		
		//Restituisc e la lista listapezzi 
		return listapezzi;
		
	}

}
