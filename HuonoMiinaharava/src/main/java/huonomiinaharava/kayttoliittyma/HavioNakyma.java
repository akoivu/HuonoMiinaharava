/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonomiinaharava.kayttoliittyma;

import huonomiinaharava.huonomiinaharava.logiikka.Ruudukko;
import huonomiinaharava.huonomiinaharava.logiikka.Ruutu;
import huonomiinaharava.huonomiinaharava.logiikka.Ruututyyppi;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Näkymä, joka kootaan pelaajan osuessa miinaan.
 * @author anttikoivurova
 */
public class HavioNakyma {

    private VBox kokonaisuus;
    private GridPane grid;
    private Ruudukko ruudukko;
    private Stage stage;
    private int aika;
    private int vikaLeveys;
    private int vikaKorkeus;
    private final Image miina = new Image("miina.png");
    private final Image nolla = new Image("tyhja0.png");
    private final Image ykkonen = new Image("tyhja1.png");
    private final Image kakkonen = new Image("tyhja2.png");
    private final Image kolmonen = new Image("tyhja3.png");
    private final Image nelonen = new Image("tyhja4.png");
    private final Image vitonen = new Image("tyhja5.png");
    private final Image kutonen = new Image("tyhja6.png");
    private final Image seiska = new Image("tyhja7.png");
    private final Image kasi = new Image("tyhja8.png");
    private final Image klikkaamaton = new Image("klikkaamaton.png");
    private final Image lippu = new Image("lippu.png");
    private final Image havioruutu = new Image("havioruutu.png");

    /**
     * Luo uuden häviönäkymän.
     * @param ruudukko pelissä käytetty ruudukko
     * @param stage primaryStage
     * @param aika pelin päätösaika
     * @param vikaLeveys viimeisen klikkauksen x-koordinaatti
     * @param vikaKorkeus viimeisen klikkauksen y-koordinaatti
     */
    public HavioNakyma(Ruudukko ruudukko, Stage stage, int aika, int vikaLeveys, int vikaKorkeus) {
        this.ruudukko = ruudukko;
        this.stage = stage;
        this.aika = aika;
        this.vikaLeveys = vikaLeveys;
        this.vikaKorkeus = vikaKorkeus;

        kokoaNakyma();
    }

    /**
     * Kokoaa häviönäkymän.
     */
    public void kokoaNakyma() {
        kokonaisuus = new VBox(10);
        grid = new GridPane();
        Label havioteksti = new Label(aika + "     Hävisit.");
        kokonaisuus.getChildren().add(havioteksti);

        for (int i = 0; i < ruudukko.getKorkeus(); i++) {
            for (int j = 0; j < ruudukko.getLeveys(); j++) {
                Ruutu ruutu = ruudukko.getRuudukko()[j][i];
                StackPane stack;
                if (ruutu.isKlikattu()) {
                    if (j == vikaLeveys && i == vikaKorkeus) {
                        stack = new StackPane(new ImageView(havioruutu));
                    } else {
                        stack = new StackPane(new ImageView(new Image("tyhja" + ruutu.getYmparys() + ".png")));
                    }
                } else {
                    if (ruutu.getTyyppi().equals(Ruututyyppi.MIINA)) {
                        stack = new StackPane(new ImageView(miina));
                    } else {
                        stack = new StackPane(new ImageView(klikkaamaton));
                    }
                }
                grid.add(stack, j, i);
            }
        }

        kokonaisuus.getChildren().add(grid);
    }

    /**
     * Palauttaa näkymän.
     * @return näkymä
     */
    public Scene scene() {
        return new Scene(kokonaisuus);
    }
}
