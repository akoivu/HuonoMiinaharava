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
public class Ruutu {

    Ruututyyppi tyyppi;
    private int leveys;
    private int korkeus;
    private boolean klikattu;
    private int ymparys;
    private boolean liputettu;

    public Ruutu(Ruututyyppi tyyppi, int leveys, int korkeus) {
        this.tyyppi = tyyppi;
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.klikattu = false;
        this.liputettu = false;
    }

    public void klikkaus() {
        this.setKlikattu(true);
    }

    public String toString() {
        switch (tyyppi) {
            case MIINA:
                if (klikattu) {
                    return "x";
                }
            case TYHJA:
                if (klikattu) {
                    return "" + ymparys;
                }
            default:
                return "o";
        }
    }

    public Ruututyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(Ruututyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public int getLeveys() {
        return leveys;
    }

    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }

    public boolean isKlikattu() {
        return klikattu;
    }

    public void setKlikattu(boolean klikattu) {
        this.klikattu = klikattu;
    }

    public int getYmparys() {
        return ymparys;
    }

    public void setYmparys(int ymparys) {
        this.ymparys = ymparys;
    }

    public boolean isLiputettu() {
        return liputettu;
    }

    public void setLiputettu(boolean liputettu) {
        this.liputettu = liputettu;
    }

}
