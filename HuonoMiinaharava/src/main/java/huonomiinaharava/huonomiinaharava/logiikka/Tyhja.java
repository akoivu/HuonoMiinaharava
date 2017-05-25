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
public class Tyhja extends Ruutu {

    private int ymparys;

    public Tyhja(int x, int y) {
        super(x, y);
        ymparys = 0;
    }

    public int getYmparys() {
        return ymparys;
    }

    public void setYmparys(int ymparys) {
        this.ymparys = ymparys;
    }

    @Override
    public int klikkaus() {
        this.setKlikattu(true);
        return ymparys;
    }

    @Override
    public String toString() {
        if (!this.isKlikattu()) {
            return "o";
        }

        return "" + ymparys;
    }

    @Override
    public int tyyppi() {
        if (this.isKlikattu()) {
            return 0;
        }
        return 1;
    }

}
