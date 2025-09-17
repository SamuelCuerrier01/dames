package cstjean.mobile.dames;

import java.util.List;

/**
 * Gère l’initialisation et l’affichage d’un {@link Damier}.
 *
 * <p>
 * Cette classe applique la configuration standard de départ
 * et affiche le damier sous forme de grille.
 * </p>
 */
public class DamierInitialiseur {

    /**
     * Initialise le damier dans sa configuration de départ standard,
     * puis l’affiche en console.
     *
     * <p>
     * - Les 20 premières cases reçoivent des pions noirs.<br>
     * - Les 10 cases centrales restent vides.<br>
     * - Les 20 dernières cases reçoivent des pions blancs.<br>
     * </p>
     *
     * <p>
     * L’affichage se fait sur une grille 10×10, où :
     * <ul>
     *     <li>{@code 'P'} = pion noir</li>
     *     <li>{@code 'p'} = pion blanc</li>
     *     <li>{@code '-'} = case vide ou non jouable</li>
     * </ul>
     * </p>
     *
     * @param damier le damier à initialiser et afficher
     *
     * @return le String de la représentation
     */
    public String initialiser(Damier damier) {
        StringBuilder representation = new StringBuilder();
        List<Pion> pions = damier.getPions();

        // Placer les pions noirs et blancs
        for (int i = 0; i < 20; i++) {
            damier.ajouterPion(i + 1, new Pion(Pion.Couleur.Noir));
        }
        // 10 cases centrales vides (indices 20-29) -> déjà null
        // 20 pions blancs
        for (int i = 30; i < 50; i++) {
            damier.ajouterPion(i + 1, new Pion(Pion.Couleur.Blanc));
        }

        // Afficher la grille
        int compteur = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i + j) % 2 == 1) {
                    Pion pion = damier.recupererPion(compteur);
                    if (pion == null) {
                        representation.append('-');
                    } else {
                        representation.append(pion.getRepresentation());
                    }
                    compteur++;
                } else {
                    representation.append('-');
                }
            }
            representation.append('\n');
        }

        return representation.toString();
    }
}
