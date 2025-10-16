import cstjean.mobile.dames.Damier;
import cstjean.mobile.dames.DeplacementPion;
import cstjean.mobile.dames.Pion;
import cstjean.mobile.dames.Dame;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestDeplacementPion {
    private Damier damier;
    private DeplacementPion deplacement;

    @Before
    public void setup() {
        damier = new Damier();
        damier.initialiser();
        deplacement = new DeplacementPion(damier);
    }

    @Test
    public void testDeplacementsSimplesPionBlanc() {
        int position = 41; // Un pion blanc du bas
        List<Integer> cases = deplacement.getToutesLesPossibilites(position);

        for (int pos : cases) {
            Pion p = damier.recupererPion(pos);
            assertTrue(p == null); // Les cases de déplacements simples doivent être vides
        }
    }

    @Test
    public void testDeplacementsSimplesPionNoir() {
        int position = 10; // Un pion noir du haut
        List<Integer> cases = deplacement.getToutesLesPossibilites(position);

        for (int pos : cases) {
            Pion p = damier.recupererPion(pos);
            assertTrue(p == null);
        }
    }

    @Test
    public void testDeplacementsSimplesDameBlanc() {
        Dame dame = new Dame(Pion.Couleur.Blanc);
        damier.ajouterPion(41, dame);
        List<Integer> cases = deplacement.getToutesLesPossibilites(41);

        for (int pos : cases) {
            Pion p = damier.recupererPion(pos);
            assertTrue(p == null);
        }
    }

    @Test
    public void testDeplacementsSimplesDameNoir() {
        Dame dame = new Dame(Pion.Couleur.Noir);
        damier.ajouterPion(10, dame);
        List<Integer> cases = deplacement.getToutesLesPossibilites(10);

        for (int pos : cases) {
            Pion p = damier.recupererPion(pos);
            assertTrue(p == null);
        }
    }

    @Test
    public void testAucuneDeplacementSiBloque() {
        Pion pionBlanc = new Pion(Pion.Couleur.Blanc);
        damier.ajouterPion(22, pionBlanc);
        damier.ajouterPion(17, new Pion(Pion.Couleur.Blanc));
        damier.ajouterPion(18, new Pion(Pion.Couleur.Blanc));

        List<Integer> cases = deplacement.getToutesLesPossibilites(22);
        assertTrue(cases.isEmpty()); // Aucune case disponible
    }

    @Test
    public void testAucuneCaptureSiPasAdverse() {
        Pion pionBlanc = new Pion(Pion.Couleur.Blanc);
        Pion pionBlanc2 = new Pion(Pion.Couleur.Blanc);
        damier.ajouterPion(22, pionBlanc);
        damier.ajouterPion(17, pionBlanc2);

        List<Integer> cases = deplacement.getToutesLesPossibilites(22);
        assertFalse(cases.contains(12)); // Pas de capture possible
    }
}
