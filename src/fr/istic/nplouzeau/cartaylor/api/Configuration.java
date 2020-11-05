package fr.istic.nplouzeau.cartaylor.api;


import java.util.Set;

public interface Configuration {

    /**
     * Verifie si une configuration est valide (aucune incompatibilit�)
     * @return true si la Configuration est valide else false
     */
    boolean isValid();

    /**
     * Verifie si une configuration est complete (une selection complete de pi�ce)
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
     * @return PartType pi�ce de la Category demande
     */
    PartType getSelectionForCategory(Category category);

    /**
     * Supprime une pi�ce d'une cat�gorie
     * @param categoryToClear Categorie de la piece a supprime
     */
    void unselectPartType(Category categoryToClear);

    /**
     * Supprimer toutes les pieces de la configuration
     */
    void clear();

}
