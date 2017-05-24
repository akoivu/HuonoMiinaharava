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

    public Ruudukko(int leveys, int korkeus, int miinat) {
        ruudukko = new Ruutu[leveys][korkeus];
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinat = miinat;
        kokoaTyhjaRuudukko();
    }

    public void kokoaTyhjaRuudukko() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                ruudukko[j][i] = new Tyhja(j, i);
            }
        }
    }

    public void kokoaRuudukko(int klikLeveys, int klikKorkeus) {
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
                ruudukko[kohtaLeveys][kohtaKorkeus] = new Miina(kohtaLeveys, kohtaKorkeus);
                j++;
                i++;
            } else {
                i++;
            }
        }
        
        ymparykset();
        
        klikkaus(klikLeveys, klikKorkeus);
    }

    public int klikkaus(int leveys, int korkeus) {
        return ruudukko[leveys][korkeus].klikkaus();
    }

    public void ymparykset() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                if (ruudukko[j][i].tyyppi() == 1) {
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
                    if (ruudukko[j][i].tyyppi() == 2) {
                        montako++;
                    }
                }
            }
        }

        Tyhja tyhja = (Tyhja) ruudukko[ruutuLeveys][ruutuKorkeus];

        tyhja.setYmparys(montako);
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
}
