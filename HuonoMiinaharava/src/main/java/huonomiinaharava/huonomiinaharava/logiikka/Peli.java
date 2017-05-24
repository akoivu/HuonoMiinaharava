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
        System.out.println("Korkeus: ");
        int korkeus = Integer.parseInt(lukija.nextLine());
        System.out.println("Leveys: ");
        int leveys = Integer.parseInt(lukija.nextLine());
        System.out.println("Miinat: ");
        int miinat = Integer.parseInt(lukija.nextLine());

        Ruudukko ruudukko = new Ruudukko(leveys, korkeus, miinat);

        System.out.println(ruudukko.toString());

        System.out.println("Korkeus: ");
        korkeus = Integer.parseInt(lukija.nextLine());
        System.out.println("Leveys: ");
        leveys = Integer.parseInt(lukija.nextLine());
        
        ruudukko.kokoaRuudukko(leveys, korkeus);
        
        System.out.println(ruudukko.toString());

        while (true) {
            System.out.println("Korkeus: ");
            korkeus = Integer.parseInt(lukija.nextLine());
            System.out.println("Leveys: ");
            leveys = Integer.parseInt(lukija.nextLine());

            if (ruudukko.klikkaus(leveys, korkeus) == 2) {
                System.out.println("Osuit miinaan ja kuolit. Ikävää");
                break;
            }

            System.out.println(ruudukko.toString());
        }

    }
}
