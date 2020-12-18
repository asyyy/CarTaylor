package impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.istic.nplouzeau.cartaylor.api.*;

public class PartImpl implements Part { 

	
	protected PartType type;
	private Map<String, Property> properties = new HashMap<>();
	
	/**
	 * Class représentant des attribues dynamiques
	 * @author Alexy
	 *
	 */
	private class Property {
		public final Supplier<String> getter;
		public final Consumer<String> setter;
		public final Set<String> possibleValues;

		/**
		 * Constructeur de property
		 * @param getter
		 * @param setter
		 * @param possibleValues
		 */
		Property(Supplier<String> getter, Consumer<String> setter, Set<String> possibleValues) {
			this.getter = getter;
			this.setter = setter;
			this.possibleValues = possibleValues;

		}
	}
	/**
	 * Setter de type
	 * @param type PartType 
	 */
	public void setPartType(PartType type) {
		Objects.requireNonNull(type);
		if(this.type == null) {
			this.type = type;
		}
	}
	
	/**
	 * Ajouter une proprieté dans properties
	 * @param name
	 * @param getter
	 * @param setter
	 * @param possibleValues
	 */
	protected void addProperty(String name, Supplier<String> getter, Consumer<String> setter,
			Set<String> possibleValues) {
		properties.put(name, new Property(getter, setter, possibleValues));
	}
	/**
	 * Getter du nom d'une properties
	 */
	@Override
	public Set<String> getPropertyNames() {
		return Collections.unmodifiableSet(properties.keySet());
	}
	/**
	 * Getter de la valeur d'une properties
	 */
	@Override
	public Optional<String> getProperty(String propertyName) {
		Objects.requireNonNull(propertyName);

		if (properties.containsKey(propertyName)) {
			return Optional.of(properties.get(propertyName).getter.get());
		}
		return Optional.empty();
	}
	/**
	 * Setter de la valeur d'une properties
	 */
	@Override
	public void setProperty(String propertyName, String propertyValue) {
		Objects.requireNonNull(propertyName);
		Objects.requireNonNull(propertyValue);

		if ((properties.containsKey(propertyName)) && (properties.get(propertyName).setter != null)) {
			properties.get(propertyName).setter.accept(propertyValue);
		} else {
			throw new IllegalArgumentException("bad property name or value: " + propertyName);
		}
	}
	/**
	 * Getter de tout les valeurs possibles d'une properties
	 */
	@Override
	public Set<String> getAvailablePropertyValues(String propertyName) {
		if (properties.containsKey(propertyName)) {
			return Collections.unmodifiableSet(properties.get(propertyName).possibleValues);
		}
		return Collections.emptySet();
	}

	@Override
	public Category getCategory() {	
		return type.getCategory();
	}

	@Override
	public PartType getType() {
		return type;
	}
	
	@Override
	public String printDescription() {
		String res = "------- \r\n";
		res += "Nom de la pièce : "+ type.getName() + "\r\n";
		res += "Categorie de la pièce : " + type.getCategory().getName() + "\r\n";
		res += "Propriétés : \r\n";
		for(String name : properties.keySet()) {
			if(getProperty(name).isPresent()) {
				res += name + " : " + getProperty(name).get() + "\r\n";
			}	
		}
		return res;
	}

	
}