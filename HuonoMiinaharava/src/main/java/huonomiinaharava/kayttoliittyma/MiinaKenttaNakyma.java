/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonomiinaharava.kayttoliittyma;

import huonomiinaharava.huonomiinaharava.logiikka.Ruudukko;
import huonomiinaharava.huonomiinaharava.logiikka.Ruudukkotila;
import huonomiinaharava.huonomiinaharava.logiikka.Ruutu;
import huonomiinaharava.huonomiinaharava.logiikka.Ruututyyppi;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Varsinaisen pelin näkymä.
 * @author anttikoivurova
 */
public class MiinaKenttaNakyma {

    private GridPane miinaKentta;
    private VBox kokonaisuus;
    private Stage stage;
    private Ruudukko ruudukko;
    private Integer aika = 0;
    private Label aikaLabel = new Label();
    private int leveys;
    private int korkeus;
    private Timeline timeline;
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
     * Uusi miinakenttä.
     *
     * @param stage primaryStage
     * @param leveys ruudukon leveys
     * @param korkeus ruudukon korkeus
     * @param miinat ruudukon miinamäärä
     */
    public MiinaKenttaNakyma(Stage stage, int leveys, int korkeus, int miinat) {
        this.stage = stage;
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.ruudukko = new Ruudukko(leveys, korkeus, miinat);
        this.miinaKentta = new GridPane();
        this.kokonaisuus = new VBox(10);
        timeline = new Timeline(new KeyFrame(
            Duration.seconds(1),
            ae -> aika++), new KeyFrame(
                Duration.seconds(1),
                ae -> aikaLabel.setText(("" + aika.toString())))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        kokonaisuus.getChildren().add(aikaLabel);
        kokonaisuus.getChildren().add(miinaKentta);
        tyhjaRuudukko();
    }

    /**
     * Palauttaa näkymän.
     *
     * @return palautettava näkymä
     */
    public Scene scene() {
        return new Scene(kokonaisuus);
    }

    /**
     * Kokoaa tyhjän miinaruudukon.
     */
    public void tyhjaRuudukko() {
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                StackPane stack;
                Ruutu ruutu = ruudukko.getRuudukko()[j][i];
                if (ruutu.isLiputettu()) {
                    stack = new StackPane(new ImageView(lippu));
                } else {
                    stack = new StackPane(new ImageView(klikkaamaton));
                }
                int leveysNyt = j;
                int korkeusNyt = i;
                stack.setOnMouseClicked(e -> {
                    if (e.getButton().equals(MouseButton.PRIMARY)) {
                        if (!ruutu.isLiputettu()) {
                            ruudukko.kokoaRuudukko(leveysNyt, korkeusNyt);
                            paivita(miinaKentta);
                        }
                    } else if (e.getButton().equals(MouseButton.SECONDARY)) {
                        ruudukko.liputus(leveysNyt, korkeusNyt);
                        tyhjaRuudukko();
                    }
                });
                miinaKentta.add(stack, j, i);
            }
        }
    }

    /**
     * Päivittää näkymän pelitilanteen mukaan.
     *
     * @param grid miinakenttä visualisoituna
     */
    public void paivita(GridPane grid) {
        if (ruudukko.getTila().equals(Ruudukkotila.VOITTOTILA)) {
            timeline.stop();
            voitto();
            return;
        }
        for (int i = 0; i < ruudukko.getKorkeus(); i++) {
            for (int j = 0; j < ruudukko.getLeveys(); j++) {
                Ruutu ruutu = ruudukko.getRuudukko()[j][i];
                StackPane stack;
                int leveysNyt = j;
                int korkeusNyt = i;
                if (ruutu.isKlikattu()) {
                    if (ruutu.getTyyppi().equals(Ruututyyppi.MIINA)) {
                        havio(leveysNyt, korkeusNyt);
                        return;
                    } else {
                        stack = new StackPane(new ImageView(new Image("tyhja" + ruutu.getYmparys() + ".png")));
                    }
                } else if (ruutu.isLiputettu()) {
                    stack = new StackPane(new ImageView(lippu));
                    stack.setOnMouseClicked(e -> {
                        if (e.getButton().equals(MouseButton.SECONDARY)) {
                            ruudukko.liputus(leveysNyt, korkeusNyt);
                            paivita(grid);
                        }
                    });
                } else {
                    stack = new StackPane(new ImageView(klikkaamaton));
                    stack.setOnMouseClicked(e -> {
                        if (e.getButton().equals(MouseButton.SECONDARY)) {
                            ruudukko.liputus(leveysNyt, korkeusNyt);
                            paivita(grid);
                        } else if (e.getButton().equals(MouseButton.PRIMARY)) {
                            ruudukko.klikkaus(leveysNyt, korkeusNyt);
                            paivita(grid);
                        }
                    });
                }
                grid.add(stack, j, i);
            }
        }
    }

    /**
     * Hoitaa tilanteen, jossa pelaaja on voittanut pelin.
     */
    public void voitto() {
        int aika = Integer.parseInt(aikaLabel.getText());
        VoittoNakyma nakyma = new VoittoNakyma(ruudukko, stage, aika);
        stage.setScene(nakyma.scene());
    }

    /**
     * Hoitaa tilanteen, jossa pelaaja on hävinnyt pelin.
     * @param vikaLeveys viimeisen klikkauksen x-koordinaatti
     * @param vikaKorkeus viimeisen klikkauksen y-koordinaatti
     */
    public void havio(int vikaLeveys, int vikaKorkeus) {
        int aika = Integer.parseInt(aikaLabel.getText());
        HavioNakyma nakyma = new HavioNakyma(ruudukko, stage, aika, vikaLeveys, vikaKorkeus);
        stage.setScene(nakyma.scene());
    }
}
