package impl;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import fr.istic.nplouzeau.cartaylor.api.*;


public class ConfigurationImpl implements Configuration {
	private static final Logger LOGGER = Logger.getLogger(ConfigurationImpl.class.getName());
	private Configurator confOr;
	private Set<Part> selected;
	/**
	 * Constructeur de Configuration
	 * 
	 */
	public ConfigurationImpl() {	
		this.selected = new HashSet<>();

	}
	/**
	 * Permet de relie cette configuration a un configurator
	 * @param c Configurator a relie
	 */
	public void linkToConfigurator(Configurator c) {
		this.confOr = c;
	}

	@Override
	public boolean isValid() {
		//Aucune incompatibilites/Tout les pre requis
		for(Part s : selected) {
			if(verifCondition(s,selected,true) || verifCondition(s,selected,false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verifie si une Part possede des pre-requis/incompatibilites dans une liste de PartType
	 * @param part PartType cible
	 * @param selected liste de PartType a compare avec part
	 * @param b true-> getRequirement | false -> getIncompatibilities
	 * @return false s'il existe un probleme de compatibilite, true sinon
	 */
	private boolean verifCondition(Part part,Set<Part> selected, boolean reqOrIncomp) {
		for(Part other : selected) {
			if(!part.equals(other)) {
				// Si b == true, on regarde les Requirement d'une PartType
				if(reqOrIncomp) {
					if(verifConditionReq(part,other)) {
						return true;
					}
				// Sinon b == false,  et on regarde les Incompatibilites d'une PartType
				}else {
					if(verifConditionInc(part,other)) {
						return true;
					}
				}				
			}
		}
		return false;
	}
	
	/**
	 * Split de verifCondition pour resoudre code smell major de sonarlint
	 * present dans la V1
	 * @param part Part
	 * @param other Part
	 * @return true si other n'est pas dans requirement de part
	 */
	private boolean verifConditionReq(Part part,Part other) {
		Set<PartType> lPart = confOr.getCompatibilityChecker().getRequirements(part.getType());
		
		return !lPart.isEmpty() && !lPart.contains(other.getType()) ;
			
	}
	
	/**
	 * Split de verifCondition pour resoudre code smell major de sonarlint
	 * present dans la V1
	 * @param part Part
	 * @param other Part
	 * @return true si other est dans incompatibilites de part
	 */
	private boolean verifConditionInc(Part part,Part other) {
		Set<PartType> lPart = confOr.getCompatibilityChecker().getIncompatibilities(part.getType());
		return !lPart.isEmpty() && lPart.contains(other.getType());		
	}
	
	/**
	 * Regarde si une categorie est presente parmis une liste de PartType
	 * @param c Category cible
	 * @param selected liste de PartType a verifie
	 * @return true si c est present parmis les categories de selected
	 */
	private boolean checkCategories(Category c, Set<Part> selected) {
		for(Part p : selected) {
			if(p.getCategory().getName().equals(c.getName())) {
				return true;
			}
		}
		return false;
	}


	@Override
	public boolean isComplete() {
		Set<Category> lAllCategories = confOr.getCategories();
		for(Category c : lAllCategories) {
			if(!checkCategories(c,selected)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Set<Part> getSelectedParts() {
		return Collections.unmodifiableSet(selected);
	}

	@Override
	public void selectPart(PartType chosenPart) {
		Objects.requireNonNull(chosenPart);
		selected.add(((PartTypeImpl) chosenPart).newInstance());
	}

	@Override
	public Optional<Part> getSelectionForCategory(Category category) {
		Objects.requireNonNull(category);
		for(Part s : selected) {
			if(s.getCategory().equals(category)) {
				return Optional.of(s);
			}
		}
		LOGGER.warning(() ->"getSelectionForCategory -> Aucune PartType pour cette categorie dans selected");
		return Optional.empty(); 
	}

	@Override
	public void unselectPartType(Category categoryToClear) {
		Objects.requireNonNull(categoryToClear);
		boolean error = true;
		Set<Part> toRemove = new HashSet<>();
		for(Part s : selected) {
			if(s.getCategory().equals(categoryToClear)) {
				toRemove.add(s);
				error = false;
			}
		}	
		if(error) {
			LOGGER.warning(() ->"unselectPartType -> Aucune PartType pour cette categorie dans selected");
		}else {
			selected.removeAll(toRemove);
		}
	}

	@Override
	public void clear() {
		selected.clear();
	}
	@Override
	public PrintStream printDescription(PrintStream ps) {
		String res = "";
		if(isValid()) {
			res += "Selection : valide \r\n";
		}else {
			res += "Selection : invalide \r\n";
		}
		
		if(isComplete()) {
			res += "Selection : complète \r\n";
		}else {
			res += "Selection : incomplète \r\n";
		}
		res += "Panier : \r\n";
		
		//StringBuiler proposé par SonarLint
		StringBuilder un = new StringBuilder();
		StringBuilder deux = new StringBuilder();
		StringBuilder trois = new StringBuilder();
		StringBuilder quatre = new StringBuilder();
		/*
		 * Obligation de créer un ordre de passage sinon
		 * impossible d'avoir des tests immobiles.
		 */
		for(Part p : selected) {
			if(p.getCategory().getName().equals("Engine")) {
				un.append(p.printDescription());
			}
			if(p.getCategory().getName().equals("Transmission")) {
				deux.append(p.printDescription());
			}
			if(p.getCategory().getName().equals("Interior")) {
				trois.append(p.printDescription());
			}
			if(p.getCategory().getName().equals("Exterior")) {
				quatre.append(p.printDescription());
			}
		}
		res += un.toString() + deux.toString() + trois.toString() + quatre.toString();	
		return ps.append(res);
	}
	@Override
	public int printPrice() {
		int res = 0;
		if(isValid()) {
			for(Part p : selected) {
				if(p.getProperty("prix").isPresent()) {
					res += Integer.valueOf(p.getProperty("prix").get());
				}
			}
		}else {
			res = -404;
		}
		return res;
	}
	
	
	

}
