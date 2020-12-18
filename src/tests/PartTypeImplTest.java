package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
public class PartTypeImplTest {
	private Category engine;
	private Category transmission;
	private PartType EG100,EG101;
	private PartType TM5,TM6,TM7;
	
	@BeforeEach
	void setUp() {
		engine = new CategoryImpl("Engine");
		transmission = new CategoryImpl("Transmission");
		TM5 = new PartTypeImpl("TM5",Transmission.class,transmission);
		EG100 = new PartTypeImpl("EG100",Engine.class,null);
		TM6 = new PartTypeImpl(null,Transmission.class,transmission);
		EG101 = new PartTypeImpl(null,Engine.class,engine);
		TM7 = new PartTypeImpl("TM7",Transmission.class,transmission);
		
	}
	@Test
	@DisplayName("this == obj")
	void test1() {
		assertTrue(EG100.equals(EG100));
	}
	@Test
	@DisplayName("obj == null")
	void test2() {
		PartTypeImpl nullValue = null;
		assertFalse(EG100.equals(nullValue));
	}
	@Test
	@DisplayName("getClass() != obj.getClass()")
	void test3() {
		assertFalse(EG100.equals(engine));
	}
	@Test
	@DisplayName("(cat == null) && (other.cat != null")
	void test4() {
		assertFalse(EG100.equals(EG101));
	}
	@Test
	@DisplayName("(!cat.equals(other.cat)")
	void test5() {
		assertFalse(EG101.equals(TM6));
	}
	@Test
	@DisplayName("(name == null) && (other.name != null)")
	void test6() {
		assertFalse(TM6.equals(TM5));
	}
	@Test
	@DisplayName("(!name.equals(other.name)")
	void test7() {
		assertFalse(TM7.equals(TM5));
	}
	
	
	
	
	

	
	
}
