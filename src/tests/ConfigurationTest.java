package tests;

import static org.junit.Assert.*;

import java.util.Optional;


import org.junit.jupiter.api.*;

import fr.istic.nplouzeau.cartaylor.api.*;
import impl.*;


class ConfigurationTest {
	
	private Configurator confOr;
	private Configuration confIon;
	private CompatibilityChecker ck;
	private CompatibilityManager cm;
	private PartType EG100,EG133,EG210,ED110,ED180,EH120;
	private PartType TM5,TM6,TA5,TS6,TSF7,TC120;
	private PartType XC,XM,XS;
	private PartType IN,IH,IS;
	private Category engine,transmission,exterior,interior;
	
	
	@BeforeEach
	private void setUp() {
		confIon = new ConfigurationImpl();
		cm = new CompatibilityManagerImpl();
		confOr = new ConfiguratorImpl(confIon,cm);
		
		
		confIon = confOr.getConfiguration();
		confIon.clear();
		
		engine = new CategoryImpl("Engine");
		transmission = new CategoryImpl("Transmission");
		exterior = new CategoryImpl("Exterior");
		interior = new CategoryImpl("Interior");
		
		EG100 = new PartTypeImpl("EG100",Engine.class,engine);
		EG133 = new PartTypeImpl("EG133",Engine.class,engine);
		EG210 = new PartTypeImpl("EG210",Engine.class,engine);
		ED110 = new PartTypeImpl("ED110",Engine.class,engine);
		ED180 = new PartTypeImpl("ED180",Engine.class,engine);
		EH120 = new PartTypeImpl("EH120",Engine.class,engine);
		
		TM5 = new PartTypeImpl("TM5",Transmission.class,transmission);
		TM6 = new PartTypeImpl("TM6",Transmission.class,transmission);
		TA5 = new PartTypeImpl("TA5",Transmission.class,transmission);
		TS6 = new PartTypeImpl("TS6",Transmission.class,transmission);
		TSF7 = new PartTypeImpl("TSF7",Transmission.class,transmission);
		TC120 = new PartTypeImpl("TC120",Transmission.class,transmission);
		
		XC = new PartTypeImpl("XC",Exterior.class,exterior);
		XM = new PartTypeImpl("XM",Exterior.class,exterior);
		XS = new PartTypeImpl("XS",Exterior.class,exterior);
		
		IN = new PartTypeImpl("IN",Interior.class,interior);
		IH = new PartTypeImpl("IH",Interior.class,interior);
		IS = new PartTypeImpl("IS",Interior.class,interior);
		
	}
	@Test
	void isCompleteTrue() {
		confIon.selectPart(ED110);
		confIon.selectPart(TM5);
		confIon.selectPart(XC);
		confIon.selectPart(IN);
		assertTrue(confIon.isComplete());
	}
	
	@Test
	void isCompleteFalse() {		
		confIon.selectPart(ED110);
		confIon.selectPart(TM5);
		confIon.selectPart(XC);
		assertFalse(confIon.isComplete());
	}
	
	@Test
	void isValidTrue() {
		confIon.selectPart(EG100);
		confIon.selectPart(TM5);
		confIon.selectPart(XC);
		confIon.selectPart(IN);	
		assertTrue(confIon.isValid());
	}
		
	@Test
	void isValidFalseIncompatibility() {
				
		confIon.selectPart(EG100);
		confIon.selectPart(TSF7);
		confIon.selectPart(XC);
		confIon.selectPart(IN);
		assertFalse(confIon.isValid());
	}
	@Test
	void isValidFalseRequirement() {
				
		confIon.selectPart(TC120);
		confIon.selectPart(TSF7);
		confIon.selectPart(XC);
		confIon.selectPart(IN);
		
		assertFalse(confIon.isValid());
	}
	@Test
	void getSelectedPart() {			
		confIon.selectPart(EG100);		
		assertFalse(confIon.getSelectedParts().isEmpty());
	}
	@Test
    void selectPartTrue() {
		
		confIon.selectPart(EG100);
		Optional<Part> p = confIon.getSelectionForCategory(engine);
		String in = p.get().getType().getName();
		assertTrue(in.equals(EG100.getName()));
		
    }
	
	@Test
    void selectPartFalse() {
		Optional<Part> p = confIon.getSelectionForCategory(engine);
		assertTrue(p.isEmpty());
    }
	@Test
	void unselectPart() {

		
		confIon.selectPart(EG100);
		confIon.selectPart(TM5);
		confIon.unselectPartType(transmission);
		Optional<Part> p = confIon.getSelectionForCategory(transmission);
		assertTrue(p.isEmpty());
	}
	@Test
	void getSelectionForCategory() {

		confIon.selectPart(EG100);
		confIon.selectPart(TM5);
		confIon.selectPart(XC);
		confIon.selectPart(IN);
		
		assertTrue(confIon.getSelectionForCategory(engine).isPresent());
		assertTrue(confIon.getSelectionForCategory(transmission).isPresent());
		assertTrue(confIon.getSelectionForCategory(exterior).isPresent());
		assertTrue(confIon.getSelectionForCategory(interior).isPresent());
	}
	@Test
	void unselectPartError() {
		confIon.unselectPartType(exterior);
		/**
		 * Je n'ai pas reussi a assertEquals avec les resultats de logger
		 */
		assertTrue(confIon.getSelectedParts().isEmpty());
	}
	
	@Test
	void clear() {

		confIon.selectPart(EG100);
		confIon.selectPart(TM5);
		confIon.selectPart(XC);
		confIon.selectPart(IN);
		confIon.clear();
		assertTrue(confIon.getSelectedParts().isEmpty());
	}
}
