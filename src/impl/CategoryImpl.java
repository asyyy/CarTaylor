package impl;

import java.util.Objects;

import fr.istic.nplouzeau.cartaylor.api.Category;

public class CategoryImpl implements Category {
	private String name = "Nom de la cat�gorie pas encore d�fini";
	/**
	 * Constructeur de cat�gorie
	 * @param name nom de la cat�gorie
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
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		CategoryImpl cat = (CategoryImpl) o ;
		return name.equals(cat.getName());
	}

}