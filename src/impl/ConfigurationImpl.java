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
	 * @param confOr le configurateur associé 
	 */
	public ConfigurationImpl(Configurator confOr) {
		Objects.requireNonNull(confOr);
		this.confOr = confOr;
		this.selected = new HashSet<PartType>();
	}
	
	@Override
	public boolean isValid() {
		//Aucune incompatibilités/Tout les pré requis
		for(PartType s : selected) {
			if(!verifIncomp(s,selected) || !verifRequirement(s,selected)) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean verifRequirement(PartType part, Set<PartType> selected) {
		Set<PartType> partRequi;
		for(PartType other : selected) {
			if(!part.equals(other)) {
				partRequi = confOr.getCompatibilityChecker().getRequirements(part);
				if(partRequi.contains(other)) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * Vérifie si une part possède des incompatibilités dans une liste de PartType
	 * @param part ciblé
	 * @param selected liste des autres partType 
	 * @return false s'il existe une incompatibilité
	 */
	private boolean verifIncomp(PartType part,Set<PartType> selected) {
		Set<PartType> partIncomp;
		
		for(PartType other : selected) {
			if(!part.equals(other)) {
				partIncomp = confOr.getCompatibilityChecker().getIncompatibilities(part);
				if(partIncomp.contains(other)) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * Regarde si une catégorie est présente parmis une liste de PartType
	 * @param c Category ciblé
	 * @param selected liste de PartType a vérifié
	 * @return true si c est présent parmis les categories de selected
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
		System.out.println("getSelectionForCategory -> Aucune PartType pour cette catégorie dans selected");
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
			System.out.println("unselectPartType -> Aucune PartType pour cette catégorie dans selected");
	}

	@Override
	public void clear() {
		selected.clear();
	}

}
