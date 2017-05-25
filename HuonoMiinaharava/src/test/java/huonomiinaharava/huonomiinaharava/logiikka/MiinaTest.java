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
public class MiinaTest {
    
    public MiinaTest() {
    }
    
    Miina miina;
    
    @Before
    public void setUp() {
        miina = new Miina(3, 3);
    }

   @Test
   public void klikattaessaKlikkautuu(){
       miina.klikkaus();
       
       assertTrue(miina.isKlikattu());
   }
   
   @Test
   public void toStringOikeinEnnenKlikkausta(){
       assertEquals("o", miina.toString());
   }
   
   @Test
   public void toStringOikeinKlikkauksenJalkeen(){
       miina.klikkaus();
       
       assertEquals("x", miina.toString());
   }
   
   @Test
   public void klikkausPalauttaaOikeanArvon(){
       assertEquals(-1, miina.klikkaus());
   }
   
   @Test
   public void oikeaTyyppi(){
       assertEquals(2, miina.tyyppi());
   }
}
