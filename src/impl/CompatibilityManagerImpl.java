package impl;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.CompatibilityChecker;
import fr.istic.nplouzeau.cartaylor.api.CompatibilityManager;
import fr.istic.nplouzeau.cartaylor.api.Configurator;
import fr.istic.nplouzeau.cartaylor.api.PartType;

public class CompatibilityManagerImpl implements CompatibilityManager {
	
	/*
	 * Chaque partType est une clé dans un map ou sa valeur
	 * est une liste de ses pré-requis/incompatibilité
	 *  
	 */
	
	private Map<PartType,Set<PartType>> lIncomp;
	private Map<PartType,Set<PartType>> lRequirement;

	
	/**
	 * Constructeur de CompatibilityManagerImpl
	 * 
	 */
	public CompatibilityManagerImpl() {
		lIncomp = new HashMap<PartType,Set<PartType>>();
		lRequirement = new HashMap<PartType,Set<PartType>>();
	}

	
	
	@Override
	public Set<PartType> getIncompatibilities(PartType reference) {
		Objects.requireNonNull(reference);
		return Collections.unmodifiableSet(lIncomp.get(reference));
	}

	@Override
	public Set<PartType> getRequirements(PartType reference) {
		return Collections.unmodifiableSet(lRequirement.get(reference));
	}

	@Override
	public void addIncompatibilities(PartType reference, Set<PartType> target) {
		Objects.requireNonNull(reference);
		Objects.requireNonNull(target);
		//Si lIncomp ne contient pas reference
		if(!lIncomp.containsKey(reference)) {
			lIncomp.put(reference, target);
		}else{
			lIncomp.get(reference).addAll(target);
		}
		croiserAddIncompatiblites(reference,target);
		
	}
	
	@Override
	public void removeIncompatibility(PartType reference, PartType target) {
		Objects.requireNonNull(reference);
		Objects.requireNonNull(target);
		if(lIncomp.containsKey(reference)) {
			lIncomp.get(reference).remove(target);
			
			//Si on supprime une incompatibilité d'un côté, il faut aussi supprimer reference des incomp de target
			if(lIncomp.containsKey(target)) {
				lIncomp.get(target).remove(reference);
			}
			
		}else {
			System.out.println(reference + " n'est pas une clé dans la liste d'incompatibilité");
		}
		
	}
	
	@Override
	public void addRequirements(PartType reference, Set<PartType> target) {
		Objects.requireNonNull(reference);
		Objects.requireNonNull(target);
		//Si lIncomp ne contient pas reference
		if(!lRequirement.containsKey(reference)) {
			lRequirement.put(reference, target);
		}else{
			lRequirement.get(reference).addAll(target);
		}
		croiserAddRequirement(reference,target);
		
	}

	@Override
	public void removeRequirement(PartType reference, PartType target) {
		Objects.requireNonNull(reference);
		Objects.requireNonNull(target);
		if(lRequirement.containsKey(reference)) {
			lRequirement.get(reference).remove(target);
			
			//Si on supprime une incompatibilité d'un côté, il faut la supprimer de l'autre
			if(lRequirement.containsKey(target)) {
				lRequirement.get(target).remove(reference);
			}
			
		}else {
			System.out.println(reference + " n'est pas une clé dans la liste de pré-requis");
		}
		
	}
	/**
	 * Ajouter reference aux listes d'incompatibilité des toutes les PartType dans target
	 * @param reference PartType incompatible
	 * @param target liste de PartType où rajouter reference comme incompatible
	 */
	private void croiserAddIncompatiblites(PartType reference, Set<PartType> target) {
		Objects.requireNonNull(reference);
		Objects.requireNonNull(target);
		for(PartType p : target) {
			//Si lIncomp ne contient pas encore p
			if(!lIncomp.containsKey(p)) {
				Set<PartType> toAdd = new HashSet<PartType>();
				toAdd.add(reference);
				lIncomp.put(p,toAdd);
			}else{
				lIncomp.get(p).add(reference);
			}
		}
		
	}
	/**
	 * Ajouter reference aux listes de pré-requis des toutes les PartType dans target
	 * @param reference PartType pré-requis
	 * @param target liste de PartType où rajouter reference comme pré-requis
	 */
	private void croiserAddRequirement(PartType reference, Set<PartType> target) {
		Objects.requireNonNull(reference);
		Objects.requireNonNull(target);
		for(PartType p : target) {
			//Si lIncomp ne contient pas encore p
			if(!lRequirement.containsKey(p)) {
				Set<PartType> toAdd = new HashSet<PartType>();
				toAdd.add(reference);
				lRequirement.put(p,toAdd);
			}else{
				lRequirement.get(p).add(reference);
			}
		}
		
	}

}
