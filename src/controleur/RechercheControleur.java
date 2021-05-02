package controleur;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import modele.BarreDeRecherche;
import modele.Equipement;

/**
 * Provoque la modification des equipements affiches au sein de l'interface
 */
public class RechercheControleur {
	
	/* Permet de récupérer la liste des resultats de la recherche */
	private BarreDeRecherche barreDeRecherche;
	
	/* Permet de modifier la liste des equipements affiches */
	private EquipementControleur equipementsControleur;
	
	/* Champs de texte representant la barre de recherche */
	private TextField searchTxt;
	
	/**
	 * Constructeur de la classe
	 * @param barreDeRecherche
	 * @param equipementsControleur
	 */
	public RechercheControleur(BarreDeRecherche barreDeRecherche, EquipementControleur equipementsControleur) {
		this.barreDeRecherche = barreDeRecherche;
		this.equipementsControleur = equipementsControleur;
	}
	
	/**
	 * Initialise le champs de texte representant la barre de recherche
	 * @param searchTxt champs de texte representant la barre de recherche
	 */
	public void setSearch(TextField searchTxt) {
		this.searchTxt = searchTxt;
	}
	
	/**
	 * Ajoute des equipements au sein du conteneur lorsque celui-ci correspond
	 * aux informations entrees dans la barre de recherche
	 * @param e
	 */
	public void confirm(ActionEvent e) {
		/* On retire tous les equipements des conteneurs */
		this.equipementsControleur.resetContent();
		this.barreDeRecherche.toEmpty();
		
		/* On remplit les conteneurs d'equipements correspondants a la saisie de la barre de recherche */
		this.barreDeRecherche.search(this.searchTxt.getText());
		Equipement[] search = this.barreDeRecherche.getRecherche();
		for(int i=0 ; i<search.length ; i++) {
			this.equipementsControleur.addContent(search[i]);
		}
		
		/* Modifie le nombre de resultats des titres des conteneurs conformement aux resultats trouves */
		this.equipementsControleur.editTitle();
	}
	
	/**
	 * Remplit la barre de recherche de l'ensemble des equipements
	 * @param e
	 */
	public void reset(ActionEvent e) {
		/* On retire tous les equipements des conteneurs */
		this.equipementsControleur.resetContent();
		this.barreDeRecherche.toEmpty();
		
		/* On vide la barre de recherche */
		this.searchTxt.setText("");
		
		/* On remplit les conteneurs de tous les equipements */
		this.barreDeRecherche.reset();
		Equipement[] search = this.barreDeRecherche.getRecherche();
		for(int i=0 ; i<search.length ; i++) {
			this.equipementsControleur.addContent(search[i]);
		}
		
		/* Modifie le nombre de resultats des titres des conteneurs conformement aux resultats trouves */
		this.equipementsControleur.editTitle();
	}
}
