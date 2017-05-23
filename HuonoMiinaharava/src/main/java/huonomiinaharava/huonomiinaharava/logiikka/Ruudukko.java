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
    private int korkeus;
    private int leveys;
    private int miinat;

    public Ruudukko(int korkeus, int leveys, int miinat) {
        ruudukko = new Ruutu[leveys][korkeus];
        this.korkeus = leveys;
        this.leveys = korkeus;
        this.miinat = miinat;
        kokoaTyhjaRuudukko();
    }

    public void kokoaTyhjaRuudukko() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                ruudukko[i][j] = new Tyhja(i, j);
            }
        }
    }

    public void kokoaRuudukko(int x, int y) {
        ArrayList<Integer> lista = new ArrayList();

        for (int i = 0; i < korkeus * leveys; i++) {
            lista.add(i);
        }
        
        Collections.shuffle(lista);
        
        int i = 0;
        int j = 0;
        while (j < miinat && i < korkeus * leveys) {
            int kohtaX = lista.get(i) / leveys;
            int kohtaY = lista.get(i) % leveys;

            if (kohtaX != x || kohtaY != y) {
                ruudukko[kohtaX][kohtaY] = new Miina(kohtaX, kohtaY);
                j++;
                i++;
            } else {
                i++;
            }
        }
        
        ymparykset();
    }

    public int klikkaus(int korkeus, int leveys) {
        return ruudukko[korkeus][leveys].klikkaus();
    }

    public void ymparykset() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                if (ruudukko[i][j].tyyppi() == 1) {
                    ymparys(i, j);
                }
            }
        }
    }

    public void ymparys(int ruutuKorkeus, int ruutuLeveys) {
        int montako = 0;

        for (int i = ruutuKorkeus - 1; i <= ruutuKorkeus + 1; i++) {
            for (int j = ruutuLeveys - 1; j <= ruutuLeveys + 1; j++) {
                if (i >= 0 && i < this.korkeus && j >= 0 && j < this.leveys) {
                    if (ruudukko[i][j].tyyppi() == 2) {
                        montako++;
                    }
                }
            }
        }

        Tyhja tyhja = (Tyhja) ruudukko[ruutuKorkeus][ruutuLeveys];

        tyhja.setYmparys(montako);
    }

    @Override
    public String toString() {
        String palaute = "";

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                palaute += ruudukko[i][j].toString();
            }

            palaute += "\n";
        }

        return palaute;
    }
}
