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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cat == null) ? 0 : cat.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartTypeImpl other = (PartTypeImpl) obj;
		if (cat == null) {
			if (other.cat != null)
				return false;
		} else if (!cat.equals(other.cat))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
