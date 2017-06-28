/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonomiinaharava.huonomiinaharava.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anttikoivurova
 */
public class RuudukkoTest {

    public RuudukkoTest() {
    }

    Ruudukko normiRuudukko;
    Ruudukko taysRuudukko;
    Ruudukko vakioRuudukkoPieni;
    Ruudukko vakioRuudukkoIso;

    @Before
    public void setUp() {
        normiRuudukko = new Ruudukko(4, 7, 24);
        taysRuudukko = new Ruudukko(4, 7, 27);
        vakioRuudukkoPieni = new Ruudukko(5, 3, 1);
        vakioRuudukkoPieni.kokoaTyhjaRuudukko();
        vakioRuudukkoPieni.getRuudukko()[2][2] = new Ruutu(Ruututyyppi.MIINA, 2, 2);
        vakioRuudukkoPieni.ymparykset();

        vakioRuudukkoIso = new Ruudukko(5, 5, 5);
        vakioRuudukkoIso.kokoaTyhjaRuudukko();
        vakioRuudukkoIso.getRuudukko()[4][0] = new Ruutu(Ruututyyppi.MIINA, 4, 0);
        vakioRuudukkoIso.getRuudukko()[1][2] = new Ruutu(Ruututyyppi.MIINA, 1, 2);
        vakioRuudukkoIso.getRuudukko()[1][3] = new Ruutu(Ruututyyppi.MIINA, 1, 3);
        vakioRuudukkoIso.getRuudukko()[2][3] = new Ruutu(Ruututyyppi.MIINA, 2, 3);
        vakioRuudukkoIso.getRuudukko()[2][4] = new Ruutu(Ruututyyppi.MIINA, 2, 4);
        vakioRuudukkoIso.ymparykset();
    }

    @Test
    public void oikeaYmparys() {
        Ruutu ruutu = vakioRuudukkoIso.getRuudukko()[2][2];

        assertEquals(3, ruutu.getYmparys());
    }

    @Test
    public void liputusToimiiTyhjalla() {
        vakioRuudukkoIso.liputus(2, 2);

        Ruutu ruutu = vakioRuudukkoIso.getRuudukko()[2][2];

        assertTrue(ruutu.isLiputettu());
    }

    @Test
    public void lipunPoistoToimiiTyhjalla() {
        vakioRuudukkoIso.liputus(2, 2);
        vakioRuudukkoIso.liputus(2, 2);
        Ruutu ruutu = vakioRuudukkoIso.getRuudukko()[2][2];

        assertFalse(ruutu.isLiputettu());
    }

    @Test
    public void liputusToimiiMiinalla() {
        vakioRuudukkoIso.liputus(2, 3);

        Ruutu ruutu = vakioRuudukkoIso.getRuudukko()[2][3];

        assertTrue(ruutu.isLiputettu());
    }

    @Test
    public void lipunPoistoToimiiMiinalla() {
        vakioRuudukkoIso.liputus(2, 3);
        vakioRuudukkoIso.liputus(2, 3);
        Ruutu ruutu = vakioRuudukkoIso.getRuudukko()[2][3];

        assertFalse(ruutu.isLiputettu());
    }

    @Test
    public void klikattuRuutuEiLiputu() {
        vakioRuudukkoIso.klikkaus(0, 0);
        vakioRuudukkoIso.liputus(0, 0);
        assertFalse(vakioRuudukkoIso.getRuudukko()[0][0].isLiputettu());
    }

    @Test
    public void ruudukkoAluksiPeliTilassa() {
        assertEquals(Ruudukkotila.PELITILA, vakioRuudukkoIso.getTila());
    }

    @Test
    public void tyhjatTyhjennettynaVoittoTila() {
        taysRuudukko.kokoaRuudukko(0, 0);
        assertEquals(Ruudukkotila.VOITTOTILA, taysRuudukko.getTila());
    }

    @Test
    public void miinaanOsuttuaHavioTila() {
        vakioRuudukkoIso.klikkaus(4, 0);
        assertEquals(Ruudukkotila.HAVIOTILA, vakioRuudukkoIso.getTila());
    }

    @Test
    public void laajennusToimii() {
        vakioRuudukkoIso.klikkaus(4, 4);

        assertEquals(1, vakioRuudukkoIso.getRuudukko()[4][1].getYmparys());
    }

    @Test
    public void laajennusToimiiKunLiputettu() {
        vakioRuudukkoIso.liputus(4, 3);
        vakioRuudukkoIso.klikkaus(4, 4);
        assertFalse(vakioRuudukkoIso.getRuudukko()[4][3].isKlikattu());
    }

    @Test
    public void ruudukkoOikeanLevyinen() {
        assertEquals(5, vakioRuudukkoPieni.getLeveys());
    }

    @Test
    public void ruudukkoOikeanKorkuinen() {
        assertEquals(3, vakioRuudukkoPieni.getKorkeus());
    }

    @Test
    public void ruudukossaOikeaMaaraMiinoja() {
        assertEquals(5, vakioRuudukkoIso.getMiinat());
    }
}
