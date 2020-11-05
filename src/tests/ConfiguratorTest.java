package tests;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.*;

import fr.istic.nplouzeau.cartaylor.api.*;
import impl.CategoryImpl;
import impl.ConfiguratorImpl;
import impl.PartTypeImpl;

public class ConfiguratorTest {
	Configurator c;
	private PartType EG100,EG133,EG210,ED110,ED180,EH120;
	private PartType TM5,TM6,TA5,TS6,TSF7,TC120;
	private PartType XC,XM,XS;
	private PartType IN,IH,IS;
	private Category engine,transmission,exterior,interior;
	private Map<Category,Set<PartType>> listInConfigurator;
	
	
	private void initList() {
		listInConfigurator = new HashMap<Category,Set<PartType>>();
		engine = new CategoryImpl("Engine");
		transmission = new CategoryImpl("Transmission");
		interior = new CategoryImpl("Interior");
		exterior = new CategoryImpl("Exterior");
				
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
		
		this.listInConfigurator.put(engine,lEngine);
		this.listInConfigurator.put(transmission,lTrans);
		this.listInConfigurator.put(exterior,lExt);
		this.listInConfigurator.put(interior,lInt);
	}
	
	@BeforeEach
	private void setUp() {
		c = new ConfiguratorImpl();
		initList();
		
		((ConfiguratorImpl) c).initList(listInConfigurator);
	
	}
	
	
	@Test
	void getCategories() {

		Set<Category> test = c.getCategories();
		assertNotEquals(null,test);
	}
	
	@Test
	void getVariants() {
		Set<PartType> compare = new HashSet<>();
		
		compare.add(IN);
		compare.add(IH);
		compare.add(IS);
		assertEquals(compare,c.getVariants(interior));
	}

}
