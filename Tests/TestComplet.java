import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestDame.class,
        TestDamier.class,
        TestDeplacementPion.class,
        TestPion.class
})
public class TestComplet {}