package controleur;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import modele.Equipement;
import modele.Panier;

/**
 * Provoque l'ajout et le deplacement d'un equipement au sein de l'interface
 */
public class PlanCuisineControleur {
	
	/* Permet de recuperer la liste des equipements ajoutes au sein du plan de cuisine par l'utilisateur */
	private Panier panier;
	
	/* Permet d'acceder a la methode permettant de modifier le message affiche */
	private MessageControleur messageControleur;
	
	/* Aire de dessin */
	private Canvas kitchenCanvas;
	
	/* Permet d'acceder aux fonctions graphiques de l'aire de dessin */
	private GraphicsContext gc;
	
	/* Image en fond du plan de cuisine */
	private ImagePattern image;
	
	/* Indices correspondants respectivement a l'equipement selectionne et superpose */
	private int index, indexSuperposed;
	
	/* Position de la sourix en abscisse et ordonnee */
	private double sourisX, sourisY;

	@FXML private Label price ;

	/**
	 * Constructeur de la classe
	 * @param panier
	 * @param messageControleur
	 */
	public PlanCuisineControleur(Panier panier, MessageControleur messageControleur) {
		this.panier = panier;
		this.messageControleur = messageControleur;
		this.image = new ImagePattern(new Image("file:ressources/icons/wood.png"), 0, 0, 50, 50, false);
		this.index = -1;
		this.indexSuperposed = -1;
		price.setText("00.00");
	}
	
	/**
	 * Initialise l'aire de dessin
	 * @param kitchenCanvas aire de dessin
	 */
	public void setCanvas(Canvas kitchenCanvas) {
		this.kitchenCanvas = kitchenCanvas;
		this.gc = this.kitchenCanvas.getGraphicsContext2D();
		
		this.draw();

		this.kitchenCanvas.setOnMouseDragged(e -> this.mouseDrag(e));
		this.kitchenCanvas.setOnMouseReleased(e -> this.mouseRelease(e));
		this.kitchenCanvas.setOnMousePressed(e -> this.mousePress(e));
	}
	
	/**
	 * Determine si un equipement est en superposition
	 * @return vrai si un equipement est en superposition, faux sinon
	 */
	private boolean checkSuperposition() {
		/* Parcourt l'ensemble des equipements du panier */
		for(int j=0 ; j<this.panier.getSize() ; j++) {
			/* Determine si un equipement n'est pas correctement place */
			if(j != this.index && this.panier.get(this.index).isSuperposed(this.panier.get(j))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Ajoute un equipement au sein du panier si aucun equipement est en superposition et que la taille
	 * de l'equipement a ajouter est plus petit que la taille du plan de cuisine
	 * @param equipement equipement a ajouter
	 */
	public void mouseClick(Equipement equipement) {
		// On verifie que l'equipement n'est pas plus grand que la taille entree par l'utilisateur
		if(equipement.getWidth() > Equipement.widthInput || equipement.getHeight() > Equipement.heightInput) {
			// Message de prevenant l'utilisateur qu'il n'y a pas assez de'espace pour ajouter l'equipement
			this.messageControleur.edit(MessageControleur.NOT_ENOUGH_SPACE);
		}
		// On vérifie qu'aucun equipement n'est superpose
		else if(this.indexSuperposed < 0) {
			Equipement newEquipement = new Equipement(equipement);
			
			/* On ajoute le nouvel equipement au panier */
			this.panier.add(newEquipement);
			this.index = this.panier.getSize()-1;

			System.out.println(this.panier.getTotal());
			price.setText(""+this.panier.getTotal());
			System.out.println(price.getLayoutX());

			/* Si l'equipement est en superposition */
			if(this.checkSuperposition()) {
				this.indexSuperposed = this.panier.getSize()-1;
				/* Affichage d'un message en cas de superposition */
				this.messageControleur.edit(MessageControleur.STACKING);
				/* L'equipement est dessine en rouge */
				newEquipement.draw(this.gc, Equipement.IS_SUPERPOSED);
			}
			else {
				this.indexSuperposed = -1;
				/* L'equipement est dessine en marron */
				newEquipement.draw(this.gc, Equipement.IS_NOT_SUPERPOSED);
			}
		}
		// S'il y a un equipement en superposition, on ne peut pas ajouter un nouvel equipement et un message est affiche
		else {
			this.messageControleur.edit(MessageControleur.STACKING);
		}
	}
	
	/**
	 * Determine quel equipement est selectionne lorsque la touche gauche de la souris est enfoncé
	 * Determine l'equipement selectionne en fonction de la position de la souris lorsque la touche gauche
	 * de la souris est enfoncée. Si un equipement n'etait pas correctement place, un message de prevention
	 * s'affiche et l'element selectionne ne peut etre deplace, a moins que ce soit l'element mal place.
	 * @param e
	 */
	public void mousePress(MouseEvent e) {
		/* Détermine l'equipement selectionne en fonction de la position de la souris */
		for(int i=this.panier.getSize()-1 ; i>=0 ; i--) {
			if(this.panier.get(i).isSelected(e.getX(), e.getY())) {
				this.index = i;
				this.sourisX = e.getX();
				this.sourisY = e.getY();
				break;
			}
		}
		
		/* Si l'equipement selectionne n'est pas l'equipement mal place, un message de prevention s'affiche */
		if(this.index >= 0 && this.indexSuperposed >= 0 && this.index != this.indexSuperposed) {
			this.index = -1;
			this.messageControleur.edit(MessageControleur.STACKING);
		}
	}
	
	/**
	 * Deplace l'equipement selectionne a l'emplacement de la souris lorsque la touche gauche reste enfoncer et
	 * modifier la couleur et le message affiches en fonction de l'emplacement de l'equipement
	 * @param e
	 */
	public void mouseDrag(MouseEvent e) {
		/* Deplace un equipement uniquement si un equipement est selectionne */
		if(index >= 0) {
			double dx = e.getX() - this.sourisX;
			double dy = e.getY() - this.sourisY;
			
			double xAvant = this.panier.get(this.index).getX();
			double yAvant = this.panier.get(this.index).getY();
			
			double width = this.panier.get(this.index).convertWidth();
			double height = this.panier.get(this.index).convertHeight();
			
			/* Modifie la position en abscisse de l'equipement si celui-ci ne depasse pas la largeur du plan de cuisine */
			if(xAvant + dx >= 0 && xAvant + dx < this.kitchenCanvas.getWidth() - width) {
				this.panier.get(this.index).setX(xAvant + dx);
				this.sourisX = e.getX();
			}
			/* Modifie la position en ordonnee de l'equipement si celui-ci ne depasse pas la longueur du plan de cuisine */
			if(yAvant + dy >= 0 && yAvant + dy < this.kitchenCanvas.getHeight() - height) {
				this.panier.get(this.index).setY(yAvant + dy);
				this.sourisY = e.getY();
			}
		
			this.draw();
			this.messageControleur.edit(MessageControleur.ADEQUAT);
			
			/* Affiche un message et change la couleur si un equipement est superpose sur un autre ou non */
			if(this.checkSuperposition()) {
				this.indexSuperposed = this.index;
				this.panier.get(this.index).draw(this.gc, Equipement.IS_SUPERPOSED);
				this.messageControleur.edit(MessageControleur.STACKING);
			}
		}
	}
	
	/**
	 * Modifie la couleur et le message affiche si, lorque la touche gauche de la souris est relachee,
	 * l'equipement selectionne n'est pas superpose sur un autre equipement
	 * @param e
	 */
	public void mouseRelease(MouseEvent e) {
		/* Un equipement doit avoir ete selectionne  */
		if(this.index >= 0) {
			/* Si aucun equipement est superpose sur un autre */
			if(!this.checkSuperposition()) {
				this.panier.get(this.index).draw(this.gc, Equipement.IS_NOT_SUPERPOSED);
				this.indexSuperposed = -1;
				this.messageControleur.edit(MessageControleur.ADEQUAT);
			}
			this.index = -1;
		}
	}
	
	/**
	 * Efface l'aire de dessin et redessine le fond du plan de cuisine et les equipements du panier
	 */
	public void draw() {
		this.gc.setFill(Color.WHITE);
		this.gc.fillRect(0, 0, this.kitchenCanvas.getWidth(), this.kitchenCanvas.getHeight());
		
		this.gc.setFill(this.image);
		this.gc.fillRect(0, 0, this.kitchenCanvas.getWidth(), this.kitchenCanvas.getHeight());
		
		for(int i=0 ; i<this.panier.getSize() ; i++) {
			this.panier.get(i).draw(this.gc, Equipement.IS_NOT_SUPERPOSED);
		}
	}
}
