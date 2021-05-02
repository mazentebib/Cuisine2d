package controleur;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import modele.Equipement;

/**
 * Provoque la modification des equipements affiches au sein de l'interface
 */
public class EquipementControleur {

	/* Permet de faire appel a la methode permettant d'ajouter un equipement au sein du plan de cuisine */
	private PlanCuisineControleur planCuisineControleur;
	
	/* Titre des conteneurs */
	private TitledPane ovenTitle, fridgeTitle, doorTitle, stoveTitle, chairTitle;
	
	/* Conteneurs */
	private VBox ovenContent, fridgeContent, doorContent, stoveContent, chairContent;
	
	/**
	 * Constructeur de la classe
	 * @param planCuisineControleur
	 */
	public EquipementControleur(PlanCuisineControleur planCuisineControleur) {
		this.planCuisineControleur = planCuisineControleur;
	}
	
	/**
	 * Initialise les titres des conteneurs
	 * @param ovenTitle
	 * @param fridgeTitle
	 * @param doorTitle
	 * @param stoveTitle
	 * @param chairTitle
	 */
	public void setPane(TitledPane[] titledPane/*TitledPane ovenTitle, TitledPane fridgeTitle, TitledPane doorTitle, TitledPane stoveTitle, TitledPane chairTitle*/) {
		this.ovenTitle = titledPane[0];
		this.fridgeTitle = titledPane[1];
		this.doorTitle = titledPane[2];
		this.stoveTitle = titledPane[3];
		this.chairTitle = titledPane[4];
	}
	
	/**
	 * Initialise les conteneurs
	 * @param ovenContent
	 * @param fridgeContent
	 * @param doorContent
	 * @param stoveContent
	 * @param chairContent
	 */
	public void setContent(VBox[] vBox) {
		this.ovenContent = vBox[0];
		this.fridgeContent = vBox[1];
		this.doorContent = vBox[2];
		this.stoveContent = vBox[3];
		this.chairContent = vBox[4];
	}
	
	/**
	 * Ajoute un equipement au conteneur
	 * @param equipement equipement a ajouter
	 */
	public void addContent(Equipement equipement) {
		/* Image de l'equipement */
		ImageView image = new ImageView(equipement.getImage());
		image.setFitHeight(75);
		image.setFitWidth(75);
		
		String text = equipement.getTitre()+"\nPrix: "+equipement.getPrix()+"€\nReference: "+equipement.getReference()+"\nDimension: "+equipement.getWidth()+"x"+equipement.getHeight();
		
		/* Informations de l'equipement */
		Label label = new Label(text, image);
		label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		label.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10.0), null)));
		label.setTooltip(new Tooltip("Cliquez sur l'équipement pour l'ajouter !"));
		label.setPadding(new Insets(10,10,10,10));
		label.setMaxWidth(Double.MAX_VALUE);
		label.setWrapText(true);
		
		/* Permet d'ajouter l'equipement au sein du plan de cuisine lorsque la souris clique sur celui-ci */
		label.setOnMouseClicked(e -> {this.planCuisineControleur.mouseClick(equipement);});
		
		/* Ajoute l'equipement au sein du container correspondant */
		if(equipement.getCategorie().equals("Four")) {
			this.ovenContent.getChildren().add(label);
		}
		else if(equipement.getCategorie().equals("Réfrigérateur")) {
			this.fridgeContent.getChildren().add(label);
		}
		else if(equipement.getCategorie().equals("Porte")) {
			this.doorContent.getChildren().add(label);
		}
		else if(equipement.getCategorie().equals("Meuble")) {
			this.stoveContent.getChildren().add(label);
		}
		else if(equipement.getCategorie().equals("Chaise")) {
			this.chairContent.getChildren().add(label);
		}
	}
	
	/**
	 * Retire les equipements des conteneurs
	 */
	public void resetContent() {
		while(!this.ovenContent.getChildren().isEmpty()) {
			this.ovenContent.getChildren().remove(0);
		}
		while(!this.fridgeContent.getChildren().isEmpty()) {
			this.fridgeContent.getChildren().remove(0);
		}
		while(!this.doorContent.getChildren().isEmpty()) {
			this.doorContent.getChildren().remove(0);
		}
		while(!this.stoveContent.getChildren().isEmpty()) {
			this.stoveContent.getChildren().remove(0);
		}
		while(!this.chairContent.getChildren().isEmpty()) {
			this.chairContent.getChildren().remove(0);
		}
	}
	
	/**
	 * Modifie le titre des conteneurs
	 */
	public void editTitle() {
		this.ovenTitle.setText("Four - "+this.ovenContent.getChildren().size()+" résultats");
		this.fridgeTitle.setText("Réfrigérateur - "+this.fridgeContent.getChildren().size()+" résultats");
		this.doorTitle.setText("Porte - "+this.doorContent.getChildren().size()+" résultats");
		this.stoveTitle.setText("Meuble - "+this.stoveContent.getChildren().size()+" résultats");
		this.chairTitle.setText("Chaise - "+this.chairContent.getChildren().size()+" résultats");
	}
}
