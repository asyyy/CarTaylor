package impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import fr.istic.nplouzeau.cartaylor.api.*;

public class Interior extends PartImpl {
	
	private Map<String,String> prix;
	private String interiorChoosed = "Not defined";
	private Set<String> interiors;
	/**
	 * Constructeur de Engine
	 * @param p PartType associe a cet PartImpl
	 */
	public Interior(PartType p) {
		Objects.requireNonNull(p);
		this.setPartType(p);
		initInteriors();
		initPrix();
		this.addProperty("prix", () -> getPrice(), null, null);
		this.addProperty("interior", () -> getInterior(), (inte)-> setInterior(inte), Collections.unmodifiableSet(interiors));
	}
	/**
	 * Initialise les prix
	 */
	private void initPrix() {
		prix = new HashMap<>();
		prix.put("in", "96");
		prix.put("ih", "58");
		prix.put("is", "80");
	}
	/**
	 * Initialise les interieurs
	 */
	private void initInteriors() {
		interiors = new HashSet<>();
		interiors.add("standard");
		interiors.add("high");
		interiors.add("sport");
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
	 * Getter de interior
	 * @return String interior
	 */
	public String getInterior() {
		return interiorChoosed;
	}
	/**
	 * Setter de color
	 * @param color
	 */
	public void setInterior(String interior) {
		Objects.requireNonNull(interior);
		this.interiorChoosed = interior;
	}
	

}
