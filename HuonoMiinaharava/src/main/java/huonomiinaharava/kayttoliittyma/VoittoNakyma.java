/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonomiinaharava.kayttoliittyma;

import huonomiinaharava.huonomiinaharava.logiikka.Ruudukko;
import huonomiinaharava.huonomiinaharava.logiikka.Ruutu;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Näkymä, joka kootaan pelaajan voittaessa pelin.
 * @author anttikoivurova
 */
public class VoittoNakyma {

    private VBox kokonaisuus;
    private GridPane grid;
    private Ruudukko ruudukko;
    private Stage stage;
    private int aika;
    private final Image lippu = new Image("lippu.png");
    private final Image nolla = new Image("tyhja0.png");
    private final Image ykkonen = new Image("tyhja1.png");
    private final Image kakkonen = new Image("tyhja2.png");
    private final Image kolmonen = new Image("tyhja3.png");
    private final Image nelonen = new Image("tyhja4.png");
    private final Image vitonen = new Image("tyhja5.png");
    private final Image kutonen = new Image("tyhja6.png");
    private final Image seiska = new Image("tyhja7.png");
    private final Image kasi = new Image("tyhja8.png");

    /**
     * Luo uuden voittonäkymän.
     * @param ruudukko pelissä käytetty ruudukko
     * @param stage primaryStage
     * @param aika pelin päätösaika
     */
    public VoittoNakyma(Ruudukko ruudukko, Stage stage, int aika) {
        this.ruudukko = ruudukko;
        this.stage = stage;
        this.aika = aika;

        kokoaNakyma();
    }

    /**
     * Palauttaa näkymän.
     * @return näkymä
     */
    public Scene scene() {
        return new Scene(kokonaisuus);
    }

    /**
     * Kokoaa voittonäkymän.
     */
    public void kokoaNakyma() {
        grid = new GridPane();
        kokonaisuus = new VBox(10);
        Label voittoteksti = new Label("Voi juku, voitit! Aikasi oli " + aika + " sekuntia.");
        kokonaisuus.getChildren().add(voittoteksti);
        for (int i = 0; i < ruudukko.getKorkeus(); i++) {
            for (int j = 0; j < ruudukko.getLeveys(); j++) {
                Ruutu ruutu = ruudukko.getRuudukko()[j][i];
                StackPane stack;
                if (ruutu.isKlikattu()) {
                    stack = new StackPane(new ImageView(new Image("tyhja" + ruutu.getYmparys() + ".png")));
                } else {
                    stack = new StackPane(new ImageView(lippu));
                }
                grid.add(stack, j, i);
            }
        }

        kokonaisuus.getChildren().add(grid);
    }
}
