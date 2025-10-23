import cstjean.mobile.dames.Damier;
import cstjean.mobile.dames.DeplacementPion;
import cstjean.mobile.dames.Pion;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

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
 * <p>
 * Ces tests utilisent l'API JUnit 3, via l'héritage de {@link TestCase}.
 * </p>
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
    public void testAffichageDame() {
        Damier damier = new Damier();
        damier.initialiser();
        damier.deplacer(35,30);
        damier.deplacer(20,24);
        damier.deplacer(31,26);
        damier.deplacer(15,20);
        damier.deplacer(26,21);
        damier.deplacer(24,29);
        damier.deplacer(30,25);
        damier.deplacer(10,15);
        damier.deplacer(36,31);
        damier.deplacer(20,24);
        damier.deplacer(32,27);
        damier.deplacer(15,20);
        damier.deplacer(37,32);
        damier.deplacer(5,10);
        damier.deplacer(34,23);
        damier.deplacer(10,15);
        damier.deplacer(41,36);
        damier.deplacer(24,29);
        damier.deplacer(27,22);
        damier.deplacer(27,22);
        damier.deplacer(16,27);
        damier.deplacer(33,24);
        damier.deplacer(20,29);
        damier.deplacer(38,33);
        damier.deplacer(17,21);
        damier.deplacer(42,37);
        damier.deplacer(15,20);
        damier.deplacer(40,35);
        damier.deplacer(20,24);
        damier.deplacer(25,20);
        damier.deplacer(27,38);
        damier.deplacer(20,15);
        damier.deplacer(21,27);
        damier.deplacer(15,10);
        damier.deplacer(27,32);
        damier.deplacer(10,5);
        damier.deplacer(11,16);
        damier.deplacer(5,10);

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
        damier.deplacer(28,22);
    }
}