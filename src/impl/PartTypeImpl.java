package impl;

import java.util.Objects;

import fr.istic.nplouzeau.cartaylor.api.*;

public class PartTypeImpl implements PartType{
	private String name = "Nom de la partie pas encore défini";
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
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		PartType part = (PartType) o ;
		return name.equals(cat.getName()) && cat.getName().equals(part.getCategory().getName());
	}

}
