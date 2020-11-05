package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;
/**
 * 
 * @author Alexy
 * <p>
 * un type public pour retrouver les compatibilités entre PartType
 */
public interface CompatibilityChecker {
    /**
     * Retourne une liste des PartType incompatibles pour une PartType
     * @param reference PartType ciblé
     * @return Set<PartType> liste des PartType incompatible avec reference
     */
    Set<PartType> getIncompatibilities(PartType reference);

    /**
     * Retourne une liste des PartType réquis pour une PartType
     * @param reference PartType ciblé
     * @return  Set<PartType> liste des PartType pré-requis pour reference
     */
    Set<PartType> getRequirements(PartType reference);

}
