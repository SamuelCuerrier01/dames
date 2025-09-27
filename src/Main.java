import cstjean.mobile.dames.Damier;
import cstjean.mobile.dames.Pion;

/**
 * Classe principale qui contient le point d’entrée du programme.
 *
 * <p>
 * Cette classe exécute la méthode {@link #main(String[])}
 * qui représente le point de départ de l’application.
 * </p>
 *
 * @author Samuel Cuerrier, Vincent
 */
public class Main {

    /**
     * Point d’entrée du programme.
     *
     * @param args les arguments passés en ligne de commande
     */
    public static void main(String[] args) {
        Damier damier = new Damier();
        damier.initialiser();

        System.out.println("=== Début des tests de déplacements (alternance naturelle des joueurs) ===");

        // Séquence de tests choisis pour être valides sur la position initiale et pour
        // préserver l'alternance naturelle (Blanc -> Noir -> Blanc -> ...)
        int[][] tests = {
                {31, 26}, // Blanc
                {20, 24}, // Noir  (remplace 10->15 qui serait invalide car 15 est occupée)
                {35, 30}, // Blanc
                {24, 35}
        };

        for (int i = 0; i < tests.length; i++) {
            int posInitial = tests[i][0];
            int posFinal = tests[i][1];

            System.out.println("\nTest " + (i + 1) + " : tenter " + posInitial + " -> " + posFinal);
            try {
                damier.deplacer(posInitial, posFinal);
            } catch (Exception e) {
                // Si une exception non prévue survient, on l'affiche pour debug
                System.out.println("⚠️ Exception inattendue : " + e);
            }
            // note : damier.deplacer affiche le damier (via DamierAfficher) et
            // n'incrémente le tour que si le déplacement a été effectué.
        }

        System.out.println("\n=== Fin des tests ===");
        System.out.println("Remarque : si tu veux tester les cas d'erreur (ex: destination occupée, mouvement invalide),");
        System.out.println("il faudra adapter la suite en gardant à l'esprit que l'échec d'un coup NE CHANGE PAS le tour.");
    }
}
