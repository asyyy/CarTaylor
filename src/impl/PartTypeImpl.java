package impl;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.istic.nplouzeau.cartaylor.api.*;

public class PartTypeImpl implements PartType {
	private String name;
	private Class<? extends PartImpl> classRef;
	private Category cat;
	
	/**
	 * Constructeur de PartType
	 * @param name String nom de la PartType
	 * @param classRef PartImpl ou sous classe de PartImpl 
	 * @param cat Category associe, non null
	 */
	public PartTypeImpl(String name, Class<? extends PartImpl> classRef, Category category) {
		this.name = name;
		this.classRef = classRef;
		this.cat = category;
	}
	
	public PartImpl newInstance() {
		Constructor<? extends PartImpl> constructor;
		try {
			constructor = classRef.getConstructor(PartType.class);
			return constructor.newInstance(this);
		} catch (Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "constructor call failed", e);
			System.exit(-1);
		}
		return null;
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
