import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import cstjean.mobile.dames.Dame;
import cstjean.mobile.dames.Damier;
import cstjean.mobile.dames.DeplacementPion;
import cstjean.mobile.dames.Pion;
import org.junit.Test;

/**
 * Classe de test unitaire pour la classe {@link Damier}.
 *
 * <p>
 * Cette suite de tests a pour objectif de vérifier le comportement
 * fondamental d'un damier de jeu de dames, en particulier :
 * </p>
 * <ul>
 *     <li>Qu'un damier nouvellement créé est vide,</li>
 *     <li>Qu'ajouter un pion augmente bien le nombre de pions présents,</li>
 *     <li>Que chaque pion ajouté est accessible à sa position précise,</li>
 *     <li>Que la méthode {@code initialiser()} place correctement
 *     les pions noirs, blancs et les cases vides.</li>
 * </ul>
 *
 * @author Samuel Cuerrier
 */
public class TestDamier {

    /**
     * Vérifie le comportement d'un {@link Damier} lors de l'ajout de pions.
     *
     * <p>
     * Étapes du test :
     * </p>
     * <ol>
     *     <li>Créer un pion blanc et un pion noir.</li>
     *     <li>Créer un damier vide et vérifier qu'il contient zéro pion.</li>
     *     <li>Ajouter le pion blanc à la position 10 et vérifier sa présence.</li>
     *     <li>Ajouter le pion noir à la position 20 et vérifier sa présence.</li>
     *     <li>Confirmer que le nombre total de pions est correctement mis à jour.</li>
     * </ol>
     */
    @Test
    public void testCreer() {
        Pion pionBlanc = new Pion(Pion.Couleur.Blanc);
        Damier damier = new Damier();

        // Vérifie que le damier est vide au départ
        assertEquals(0, damier.grandeur());

        // Ajout d'un pion blanc
        damier.ajouterPion(10, pionBlanc);
        assertEquals(1, damier.grandeur());
        assertEquals(pionBlanc, damier.recupererPion(10));

        // Ajout d'un pion noir
        Pion pionNoir = new Pion(Pion.Couleur.Noir);
        damier.ajouterPion(20, pionNoir);
        assertEquals(2, damier.grandeur());
        assertEquals(pionNoir, damier.recupererPion(20));
    }

    /**
     * Vérifie le comportement de la méthode {@link Damier#initialiser()}.
     *
     * <p>
     * Étapes du test :
     * </p>
     * <ol>
     *     <li>Créer un damier vide et vérifier qu'il contient zéro pion.</li>
     *     <li>Appeler {@code initialiser()} et vérifier que le damier
     *     contient désormais 40 pions.</li>
     *     <li>Confirmer que les 20 premières cases sont occupées
     *     par des pions noirs ({@code 'P'}).</li>
     *     <li>Vérifier que les cases centrales (indices 21 à 29) restent vides.</li>
     *     <li>Confirmer que les 20 dernières cases sont occupées
     *     par des pions blancs ({@code 'p'}).</li>
     * </ol>
     */
    @Test
    public void testInitialiser() {
        Damier damier = new Damier();
        assertEquals(0, damier.grandeur());

        damier.initialiser();
        assertEquals(40, damier.grandeur());

        // Vérifie les pions noirs
        for (int i = 0; i < 20; i++) {
            assertEquals('P', damier.getPions().get(i).getRepresentation());
        }

        // Vérifie les cases centrales
        for (int i = 21; i < 30; i++) {
            assertNull(damier.getPions().get(i));
        }

        // Vérifie les pions blancs
        for (int i = 31; i < 50; i++) {
            assertEquals('p', damier.getPions().get(i).getRepresentation());
        }
    }

    @Test
    public void testDeplacer() {
        // initialiser le damier
        Damier damier = new Damier();
        damier.initialiser();

        // test déplacement du mauvais pion
        assertEquals(Pion.Couleur.Blanc, damier.getJoueurCourant());
        assertNull(damier.recupererPion(25));
        damier.deplacer(20, 25);
        assertNull(damier.recupererPion(25));
        assertEquals(Pion.Couleur.Blanc, damier.getJoueurCourant());

        assertEquals(Pion.Couleur.Blanc, damier.getJoueurCourant());
        assertNull(damier.recupererPion(30));
        damier.deplacer(35, 30);
        assertNull(damier.recupererPion(35));
        assertEquals(Pion.Couleur.Noir, damier.getJoueurCourant());

        assertEquals(Pion.Couleur.Noir, damier.getJoueurCourant());
        assertNull(damier.recupererPion(25));
        damier.deplacer(20, 25);
        assertEquals(Pion.Couleur.Blanc, damier.getJoueurCourant());
    }

    @Test
    public void testAffichageDameBlanche() {
        Damier damier = new Damier();
        damier.initialiser();
        damier.deplacer(35, 30);
        damier.deplacer(20, 24);
        damier.deplacer(31, 26);
        damier.deplacer(15, 20);
        damier.deplacer(26, 21);
        damier.deplacer(24, 29);
        damier.deplacer(30, 25);
        damier.deplacer(10, 15);
        damier.deplacer(36, 31);
        damier.deplacer(20, 24);
        damier.deplacer(32, 27);
        damier.deplacer(15, 20);
        damier.deplacer(37, 32);
        damier.deplacer(5, 10);
        damier.deplacer(34, 23);
        damier.deplacer(10, 15);
        damier.deplacer(41, 36);
        damier.deplacer(24, 29);
        damier.deplacer(27, 22);
        damier.deplacer(27, 22);
        damier.deplacer(16, 27);
        damier.deplacer(33, 24);
        damier.deplacer(20, 29);
        damier.deplacer(38, 33);
        damier.deplacer(17, 21);
        damier.deplacer(42, 37);
        damier.deplacer(15, 20);
        damier.deplacer(40, 35);
        damier.deplacer(20, 24);
        damier.deplacer(25, 20);
        damier.deplacer(27, 38);
        damier.deplacer(20, 15);
        damier.deplacer(21, 27);
        damier.deplacer(15, 10);
        damier.deplacer(27, 32);
        damier.deplacer(10, 5);
        damier.deplacer(11, 16);
        damier.deplacer(5, 10);
        damier.deplacer(24, 30);
        damier.deplacer(10, 15);
        damier.deplacer(6, 11);
        damier.deplacer(33, 28);
        damier.deplacer(16, 21);
        damier.deplacer(15, 33);
    }

    @Test
    public void testAffichageDameNoire() {
        Damier damier = new Damier();
        damier.ajouterPion(47, new Dame(Pion.Couleur.Noir));
        assertEquals('D', damier.recupererPion(47).getRepresentation());
    }

    @Test
    public void testCreerDames() {
        Damier damier = new Damier();
        damier.initialiser();
    }

    @Test
    public void testEstCapturable() {
        Damier damier = new Damier();
        damier.initialiser();
        DeplacementPion dp = new DeplacementPion(damier);
        damier.deplacer(35, 30);
        damier.deplacer(20, 24);
        damier.deplacer(28, 22);
    }

    @Test
    public void testCapturePionBlancVersLaDroite() {
        Damier damier = new Damier();
        for (int i = 1; i <= 50; i++) {
            damier.ajouterPion(i, null);
        }
        Pion blanc = new Pion(Pion.Couleur.Blanc);
        Pion noir = new Pion(Pion.Couleur.Noir);
        damier.ajouterPion(22, blanc);
        damier.ajouterPion(18, noir);
        damier.deplacer(22, 13);
        assertNull(damier.recupererPion(18));
        assertEquals(blanc, damier.recupererPion(13));
    }

    @Test
    public void testPromotionBlancEnDame() {
        Damier damier = new Damier();
        for (int i = 1; i <= 50; i++) {
            damier.ajouterPion(i, null);
        }
        damier.ajouterPion(44, new Pion(Pion.Couleur.Noir));
        damier.ajouterPion(9, new Pion());
        assertEquals('P', damier.recupererPion(44).getRepresentation());
        assertEquals('p', damier.recupererPion(9).getRepresentation());
        damier.deplacer(9, 3);
        damier.deplacer(44, 50);
        assertEquals('D', damier.recupererPion(50).getRepresentation());
        assertEquals('d', damier.recupererPion(3).getRepresentation());
    }

    @Test
    public void testCapturePionBlanc_HorsLigne1_Gauche() {
        Damier damier = new Damier();
        for (int i = 1; i <= 50; i++) {
            damier.ajouterPion(i, null);
        }
        Pion blanc = new Pion(Pion.Couleur.Blanc);
        Pion noir = new Pion(Pion.Couleur.Noir);
        damier.ajouterPion(26, blanc);
        damier.ajouterPion(21, noir);
        damier.deplacer(26, 17);
        assertNull(damier.recupererPion(21));
        assertEquals(blanc, damier.recupererPion(17));
    }

    @Test
    public void testCaptureParDame_Simple() {
        Damier damier = new Damier();
        for (int i = 1; i <= 50; i++) {
            damier.ajouterPion(i, null);
        }
        Dame dame = new Dame(Pion.Couleur.Blanc);
        damier.ajouterPion(32, dame);
        damier.deplacer(32, 23);
        assertEquals(dame, damier.recupererPion(23));
    }

    @Test
    public void testDameDamier() {
        Damier damier = new Damier();
        DeplacementPion dp = new DeplacementPion(damier);
        Dame dameBlanc = new Dame(Pion.Couleur.Blanc);
        damier.ajouterPion(32, dameBlanc);
        assertEquals(dameBlanc, damier.recupererPion(32));
        assertEquals(38, damier.capture(32, 43, dp));
        assertEquals(37, damier.capture(32, 41, dp));
        assertEquals(27, damier.capture(32, 21, dp));
        assertEquals(28, damier.capture(32, 23, dp));
    }

    @Test
    public void testPionNoirCapture() {
        Damier damier = new Damier();
        DeplacementPion dp = new DeplacementPion(damier);
        Pion noir = new Pion(Pion.Couleur.Noir);
        Pion blanc = new Pion();
        damier.ajouterPion(28, blanc);
        damier.ajouterPion(23, noir);
        assertEquals(28, damier.capture(23, 32, dp));
        assertEquals(29, damier.capture(23, 34, dp));
        assertEquals(22, damier.capture(28, 17, dp));
        assertEquals(-1, damier.capture(28, 38, dp));

    }

    @Test
    public void testDeplacerThrowsException() {
        Damier damier = new Damier();

        assertThrows(Exception.class, () -> {
            damier.deplacer(60, 1);
        });
    }

}