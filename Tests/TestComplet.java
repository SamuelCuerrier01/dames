import junit.framework.TestSuite;

/**
 * La classe {@code TestComplet} regroupe l'ensemble des tests unitaires
 * du projet dans une suite de tests commune.
 *
 * <p>Elle permet d'exécuter simultanément les tests de plusieurs classes
 * de test JUnit (par exemple {@link TestDamier} et {@link TestPion}).</p>
 *
 *
 * @author Samuel Cuerrier, Vincent
 */
public class TestComplet {

    /**
     * Crée et retourne une suite de tests contenant toutes les classes
     * de test à exécuter.
     *
     * @return la suite de tests composée de {@link TestDamier} et {@link TestPion}.
     */
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestDamier.class);
        suite.addTestSuite(TestPion.class);
        suite.addTestSuite(TestDame.class);
        return suite;
    }
}
