package impl;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import fr.istic.nplouzeau.cartaylor.api.CompatibilityManager;

import fr.istic.nplouzeau.cartaylor.api.PartType;

public class CompatibilityManagerImpl extends CompatibilityCheckerImpl implements CompatibilityManager {
	
	/**
	 * Constructeur de CompatibilityManagerImpl
	 * 
	 */
	public CompatibilityManagerImpl() {
		super();
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
		croisement(reference,target,lIncomp);
		
	}
	
	@Override
	public void removeIncompatibility(PartType reference, PartType target) {
		Objects.requireNonNull(reference);
		Objects.requireNonNull(target);
		if(lIncomp.containsKey(reference)) {
			lIncomp.get(reference).remove(target);
			
			//Si on supprime une incompatibilite d'un côte, il faut aussi supprimer reference des incomp de target
			if(lIncomp.containsKey(target)) {
				lIncomp.get(target).remove(reference);
			}
			
		}else {
			System.out.println(reference + " n'est pas une cle dans la liste d'incompatibilite");
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

		croisement(reference,target,lRequirement);
		
	}

	@Override
	public void removeRequirement(PartType reference, PartType target) {
		Objects.requireNonNull(reference);
		Objects.requireNonNull(target);
		if(lRequirement.containsKey(reference)) {
			lRequirement.get(reference).remove(target);
			
			//Si on supprime une incompatibilite d'un côte, il faut la supprimer de l'autre
			if(lRequirement.containsKey(target)) {
				lRequirement.get(target).remove(reference);
			}
			
		}else {
			System.out.println(reference + " n'est pas une cle dans la liste de pre-requis");
		}
		
	}
	
	/**
	 * Ajoute reference aux listes de pre-requis/Incompatibilites 
	 * de toutes les PartType dans target
	 * @param reference PartType a ajoute
	 * @param target Set<PartType> a parcourir
	 * @param map liste des pre-requis/incompatibilites (lIncomp ou lRequirement)
	 */
	private void croisement(PartType reference, Set<PartType> target,Map<PartType,Set<PartType>> map) {
		Objects.requireNonNull(reference);
		Objects.requireNonNull(target);
		for(PartType p : target) {
			//Si map ne contient pas encore p
			if(!map.containsKey(p)) {
				Set<PartType> toAdd = new HashSet<PartType>();
				toAdd.add(reference);
				map.put(p,toAdd);
			}else{
				map.get(p).add(reference);
			}
		}
		
	}
	

}
