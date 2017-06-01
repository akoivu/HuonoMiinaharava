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
        vakioRuudukkoPieni = new Ruudukko(3, 3, 1);
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
    public void lautaOnOikeanMuotoinenAluksi() {
        String testi = "";

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                testi += "o";
            }

            testi += "\n";
        }

        assertEquals(testi, normiRuudukko.toString());
    }

    @Test
    public void ekaKlikkausReunassa() {
        taysRuudukko.kokoaRuudukko(0, 2);

        assertEquals(taysRuudukko.getRuudukko()[0][2].toString(), "5");
    }

    @Test
    public void ekaKlikkausKeskella() {
        taysRuudukko.kokoaRuudukko(1, 2);

        assertEquals(taysRuudukko.getRuudukko()[1][2].toString(), "8");
    }

    @Test
    public void ekaKlikkausNurkassa() {
        taysRuudukko.kokoaRuudukko(0, 0);

        assertEquals(taysRuudukko.getRuudukko()[0][0].toString(), "3");
    }

    @Test
    public void laajennusToimii() {
        vakioRuudukkoPieni.klikkaus(0, 0);

        String vertailu = "000\n"
                + "011\n"
                + "01o\n";

        assertEquals(vertailu, vakioRuudukkoPieni.toString());
    }

    @Test
    public void oikeaYmparys() {
        Ruutu ruutu = vakioRuudukkoIso.getRuudukko()[2][2];

        assertEquals(3, ruutu.getYmparys());
    }
}
