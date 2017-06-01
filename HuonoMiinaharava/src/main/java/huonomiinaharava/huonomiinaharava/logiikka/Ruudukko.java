/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonomiinaharava.huonomiinaharava.logiikka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author anttikoivurova
 */
public class Ruudukko {

    private Ruutu[][] ruudukko;
    private int leveys;
    private int korkeus;
    private int miinat;
    private int jaljella;
    private Ruudukkotila tila;
    private long alkuaika;

    public Ruudukko(int leveys, int korkeus, int miinat) {
        ruudukko = new Ruutu[leveys][korkeus];
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinat = miinat;
        tila = Ruudukkotila.PELITILA;
        jaljella = this.leveys * this.korkeus - miinat;
        alkuaika = System.nanoTime();
        kokoaTyhjaRuudukko();
    }

    public void kokoaTyhjaRuudukko() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                ruudukko[j][i] = new Ruutu(Ruututyyppi.TYHJA, j, i);
            }
        }
    }

    public String kokoaRuudukko(int klikLeveys, int klikKorkeus) {
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

        return klikkaus(klikLeveys, klikKorkeus);
    }

    public String klikkaus(int leveys, int korkeus) {
        Ruutu nykyinen = ruudukko[leveys][korkeus];
        nykyinen.klikkaus();

        if (nykyinen.getTyyppi().equals(Ruututyyppi.MIINA)) {
            tila = Ruudukkotila.HAVIOTILA;
            return this.havioViesti(leveys, korkeus);
        } else if (nykyinen.getYmparys() == 0) {
            laajennus(leveys, korkeus);
        } else {
            jaljella--;
        }

        if (jaljella == 0) {
            tila = Ruudukkotila.VOITTOTILA;
            return voittoViesti(System.nanoTime() - alkuaika);
        }

        return toString();
    }

    public void laajennus(int ruutuLeveys, int ruutuKorkeus) {
        for (int i = ruutuKorkeus - 1; i <= ruutuKorkeus + 1; i++) {
            for (int j = ruutuLeveys - 1; j <= ruutuLeveys + 1; j++) {
                if (i >= 0 && i < this.korkeus && j >= 0 && j < this.leveys && !ruudukko[j][i].isKlikattu()) {
                    ruudukko[j][i].klikkaus();

                    if (ruudukko[j][i].getYmparys() == 0) {
                        laajennus(j, i);
                    } else {
                        jaljella--;
                    }
                }
            }
        }

        jaljella--;
    }

    public void ymparykset() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                if (ruudukko[j][i].getTyyppi().equals(Ruututyyppi.TYHJA)) {
                    ymparys(j, i);
                }
            }
        }
    }

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

    public Ruudukkotila getTila() {
        return tila;
    }

    public void setTila(Ruudukkotila tila) {
        this.tila = tila;
    }

    public long getAlkuaika() {
        return alkuaika;
    }

    public void setAlkuaika(long alkuaika) {
        this.alkuaika = alkuaika;
    }
    
    

    public Ruutu[][] getRuudukko() {
        return ruudukko;
    }

    public void setRuudukko(Ruutu[][] ruudukko) {
        this.ruudukko = ruudukko;
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

    public int getMiinat() {
        return miinat;
    }

    public void setMiinat(int miinat) {
        this.miinat = miinat;
    }

    public int getJaljella() {
        return jaljella;
    }

    public void setJaljella(int jaljella) {
        this.jaljella = jaljella;
    }

    @Override
    public String toString() {
        String palaute = "";

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                palaute += ruudukko[j][i].toString();
            }

            palaute += "\n";
        }

        return palaute;
    }

    public String voittoViesti(long aika) {
        String palaute = "Hyvin tehty, jesjes!\nAikasi oli " + aika / 1000000000 + " sekuntia.\n";

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                if (ruudukko[j][i].getTyyppi().equals(Ruututyyppi.TYHJA)) {
                    palaute += ruudukko[j][i].toString();
                } else {
                    palaute += "L";
                }
            }

            palaute += "\n";
        }

        return palaute;
    }

    public String havioViesti(int osumaleveys, int osumakorkeus) {
        String palaute = "Hävisit. Harmi!\n";

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                if (j == osumaleveys && i == osumakorkeus) {
                    palaute += "X";
                } else if (ruudukko[j][i].getTyyppi().equals(Ruututyyppi.MIINA)){
                    palaute += "x";
                } else {
                    palaute += ruudukko[j][i].toString();
                }
            }

            palaute += "\n";
        }

        return palaute;
    }
}
