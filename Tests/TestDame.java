import cstjean.mobile.dames.Dame;
import cstjean.mobile.dames.Pion;
import junit.framework.TestCase;

/**
 * Classe de test unitaire pour {@link Dame}.
 *
 * <p>
 * Cette classe vérifie le comportement fondamental des dames,
 * notamment :
 * </p>
 * <ul>
 *     <li>La création correcte d’une dame blanche (par défaut) et d’une dame noire,</li>
 *     <li>La couleur attribuée à chaque dame,</li>
 *     <li>La représentation correcte sous forme de caractère
 *     ({@code 'd'} pour une dame blanche et {@code 'D'} pour une dame noire).</li>
 * </ul>
 *
 * <p>
 * Les assertions utilisent JUnit afin de garantir que la classe {@link Dame}
 * se comporte comme attendu.
 * </p>
 *
 * @author Samuel Cuerrier, Vincent
 */
public class TestDame extends TestCase {

    /**
     * Vérifie la création de dames noires et blanches,
     * ainsi que leur représentation textuelle.
     */
    public void testCreer() {
        Dame dameBlanche = new Dame(Pion.Couleur.Blanc);
        Dame dameNoire = new Dame(Pion.Couleur.Noir);
        Dame dameSansCouleur = new Dame();

        assertEquals(Pion.Couleur.Blanc, dameBlanche.getCouleur());
        assertEquals(Pion.Couleur.Noir, dameNoire.getCouleur());
        assertEquals(Pion.Couleur.Blanc, dameSansCouleur.getCouleur());
        assertEquals('d', dameBlanche.getRepresentation());
        assertEquals('D', dameNoire.getRepresentation());
        assertEquals('d', dameSansCouleur.getRepresentation());
    }
}
