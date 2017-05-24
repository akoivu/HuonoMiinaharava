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
    
    @Before
    public void setUp() {
        normiRuudukko = new Ruudukko(4, 7, 24);
        taysRuudukko = new Ruudukko(4, 7, 27);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void lautaOnOikeanMuotoinenAluksi(){
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
    public void ekaKlikkausReunassa(){
        taysRuudukko.kokoaRuudukko(0, 2);
        
        assertEquals(taysRuudukko.getRuudukko()[0][2].toString(), "5");
    }
    
    @Test
    public void ekaKlikkausKeskella(){
        taysRuudukko.kokoaRuudukko(1, 2);
        
        assertEquals(taysRuudukko.getRuudukko()[1][2].toString(), "8");
    }
    
    @Test
    public void ekaKlikkausNurkassa(){
        taysRuudukko.kokoaRuudukko(0, 0);
        
        assertEquals(taysRuudukko.getRuudukko()[0][0].toString(), "3");
    }
}
