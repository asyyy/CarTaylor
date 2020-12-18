package impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import fr.istic.nplouzeau.cartaylor.api.*;

public class Engine extends PartImpl {
	
	private Map<String,String> prix;
	private String carbu = "Not defined";
	private String puissance = "Not defined";
	private Set<String> carbuList;
	private Set<String> puissanceList;
	/**
	 * Constructeur de Engine
	 * @param p PartType associe a cet PartImpl
	 */
	public Engine(PartType p) {
		Objects.requireNonNull(p);
		this.setPartType(p);
		initPrix();
		initCarbuList();
		initPuissanceList();
		this.addProperty("prix", () -> getPrice(), null, null);
		this.addProperty("carburant", () -> getCarbu(), (carbu)-> setCarbu(carbu), Collections.unmodifiableSet(carbuList));
		this.addProperty("puissance", () -> getPuissance(), (power)-> setPuissance(power), Collections.unmodifiableSet(puissanceList));
	}
	/**
	 * Initialise les prix
	 */
	private void initPrix() {
		prix = new HashMap<>();
		prix.put("eg100", "100");
		prix.put("eg133", "133");
		prix.put("eg210", "210");
		prix.put("ed110", "110");
	}
	/**
	 * Initialise les carburants
	 */
	private void initCarbuList() {
		carbuList = new HashSet<>();
		carbuList.add("essence");
		carbuList.add("diesel");
		carbuList.add("gasoline/electric hybrid");
	}
	/**
	 * Initialise les puissances
	 */
	private void initPuissanceList() {
		puissanceList = new HashSet<>();
		puissanceList.add("100kw");
		puissanceList.add("133kw");
		puissanceList.add("210kw");
		puissanceList.add("110kw");
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
	 * Getter de carbu
	 * @return String carbu
	 */
	public String getCarbu() {
		return carbu;
	}
	/**
	 * Getter de puissance
	 * @return	String puissance
	 */
	public String getPuissance() {
		return puissance;
	}
	/**
	 * Setter de carbu
	 * @param carbu String non null
	 */
	public void setCarbu(String carbu) {
		Objects.requireNonNull(carbu);
		this.carbu = carbu;
	}
	/**
	 * Setter de puissance
	 * @param puissance String non null
	 */
	public void setPuissance(String puissance) {
		Objects.requireNonNull(puissance);
		this.puissance = puissance;
	}

}
