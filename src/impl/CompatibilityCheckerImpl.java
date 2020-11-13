package impl;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import fr.istic.nplouzeau.cartaylor.api.*;

public class CompatibilityCheckerImpl implements CompatibilityChecker{
	/*
	 * Chaque partType est une cle dans un map et sa valeur
	 * est une liste de ses pre-requis/incompatibilite
	 *  
	 */
	
	public Map<PartType,Set<PartType>> lIncomp;
	public Map<PartType,Set<PartType>> lRequirement;
	/**
	 * Constructeur de CompatibilityCheckImpl
	 */
	public CompatibilityCheckerImpl(){
		lIncomp = new HashMap<PartType,Set<PartType>>();
		lRequirement = new HashMap<PartType,Set<PartType>>();
    }

	@Override
	public Set<PartType> getIncompatibilities(PartType reference) {
		Objects.requireNonNull(reference);
		if(lIncomp.containsKey(reference)) {
			return Collections.unmodifiableSet(lIncomp.get(reference));
		}else {
			return null;
		}
		
	}

	@Override
	public Set<PartType> getRequirements(PartType reference) {
		Objects.requireNonNull(reference);
		if(lRequirement.containsKey(reference)) {
			return Collections.unmodifiableSet(lRequirement.get(reference));
		}else {
			return null;
		}
		
	}

}
