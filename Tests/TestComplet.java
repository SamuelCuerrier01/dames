import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * Suite de tests regroupant toutes les classes de test du projet.
 *
 * <p>Permet d'exécuter en une seule fois tous les tests unitaires
 * pour les classes {@code Dame}, {@code Damier}, {@code DeplacementPion}
 * et {@code Pion}.</p>
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
    TestDame.class,
    TestDamier.class,
    TestDeplacementPion.class,
    TestPion.class
})
public class TestComplet {}