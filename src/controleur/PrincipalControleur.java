package controleur;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import modele.BarreDeRecherche;
import modele.Equipement;

/**
 * Controleur principale permettant de récupérer les composants de l'inteface et de les initialiser
 */
public class PrincipalControleur {
	
	/* Aire de dessin */
	@FXML private Canvas kitchenCanvas;
	
	/* Titre des conteneurs */
	@FXML private TitledPane ovenTitle, fridgeTitle, doorTitle, stoveTitle, chairTitle;
	
	/* Conteneurs */
	@FXML private VBox ovenContent, fridgeContent, doorContent, stoveContent, chairContent;
	
	/* Champs contenant la largeur et la longueur saisies par l'utilisateur et le message de prevention */
	@FXML private Label widthKitchen, heightKitchen, notify;
	
	/* Champs de saisie de la recherche */
	@FXML private TextField searchTxt;
	
	/* Bouton de confirmation et de reinitialisation de la recherche */
	@FXML private Button confirmSearch, resetSearch;

	/* field de prix total */
	@FXML private Label price;

	/* Permet d'acceder a la liste des equipements de la recherche */
	private BarreDeRecherche barreDeRecherche;
	
	/* Permet d'initialiser l'aire de dessin */
	private PlanCuisineControleur planCuisineControleur;
	
	/* Permet d'initialiser le champs a modifier pour afficher un message de prevention */
	private MessageControleur messageControleur;
	
	/* Permet d'initialiser le champs de saisie de la recherche */
	private RechercheControleur rechercheControleur;
	
	/* Permet d'initialiser les conteneurs */
	private EquipementControleur equipementsControleur;
	
	/* Permet d'acceder aux fonctions graphiques de l'aire de dessin */
	private GraphicsContext gc;
	
	/**
	 * Constructeur de la classe
	 * @param barreDeRecherche
	 * @param planCuisineControleur
	 * @param messageControleur
	 * @param rechercheControleur
	 * @param equipementsControleur
	 */
	public PrincipalControleur(BarreDeRecherche barreDeRecherche, PlanCuisineControleur planCuisineControleur, MessageControleur messageControleur, RechercheControleur rechercheControleur, EquipementControleur equipementsControleur) {
		this.barreDeRecherche = barreDeRecherche;
		this.planCuisineControleur = planCuisineControleur;
		this.messageControleur = messageControleur;
		this.rechercheControleur = rechercheControleur;
		this.equipementsControleur = equipementsControleur;
	}
	
	/**
	 * Initialisation de composants
	 */
	public void init() {
		this.messageControleur.setNotify(this.notify);
		this.planCuisineControleur.setCanvas(this.kitchenCanvas);
		this.rechercheControleur.setSearch(this.searchTxt);
		this.equipementsControleur.setPane(new TitledPane[]{ this.ovenTitle, this.fridgeTitle, this.doorTitle, this.stoveTitle, this.chairTitle });
		this.equipementsControleur.setContent(new VBox[]{ this.ovenContent, this.fridgeContent, this.doorContent, this.stoveContent, this.chairContent });

		Equipement[] search = this.barreDeRecherche.getRecherche();
		for(int i=0 ; i<search.length ; i++) {
			this.equipementsControleur.addContent(search[i]);
		}
		
		this.equipementsControleur.editTitle();
		
		this.confirmSearch.setOnAction(e -> this.rechercheControleur.confirm(e));
		this.resetSearch.setOnAction(e -> this.rechercheControleur.reset(e));

	}
	
	/**
	 * Initialise la largeur et la hauteur de l'aire de dessin et celles saisies par l'utilisateur
	 * @param width largeur saisie par l'utilisateur
	 * @param height longueur saisie par l'utilisateur
	 */
	public void setSize(int width, int height) {
		Equipement.widthCanvas = this.kitchenCanvas.getWidth();
		Equipement.heightCanvas = this.kitchenCanvas.getHeight();
		Equipement.widthInput = width;
		Equipement.heightInput = height;
		
		/* Affichage de la largeur et de la hauteur saisie par l'utilisateur au sein de champs */
		this.widthKitchen.setText(Integer.toString(width));
		this.heightKitchen.setText(Integer.toString(height));
	}
}