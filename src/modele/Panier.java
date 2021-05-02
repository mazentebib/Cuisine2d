package modele;

import java.util.ArrayList;

/**
 * Classe representative de la liste des equipements ajoutes par l'utilisateur dans le plan de cuisine
 */
public class Panier {

	/* Ensemble des equipements ajoutes par l'utilisateur dans le plan de cuisine */
	private ArrayList<Equipement> panier;
	
	/* Prix de l'ensemble des equipements ajoutes par l'utilisateur dans le plan de cuisine */
	private double total;
	
	/**
	 * Constructeur de la classe
	 */
	public Panier() {
		this.panier = new ArrayList<Equipement>();
	}
	
	/**
	 * Ajoute un equipement au sein du panier
	 * @param equipement equipement a ajouter
	 */
	public void add(Equipement equipement) {
		this.panier.add(equipement);
		this.total += equipement.getPrix();
	}
	
	/**
	 * Retire un equipement au sein du panier
	 * @param equipement equipement a retirer
	 */
	public void remove(Equipement equipement) {
		this.panier.remove(equipement);
		this.total -= equipement.getPrix();
	}
	
	/**
	 * Retourne un equipement du panier a partir d'un index
	 * @param index index de l'equipement a retourner
	 * @return equipement correspondant a index
	 */
	public Equipement get(int index) {
		return this.panier.get(index);
	}
	
	/**
	 * Retourne le nombre d'equipements contenus au sein du panier
	 * @return nombre d'equipements contenus au sein du panier
	 */
	public int getSize() {
		return this.panier.size();
	}
	
	/**
	 * Retourne le prix total des equipements contenus au sein du panier
	 * @return prix total des equipements contenus au sein du panier
	 */
	public double getTotal() {
		return this.total;
	}
	
	/**
	 * Retourne l'ensemble des equipements contenus au sein du panier
	 * @return l'ensemble des equipements contenus au sein du panier
	 */
	public Equipement[] getAll() {
		Equipement[] panier = new Equipement[this.panier.size()];
		
		for(int i=0 ; i<panier.length ; i++) {
			panier[i] = this.panier.get(i);
		}
		
		return panier;
	}
}
