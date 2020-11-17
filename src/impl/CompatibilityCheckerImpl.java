package impl;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
	
	protected Map<PartType,Set<PartType>> lIncomp;
	protected Map<PartType,Set<PartType>> lRequirement;
	/**
	 * Constructeur de CompatibilityCheckImpl
	 */
	public CompatibilityCheckerImpl(){
		lIncomp = new HashMap<>();
		lRequirement = new HashMap<>();
    }

	@Override
	public Set<PartType> getIncompatibilities(PartType reference) {
		Objects.requireNonNull(reference);
		if(lIncomp.containsKey(reference)) {
			return Collections.unmodifiableSet(lIncomp.get(reference));
		}else {
			return new HashSet<>();
		}
		
	}

	@Override
	public Set<PartType> getRequirements(PartType reference) {
		Objects.requireNonNull(reference);
		if(lRequirement.containsKey(reference)) {
			return Collections.unmodifiableSet(lRequirement.get(reference));
		}else {
			return new HashSet<>();
		}
		
	}

}
