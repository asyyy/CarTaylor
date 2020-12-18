package fr.istic.nplouzeau.cartaylor.api;

import impl.PartImpl;

public interface PartType {
	
    /**
     * Retourne le nom de la pièce
     * @return String
     */
    String getName();
    /**
     * Retourne le nom de la catégorie de la pièce
     * @return Category
     */
    Category getCategory();
}
