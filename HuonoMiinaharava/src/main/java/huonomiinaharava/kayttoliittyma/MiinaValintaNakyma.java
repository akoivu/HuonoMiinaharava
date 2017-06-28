/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonomiinaharava.kayttoliittyma;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Näkymä, jossa pelaaja valitsee miinojen määrän.
 * @author anttikoivurova
 */
public class MiinaValintaNakyma {

    private GridPane grid;
    private Stage stage;
    private ChoiceBox miinaValinta;
    private Label errorLabel;
    private int leveys;
    private int korkeus;
    private int miinat;

    /**
     * Luo näkymän, jossa voit valita ruudukon miinamäärän.
     *
     * @param stage primaryStage
     * @param leveys valittu leveys
     * @param korkeus valittu korkeus
     */
    public MiinaValintaNakyma(Stage stage, int leveys, int korkeus) {
        this.stage = stage;
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinat = 0;
        luoNakyma();
    }

    /**
     * Kokoaa näkymän.
     */
    private void luoNakyma() {
        grid = new GridPane();
        grid.setPrefHeight(130);
        grid.setPrefWidth(300);
        errorLabel = new Label("");
        errorLabel.prefWidthProperty().bind(stage.widthProperty());
        Label leveysValittu = new Label("Valittu leveys: " + leveys);
        Label korkeusValittu = new Label("Valittu korkeus: " + korkeus);
        Label miinaLabel = new Label("Miinat");
        miinaValinta = new ChoiceBox(FXCollections.observableArrayList(valinnat(1, (leveys * korkeus) - 1)));
        Button jatkonappi = new Button("Luo miinakenttä");

        jatkonappi.setOnAction(this::nappiToiminta);

        grid.add(leveysValittu, 0, 0);
        grid.add(korkeusValittu, 0, 1);
        grid.add(miinaLabel, 0, 2);
        grid.add(miinaValinta, 1, 2);
        grid.add(jatkonappi, 0, 3);
        grid.add(errorLabel, 0, 4);
    }

    /**
     * Napin actionlistener, valittaa jos miinamäärää ei valittu.
     *
     * @param e Tapahtuma mikä tapahtunut
     */
    private void nappiToiminta(ActionEvent e) {
        try {
            miinat = Integer.parseInt((String) miinaValinta.getValue());
            MiinaKenttaNakyma miinaKenttaNakyma = new MiinaKenttaNakyma(stage, leveys, korkeus, miinat);
            stage.setScene(miinaKenttaNakyma.scene());
        } catch (NumberFormatException error) {
            errorLabel.setText("Valitse ruudukon miinamäärä.");
        }
    }

    /**
     * Palauttaa näkymän.
     *
     * @return palautettava näkymä
     */
    public Scene scene() {
        return new Scene(grid);
    }

    /**
     * Antaa listan numeroita suuruusjärjestyksessä alarajasta ylärajaan.
     *
     * @param alaraja Numero, mistä numerosarja alkaa.
     * @param ylaraja Numero, mihin numerosarja loppuu.
     * @return Lista numeroita
     */
    public ArrayList<String> valinnat(int alaraja, int ylaraja) {
        ArrayList<String> lista = new ArrayList();
        for (int i = alaraja; i <= ylaraja; i++) {
            lista.add("" + i);
        }

        return lista;
    }
}
