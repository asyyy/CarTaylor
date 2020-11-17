package impl;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import fr.istic.nplouzeau.cartaylor.api.*;


public class ConfigurationImpl implements Configuration {

	private Configurator confOr;
	private Set<PartType> selected;

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
		for(PartType s : selected) {
			if(verifCondition(s,selected,true) || verifCondition(s,selected,false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verifie si une PartType possede des pre-requis/incompatibilites dans une liste de PartType
	 * @param part PartType cible
	 * @param selected liste de PartType a compare avec part
	 * @param b true-> getRequirement | false -> getIncompatibilities
	 * @return false s'il existe un probleme de ccompatibilite, true sinon
	 */
	private boolean verifCondition(PartType part,Set<PartType> selected, boolean reqOrIncomp) {
		Set<PartType> lPart;

		for(PartType other : selected) {
			if(!part.equals(other)) {
				// Si b == true, on regarde les Requirement d'une PartType
				if(reqOrIncomp) {
					lPart = confOr.getCompatibilityChecker().getRequirements(part);
					if(lPart != null && !lPart.contains(other)) {
						return true;
					}
					// Sinon b == false,  et on regarde les Incompatibilites d'une PartType
				}else {
					lPart = confOr.getCompatibilityChecker().getIncompatibilities(part);
					if(lPart != null && lPart.contains(other)) {
						return true;
					}
				}				

			}
		}
		return false;
	}
	/**
	 * Regarde si une categorie est presente parmis une liste de PartType
	 * @param c Category cible
	 * @param selected liste de PartType a verifie
	 * @return true si c est present parmis les categories de selected
	 */
	private boolean checkCategories(Category c, Set<PartType> selected) {
		for(PartType p : selected) {
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
	public Set<PartType> getSelectedParts() {
		return Collections.unmodifiableSet(selected);
	}

	@Override
	public void selectPart(PartType chosenPart) {
		Objects.requireNonNull(chosenPart);
		selected.add(chosenPart);
	}

	@Override
	public PartType getSelectionForCategory(Category category) {
		Objects.requireNonNull(category);
		for(PartType s : selected) {
			if(s.getCategory().equals(category)) {
				return s;
			}
		}
		System.out.println("getSelectionForCategory -> Aucune PartType pour cette categorie dans selected");
		return null; 
	}

	@Override
	public void unselectPartType(Category categoryToClear) {
		Objects.requireNonNull(categoryToClear);
		boolean error = true;
		for(PartType s : selected) {
			if(s.getCategory().equals(categoryToClear)) {
				selected.remove(s);
				error = false;
			}
		}
		if(error) 
			System.out.println("unselectPartType -> Aucune PartType pour cette categorie dans selected");
	}

	@Override
	public void clear() {
		selected.clear();
	}

}
