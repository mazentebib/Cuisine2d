package modele;

import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * Conteneur de l'ensemble des equipements de l'interface
 */
public class EtatEquipement {

	/* Liste contenant l'ensemble des equipements de l'application */
	private ArrayList<Equipement> equipements;
	
	/**
	 * Constructeur de la classe
	 */
	public EtatEquipement() {
		this.equipements = new ArrayList<Equipement>();
		this.fill();
	}
	
	/**
	 * Remplit la liste d'equipements
	 */
	private void fill() {
		this.equipements.add(new Equipement(100, 75, new Image("file:ressources/example/oven_1.png"), "Four", 100.25, "DF42D4F", "Four"));
		this.equipements.add(new Equipement(75, 50, new Image("file:ressources/example/oven_2.png"), "Micro-ondes", 25.50, "D43DF42", "Four"));
		this.equipements.add(new Equipement(50, 50, new Image("file:ressources/example/chair_1.png"), "Chaise", 15.30, "ERF242", "Chaise"));
		this.equipements.add(new Equipement(40, 40, new Image("file:ressources/example/chair_2.png"), "Chaise", 16.20, "DSFS543", "Chaise"));
		this.equipements.add(new Equipement(100, 20, new Image("file:ressources/example/door_1.png"), "Porte", 35.50, "DF754DSF", "Porte"));
		this.equipements.add(new Equipement(100, 20, new Image("file:ressources/example/door_2.png"), "Porte", 45.5, "254DFSD", "Porte"));
		this.equipements.add(new Equipement(75, 75, new Image("file:ressources/example/fridge_1.png"), "Réfrigérateur", 150.99, "DSF2454", "Réfrigérateur"));
		this.equipements.add(new Equipement(75, 75, new Image("file:ressources/example/fridge_2.png"), "Réfrigérateur", 250.50, "DF4225", "Réfrigérateur"));
		this.equipements.add(new Equipement(150, 50, new Image("file:ressources/example/table_1.png"), "Table", 20.25, "HGF445DSF", "Meuble"));
		this.equipements.add(new Equipement(100, 100, new Image("file:ressources/example/table_2.png"), "Table", 30.40, "DSF445DS", "Meuble"));
	}
	
	/**
	 * Ajoute un equipement au sein de la liste
	 * @param equipement equipement a ajouter
	 */
	public void add(Equipement equipement) {
		this.equipements.add(equipement);
	}
	
	/**
	 * Retourne un equipement dont la position correspond a index
	 * @param index position de l'equipement a retourner
	 * @return un equipement dont la position correspond a index
	 */
	public Equipement get(int index) {
		return this.equipements.get(index);
	}
	
	/**
	 * Retourne la taille de la liste
	 * @return taille de la liste
	 */
	public int getSize() {
		return this.equipements.size();
	}
	
	/**
	 * Retourne, sous forme de tableau, l'ensemble des equipements de la liste
	 * @return l'ensemble des equipements de la liste
	 */
	public Equipement[] getAll() {
		Equipement[] equipements = new Equipement[this.equipements.size()];
		
		for(int i=0 ; i<equipements.length ; i++) {
			equipements[i] = this.equipements.get(i);
		}
		
		return equipements;
	}
}