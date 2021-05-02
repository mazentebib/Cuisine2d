package vue;

import java.util.Optional;

import controleur.PrincipalControleur;
import controleur.RechercheControleur;
import controleur.EquipementControleur;
import controleur.MessageControleur;
import controleur.PlanCuisineControleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.BarreDeRecherche;
import modele.EtatEquipement;
import modele.Panier;

/**
 * Lancement de l'interface
 */
public class Vue extends Application {
	
	/* Liste l'ensemble des equipements ajoutes par l'utilisateur dans le plan de cuisine */
	private Panier panier;
	
	/* Affiche une boite de dialogue et retourne son resultat */
	private BoiteDialogue boiteDialogue;
	
	/* Liste l'ensemble des equipement de l'interface */
	private EtatEquipement etatEquipement;
	
	/* Message preventif */
	private MessageControleur messageControleur;
	
	/* Liste l'ensemble des equipements resultants de la recherche de l'utilisateur */
	private BarreDeRecherche barreDeRecherche;
	
	/* Provoque la modification des equipements affiches au sein de l'interface */
	private EquipementControleur equipementsControleur;
	
	/* Provoque l'ajout et le deplacement d'un equipement au sein de l'interface */
	private PlanCuisineControleur planCuisineControleur;
	
	/* Provoque la modification des equipements affiches au sein de l'interface */
	private RechercheControleur rechercheControleur;
	
	/* Récupère les composants de l'interface et les initialise */
	private PrincipalControleur principalControleur;
	
	/**
	 * Constructeur de la classe
	 */
	public Vue() {
		this.panier = new Panier();
		this.boiteDialogue = new BoiteDialogue();
		this.etatEquipement = new EtatEquipement();
		this.messageControleur = new MessageControleur();
		this.barreDeRecherche = new BarreDeRecherche(this.etatEquipement);
		this.planCuisineControleur = new PlanCuisineControleur(this.panier, this.messageControleur);
		this.equipementsControleur = new EquipementControleur(this.planCuisineControleur);
		this.rechercheControleur = new RechercheControleur(this.barreDeRecherche, this.equipementsControleur);
		this.principalControleur = new PrincipalControleur(this.barreDeRecherche, this.planCuisineControleur, this.messageControleur, this.rechercheControleur, this.equipementsControleur);
	}
	
	/**
	 * Construction et affichage d'une fenetre
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Configuration d'une Cuisine 2D");
		
		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("fxml/interfacePrincipale.fxml"));
		//fxmlLoader.setController(this.principalControleur);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		this.principalControleur.init();
		
		Optional<Integer[]> result = this.boiteDialogue.initDialogBox();
		result.ifPresent(res -> this.principalControleur.setSize(res[0], res[1]));
	}
	
	/**
	 * Méthode principale permettant le lancement de l'application
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}