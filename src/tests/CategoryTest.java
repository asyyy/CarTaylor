package tests;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.CompatibilityChecker;
import fr.istic.nplouzeau.cartaylor.api.CompatibilityManager;
import fr.istic.nplouzeau.cartaylor.api.Configuration;
import fr.istic.nplouzeau.cartaylor.api.Configurator;
import fr.istic.nplouzeau.cartaylor.api.PartType;
import impl.CategoryImpl;
import impl.CompatibilityManagerImpl;
import impl.ConfigurationImpl;
import impl.ConfiguratorImpl;
import impl.Engine;
import impl.PartTypeImpl;

public class CategoryTest {
	
	@Test
	public void equalsNull() {
		CategoryImpl engine2 = new CategoryImpl("Engine");
		assertFalse(engine2.equals(null));
	}
	@Test
	public void differentClass() {
		CategoryImpl engine = new CategoryImpl("");
		PartType eg100 = new PartTypeImpl("EG100",Engine.class,engine);
		assertFalse(engine.equals(eg100));
	}
}
