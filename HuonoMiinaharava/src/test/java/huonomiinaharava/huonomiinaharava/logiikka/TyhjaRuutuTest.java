/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonomiinaharava.huonomiinaharava.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    public void oikeaSyoteEnnenKlikkausta() {
        assertEquals("o", tyhja.toString());
    }

    @Test
    public void oikeaSyoteKlikkauksenJalkeen() {
        tyhja.klikkaus();
        assertEquals("3", tyhja.toString());
    }

    @Test
    public void oikeaTyyppi() {
        assertEquals(Ruututyyppi.TYHJA, tyhja.getTyyppi());
    }
}
