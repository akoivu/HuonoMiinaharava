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
        int[] dimensioTaulukko = dimensiot();
        int miinat = miinaMaara((dimensioTaulukko[0] + 1) * (dimensioTaulukko[1] + 1));

        Ruudukko ruudukko = new Ruudukko(dimensioTaulukko[0] + 1, dimensioTaulukko[1] + 1, miinat);

        System.out.println(ruudukko.toString());

        dimensioTaulukko = dimensiot();

        ruudukko.kokoaRuudukko(dimensioTaulukko[0], dimensioTaulukko[1]);

        System.out.println(ruudukko.toString());

        while (true) {
            dimensioTaulukko = dimensiot();

            if (ruudukko.getRuudukko()[dimensioTaulukko[0]][dimensioTaulukko[1]].isKlikattu()) {
                continue;
            }

            int palautus = ruudukko.klikkaus(dimensioTaulukko[0], dimensioTaulukko[1]);

            if (palautus == -1) {
                System.out.println("Osuit miinaan ja kuolit. Ikävää.");
                break;
            } else if (palautus == -2) {
                System.out.println("Voi juku, olet varmaan joku miinaharavamestarihenkilö.");
                break;
            }

            System.out.println(ruudukko.toString());
        }

        System.out.println(ruudukko.toString());
    }

    public int[] dimensiot() {
        System.out.println("Korkeus: ");
        int korkeus = Integer.parseInt(lukija.nextLine()) - 1;

        while (korkeus >= 20 || korkeus < 0) {
            System.out.println("Korkeuden pitää olla kokonaisluku välillä [1, 20]. Yritähän nyt vielä kerran: ");
            korkeus = Integer.parseInt(lukija.nextLine()) - 1;
        }

        System.out.println("Leveys: ");
        int leveys = Integer.parseInt(lukija.nextLine()) - 1;

        while (leveys >= 20 || leveys < 0) {
            System.out.println("Leveyden pitää olla kokonaisluku välillä [1, 20]. Yritähän nyt vielä kerran: ");
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
