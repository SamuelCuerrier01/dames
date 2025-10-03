import cstjean.mobile.dames.Damier;
import cstjean.mobile.dames.DeplacementPion;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestDeplacementPion {
    @Test
    public void testDeplacer() {
        Damier d = new Damier();
        d.initialiser();

        DeplacementPion dp = new DeplacementPion();
        assertTrue(dp.estDeplacementValide(d, 35, 30));
        assertFalse(dp.estDeplacementValide(d, 35, 25));
        assertFalse(dp.contientPionMillieu(d, 35, 25));
    }
}
