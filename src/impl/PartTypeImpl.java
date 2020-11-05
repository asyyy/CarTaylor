package impl;

import java.util.Objects;

import fr.istic.nplouzeau.cartaylor.api.*;

public class PartTypeImpl implements PartType{
	private String name = "Nom de la partie pas encore d�fini";
	private Category cat;
	
	/**
	 * Constructeur de PartType
	 * @param name
	 * @param cat  Category non null
	 */
	public PartTypeImpl(String name, Category cat) {
		Objects.requireNonNull(cat);
		Objects.requireNonNull(name);
		this.name= name;
		this.cat = cat;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public Category getCategory() {
		return cat;
	}

}
