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
public abstract class Ruutu {
    
    private int x;
    private int y;
    private boolean klikattu;
    private boolean lippu;

    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
        this.klikattu = false;
    }
    
    public abstract int klikkaus();
    
    public abstract int tyyppi();

    public boolean isKlikattu() {
        return klikattu;
    }

    public void setKlikattu(boolean klikattu) {
        this.klikattu = klikattu;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
