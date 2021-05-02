package modele;

import java.util.ArrayList;

/**
 * Recherche des equipements correspondants aux informations entrees et retourne cette recherche
 * au sein d'un tableau, vide et reinitisalise la recherche
 * 
 */
public class BarreDeRecherche {

	/* Tableau correspondant a l'ensemble des equipements */
	private Equipement[] ensemble;
	
	/* Liste de l'ensemble des equipements de la recherche */
	private ArrayList<Equipement> recherche;
	
	/**
	 * Constructeur de la classe
	 * @param etatEquipement
	 */
	public BarreDeRecherche(EtatEquipement etatEquipement) {
		this.ensemble = etatEquipement.getAll();
		this.reset();
	}
	
	/**
	 * Remplit la recherche d'equipements correspondants a la saisie
	 * @param text
	 */
	public void search(String text) {
		this.toEmpty();
		
		for(int i=0 ; i<this.ensemble.length ; i++) {
			/* Ignore la case et recherche le titre et la categorie correspondant a la saisie */
			if(text.toLowerCase().contains(this.ensemble[i].getTitre().toLowerCase())
				|| text.toLowerCase().contains(this.ensemble[i].getReference().toLowerCase())
				|| this.ensemble[i].getTitre().toLowerCase().contains(text.toLowerCase())
				|| this.ensemble[i].getReference().toLowerCase().contains(text.toLowerCase())) {
				
				this.recherche.add(this.ensemble[i]);
			}
		}
	}
	
	/**
	 * Vide la recherche
	 */
	public void toEmpty() {
		while(!this.recherche.isEmpty()) {
			this.recherche.remove(0);
		}
	}
	
	/**
	 * Reinitialise la recherche
	 */
	public void reset() {
		this.recherche = new ArrayList<Equipement>();
		
		for(int i=0 ; i<this.ensemble.length ; i++) {
			this.recherche.add(this.ensemble[i]);
		}
	}
	
	/**
	 * Retourne, sous forme de tableau, les equipements de la recherche
	 * @return
	 */
	public Equipement[] getRecherche() {
		Equipement[] recherche = new Equipement[this.recherche.size()];
		
		for(int i=0 ; i<recherche.length ; i++) {
			recherche[i] = this.recherche.get(i);
		}
		
		return recherche;
	}
}
