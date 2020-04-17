package it.polito.tdp.quadrato.model;

import java.util.ArrayList;
import java.util.List;

public class RisolviQuadrato {
	private int N ; // lato del quadrato
	private int N2 ; // numero di caselle (N^2)
	private int magica ; // costante magica
	
	public RisolviQuadrato(int N){
		this.N = N;
		this.N2 = N*N;
		this.magica = N*(N2+1)/2;
	}
	// Calcola tutti i quadrati magici
	public void quadrati() {
		
		List<Integer> parziale = new ArrayList<Integer>();
		int livello = 0;
		ricorsiva(parziale, livello);
	}
	// Procedura ricorsiva ver e propria
	private void ricorsiva(List<Integer> parziale, int livello) {
		
		if(livello == this.N2) {	// Caso terminale
			
			if(controlla(parziale)) {	// Il quadrato è magico
				System.out.println(parziale);
			}
			return;
		}
		
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
}
