package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

public interface Configurator {
    /**
     * Retourne la liste de toutes les catégories
     * @return Set Category category possibles
     */
    Set<Category> getCategories();

    /**
     * Retourne toutes les variantes d'une catégorie
     * @param category Category cible
     * @return Set PartType variant de category
     */
    Set<PartType> getVariants(Category category);

    /**
     * Retourne la configuration
     * @return Configuration
     */

    Configuration getConfiguration();

    /**
     * Retourne le CompatibilityChecker
     * @return CompatibilityChecker
     */
    CompatibilityChecker getCompatibilityChecker();

}
