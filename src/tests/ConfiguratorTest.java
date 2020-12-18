package tests;


import static org.junit.Assert.*;



import java.util.HashSet;

import java.util.Set;

import org.junit.jupiter.api.*;

import fr.istic.nplouzeau.cartaylor.api.*;
import impl.CategoryImpl;
import impl.CompatibilityManagerImpl;
import impl.ConfigurationImpl;
import impl.ConfiguratorImpl;
import impl.Interior;
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
		IN = new PartTypeImpl("IN",Interior.class,interior);
		IH = new PartTypeImpl("IH",Interior.class,interior);
		IS = new PartTypeImpl("IS",Interior.class,interior);
	
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
		
		assertTrue(cInterior.containsAll(compare));
	}
	@Test
	void getVariantEmpty() {
		CategoryImpl suspension = new CategoryImpl("suspension");
		assertTrue(c.getVariants(suspension).isEmpty());
	}

}
