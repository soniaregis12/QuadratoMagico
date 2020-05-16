package it.polito.tdp.quadrato.model;

import java.util.ArrayList;
import java.util.List;

public class RisolviQuadrato {
	private int N ; // lato del quadrato
	private int N2 ; // numero di caselle (N^2)
	private int magica ; // costante magica
	private List<List<Integer>> soluzioni;
	
	public RisolviQuadrato(int N){
		this.N = N;
		this.N2 = N*N;
		this.magica = N*(N2+1)/2;
	}
	// Calcola tutti i quadrati magici
	public List<List<Integer>> quadrati() {
		
		List<Integer> parziale = new ArrayList<Integer>();
		int livello = 0;
		this.soluzioni = new ArrayList<List<Integer>>();
		ricorsiva(parziale, livello);
		return this.soluzioni;
	}
	// Procedura ricorsiva vera e propria
	private void ricorsiva(List<Integer> parziale, int livello) {
		
		if(livello == this.N2) {	// Caso terminale
			
			if(controlla(parziale)) {	// Il quadrato è magico
				//System.out.println(parziale);
				this.soluzioni.add(new ArrayList<Integer>(parziale));		// Quando porto fuori dei dati devo portare fuori il valore, non il suo riferimento
			}
			return;
		}
		// Devo aggiungere controlli intemedi quando il livello è multiplo di N, ovvero ho finito una riga
		
		if(livello%N == 0 && livello != 0) {		// La funzione controlla se livello è un multiplo di N
			if(!controllaRiga(parziale, livello/N-1)) {
				return;	// Potatura dell'albero di ricerca
			}
		}
		// Cado intermedio
		for(int valore=1; valore <= this.N2; valore++) {
			if(!parziale.contains(valore)) {		// Se non lo contiene allora posso provarlo
				parziale.add(valore);
				ricorsiva(parziale, livello+1);
				parziale.remove(parziale.size()-1);
			}
		}
	}
	
	
	/**
	 * Verifica se una soluzione rispetta tutte le somme
	 * @param parziale
	 * @return
	 */
	private boolean controlla(List<Integer> parziale) {
		if(parziale.size()!=this.N*this.N)
			throw new IllegalArgumentException("Numero di elementi insufficiente") ;
		
		// Fai la somma delle righe
		for(int riga=0; riga<this.N; riga++) {
			int somma = 0 ;
			for(int col=0; col<this.N; col++) {
				somma += parziale.get(riga*this.N+col) ;
			}
			if(somma!=this.magica)
				return false ;
		}
		
		// Fai la somma delle colonne
		for(int col=0; col<this.N; col++) {
			int somma = 0 ;
			for(int riga=0; riga<this.N; riga++) {
				somma += parziale.get(riga*this.N+col) ;
			}
			if(somma!=this.magica)
				return false ;
		}
		
		// diagonale principale
		int somma = 0;
		for(int riga=0; riga<this.N; riga++) {
			somma += parziale.get(riga*this.N+riga) ;
		}
		if(somma!=this.magica)
			return false ;
		
		// diagonale inversa
		somma = 0;
		for(int riga=0; riga<this.N; riga++) {
			somma += parziale.get(riga*this.N+(this.N-1-riga)) ;
		}
		if(somma!=this.magica)
			return false ;

		return true ;
	}
	
	private boolean controllaRiga(List<Integer> parziale, int riga) {
		int somma = 0;
		
		for(int col=0; col<N; col++) {
			somma+= parziale.get(riga*N+col);
		}
		return (somma==magica);
	}
}
