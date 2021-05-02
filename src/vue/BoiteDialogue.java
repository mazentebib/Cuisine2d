package vue;

import java.util.Optional;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Initialise une boite de dialogue et retourne la valeur de celle-ci
 */
public class BoiteDialogue {
	
	/**
	 * Initialise une boite de dialogue qui permettra la saisie de la largeur
	 * et la hauteur de la cuisine par l'utilisateur
	 * @return le resultat de la boite de dialogue
	 */
	public Optional<Integer[]> initDialogBox() {
		
		/* Creation d'une boite de dialogue retournant un tableau de integer */
		Dialog<Integer[]> dialog = new Dialog<Integer[]>();
		dialog.setTitle("Configuration d'une Cuisine 2D");
		dialog.setHeaderText("Entrez les dimensions de votre cuisine :");
		
		/* Insertion d'un bouton "Confirmer" */
		ButtonType doneButton = new ButtonType("Confirmer", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(doneButton);
		
		/* Mise en page sous forme de grille */
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20,150,10,10));
		
		/* Composant permettant la saisie de la largeur de la cuisine */
		TextField widthTxt = new TextField("200");
		widthTxt.textProperty().addListener(new ChangeListener<String>() {
			@Override
			/* Permet d'éviter la saisie de caracteres non-numeriques */
			public void changed(ObservableValue observable, String oldValue, String newValue) {
				if(!newValue.matches("\\d*")) {
					widthTxt.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		grid.add(new Label("Largeur"), 0, 0);
		grid.add(widthTxt, 1, 0);
		
		/* Composant permettant la saisie de la hauteur de la cuisine */
		TextField heightTxt = new TextField("200");
		widthTxt.textProperty().addListener(new ChangeListener<String>() {
			@Override
			/* Permet d'éviter la saisie de caracteres non-numeriques */
			public void changed(ObservableValue observable, String oldValue, String newValue) {
				if(!newValue.matches("\\d*")) {
					widthTxt.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		grid.add(new Label("Longueur"), 0, 1);
		grid.add(heightTxt, 1, 1);
		
		/* Message permettant de prevenir l'utilisateur */
		ImageView image = new ImageView(new Image("file:ressources/warning.png"));
		image.setFitHeight(25);
		image.setFitWidth(25);
		grid.add(new Label("", image), 0, 2);
		grid.add(new Label("(Vous ne pourrez plus modifier ces saisies)"), 1, 2);
		
		/* Ajout de la mise en page au sein de la boite de dialogue */
		dialog.getDialogPane().setContent(grid);
		
		/* Valeur retournee par la boite de dialogue */
		dialog.setResultConverter(dialogButton -> this.getResult(dialogButton, doneButton, widthTxt, heightTxt));
		
		return dialog.showAndWait();
	}
	
	/**
	 * Retourne la largeur et la hauteur saisie par l'utilisateur au sein d'un tableau
	 * @param dialogButton bouton appuye
	 * @param doneButton bouton de confirmation
	 * @param widthTxt composant contenant la largeur de la cuisine
	 * @param heightTxt composant contenant la hauteur de la cuisine
	 * @return la largeur et la hauteur au sein d'un tableau
	 */
	private Integer[] getResult(ButtonType dialogButton, ButtonType doneButton, TextField widthTxt, TextField heightTxt) {
		if(dialogButton == doneButton) {
			try {
				Integer[] result = new Integer[2]; 
				
				result[0] = Integer.parseInt(widthTxt.getText());
				result[1] = Integer.parseInt(heightTxt.getText());
				
				return result;
			}
			catch(NumberFormatException e) {
				return new Integer[]{200,200};
			}
		}
		else {
			/* On quitte l'application */
			Platform.exit();
			return null;
		}
	}
}
