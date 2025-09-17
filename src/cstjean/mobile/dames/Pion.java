package cstjean.mobile.dames;

/**
 * Représente un pion dans un jeu de dames.
 *
 * <p>
 * Chaque pion possède une {@link Couleur}, soit noir, soit blanc.
 * Un pion noir est représenté par le caractère {@code 'P'},
 * tandis qu’un pion blanc est représenté par {@code 'p'}.
 * </p>
 *
 * <p>
 * Cette classe peut être étendue, par exemple par {@link Dame},
 * pour représenter des pièces spéciales du jeu.
 * </p>
 *
 * @author Samuel Cuerrier
 */
public class Pion {

    /**
     * Énumération des couleurs possibles pour un pion.
     */
    public enum Couleur {
        /** Représente un pion de couleur noire. */
        Noir,
        /** Représente un pion de couleur blanche. */
        Blanc
    }

    /** La couleur du pion. */
    private final Couleur couleur;

    /**
     * Construit un pion avec la couleur spécifiée.
     *
     * @param couleur la couleur du pion (noir ou blanc)
     */
    public Pion(Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     * Construit un pion blanc par défaut.
     */
    public Pion() {
        this.couleur = Couleur.Blanc;
    }

    /**
     * Retourne la couleur du pion.
     *
     * @return la couleur du pion ({@link Couleur#Noir} ou {@link Couleur#Blanc})
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Retourne le caractère représentant graphiquement le pion.
     *
     * <ul>
     *   <li>{@code 'P'} pour un pion noir,</li>
     *   <li>{@code 'p'} pour un pion blanc.</li>
     * </ul>
     *
     * @return un caractère représentant la pièce
     */
    public char getRepresentation() {
        if (this.couleur == Couleur.Noir) {
            return 'P';
        } else {
            return 'p';
        }
    }
}
