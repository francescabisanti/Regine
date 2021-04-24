package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {

	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
   	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	
	
	
	private int N;
	private List <Integer> soluzione;
	
	public List <Integer> risolvi(int N){
		this.N=N;
		List <Integer> parziale= new ArrayList <Integer>();
		this.soluzione= null; //se non trova nulla mi da null
		cerca(parziale,0);
		//quando esco dalla cerca, parziale è vuota
		return this.soluzione;
		
	}
	//cerca== true : trovato; cerca== false : cerca ancora
	private boolean cerca(List<Integer>parziale, int livello) {
		if(livello==N) {
			// caso terminale
			//System.out.println(parziale); //per ora la stampo
			this.soluzione= new ArrayList<>(parziale);
			return true;
		} else {
			for(int colonna=0; colonna<N; colonna++) {
				// if la possa nella casella [livello][colonna] è valida
				// se sì, aggiungi a parziale e fai ricorsione
			
			if(posValida(parziale, colonna)) {
				//allora fai ricorsione
				//parziale.add(colonna);//sto aggiungendo un numeretto al fondo della lista
				//es [0,6,4,7,1] il mio 1 è indice aggiunto alla parziale
				//abbiamo visto però che anche la pos 5 era valida, come alternativa all 1
				//quindi torno indietro, tolgo l'1 e provo con il 5 dopo il 7
				//ATTENZIONE A NON AGGIUNGERE
				
				//se non voglio fare il backtracking
				//List <Integer> parzialeNuovo= new ArrayList <>(parziale);
				//parzialeNuovo.add(colonna);
				//cerca(parzialeNuovo, livello+1);
				
				parziale.add(colonna);
				boolean trovato=cerca(parziale, livello+1);
				if(trovato)
					return true; //minuto58
				
				parziale.remove(parziale.size()-1);//tolgo l'elemento appena aggiunto, backtracking
			}
		}
		}
		return false;
	}
	
	private boolean posValida(List<Integer> parziale, int colonna) {
		int livello=parziale.size();
		//controlla se viene mangiata in verticale
		if(parziale.contains(colonna))
			return false;
		//controlla le diagonali:confronta la posizione (livello, colonna) con (r,c)
		//delle regine esistenti
		for(int r=0; r<livello; r++) {
			int c=parziale.get(r);
			
			if(r+c==livello+colonna || r-c==livello-colonna)
				return false;
			//minuto 32
			//io mi concentro solo su una riga e  non si guarda avanti o indietro
		}
		//altrimenti vuol dire che non ho conflitti e la posizione è valida
		return true;
	}
	
	
	
	
}
