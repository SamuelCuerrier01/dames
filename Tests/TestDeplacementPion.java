import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import cstjean.mobile.dames.Dame;
import cstjean.mobile.dames.Damier;
import cstjean.mobile.dames.DeplacementPion;
import cstjean.mobile.dames.Pion;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test unitaire pour la classe {@link DeplacementPion}.
 *
 * <p>Vérifie les différents comportements liés aux déplacements
 * et aux captures des pions et des dames sur le damier.</p>
 *
 * <p>Les tests couvrent :
 * <ul>
 *     <li>Les déplacements simples des pions blancs et noirs,</li>
 *     <li>Les déplacements et captures possibles des dames,</li>
 *     <li>Les situations où un pion est bloqué,</li>
 *     <li>Les positions invalides et les coordonnées incorrectes.</li>
 * </ul>
 * </p>
 */
public class TestDeplacementPion {
    /** Le damier sur lequel les tests de déplacement sont effectués. */
    private Damier damier;

    /** L’objet responsable de calculer les déplacements et captures possibles des pions. */
    private DeplacementPion deplacement;

    /**
     * Initialise un nouveau damier avant chaque test.
     *
     * <p>Cette méthode crée un damier vide, l’initialise avec les pions
     * de départ, puis instancie l’objet {@link DeplacementPion}
     * qui sera utilisé pour vérifier les mouvements possibles.</p>
     */
    @Before
    public void setup() {
        damier = new Damier();
        deplacement = new DeplacementPion(damier);
    }

    @Test
    public void testDeplacementsSimplesPionBlanc() {
        int position = 41;
        List<Integer> cases = deplacement.getToutesLesPossibilites(position);

        for (int pos : cases) {
            Pion p = damier.recupererPion(pos);
            assertNull(p);
        }
    }

    @Test
    public void testDeplacementsSimplesPionNoir() {
        int position = 10;
        List<Integer> cases = deplacement.getToutesLesPossibilites(position);

        for (int pos : cases) {
            Pion p = damier.recupererPion(pos);
            assertNull(p);
        }
    }

    @Test
    public void testDeplacementsSimplesDameBlanc() {
        Dame dame = new Dame(Pion.Couleur.Blanc);
        damier.ajouterPion(41, dame);
        List<Integer> cases = deplacement.getToutesLesPossibilites(41);

        for (int pos : cases) {
            Pion p = damier.recupererPion(pos);
            assertNull(p);
        }
    }

    @Test
    public void testDeplacementsSimplesDameNoir() {
        Dame dame = new Dame(Pion.Couleur.Noir);
        damier.ajouterPion(10, dame);
        List<Integer> cases = deplacement.getToutesLesPossibilites(10);

        for (int pos : cases) {
            Pion p = damier.recupererPion(pos);
            assertNull(p);
        }
    }

    @Test
    public void testAucuneDeplacementSiBloque() {
        Pion pionBlanc = new Pion(Pion.Couleur.Blanc);
        damier.ajouterPion(22, pionBlanc);
        damier.ajouterPion(17, new Pion(Pion.Couleur.Blanc));
        damier.ajouterPion(18, new Pion(Pion.Couleur.Blanc));

        List<Integer> cases = deplacement.getToutesLesPossibilites(22);
        assertTrue(cases.isEmpty());
    }

    @Test
    public void testAucuneCaptureSiPasAdverse() {
        Pion pionBlanc = new Pion(Pion.Couleur.Blanc);
        Pion pionBlanc2 = new Pion(Pion.Couleur.Blanc);
        damier.ajouterPion(22, pionBlanc);
        damier.ajouterPion(17, pionBlanc2);

        List<Integer> cases = deplacement.getToutesLesPossibilites(22);
        assertFalse(cases.contains(12));
    }

    @Test
    public void testDeplacementSiDame() {
        damier.ajouterPion(21, new Dame());
        assertNull(damier.recupererPion(27));
        damier.deplacer(21, 27);
        assertEquals('d', damier.recupererPion(27).getRepresentation());
    }

    @Test
    public void testConvertirPositionInvalide() {
        int[] coord = deplacement.convertirPosition(999);
        assertEquals(-1, coord[0]);
        assertEquals(-1, coord[1]);
    }

    @Test
    public void testPositionDepuisCoordInvalide() {
        int pos = deplacement.positionDepuisCoord(0, 0);
        assertEquals(-1, pos);
    }

    @Test
    public void testCapturePossiblePourDame() {
        Dame dameBlanche = new Dame(Pion.Couleur.Blanc);
        Pion noir = new Pion(Pion.Couleur.Noir);
        damier.ajouterPion(32, dameBlanche);
        damier.ajouterPion(27, noir);
        List<Integer> cases = deplacement.getToutesLesPossibilites(32);

        boolean capturePossible = false;
        for (int pos : cases) {
            if (pos > 27) {
                capturePossible = true;
                break;
            }
        }
        assertTrue(capturePossible);
    }

    @Test
    public void testGetPosCapturables() {
        Pion pionBlanc = new Pion(Pion.Couleur.Blanc);
        Pion pionNoir = new Pion(Pion.Couleur.Noir);
        damier.ajouterPion(32, pionBlanc);
        damier.ajouterPion(27, pionNoir);
        deplacement.getToutesLesPossibilites(32);
        assertTrue(deplacement.getPosCapturables().contains(27));
        assertFalse(deplacement.getPosCapturables().contains(32));
    }

    @Test
    public void testPositionDepuisCoordRetourFinalCobertura() {
        DeplacementPion dp = new DeplacementPion(new Damier());
        assertEquals(-1, dp.positionDepuisCoord(0, 0));
    }

    @Test
    public void testPositionDepuisCoordBornesInvalide() {
        DeplacementPion dp = new DeplacementPion(new Damier());
        assertEquals(-1, dp.positionDepuisCoord(-1, 5));
    }

    @Test
    public void testPositionDepuisCoordBornesValide() {

    }
}
