package impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import fr.istic.nplouzeau.cartaylor.api.*;

public class Transmission extends PartImpl {
	
	private Map<String,String> prix;
	private String boite = "Not defined";
	private String nombreDeVitesse= "Not defined";
	private Set<String> boitesList;
	private Set<String> vitessesList;
	/**
	 * Constructeur de Engine
	 * @param p PartType associe a cet PartImpl
	 */
	public Transmission(PartType p) {
		Objects.requireNonNull(p);
		this.setPartType(p);
		initPrix();
		initboitesList();
		initVitesseList();
		this.addProperty("prix", () -> getPrice(), null, null);
		this.addProperty("boite", () -> getBoite(), (boite)-> setBoite(boite), Collections.unmodifiableSet(boitesList));
		this.addProperty("vitesse", () -> getVitesse(), (vitesse)-> setVitesse(vitesse), Collections.unmodifiableSet(vitessesList));
	}
	/**
	 * Initialise les prix
	 */
	private void initPrix() {
		prix = new HashMap<>();
		prix.put("tm5", "50");
		prix.put("tm6", "60");
		prix.put("ta5", "50");
		prix.put("ts6", "60");
		prix.put("tsf7", "70");
		prix.put("tc120", "120");
	}
	/**
	 * Initialise les boites
	 */
	private void initboitesList() {
		boitesList = new HashSet<>();
		boitesList.add("automatique");
		boitesList.add("manuelle");
	}
	/**
	 * Initialise les vitesses
	 */
	private void initVitesseList() {
		vitessesList = new HashSet<>();
		vitessesList.add("5");
		vitessesList.add("6");
		vitessesList.add("7");
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
	 * Getter de boite
	 * @return String boite
	 */
	public String getBoite() {
		return boite;
	}
	/**
	 * Getter de vitesse
	 * @return String vitesse
	 */
	public String getVitesse() {
		return nombreDeVitesse;
	}
	/**
	 * Setter de boite
	 * @param carburant String non null
	 */
	public void setBoite(String carburant) {
		Objects.requireNonNull(carburant);
		this.boite = carburant;
	}
	/**
	 * Setter de boite
	 * @param puissance String non null
	 */
	public void setVitesse(String puissance) {
		Objects.requireNonNull(puissance);
		this.nombreDeVitesse = puissance;
	}

}
