
package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;
/**
 * 
 * @author Alexy
 *<p>
 * type public pour g�rer les compatibilit�s entre PartType
 */
public interface CompatibilityManager extends CompatibilityChecker {
    /**
     *  Ajoute une PartType � une liste de PartType incompatibles
     * @param reference PartType o� rajouter des incompatibilit�s
     * @param target liste d'incompatibilit� que l'on souhaite rajouter
     */
    void addIncompatibilities(PartType reference, Set<PartType> target);

    /**
     *  Supprime une PartType d'une liste de PartType incompatibles
     * @param reference PartType o� supprimer des incompatibilit�s
     * @param target PartType que l'on souhaite supprimer de la liste d'incompatibilit�
     */
    void removeIncompatibility(PartType reference, PartType target);

    /**
     *  Ajoute une PartType � une liste de PartType pre-requis
     * @param reference PartType  o� rajouter des pr�-requis
     * @param target liste de compatibilit� que l'on souhaite augmenter
     */
    void addRequirements(PartType reference, Set<PartType> target);

    /**
     *  Supprime une PartType d'une liste de PartType pre-requis
     * @param reference PartType o� supprimer des pr�-requis
     * @param target que l'on souhaite supprimer de la liste de pr�-requis
     */
    void removeRequirement(PartType reference, PartType target);


}
