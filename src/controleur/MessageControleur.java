package controleur;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Provoque la modification du message affiche au sein de l'interface
 */
public class MessageControleur {

	/* Valeur determinant qu'il n'y a aucun probleme detecte */
	public static final int ADEQUAT = 0;
	
	/* Valeur determinant que l'espace est insuffisant */
	public static final int NOT_ENOUGH_SPACE = 1;
	
	/* Valeur determinant qu'il y a une superposition d'equipements */
	public static final int STACKING = 2;
	
	/* Texte a modifier pour l'affichage des messages */
	private Label notify;
	
	/* Image de prevention */
	private ImageView imageWarning;
	
	/* Image de conformite */
	private ImageView imageValidate;
	
	/**
	 * Constructeur de la classe
	 */
	public MessageControleur() {
		this.imageWarning = new ImageView(new Image("file:ressources/icons/warning.png"));
		this.imageWarning.setFitWidth(25);
		this.imageWarning.setFitHeight(25);
		
		this.imageValidate = new ImageView(new Image("file:ressources/icons/validate.png"));
		this.imageValidate.setFitWidth(25);
		this.imageValidate.setFitHeight(25);
	}
	
	/**
	 * Initialise le champ a modifier
	 * @param notify
	 */
	public void setNotify(Label notify) {
		this.notify = notify;
	}
	
	/**
	 * Modifie le texte a afficher
	 * @param idMessage
	 */
	public void edit(int idMessage) {
		String text = "";
		
		if(idMessage == NOT_ENOUGH_SPACE) {
			text = "Votre cuisine ne dispose pas d'un espace assez grand pour ajouter cet équipement.";
			this.notify.setGraphic(this.imageWarning);
		}
		else if(idMessage == STACKING) {
			text = "Un de vos équipements est positionné sur un autre. Veuillez déplacer celui en rouge à un emplacement adéquat afin de poursuivre.";
			this.notify.setGraphic(this.imageWarning);
		}
		else if(idMessage == ADEQUAT) {
			text = "Aucun problème détecté";
			this.notify.setGraphic(this.imageValidate);
		}
		
		this.notify.setText(text);
	}
}