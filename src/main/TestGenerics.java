package main;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import main.constant.Constant;
import main.enums.TipiPezzi;
import main.enums.TipiVeicoli;
import main.factory.FactoryPezzo;
import main.pezzi.PezziTerminatiException;
import main.pezzi.Pezzo;
import main.pezzi.PezzoQuantita;
import main.pezzi.PezzoSconosciutoException;
import main.pezzi.Ruota;
import main.pezzi.SportelliSbagliatiException;
import main.pezzi.Sportello;
import main.veicoli.Auto;
import main.veicoli.Camion;
import main.veicoli.Moto;
import main.veicoli.Veicolo;
import main.veicoli.VeicoloSconosciutoException;

/**
 * Programma che testa il funzionamento delle classi Pezzo, Veicolo e delle sottoclassi
 * @author Manelli
 *
 */
public class TestGenerics  {

	/**
	 * Nel main viene letto un file con pezzi e quantità disponibili, che vengono assegnati ai veicoli creati
	 * tramite catene di montaggio
	 * @param args
	 * @throws Exception
	 */

	//Classe thread
	public static class MyThread extends Thread{

		//attributi
		private CatenaMontaggio catena;
		private  List<PezzoQuantita> listaPezzi;
		private Veicolo veicolo;
		private TipiVeicoli tipoVeicolo;

		//costruttore
		public MyThread(CatenaMontaggio catena, List<PezzoQuantita> listaPezzi, Veicolo veicolo, TipiVeicoli tipoVeicolo) {
			this.catena=catena;
			this.listaPezzi=listaPezzi;
			this.veicolo = veicolo;
			this.tipoVeicolo=tipoVeicolo;
		}

		@Override
		public void run() {
			//Oggetto a cui si vuole che i thread non accedano contemporaneamente --> synchronized
			//			synchronized (this.listaPezzi) {
			try {
				this.listaPezzi.stream().forEach(pezzoQuantita -> {
					System.out.println("Start thread secondario per "+ this.tipoVeicolo + " - " + 
							pezzoQuantita.getPezzo().getTipoPezzo() + " " + pezzoQuantita.getQuantita() );
				});
				this.catena.apply(this.listaPezzi, this.veicolo, this.tipoVeicolo);
				this.listaPezzi.stream().forEach(pezzoQuantita -> {
					System.out.println("End thread secondario per "+ this.tipoVeicolo + " - " + 
							pezzoQuantita.getPezzo().getTipoPezzo() + " " + pezzoQuantita.getQuantita() );
				});
			} catch (VeicoloSconosciutoException | PezzoSconosciutoException | SportelliSbagliatiException
					| PezziTerminatiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("finito thread per " + this.tipoVeicolo);
		}
		//		}
	}

	public static void main(String[] args) throws Exception {

		//Creazione lista pezzi disponibili
		List<PezzoQuantita> listaPezzi = new ArrayList<>();

		//Si legge lista da file txt tramite l'apposita funzione readFromTxt
		listaPezzi = readFromTxt(Constant.PATH_READER);

		//Se lista pezzi è vuota si stampa errore, altrimenti si stampano nome dei pezzi e quantità disponibili
		if(listaPezzi.size() == 0) {
			System.out.println("Non sono risucito a caricare nessun pezzo."); //TODO creare eccezione
		}
		else {
			listaPezzi.stream().forEach(pezzoQuantita -> {
				System.out.println(pezzoQuantita.getPezzo().getTipoPezzo() + " " + pezzoQuantita.getQuantita() );
			});
		}

		//Creazione lista dei robot
		List<Robot> listaRobot = new ArrayList<>();
		Robot<Ruota> robotRuote = new Robot<Ruota>(TipiPezzi.Ruota);
		Robot<Sportello> robotSportelli = new Robot<Sportello>(TipiPezzi.Sportello);
		listaRobot.add(robotRuote);
		listaRobot.add(robotSportelli);

		//Creazione catene di montaggio per ogni tipo di veicolo
		CatenaMontaggio<Auto> catenaMontaggioAuto = new CatenaMontaggio<Auto>(listaRobot);
		CatenaMontaggio<Moto> catenaMontaggioMoto = new CatenaMontaggio<Moto>(listaRobot);
		CatenaMontaggio<Camion> catenaMontaggioCamion = new CatenaMontaggio<Camion>(listaRobot);

		//Creazione veicoli
		Auto auto = new Auto(2);
		Moto moto = new Moto();
		Camion camion = new Camion(4,4);

		//Creazione threadpool con core size = 3
		ExecutorService executor = Executors.newFixedThreadPool(4);

		//Creazione e inserimento dei thread nel threadpool + esecuzione
		executor.submit(new  MyThread(catenaMontaggioAuto, listaPezzi, auto, auto.getTipoVeicolo()));
		executor.submit(new  MyThread(catenaMontaggioMoto, listaPezzi, moto, moto.getTipoVeicolo() ));
		executor.submit(new  MyThread(catenaMontaggioCamion, listaPezzi, camion, camion.getTipoVeicolo()));
//		executor.submit(new  MyThread(catenaMontaggioAuto, listaPezzi, auto, auto.getTipoVeicolo()));


		//Shutdown del threadpool
		executor.shutdown();


		/**
		 * TODO
		 * STEP 1
		 * CONSTANT fare per tutte le costanti
		 * gestire le eccezioni custom --> gestione eccezione custom nel caso finiscano i pezzi: PezziTerminatiException
		 * 									gestion eccezione se Veicolo o Pezzo non sono validi: PezzoSconisciutoException, VeicoloSconosciutoException
		 *									gestione numero sbagliato di sportelli: SportelliSbagliatiException
		 * 
		 * warning eclipse
		 * cambiamo nomi (es catena 1 diventa catenaMontaggioAuto)
		 * l'obiettivo è prima creare le 3 catene di montaggio e poi fare l'apply di tutte le auto
		 * una volta terminato tutto il main, aggiornare il file txt
		 * 
		 * Pensare a come modificare il codice in modo da evitare di portarsi dietro sempre il nome del pezzo
		 *  o del veicolo (potresti utilizzare il get class e sfruttare l'enumeratore per mappare le classi nei factory)
		 *  
		 * creare N package in cui mettiamo il package pezzi, il package veicoli
		 * commenti
		 * 
		 * 
		 * STEP 2 
		 * lavorare con i multi thread
		 * Avviamo un thread per ogni catena di montaggio
		 * e poi facciamo la solita esecuzione di apply auto, camion e moto in modo parallelo (threadpool)(Future)
		 * CONCORRENZA
		 * sfruttiamo stream (no for)
		 * 
		 * capire perche non funziona sync
		 */


		//Check: se dopo lo shutdown le operazioni si sono concluse abbastanza velocemente, aggiorno file
		if(executor.awaitTermination(2, TimeUnit.SECONDS)) {
			listaPezzi.stream().forEach(pezzoQuantita -> {
				System.out.println("main thread: " + pezzoQuantita.getPezzo().getTipoPezzo() + " " + pezzoQuantita.getQuantita() );
			});

			//Aggiornamento file txt

			Path path = Paths.get(Constant.PATH_READER);
			//Cancella file e ne crea uno nuovo con lo stesso nome
			Files.deleteIfExists(path);
			Files.createFile(path);
			//Scrive su nuovo file le nuove info
			FileWriter fw = new FileWriter(Constant.PATH_READER, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			listaPezzi.stream().forEach(pezzoQuantita -> {
				pw.println(pezzoQuantita.getPezzo().getTipoPezzo() + "," + pezzoQuantita.getQuantita() );
			});

			//Si chiude la print writer
			pw.close();
		}
	}

	/**
	 * 
	 * @param filePath, path del file txt in entrata
	 * @return la lista List<PezzoQuantita> letta dal file
	 * @throws Exception
	 */
	private static List<PezzoQuantita> readFromTxt(String filePath) throws Exception, PezzoSconosciutoException{

		//La lista di PezzoQuantita che viene restituita
		List<PezzoQuantita> listapezzi = Collections.synchronizedList(new ArrayList<PezzoQuantita>());


		try(	//Lettura del file tramite FilePath
				FileReader fr = new FileReader(filePath, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(fr);
				) {

			//Si legge una linea del file
			String line = br.readLine();

			//Finchè la linea letta non è vuota la si divide in nome pezzo e quantità e si riempie la lista listapezzi 
			while(line != null) {
				//Array di oggetti che contiene gli elementi della riga
				Object[] attributes = line.split(",");

				//Si estrae il tipo del pezzo e la quantità disponibile dall'array
				TipiPezzi tipoPezzo  = TipiPezzi.valueOf(attributes[0].toString());
				int quantita = Integer.valueOf(attributes[1].toString());

				//Si trasforma il nome del pezzo nel pezzo vero e proprio tramite la classe FactoryPezzo
				// (non si pu istanziare direttamente un pezzo perchè classe astratta)
				Pezzo pezzo = FactoryPezzo.getPezzoFromTipo(tipoPezzo);

				//Si crea il il PezzoQuantita e si aggiunge nella lista listpezzi
				PezzoQuantita pezzoquantita = new PezzoQuantita(pezzo, quantita);
				listapezzi.add(pezzoquantita);

				//Si legge la linea successiva
				line = br.readLine();
			}
		} catch (Exception e) {
			//Se il file non esiste: Eccezione
			System.out.println("Eccezione perche non ho trovato il file: " + e.getMessage());
		} 
		//Restituisce la lista listapezzi 
		return listapezzi;

	}

}
