
package huonomiinaharava.huonomiinaharava.logiikka;

import java.util.ArrayList;
import java.util.Collections;

public class Ruudukko {

    private Ruutu[][] ruudukko;
    private int leveys;
    private int korkeus;
    private int miinat;
    private int liputetut;
    private int jaljella;
    private Ruudukkotila tila;
    private long alkuaika;

    /**
     * Luo yksittäisen miinaharavaruudukon.
     * @param leveys ruudukon leveys
     * @param korkeus ruudukon korkeus
     * @param miinat ruudukon miinat
     */
    public Ruudukko(int leveys, int korkeus, int miinat) {
        this.ruudukko = new Ruutu[leveys][korkeus];
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinat = miinat;
        this.tila = Ruudukkotila.PELITILA;
        this.jaljella = this.leveys * this.korkeus - this.miinat;
        this.liputetut = 0;
        kokoaTyhjaRuudukko();
    }

    /**
     * Alustaa ruudukon täyteen tyhjiä ruutuja.
     */
    public void kokoaTyhjaRuudukko() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                ruudukko[j][i] = new Ruutu(Ruututyyppi.TYHJA, j, i);
            }
        }
    }

    /**
     * Ensimmäisen klikkauksen jälkeen asettaa miinat tyhjään ruudukkoon.
     * @param klikLeveys ensimmäisen klikkauksen leveyskoordinaatti
     * @param klikKorkeus ensimmäisen klikkauksen korkeuskoordinaatti
     * @param alku ensimmäisen klikkauksen aika
     */
    public void kokoaRuudukko(int klikLeveys, int klikKorkeus, long alku) {
        alkuaika = alku;
        ArrayList<Integer> lista = new ArrayList();

        for (int i = 0; i < leveys * korkeus; i++) {
            lista.add(i);
        }

        Collections.shuffle(lista);
        int i = 0;
        int j = 0;
        while (j < miinat && i < leveys * korkeus) {
            int kohtaLeveys = lista.get(i) % leveys;
            int kohtaKorkeus = lista.get(i) / leveys;

            if (kohtaLeveys != klikLeveys || kohtaKorkeus != klikKorkeus) {
                ruudukko[kohtaLeveys][kohtaKorkeus] = new Ruutu(Ruututyyppi.MIINA, kohtaLeveys, kohtaKorkeus);
                j++;
                i++;
            } else {
                i++;
            }
        }

        ymparykset();
        klikkaus(klikLeveys, klikKorkeus);
    }

    /**
     * Liputtaa ruudun.
     * @param leveys liputettavan ruudun leveyskoordinaatti
     * @param korkeus liputettavan ruudun korkeuskoordinaatti 
     */
    public void liputus(int leveys, int korkeus) {
        Ruutu ruutu = ruudukko[leveys][korkeus];

        if (ruutu.isKlikattu()) {
            return;
        }

        if (ruutu.isLiputettu()) {
            liputetut--;
        } else {
            liputetut++;
        }

        ruutu.setLiputettu(!ruutu.isLiputettu());
    }

    /**
     * Hoitaa ruudukon ruudun klikkauksen riippuen ruudun tilasta; saattaa muuttaa ruudukon tilaa. 
     * @param leveys klikattavan ruudun leveyskoordinaatit
     * @param korkeus klikattavan ruudun korkeuskoordinaatit
     */
    public void klikkaus(int leveys, int korkeus) {
        Ruutu nykyinen = ruudukko[leveys][korkeus];

        nykyinen.klikkaus();

        if (nykyinen.getTyyppi().equals(Ruututyyppi.MIINA)) {
            tila = Ruudukkotila.HAVIOTILA;
        } else if (nykyinen.getYmparys() == 0) {
            laajennus(leveys, korkeus);
        } else {
            jaljella--;
        }

        if (jaljella == 0) {
            tila = Ruudukkotila.VOITTOTILA;
        }
    }

    /**
     * Automaattisesti aukaisee klikattavan ympärillä olevat ruudut, jos ruudun ympärillä on  nolla miinaa.
     * @param ruutuLeveys käsiteltävän ruudun leveyskoordinaatti
     * @param ruutuKorkeus käsiteltävän ruudun korkeuskoordinaatti 
     */
    public void laajennus(int ruutuLeveys, int ruutuKorkeus) {
        for (int i = ruutuKorkeus - 1; i <= ruutuKorkeus + 1; i++) {
            for (int j = ruutuLeveys - 1; j <= ruutuLeveys + 1; j++) {
                if (i >= 0 && i < this.korkeus && j >= 0 && j < this.leveys && !ruudukko[j][i].isKlikattu()) {
                    ruudukko[j][i].klikkaus();
                    if (ruudukko[j][i].isLiputettu()) {
                        continue;
                    } else if (ruudukko[j][i].getYmparys() == 0) {
                        laajennus(j, i);
                    } else {
                        jaljella--;
                    }
                }
            }
        }

        jaljella--;
    }

    /**
     * Käy läpi ruudukon ja kutsuu jokaiselle ruudulle metodia ympärys().
     */
    public void ymparykset() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                if (ruudukko[j][i].getTyyppi().equals(Ruututyyppi.TYHJA)) {
                    ymparys(j, i);
                }
            }
        }
    }

    /**
     * Selvittää käsiteltävää ruutua ympäröivien miinojen määrän.
     * @param ruutuLeveys käsiteltävän ruudun leveyskoordinaatti
     * @param ruutuKorkeus käsiteltävän ruudun korkeuskoordinaatti
     */
    public void ymparys(int ruutuLeveys, int ruutuKorkeus) {
        int montako = 0;

        for (int i = ruutuKorkeus - 1; i <= ruutuKorkeus + 1; i++) {
            for (int j = ruutuLeveys - 1; j <= ruutuLeveys + 1; j++) {
                if (i >= 0 && i < this.korkeus && j >= 0 && j < this.leveys) {
                    if (ruudukko[j][i].getTyyppi().equals(Ruututyyppi.MIINA)) {
                        montako++;
                    }
                }
            }
        }
        Ruutu ruutuhommeli = ruudukko[ruutuLeveys][ruutuKorkeus];
        ruutuhommeli.setYmparys(montako);
    }

    public void setAlkuaika(long alkuaika) {
        this.alkuaika = alkuaika;
    }

    public Ruutu[][] getRuudukko() {
        return ruudukko;
    }
    
    public Ruudukkotila getTila() {
        return tila;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public int getMiinat() {
        return miinat;
    }
}
