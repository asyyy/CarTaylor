package impl;
import java.util.Objects;
import java.util.Set;

import fr.istic.nplouzeau.cartaylor.api.*;

public class CompatibilityCheckerImpl implements CompatibilityChecker{
	Configurator confOr;
	CompatibilityManagerImpl cm;
	
	public CompatibilityCheckerImpl(Configurator confOr) {
		Objects.requireNonNull(confOr);
		this.confOr = confOr;
	}
	
	@Override
	public Set<PartType> getIncompatibilities(PartType reference) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PartType> getRequirements(PartType reference) {
		// TODO Auto-generated method stub
		return null;
	}

}
