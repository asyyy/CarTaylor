package impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import fr.istic.nplouzeau.cartaylor.api.*;

public class Exterior extends PartImpl {
	
	private Map<String,String> prix;
	private String color = "Not defined";
	private Set<String> colorsList;
	/**
	 * Constructeur de Engine
	 * @param p PartType associe a cet PartImpl
	 */
	public Exterior(PartType p) {
		Objects.requireNonNull(p);
		this.setPartType(p);
		initcolorsList();
		initPrix();
		this.addProperty("prix", () -> getPrice(), null, null);
		this.addProperty("color", () -> getColor(), (color)-> setColor(color), Collections.unmodifiableSet(colorsList));
	}
	/**
	 * Initialise les prix
	 */
	private void initPrix() {
		prix = new HashMap<>();
		prix.put("xc", "50");
		prix.put("xm", "60");
		prix.put("xs", "50");
	}
	/**
	 * Initialise les couleurs
	 */
	private void initcolorsList() {
		colorsList = new HashSet<>();
		colorsList.add("standard");
		colorsList.add("high");
		colorsList.add("sport");
	}
	/**
	 * Getter de price
	 * @return String price
	 */
	public String getPrice() {	
		if(prix.containsKey(this.type.getName().toLowerCase())) {
			return prix.get(this.type.getName().toLowerCase());
		}else {
			return "Piece non trouvé";
		}
	}
	/**
	 * Getter de color
	 * @return String color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * Setter de color 
	 * @param color String non null
	 */
	public void setColor(String color) {
		Objects.requireNonNull(color);
		this.color = color;
	}

}
