package cstjean.mobile.dames;

/**
 * Représente une dame dans le jeu de dames.
 *
 * <p>
 * Une dame est une pièce spéciale qui hérite de {@link Pion}.
 * Contrairement au pion, elle peut se déplacer et capturer en avant
 * comme en arrière (logique de déplacement à gérer ailleurs).
 * </p>
 *
 * <p>
 * Cette classe redéfinit la représentation textuelle d’une dame :
 * </p>
 * <ul>
 *     <li>{@code 'D'} pour une dame noire,</li>
 *     <li>{@code 'd'} pour une dame blanche.</li>
 * </ul>
 *
 * @author Samuel Cuerrier
 */
public class Dame extends Pion {

    /**
     * Construit une dame avec une couleur donnée.
     *
     * @param couleur la couleur de la dame ({@link Couleur#Noir} ou {@link Couleur#Blanc})
     */
    public Dame(Couleur couleur) {
        super(couleur);
    }

    /**
     * Construit une dame sans couleur spécifiée.
     *
     * <p>
     * L’attribution de la couleur devra être effectuée ultérieurement.
     * </p>
     */
    public Dame() {
    }

    /**
     * Retourne le caractère représentant la dame selon sa couleur.
     *
     * @return {@code 'D'} si la dame est noire,
     *         {@code 'd'} si la dame est blanche
     */
    @Override
    public char getRepresentation() {
        if (this.getCouleur() == Couleur.Noir) {
            return 'D';
        } else {
            return 'd';
        }
    }
}
