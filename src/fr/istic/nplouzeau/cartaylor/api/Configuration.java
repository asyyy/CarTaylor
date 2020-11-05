package fr.istic.nplouzeau.cartaylor.api;


import java.util.Set;

public interface Configuration {

    /**
     * Verifie si une configuration est valide (aucune incompatibilité)
     * @return true si la Configuration est valide else false
     */
    boolean isValid();

    /**
     * Verifie si une configuration est complete (une selection complete de pièce)
     * @return true si la Configuration est complete else false
     */
    boolean isComplete();

    /**
     * Retourne la liste des pieces selectionnes
     * @return Set<PartType> piece selectionnes
     */
    Set<PartType> getSelectedParts();

    /**
     * Ajoute une piece a la liste des pieces selectionnes 
     * @param chosenPart piece a ajoute
     */
    void selectPart(PartType chosenPart);

    /**
     * Retourne la selection d'une categorie de piece
     * @param category 
     * @return PartType pièce de la Category demande
     */
    PartType getSelectionForCategory(Category category);

    /**
     * Supprime une pièce d'une catégorie
     * @param categoryToClear Categorie de la piece a supprime
     */
    void unselectPartType(Category categoryToClear);

    /**
     * Supprimer toutes les pieces de la configuration
     */
    void clear();

}
