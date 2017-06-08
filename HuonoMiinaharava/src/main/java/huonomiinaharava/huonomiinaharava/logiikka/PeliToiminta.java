/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonomiinaharava.huonomiinaharava.logiikka;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

/**
 *
 * @author anttikoivurova
 */
public class PeliToiminta {

    private Ruudukko ruudukko;
    private ImageView[][] kuvaRuudukko;
    private GridPane grid;

    public PeliToiminta(int leveys, int korkeus, int miinat) {
        ruudukko = new Ruudukko(leveys, korkeus, miinat);
        kuvaRuudukko = new ImageView[leveys][korkeus];
        grid = new GridPane();

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                System.out.println("This kills the program");
//                grid.add(new ImageView(ruudukko.getRuudukko()[j][i].getTyhjaKuva()), j, i);
            }
        }

//        asetaActionit(leveys, korkeus);
    }

    public GridPane getGrid() {
        return grid;
    }

    public void asetaActionit(int leveys, int korkeus) {
        for (int i = 1; i < korkeus; i++) {
            for (int j = 1; j < leveys; j++) {
                ImageView kuva = kuvaRuudukko[j][i];

                kuva.setOnMouseClicked(e -> {
                    if (e.getButton().equals(MouseButton.SECONDARY)) {
                        System.out.println("asd");
                    } else {
                        System.out.println("Hou");
                    }
                });
            }
        }
    }

    public void klikkaus(int leveys, int korkeus) {
        
    }
}
