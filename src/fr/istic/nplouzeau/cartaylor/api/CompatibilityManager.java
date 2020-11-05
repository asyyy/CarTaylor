
package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;
/**
 * 
 * @author Alexy
 *<p>
 * type public pour gérer les compatibilités entre PartType
 */
public interface CompatibilityManager extends CompatibilityChecker {
    /**
     *  Ajoute une PartType à une liste de PartType incompatibles
     * @param reference PartType où rajouter des incompatibilités
     * @param target liste d'incompatibilité que l'on souhaite rajouter
     */
    void addIncompatibilities(PartType reference, Set<PartType> target);

    /**
     *  Supprime une PartType d'une liste de PartType incompatibles
     * @param reference PartType où supprimer des incompatibilités
     * @param target PartType que l'on souhaite supprimer de la liste d'incompatibilité
     */
    void removeIncompatibility(PartType reference, PartType target);

    /**
     *  Ajoute une PartType à une liste de PartType pre-requis
     * @param reference PartType  où rajouter des pré-requis
     * @param target liste de compatibilité que l'on souhaite augmenter
     */
    void addRequirements(PartType reference, Set<PartType> target);

    /**
     *  Supprime une PartType d'une liste de PartType pre-requis
     * @param reference PartType où supprimer des pré-requis
     * @param target que l'on souhaite supprimer de la liste de pré-requis
     */
    void removeRequirement(PartType reference, PartType target);


}
