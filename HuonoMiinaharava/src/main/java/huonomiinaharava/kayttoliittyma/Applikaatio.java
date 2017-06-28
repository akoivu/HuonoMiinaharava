package huonomiinaharava.kayttoliittyma;

import huonomiinaharava.huonomiinaharava.logiikka.Ruudukko;
import javafx.application.Application;
import javafx.stage.Stage;

public class Applikaatio extends Application {

    private Ruudukko ruudukko;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ValintaNakyma nakyma = new ValintaNakyma(primaryStage);
        primaryStage.setScene(nakyma.scene());
        primaryStage.show();
    }

    /**
     * main Käynnistää ohjelman.
     *
     * @param args En kuule tiedä
     */
    public static void main(String[] args) {
        launch(Applikaatio.class);
    }
}
