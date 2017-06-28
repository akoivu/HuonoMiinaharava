package huonomiinaharava.huonomiinaharava.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anttikoivurova
 */
public class TyhjaRuutuTest {

    public TyhjaRuutuTest() {
    }

    Ruutu tyhja;

    @Before
    public void setUp() {
        tyhja = new Ruutu(Ruututyyppi.TYHJA, 3, 3);
        tyhja.setYmparys(3);
    }

    @Test
    public void aluksiKlikkaamatta() {
        assertFalse(tyhja.isKlikattu());
    }

    @Test
    public void klikattaessaKlikkautuu() {
        tyhja.klikkaus();

        assertTrue(tyhja.isKlikattu());
    }

    @Test
    public void oikeaTyyppi() {
        assertEquals(Ruututyyppi.TYHJA, tyhja.getTyyppi());
    }
}
