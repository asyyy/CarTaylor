package impl;

import java.util.Objects;

import fr.istic.nplouzeau.cartaylor.api.Category;

public class CategoryImpl implements Category {
	
	private String name = "Nom de la categorie pas encore defini";
	/**
	 * Constructeur de categorie
	 * @param name nom de la categorie
	 */
	public CategoryImpl(String name) {
		Objects.requireNonNull(name);
		this.name = name; 
	}
	@Override
	public String getName() {
		return name;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		CategoryImpl other = (CategoryImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}