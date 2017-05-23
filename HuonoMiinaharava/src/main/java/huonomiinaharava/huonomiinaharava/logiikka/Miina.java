/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonomiinaharava.huonomiinaharava.logiikka;

/**
 *
 * @author anttikoivurova
 */
public class Miina extends Ruutu {

    public Miina(int x, int y) {
        super(x, y);
    }

    @Override
    public int klikkaus() {
        this.setKlikattu(true);
        
        return 2;
    }
    
    @Override
    public String toString(){
        if(!this.isKlikattu()) return "o";
        
        return "x";
    }

    @Override
    public int tyyppi() {
        return 2;
    }
    
}
