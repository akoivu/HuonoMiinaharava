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
public class MiinaRuutuTest {

    public MiinaRuutuTest() {
    }

    Ruutu miina;

    @Before
    public void setUp() {
        miina = new Ruutu(Ruututyyppi.MIINA, 3, 3);
    }

    @Test
    public void klikattaessaKlikkautuu() {
        miina.klikkaus();

        assertTrue(miina.isKlikattu());
    }

    @Test
    public void toStringOikeinEnnenKlikkausta() {
        assertEquals("o", miina.toString());
    }

    @Test
    public void toStringOikeinKlikkauksenJalkeen() {
        miina.klikkaus();

        assertEquals("x", miina.toString());
    }

    @Test
    public void oikeaTyyppi() {
        assertEquals(Ruututyyppi.MIINA, miina.getTyyppi());
    }

    @Test
    public void eiAluksiLippua() {
        assertFalse(miina.isLiputettu());
    }

    public void lippuKunLiputetaan() {
        miina.setLiputettu(true);
        assertTrue(miina.isLiputettu());
    }
}
