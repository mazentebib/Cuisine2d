package modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Classe representative d'un equipement
 */
public class Equipement {

	/* Determine lorsqu'il y a un element superpose */
	public static final boolean IS_SUPERPOSED = true;
	
	/* Determine lorsqu'il n'y a pas d'element superpose  */
	public static final boolean IS_NOT_SUPERPOSED = false;
	
	/* Position en abscisse de l'equipement */
	private double x;
	
	/* Position en ordonnee de l'equipement */
	private double y;
	
	/* Largeur de l'equipement */
	private double width;
	
	/* Hauteur de l'equipement */
	private double height;
	
	/* Image representative de l'equipement */
	private Image image;
	
	/* Nom de l'equipement */
	private String titre;
	
	/* Prix de l'equipement */
	private double prix;
	
	/* Reference de l'equipement */
	private String reference;
	
	/* Categorie de l'equipement */
	private String categorie;
	
	/* Largeur du canvas sur lequel sera dessine l'equipement */
	public static double widthCanvas;
	
	/* Hauteur du canvas sur lequel sera dessine l'equipement */
	public static double heightCanvas;
	
	/* Largeur entree par l'utilisateur */
	public static double widthInput;
	
	/* Hauteur entree par l'utilisateur */
	public static double heightInput;
	
	/**
	 * Constructeur de la classe
	 * @param width
	 * @param height
	 * @param image
	 * @param titre
	 * @param prix
	 * @param reference
	 * @param categorie
	 */
	public Equipement(double width, double height, Image image, String titre, double prix, String reference, String categorie) {
		this.x = 0;
		this.y = 0;
		this.width = width;
		this.height = height;
		this.image = image;
		this.titre = titre;
		this.prix = prix;
		this.reference = reference;
		this.categorie = categorie;
	}
	
	/**
	 * Constructeur de la classe
	 * @param equipement
	 */
	public Equipement(Equipement equipement) {
		this.x = 0;
		this.y = 0;
		this.width = equipement.getWidth();
		this.height = equipement.getHeight();
		this.image = equipement.getImage();
		
		this.titre = equipement.getTitre();
		this.prix = equipement.getPrix();
		this.reference = equipement.getReference();
		this.categorie = equipement.getCategorie();
	}
	
	/**
	 * Retourne la position en abscisse de l'equipement
	 * @return la position en abscisse de l'equipement
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Retourne la position en ordonnee de l'equipement
	 * @return la position en ordonnee de l'equipement
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Retourne la largeur de l'equipement
	 * @return la largeur de l'equipement
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Retourne la longueur de l'equipement
	 * @return la longueur de l'equipement
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Retourne l'image de l'equipement
	 * @return l'image de l'equipement
	 */
	public Image getImage() {
		return this.image;
	}
	
	/**
	 * Retourne le nom de l'equipement
	 * @return le nom de l'equipement
	 */
	public String getTitre() {
		return this.titre;
	}
	
	/**
	 * Retourne le prix de l'equipement
	 * @return le prix de l'equipement
	 */
	public double getPrix() {
		return this.prix;
	}
	
	/**
	 * Retourne la reference de l'equipement
	 * @return la reference de l'equipement
	 */
	public String getReference() {
		return this.reference;
	}
	
	/**
	 * Retourne la categorie de l'equipement
	 * @return la categorie de l'equipement
	 */
	public String getCategorie() {
		return this.categorie;
	}
	
	/**
	 * Modifie la position en abscisse de l'equipement
	 * @param x la position en abscisse de l'equipement
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Modifie la position en ordonnee de l'equipement
	 * @param y la position en ordonnee de l'equipement
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Convertit la largeur de l'equipement conformement a la largeur entree par l'utilisateur
	 * @return la largeur a utiliser lors de l'ajout au sein de l'interface
	 */
	public double convertWidth() {
		return (this.widthCanvas/this.widthInput)*this.width;
	}
	
	/**
	 * Convertit la largeur de l'equipement conformement a la largeur entree par l'utilisateur
	 * @return la longueur a utiliser lors de l'ajout au sein de l'interface
	 */
	public double convertHeight() {
		return (this.heightCanvas/this.heightInput)*this.height;
	}
	
	/**
	 * Vérifie si l'equipement est selectionne
	 * @param x position en abscisse
	 * @param y position en ordonnee
	 * @return vrai s'il s'agit de l'equipement selectionne, faux sinon
	 */
	public boolean isSelected(double x, double y) {
		return (x >= this.x) && (x <= this.x + this.convertWidth()) && (y >= this.y) && (y <= this.y + this.convertHeight());
	}
	
	/**
	 * Vérifie si l'equipement est superpose sur un autre equipement
	 * @param equipement un equipement
	 * @return vrai si l'equipement se trouve sur un autre equipement, faux sinon
	 */
	public boolean isSuperposed(Equipement equipement) {
		return (
			((this.x + this.convertWidth() >= equipement.getX()) && (this.x <= equipement.getX() + equipement.convertWidth())) &&
			((this.y + this.convertHeight() >= equipement.getY()) && (this.y <= equipement.getY() + equipement.convertHeight()))
		);
	}
	
	/**
	 * Dessine l'equipement d'une certaine couleur s'il y a (ou non) superposition
	 * @param gc
	 * @param isSuperposed
	 */
	public void draw(GraphicsContext gc, boolean isSuperposed) {
		/* Dessine en rouge s'il y a superposition */
		if(isSuperposed == IS_SUPERPOSED) {
			gc.setFill(Color.RED);
		}
		/* Dessine en marron s'il n'y a pas de superposition */
		else {
			gc.setFill(Color.BROWN);
		}
		gc.fillRect(this.x, this.y, this.convertWidth(), this.convertHeight());
	}
}
