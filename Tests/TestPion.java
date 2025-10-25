import static org.junit.Assert.assertEquals;

import cstjean.mobile.dames.Pion;
import org.junit.Test;

/**
 * Classe de test unitaire pour la classe {@link Pion}.
 *
 * <p>
 * Cette suite de tests a pour objectif de vérifier :
 * </p>
 * <ul>
 *     <li>La création d'un pion avec une couleur explicite
 *     (noir ou blanc) initialise correctement la couleur,</li>
 *     <li>La création d'un pion sans paramètre attribue la couleur par défaut (blanc),</li>
 *     <li>La représentation visuelle d'un pion correspond bien
 *     à sa couleur ({@code 'P'} pour un pion noir, {@code 'p'} pour un pion blanc).</li>
 * </ul>
 *
 * @author Samuel Cuerrier
 */
public class TestPion {

    /**
     * Vérifie la création et la représentation de pions
     * avec et sans couleur spécifiée.
     *
     * <p>
     * Étapes du test :
     * </p>
     * <ol>
     *     <li>Créer un pion noir et vérifier sa couleur et sa représentation.</li>
     *     <li>Créer un pion blanc et vérifier sa couleur et sa représentation.</li>
     *     <li>Créer un pion sans préciser la couleur et vérifier
     *     que la couleur par défaut est bien "blanc"
     *     ainsi que sa représentation.</li>
     * </ol>
     */
    @Test
    public void testCreer() {
        // Création de pions
        Pion pionNoir = new Pion(Pion.Couleur.Noir);
        Pion pionBlanc = new Pion(Pion.Couleur.Blanc);
        Pion pionSansCouleur = new Pion();

        // Vérifications
        assertEquals(Pion.Couleur.Noir, pionNoir.getCouleur());
        assertEquals(Pion.Couleur.Blanc, pionBlanc.getCouleur());
        assertEquals(Pion.Couleur.Blanc, pionSansCouleur.getCouleur()); // couleur par défaut
        assertEquals('P', pionNoir.getRepresentation());
        assertEquals('p', pionBlanc.getRepresentation());
        assertEquals('p', pionSansCouleur.getRepresentation());
    }
}
