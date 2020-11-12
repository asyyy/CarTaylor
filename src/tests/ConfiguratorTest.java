package tests;


import static org.junit.Assert.*;


import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.*;

import fr.istic.nplouzeau.cartaylor.api.*;
import impl.CategoryImpl;
import impl.CompatibilityCheckerImpl;
import impl.CompatibilityManagerImpl;
import impl.ConfigurationImpl;
import impl.ConfiguratorImpl;
import impl.PartTypeImpl;

public class ConfiguratorTest {
	private Configurator c;
	private Configuration confIon;
	private CompatibilityChecker ck;
	private CompatibilityManager cm;
	private PartType IN,IH,IS;
	private Category interior;

	
	private void initList() {

		interior = new CategoryImpl("Interior");
		IN = new PartTypeImpl("IN",interior);
		IH = new PartTypeImpl("IH",interior);
		IS = new PartTypeImpl("IS",interior);
	
	}
	
	@BeforeEach
	private void setUp() {
		confIon = new ConfigurationImpl();
		cm = new CompatibilityManagerImpl();
		c = new ConfiguratorImpl(confIon,cm);
		initList();
		
		interior = new CategoryImpl("Interior");
	}
	
	
	@Test
	void getCategories() {

		Set<Category> test = c.getCategories();
		assertNotEquals(null,test);
	}
	
	@Test
	void getVariants() {
		Set<PartType> compare = new HashSet<>();
		Set<PartType> cInterior = c.getVariants(interior);

		compare.add(IN);
		compare.add(IH);
		compare.add(IS);
		
		List<String> lComp = new ArrayList<String>();
		List<String> lCInt = new ArrayList<String>();
		
		
		for(PartType p : compare) {
			lComp.add(p.getName());
		}
		for(PartType p : cInterior) {
			lCInt.add(p.getName());
		}
		
		assertTrue(lCInt.containsAll(lComp));
	}
	

}
