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
    private int liputetut;
    private int jaljella;
    private Ruudukkotila tila;
    private long alkuaika;

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

    public void kokoaTyhjaRuudukko() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                ruudukko[j][i] = new Ruutu(Ruututyyppi.TYHJA, j, i);
            }
        }
    }

    public String kokoaRuudukko(int klikLeveys, int klikKorkeus, long alku) {
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
        return klikkaus(klikLeveys, klikKorkeus);
    }

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

    public String klikkaus(int leveys, int korkeus) {
        Ruutu nykyinen = ruudukko[leveys][korkeus];

        if (nykyinen.isLiputettu()) {
            return toString();
        }

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

        return toString(System.nanoTime() - alkuaika, leveys, korkeus);
    }

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

    public void setAlkuaika(long alkuaika) {
        this.alkuaika = alkuaika;
    }

    public Ruutu[][] getRuudukko() {
        return ruudukko;
    }

    public Ruudukkotila getTila() {
        return tila;
    }

    public String toString(long aika, int leveys, int korkeus) {
        String palaute = "";
        if (tila.equals(Ruudukkotila.HAVIOTILA)) {
            palaute += "HAISADIOYijnoetedgijopdijopgdpjoi\n";
        } else if (tila.equals(Ruudukkotila.VOITTOTILA)) {
            palaute += "JESJEJSEJJSEJSEJALNKJKNFS\n";
        }

        for (int i = 0; i < this.korkeus; i++) {
            for (int j = 0; j < this.leveys; j++) {
                if (j == leveys && i == korkeus && tila.equals(Ruudukkotila.HAVIOTILA)) {
                    palaute += "X";
                } else if (ruudukko[j][i].getTyyppi().equals(Ruututyyppi.MIINA) && tila.equals(Ruudukkotila.VOITTOTILA)) {
                    palaute += "L";
                } else {
                    palaute += ruudukko[j][i].toString();
                }
            }

            palaute += "\n";
        }

        return palaute;
    }
}
