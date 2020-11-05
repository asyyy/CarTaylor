package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.*;

import fr.istic.nplouzeau.cartaylor.api.*;
import impl.*;


class ConfigurationTest {
	
	private Configurator confOr;
	private Configuration confIon;
	private PartType EG100,EG133,EG210,ED110,ED180,EH120;
	private PartType TM5,TM6,TA5,TS6,TSF7,TC120;
	private PartType XC,XM,XS;
	private PartType IN,IH,IS;
	private Category engine,transmission,exterior,interior;
	private CompatibilityManager cm;
	
	@BeforeEach
	private void setUp() {
		confOr = new ConfiguratorImpl();
		cm = (CompatibilityManager)confOr.getCompatibilityChecker();
		confIon = confOr.getConfiguration();
		confIon.clear();
		
		engine = new CategoryImpl("Engine");
		transmission = new CategoryImpl("Transmission");
		exterior = new CategoryImpl("Exterior");
		interior = new CategoryImpl("Interior");
		
		EG100 = new PartTypeImpl("EG100",engine);
		EG133 = new PartTypeImpl("EG133",engine);
		EG210 = new PartTypeImpl("EG210",engine);
		ED110 = new PartTypeImpl("ED110",engine);
		ED180 = new PartTypeImpl("ED180",engine);
		EH120 = new PartTypeImpl("EH120",engine);
		
		TM5 = new PartTypeImpl("TM5",transmission);
		TM6 = new PartTypeImpl("TM6",transmission);
		TA5 = new PartTypeImpl("TA5",transmission);
		TS6 = new PartTypeImpl("TS6",transmission);
		TSF7 = new PartTypeImpl("TSF7",transmission);
		TC120 = new PartTypeImpl("TC120",transmission);
		
		XC = new PartTypeImpl("XC",exterior);
		XM = new PartTypeImpl("XM",exterior);
		XS = new PartTypeImpl("XS",exterior);
		
		IN = new PartTypeImpl("IN",interior);
		IH = new PartTypeImpl("IH",interior);
		IS = new PartTypeImpl("IS",interior);
		
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
	void getSelectedPart() {
		confIon.selectPart(EG100);
		Set<PartType> compare = new HashSet<PartType>();
		compare.add(EG100);
		assertEquals(confIon.getSelectedParts(),compare);
	}
	@Test
    void selectPart() {
		confIon.selectPart(EG100);
		assertTrue(confIon.getSelectedParts().contains(EG100));
    }
	@Test
	void unselectPart() {
		confIon.selectPart(EG100);
		confIon.selectPart(TM5);
		confIon.unselectPartType(transmission);
		assertFalse(confIon.getSelectedParts().contains(TM5));
	}
	@Test
	void getSelectionForCategory() {
		confIon.selectPart(EG100);
		confIon.selectPart(TM5);
		confIon.selectPart(XC);
		confIon.selectPart(IN);
		
		assertEquals(confIon.getSelectionForCategory(engine).getName(),EG100.getName());
		assertEquals(confIon.getSelectionForCategory(transmission).getName(),TM5.getName());
		assertEquals(confIon.getSelectionForCategory(exterior).getName(),XC.getName());
		assertEquals(confIon.getSelectionForCategory(interior).getName(),IN.getName());
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
