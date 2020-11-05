package tests;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.*;
import impl.*;

import fr.istic.nplouzeau.cartaylor.api.*;

public class CompatibilityManagerTest {
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
	@DisplayName("add/get requirement sans vérif croisement")
	void addRequirement() {
		Set<PartType> lTC120 = new HashSet<>();
		lTC120.add(EH120);
		cm.addRequirements(TC120, lTC120);
		
		Set<PartType> compare = cm.getRequirements(TC120);
		assertEquals(compare,lTC120);
		
	}
	@Test
	@DisplayName("add/get requirements avec vérif croisement")
	void addRequirement2() {
		Set<PartType> lTC120 = new HashSet<>();
		Set<PartType> lEH120 = new HashSet<>();
		
		lTC120.add(EH120);
		lEH120.add(TC120);
		
		cm.addRequirements(TC120, lTC120);
		cm.addRequirements(EH120, lEH120);
	
		Set<PartType> compareTC120 = cm.getRequirements(TC120);
		Set<PartType> compareEH120 = cm.getRequirements(EH120);

		assertEquals(compareTC120,lTC120);
		assertEquals(compareEH120,lEH120);
		
	}
	

	
	@Test
	@DisplayName("add/get incompatibilités sans vérif croisement")
	void addIncompatibilities() {
		Set<PartType> lTSF7 = new HashSet<>();
		lTSF7.add(ED110);lTSF7.add(EG133);lTSF7.add(EG100);
		cm.addIncompatibilities(TSF7, lTSF7);
		
		Set<PartType> compare = cm.getIncompatibilities(TSF7);
		
		assertEquals(compare,lTSF7);
	}
	@Test
	@DisplayName("add/get incompatibilités avec vérif croisement")
	void addIncompatibilities2() {
		Set<PartType> lTSF7 = new HashSet<>();
		Set<PartType> lED110 = new HashSet<>();
		Set<PartType> lEG133 = new HashSet<>();
		Set<PartType> lEG100 = new HashSet<>();
		
		lTSF7.add(ED110);lTSF7.add(EG133);lTSF7.add(EG100);
		lEG100.add(ED110);lEG100.add(EG133);lEG100.add(TSF7);
		lEG133.add(ED110);lEG133.add(TSF7);lEG133.add(EG100);
		lED110.add(TSF7);lED110.add(EG133);lED110.add(EG100);
		
		cm.addIncompatibilities(TSF7, lTSF7);
		cm.addIncompatibilities(ED110, lED110);
		cm.addIncompatibilities(EG133, lEG133);
		cm.addIncompatibilities(EG100, lEG100);
		
		Set<PartType> compareTSF7 = cm.getIncompatibilities(TSF7);
		Set<PartType> compareED110 = cm.getIncompatibilities(ED110);
		Set<PartType> compareEG133 = cm.getIncompatibilities(EG133);
		Set<PartType> compareEG100 = cm.getIncompatibilities(EG100);
		
		assertEquals(compareTSF7,lTSF7);
		assertEquals(compareED110,lED110);
		assertEquals(compareEG133,lEG133);
		assertEquals(compareEG100,lEG100);
	
	}
	@Test
	@DisplayName("remove/get requirement sans vérif croisement")
	void removeRequirement() {
		Set<PartType> lTC120 = new HashSet<>();
		lTC120.add(EH120);lTC120.add(IS);
		
		//Vérification qu'on ajoute bien la liste de requirement
		cm.addRequirements(TC120, lTC120);
		Set<PartType> compare = cm.getRequirements(TC120);
		assertEquals(compare,lTC120);
		
		//On supprime un même élément des 2 listes que l'on compare
		lTC120.remove(IS);		
		cm.removeRequirement(TC120, IS);	
		Set<PartType> compare2 = cm.getRequirements(TC120);		
		assertEquals(compare2,lTC120);
				
	}
	@Test
	@DisplayName("remove/get requirement avec vérif croisement")
	void removeRequirement2() {
		Set<PartType> lTC120 = new HashSet<>();
		Set<PartType> lIS = new HashSet<>();
		lTC120.add(EH120);lTC120.add(IS);
		lIS.add(TC120);
		
		//Vérification qu'on ajoute bien la liste de requirement
		cm.addRequirements(TC120, lTC120);
		Set<PartType> compareTC120before = cm.getRequirements(TC120);
		Set<PartType> compareISbefore = cm.getRequirements(IS);
		assertEquals(compareTC120before,lTC120);
		assertEquals(compareISbefore,lIS);
		
		//On supprime un même élément des 2 listes que l'on compare
		lTC120.remove(IS);		
		lIS.remove(TC120);
		cm.removeRequirement(TC120, IS);	
		Set<PartType> compareTC120after = cm.getRequirements(TC120);
		Set<PartType> compareISafter = cm.getRequirements(IS);	
		
		assertEquals(compareTC120after,lTC120);
		assertEquals(compareISafter,lIS);
		
	}
	@Test
	@DisplayName("remove/get incompatibilités sans vérif croisement")
	void removeIncompatibilities() {
		Set<PartType> lTSF7 = new HashSet<>();
		lTSF7.add(ED110);lTSF7.add(EG133);lTSF7.add(EG100);
				
		//Vérification qu'on ajoute bien la liste d'incompatibilités
		cm.addIncompatibilities(TSF7, lTSF7);		
		Set<PartType> compare = cm.getIncompatibilities(TSF7);		
		assertEquals(compare,lTSF7);
		
		lTSF7.remove(ED110);
		cm.removeIncompatibility(TSF7, ED110);
		Set<PartType> compare2 = cm.getIncompatibilities(TSF7);
		assertEquals(compare2,lTSF7);
			
	}
	@Test
	@DisplayName("remove/get incompatibilités avec vérif croisement")
	void removeIncompatibilities2() {
		Set<PartType> lTSF7 = new HashSet<>();
		Set<PartType> lED110 = new HashSet<>();
		Set<PartType> lEG133 = new HashSet<>();
		Set<PartType> lEG100 = new HashSet<>();
		
		lTSF7.add(ED110);lTSF7.add(EG133);lTSF7.add(EG100);
		lEG100.add(ED110);lEG100.add(EG133);lEG100.add(TSF7);
		lEG133.add(ED110);lEG133.add(TSF7);lEG133.add(EG100);
		lED110.add(TSF7);lED110.add(EG133);lED110.add(EG100);
		
		cm.addIncompatibilities(TSF7, lTSF7);
		cm.addIncompatibilities(ED110, lED110);
		cm.addIncompatibilities(EG133, lEG133);
		cm.addIncompatibilities(EG100, lEG100);
		
		Set<PartType> compareTSF7 = cm.getIncompatibilities(TSF7);
		Set<PartType> compareED110 = cm.getIncompatibilities(ED110);
		Set<PartType> compareEG133 = cm.getIncompatibilities(EG133);
		Set<PartType> compareEG100 = cm.getIncompatibilities(EG100);
		assertEquals(compareTSF7,lTSF7);
		assertEquals(compareED110,lED110);
		assertEquals(compareEG133,lEG133);
		assertEquals(compareEG100,lEG100);
		
		lTSF7.remove(ED110);
		lED110.remove(TSF7);
		cm.removeIncompatibility(TSF7, ED110);
		
		//Vérification que la modification a bien été prise en compte
		assertEquals(compareTSF7,lTSF7);
		assertEquals(compareED110,lED110);
		
		//Vérification que les autre listes n'ont pas été modifiées
		assertEquals(compareEG133,lEG133);
		assertEquals(compareEG100,lEG100);
		
		
			
	}
}
