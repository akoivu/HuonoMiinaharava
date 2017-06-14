/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonomiinaharava.huonomiinaharava.logiikka;


/**
 * Luokka tarjoaa yksittäisen miinaharavaruudun toiminnallisuuden.
 */
public class Ruutu {

    private Ruututyyppi tyyppi;
    private int leveys;
    private int korkeus;
    private boolean klikattu;
    private int ymparys;
    private boolean liputettu;

    /**
     * Muodostaa yksittäisen miinaharavaruudun.
     *
     * @param tyyppi Minkätyyppinen ruutu tulee olemaan
     * @param leveys Ruudun paikka leveyssuunnassa
     * @param korkeus Ruudun paikka korkeussuunnassa
     */
    public Ruutu(Ruututyyppi tyyppi, int leveys, int korkeus) {
        this.tyyppi = tyyppi;
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.klikattu = false;
        this.liputettu = false;
    }

    /**
     * Suorittaa yksittäisen ruudun klikkauksen.
     */
    public void klikkaus() {
        this.setKlikattu(true);
    }

    public Ruututyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(Ruututyyppi tyyppi) {
        this.tyyppi = tyyppi;
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
