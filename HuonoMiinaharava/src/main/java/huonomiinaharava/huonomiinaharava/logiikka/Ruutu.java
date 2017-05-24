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
    
    private int leveys;
    private int korkeus;
    private boolean klikattu;
    private boolean lippu;

    public Ruutu(int x, int y) {
        this.leveys = x;
        this.korkeus = y;
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
        return leveys;
    }

    public void setX(int x) {
        this.leveys = x;
    }

    public int getY() {
        return korkeus;
    }

    public void setY(int y) {
        this.korkeus = y;
    }
    
    
}
