package fr.istic.nplouzeau.cartaylor.api;


import java.io.PrintStream;
import java.util.Optional;
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
     * @return Set<Party> piece selectionnes
     */
    Set<Part> getSelectedParts();

    /**
     * Ajoute une piece a la liste des pieces selectionnes 
     * @param chosenPart piece a ajoute
     */
    void selectPart(PartType chosenPart);

    /**
     * Retourne la selection d'une categorie de piece
     * @param category Category cible
     * @return Optional<Part> pi�ce de la Category demande
     */
    Optional<Part> getSelectionForCategory(Category category);

    /**
     * Supprime une pi�ce d'une cat�gorie
     * @param categoryToClear Categorie de la piece a supprime
     */
    void unselectPartType(Category categoryToClear);

    /**
     * Supprimer toutes les pieces de la selection.
     */
    void clear();
    
    /**
     * Description de la configuration
     * @param print PrintStream
     * @return PrintStream String Description des pieces selectionnees ainsi que etat de complete et valid
     */
    PrintStream printDescription(PrintStream ps);
    
    /**
     * Affiche le prix de la selection
     * Seulement si elle est valide
     */
    int printPrice();

}
