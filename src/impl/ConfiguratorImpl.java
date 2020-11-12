package impl;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.HashMap;

import fr.istic.nplouzeau.cartaylor.api.*;

public class ConfiguratorImpl implements Configurator {
	
	private Configuration confIon;
	private Set<Category> lCategories;
	private CompatibilityChecker ck;
	private Map<Category,Set<PartType>> lVariant;
	
	/**
	 * Constructeur de Configurator
	 */
	public ConfiguratorImpl(Configuration c,CompatibilityChecker cm) {
		
		this.confIon = c;
		this.ck = cm;
		
		initList();
	}
	
	public void initList() {
		ConfigurationImpl c2 = (ConfigurationImpl) confIon;
		c2.linkToConfigurator(this);
 		
		this.lVariant = new HashMap<Category,Set<PartType>>();
		this.lCategories = new HashSet<Category>();
		
		Category engine = new CategoryImpl("Engine");
		Category transmission = new CategoryImpl("Transmission");
		Category interior = new CategoryImpl("Interior");
		Category exterior = new CategoryImpl("Exterior");
		this.lCategories.add(engine);
		this.lCategories.add(transmission);
		this.lCategories.add(exterior);
		this.lCategories.add(interior);
		
		
		PartType EG100 = new PartTypeImpl("EG100",engine);
		PartType EG133 = new PartTypeImpl("EG133",engine);
		PartType EG210 = new PartTypeImpl("EG210",engine);
		PartType ED110 = new PartTypeImpl("ED110",engine);
		PartType ED180 = new PartTypeImpl("ED180",engine);
		PartType EH120 = new PartTypeImpl("EH120",engine);
		
		PartType TM5 = new PartTypeImpl("TM5",transmission);
		PartType TM6 = new PartTypeImpl("TM6",transmission);
		PartType TA5 = new PartTypeImpl("TA5",transmission);
		PartType TS6 = new PartTypeImpl("TS6",transmission);
		PartType TSF7 = new PartTypeImpl("TSF7",transmission);
		PartType TC120 = new PartTypeImpl("TC120",transmission);
		
		PartType XC = new PartTypeImpl("XC",exterior);
		PartType XM = new PartTypeImpl("XM",exterior);
		PartType XS = new PartTypeImpl("XS",exterior);
		
		PartType IN = new PartTypeImpl("IN",interior);
		PartType IH = new PartTypeImpl("IH",interior);
		PartType IS = new PartTypeImpl("IS",interior);
		
		Set<PartType> lEngine = new HashSet<PartType>();
		Set<PartType> lTrans = new HashSet<PartType>();
		Set<PartType> lExt = new HashSet<PartType>();
		Set<PartType> lInt = new HashSet<PartType>();
		
		lEngine.add(EG100);lEngine.add(EG133);lEngine.add(EG210);
		lEngine.add(ED110);lEngine.add(ED180);lEngine.add(EH120);
		
		lTrans.add(TM5);lTrans.add(TM6);lTrans.add(TA5);
		lTrans.add(TS6);lTrans.add(TSF7);lTrans.add(TC120);
		
		lExt.add(XC);lExt.add(XM);lExt.add(XS);
		
		lInt.add(IN);lInt.add(IH);lInt.add(IS);
		
	    lVariant.put(engine,lEngine);
		lVariant.put(transmission,lTrans);
		lVariant.put(exterior,lExt);
		lVariant.put(interior,lInt);	
	}
	
	@Override
	public Set<Category> getCategories() {
		return Collections.unmodifiableSet(lCategories);
	}

	@Override
	public Set<PartType> getVariants(Category category) {
		Objects.requireNonNull(category);
		Category res = new CategoryImpl("");
		for(Category c : lVariant.keySet()) {
			if(c.equals(category)) {
				res = c;
			}
		}
		return Collections.unmodifiableSet(lVariant.get(res));
	}
	
	@Override
	public Configuration getConfiguration() {
		return confIon;
	}

	@Override
	public CompatibilityChecker getCompatibilityChecker() {	
		return ck;
	}

}
