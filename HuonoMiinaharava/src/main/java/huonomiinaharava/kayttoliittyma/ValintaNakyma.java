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
 * Näkymä, jossa pelaaaja valitsee pelin ruudukon leveyden ja korkeuden.
 * @author anttikoivurova
 */
public class ValintaNakyma {

    private GridPane grid;
    private Stage stage;
    private ChoiceBox leveysValinta;
    private ChoiceBox korkeusValinta;
    private Label errorLabel;

    /**
     * Luo näkymän, jossa voit valita ruudukon leveyden ja korkeuden.
     *
     * @param stage primaryStage
     */
    public ValintaNakyma(Stage stage) {
        this.stage = stage;
        luoNakyma();
    }

    /**
     * Kokoaa näkymän.
     */
    public void luoNakyma() {
        grid = new GridPane();
        grid.setPrefHeight(130);
        grid.setPrefWidth(300);
        errorLabel = new Label("");
        errorLabel.prefWidthProperty().bind(stage.widthProperty());
        Label leveysLabel = new Label("Leveys");
        Label korkeusLabel = new Label("Korkeus");
        leveysValinta = new ChoiceBox(FXCollections.observableArrayList(valinnat(2, 20)));
        korkeusValinta = new ChoiceBox(FXCollections.observableArrayList(valinnat(2, 15)));
        Button jatkoNappi = new Button("Jatka");
        jatkoNappi.setOnAction(this::nappiToiminta);
        grid.add(leveysLabel, 0, 0);
        grid.add(korkeusLabel, 0, 1);
        grid.add(leveysValinta, 1, 0);
        grid.add(korkeusValinta, 1, 1);
        grid.add(jatkoNappi, 0, 2);
        grid.add(errorLabel, 0, 3);
    }

    /**
     * Napin actionlistener, valittaa jos leveyttä tai korkeutta ei valittu.
     *
     * @param e Tapahtuma mikä tapahtunut
     */
    private void nappiToiminta(ActionEvent e) {
        try {
            int leveys = Integer.parseInt((String) leveysValinta.getValue());
            int korkeus = Integer.parseInt((String) korkeusValinta.getValue());
            MiinaValintaNakyma miinaValintaNakyma = new MiinaValintaNakyma(stage, leveys, korkeus);
            stage.setScene(miinaValintaNakyma.scene());
        } catch (NumberFormatException error) {
            errorLabel.setText("Valitse ruudukon korkeus ja leveys.");
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
