package tests;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.nplouzeau.cartaylor.api.*;
import impl.*;

public class PartImplTest {
	private Configurator confOr;
	private Configuration confIon;
	private CompatibilityManager cm;
	private PartType EG100;
	private PartType TM5,TSF7;
	private PartType XC;
	private PartType IN;
	private Category engine,transmission,exterior,interior;
	private ResultatPropertiesTest resTest;
	private ByteArrayOutputStream os;
	private String piece = "Piece non trouvé";
	
	@BeforeEach
	private void setUp() {
		resTest = new ResultatPropertiesTest();
		confIon = new ConfigurationImpl();
		cm = new CompatibilityManagerImpl();
		confOr = new ConfiguratorImpl(confIon,cm);
		os = new ByteArrayOutputStream();
		
		confIon = confOr.getConfiguration();
		confIon.clear();
		
		engine = new CategoryImpl("Engine");
		transmission = new CategoryImpl("Transmission");
		exterior = new CategoryImpl("Exterior");
		interior = new CategoryImpl("Interior");
		
		EG100 = new PartTypeImpl("EG100",Engine.class,engine);	
		TM5 = new PartTypeImpl("TM5",Transmission.class,transmission);
		TSF7 = new PartTypeImpl("TSF7",Transmission.class,transmission);
		XC = new PartTypeImpl("XC",Exterior.class,exterior);	
		IN = new PartTypeImpl("IN",Interior.class,interior);
		
	}
	@Test
	@DisplayName("Selection : Valide Complète | Properties :Vide")
	void test1() {
		confIon.selectPart(EG100);
		confIon.selectPart(TM5);
		confIon.selectPart(XC);
		confIon.selectPart(IN);
		confIon.printDescription(new PrintStream(os));
		String expected = resTest.getTest1();
		assertEquals(os.toString(),expected);
	}
	@Test
	@DisplayName("Selection : Valide Incomplète | Properties :Vide")
	void test2() {
		confIon.selectPart(EG100);
		confIon.selectPart(TM5);
		confIon.selectPart(XC);
		confIon.printDescription(new PrintStream(os));
		String expected = resTest.getTest2();
		assertEquals(os.toString(),expected);
	}
	@Test
	@DisplayName("Selection : Invalide Incomplète | Properties : Vide")
	void test3() {
		confIon.selectPart(EG100);
		confIon.selectPart(TSF7);
		confIon.selectPart(XC);
		confIon.selectPart(IN);
		confIon.printDescription(new PrintStream(os));
		String expected = resTest.getTest3();
		assertEquals(os.toString(),expected);
	}
	@Test
	@DisplayName("Selection : Valide Complète | Properties : Pleine")
	void test4() {
		
		//Selection engine + properties
		confIon.selectPart(EG100);
		Part eg100 = confIon.getSelectionForCategory(engine).get();
		eg100.setProperty("carburant", "essence");
		eg100.setProperty("puissance", "180kw");
		confIon.selectPart(TM5);
		
		//Selection transmission + properties
		Part tm5 = confIon.getSelectionForCategory(transmission).get();
		tm5.setProperty("boite", "automatique");
		tm5.setProperty("vitesse", "7");
		
		//Selection exterior + properties	
		confIon.selectPart(XC);
		Part xc = confIon.getSelectionForCategory(exterior).get();
		xc.setProperty("color", "standart");
		
		//Selection interior + properties
		confIon.selectPart(IN);
		Part in = confIon.getSelectionForCategory(interior).get();
		in.setProperty("interior", "standart");
		String expected = resTest.getTest4();
				
		
		confIon.printDescription(new PrintStream(os));
		assertEquals(os.toString(),expected);
	}
	@Test
	@DisplayName("printPrice Valid")
	void test5() {
		confIon.selectPart(EG100);
		confIon.selectPart(TM5);
		confIon.selectPart(XC);
		confIon.selectPart(IN);
		/*
		 * eg100 = 100
		 * tm5 = 50
		 * xc = 50
		 * in = 96
		 * Total 296
		 */
		assertEquals(confIon.printPrice(),296);	
	}
	@Test
	@DisplayName("printPrice Invalid")
	void test6() {
		confIon.selectPart(EG100);
		confIon.selectPart(TSF7);
		confIon.selectPart(XC);
		confIon.selectPart(IN);
		
		assertEquals(confIon.printPrice(),-404);	
	}
	@Test
	@DisplayName("Engine -> Prix non trouve")
	void test7() {
		PartType douglasAdam = new PartTypeImpl("douglasAdam",Engine.class,engine);
		confIon.selectPart(douglasAdam);
		Part d = confIon.getSelectionForCategory(engine).get();
		String found = d.getProperty("prix").get();
		
		assertEquals(found,piece);	
	}
	@Test
	@DisplayName("Transmission -> Prix non trouve")
	void test8() {
		PartType douglasAdam = new PartTypeImpl("douglasAdam",Transmission.class,transmission);
		confIon.selectPart(douglasAdam);
		Part d = confIon.getSelectionForCategory(transmission).get();
		String found = d.getProperty("prix").get();
		
		assertEquals(found,piece);	
	}
	@Test
	@DisplayName("Exterior -> Prix non trouve")
	void test9() {
		PartType douglasAdam = new PartTypeImpl("douglasAdam",Exterior.class,exterior);
		confIon.selectPart(douglasAdam);
		Part d = confIon.getSelectionForCategory(exterior).get();
		String found = d.getProperty("prix").get();
		
		assertEquals(found,piece);	
	}
	@Test
	@DisplayName("Interior -> Prix non trouve")
	void test10() {
		PartType douglasAdam = new PartTypeImpl("douglasAdam",Interior.class,interior);
		confIon.selectPart(douglasAdam);
		Part d = confIon.getSelectionForCategory(interior).get();
		String found = d.getProperty("prix").get();
		
		assertEquals(found,piece);	
	}
	@Test
	@DisplayName("Engine -> getPropertyName()")
	void test11() {
		confIon.selectPart(EG100);
		Part d = confIon.getSelectionForCategory(engine).get();
		Set<String> expected = Set.of("prix","carburant","puissance");
		assertTrue(d.getPropertyNames().containsAll(expected));	
	}
	@Test
	@DisplayName("Transmission -> getPropertyName()")
	void test12() {
		confIon.selectPart(TSF7);
		Part d = confIon.getSelectionForCategory(transmission).get();
		Set<String> expected = Set.of("prix","boite","vitesse");
		assertTrue(d.getPropertyNames().containsAll(expected));	
	}
	@Test
	@DisplayName("Exterior -> getPropertyName()")
	void test13() {
		confIon.selectPart(XC);
		Part d = confIon.getSelectionForCategory(exterior).get();
		Set<String> expected = Set.of("prix","color");
		assertTrue(d.getPropertyNames().containsAll(expected));	
	}
	@Test
	@DisplayName("Interior -> getPropertyName()")
	void test14() {
		confIon.selectPart(IN);
		Part d = confIon.getSelectionForCategory(interior).get();
		Set<String> expected = Set.of("prix","interior");
		assertTrue(d.getPropertyNames().containsAll(expected));	
	}
	@Test
	@DisplayName("getProperty() is Empty")
	void test15() {
		confIon.selectPart(IN);
		Part d = confIon.getSelectionForCategory(interior).get();
		assertTrue(d.getProperty("color").isEmpty());	
	}
	@Test
	@DisplayName("getAvailablePropertyValues() full ")
	void test16() {
		confIon.selectPart(IN);
		Part d = confIon.getSelectionForCategory(interior).get();
		Set<String> expected = Set.of("standard","high","sport");
		assertTrue(d.getAvailablePropertyValues("interior").containsAll(expected));	
	}
	@Test
	@DisplayName("getAvailablePropertyValues() is empty")
	void test17() {
		confIon.selectPart(IN);
		Part d = confIon.getSelectionForCategory(interior).get();
		assertTrue(d.getAvailablePropertyValues("color").isEmpty());	
	}
	

	@Test
	@DisplayName("setProperty() IllegalArgumentException")
	void test18() {
		confIon.selectPart(IN);
		Part d = confIon.getSelectionForCategory(interior).get();
		Assertions.assertThrows(IllegalArgumentException.class, 
		()->d.setProperty("exterior", "dirkGently"),"bad property name or value: exterior");
		
	}
	
	

}
