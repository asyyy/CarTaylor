package fr.istic.nplouzeau.cartaylor.api;

public interface PartType {
	
    /**
     * Retourne le nom de la pi�ce
     * @return String
     */
    String getName();
    /**
     * Retourne le nom de la cat�gorie de la pi�ce
     * @return Category
     */
    Category getCategory();
}
