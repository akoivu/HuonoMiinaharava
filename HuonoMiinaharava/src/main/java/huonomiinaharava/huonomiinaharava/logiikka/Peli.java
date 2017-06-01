/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonomiinaharava.huonomiinaharava.logiikka;

import java.util.Scanner;

/**
 *
 * @author anttikoivurova
 */
public class Peli {

    private Ruudukko ruudukko;
    private Scanner lukija;

    public Peli() {
        lukija = new Scanner(System.in);
    }

    public void kaynnista() {
        int[] ruudukkoDimensiot = dimensiot(20, 20);
        int miinat = miinaMaara((ruudukkoDimensiot[0] + 1) * (ruudukkoDimensiot[1] + 1));

        ruudukko = new Ruudukko(ruudukkoDimensiot[0] + 1, ruudukkoDimensiot[1] + 1, miinat);

        System.out.println(ruudukko.toString());
        
        long alku = System.nanoTime();

        int[] dimensioTaulukko = dimensiot(ruudukkoDimensiot[0] + 1, ruudukkoDimensiot[1] + 1);

        ruudukko.kokoaRuudukko(dimensioTaulukko[0], dimensioTaulukko[1]);

        System.out.println(ruudukko.toString());
        
        if(ruudukko.getJaljella() == 0){
            long kesto = System.nanoTime() - alku;
            System.out.println(ruudukko.voittoViesti(kesto));
        }

        while (ruudukko.getTila().equals(Ruudukkotila.PELITILA)) {
            dimensioTaulukko = dimensiot(ruudukkoDimensiot[0] + 1, ruudukkoDimensiot[1] + 1);

            if (ruudukko.getRuudukko()[dimensioTaulukko[0]][dimensioTaulukko[1]].isKlikattu()) {
                continue;
            }

            System.out.println(ruudukko.klikkaus(dimensioTaulukko[0], dimensioTaulukko[1]));
        }
    }

    public int[] dimensiot(int leveysraja, int korkeusraja) {
        System.out.println("Korkeus: ");
        int korkeus = Integer.parseInt(lukija.nextLine()) - 1;

        while (korkeus >= korkeusraja || korkeus < 0) {
            System.out.println("Korkeuden pitää olla kokonaisluku välillä [1, " + korkeusraja + "]. Yritähän nyt vielä kerran: ");
            korkeus = Integer.parseInt(lukija.nextLine()) - 1;
        }

        System.out.println("Leveys: ");
        int leveys = Integer.parseInt(lukija.nextLine()) - 1;

        while (leveys >= leveysraja || leveys < 0) {
            System.out.println("Leveyden pitää olla kokonaisluku välillä [1, " + leveysraja + "]. Yritähän nyt vielä kerran: ");
            leveys = Integer.parseInt(lukija.nextLine()) - 1;
        }

        return new int[]{leveys, korkeus};
    }

    public int miinaMaara(int raja) {
        System.out.println("Miinat: ");
        int miinat = Integer.parseInt(lukija.nextLine());

        while (miinat >= raja || miinat < 0) {
            System.out.println("Yritähän nyt vielä kerran: ");
            miinat = Integer.parseInt(lukija.nextLine());
        }

        return miinat;
    }
}
