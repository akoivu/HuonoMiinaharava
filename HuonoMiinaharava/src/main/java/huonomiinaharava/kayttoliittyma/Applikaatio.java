package huonomiinaharava.kayttoliittyma;

import huonomiinaharava.huonomiinaharava.logiikka.PeliToiminta;
import huonomiinaharava.huonomiinaharava.logiikka.Ruutu;
import huonomiinaharava.huonomiinaharava.logiikka.Ruututyyppi;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Applikaatio extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Ruutu ruutu = new Ruutu(Ruututyyppi.MIINA, 3, 3);
        primaryStage.setTitle("Ruudukkovalinta");
        Label leveys = new Label("Leveys:");
        ChoiceBox leveysValinta = new ChoiceBox(FXCollections.observableArrayList(valinnat(15)));
        Label korkeus = new Label("Korkeus:");
        ChoiceBox korkeusValinta = new ChoiceBox(FXCollections.observableArrayList(valinnat(10)));

        Button dimensioNappi = new Button("Jatka");
        Button miinaNappi = new Button("Jatka");
        Button paluuNappi = new Button("Paluu");
        Label miinat = new Label("Miinat");
        Label tekstiLeveys = new Label("Leveys: ");
        Label tekstiKorkeus = new Label("Korkeus: ");

        GridPane miinaGrid = new GridPane();
        miinaGrid.add(miinat, 1, 3);
        miinaGrid.add(miinaNappi, 1, 4);

        GridPane dimensioGrid = new GridPane();
        dimensioGrid.add(leveys, 1, 1);
        dimensioGrid.add(leveysValinta, 2, 1);
        dimensioGrid.add(korkeus, 1, 2);
        dimensioGrid.add(korkeusValinta, 2, 2);
        dimensioGrid.add(dimensioNappi, 1, 3);
        
        Scene miinaNakyma = new Scene(miinaGrid);
        Scene dimensioNakyma = new Scene(dimensioGrid);
        
        primaryStage.setScene(dimensioNakyma);

        primaryStage.show();

        dimensioNappi.setOnAction((event) -> {
            int valittuLeveys = Integer.parseInt((String) leveysValinta.getValue());
            int valittuKorkeus = Integer.parseInt((String) korkeusValinta.getValue());

            Label infoLeveys = new Label("Leveys: " + valittuLeveys);
            Label infoKorkeus = new Label("Korkeus: " + valittuKorkeus);

            ChoiceBox miinaValinta = new ChoiceBox(FXCollections.observableArrayList(valinnat(valittuLeveys * valittuKorkeus)));

            miinaGrid.add(infoLeveys, 1, 1);
            miinaGrid.add(infoKorkeus, 1, 2);
            miinaGrid.add(miinaValinta, 2, 3);

            primaryStage.setScene(miinaNakyma);
        });

        miinaNappi.setOnAction((event) -> {
            ChoiceBox leveysBoksi = (ChoiceBox) dimensioGrid.getChildren().get(1);
            int valittuLeveys = Integer.parseInt((String) leveysBoksi.getValue());
            ChoiceBox korkeusBoksi = (ChoiceBox) dimensioGrid.getChildren().get(3);
            int valittuKorkeus = Integer.parseInt((String) korkeusBoksi.getValue());
            ChoiceBox miinaBoksi = (ChoiceBox) miinaGrid.getChildren().get(4);
            int valittuMiinaMaara = Integer.parseInt((String) miinaBoksi.getValue());

            PeliToiminta peli = new PeliToiminta(valittuLeveys, valittuKorkeus, valittuMiinaMaara);

            primaryStage.setScene(new Scene(peli.getGrid()));
        });
        
       
    }

    public static void main(String[] args) {
        launch(Applikaatio.class);
    }

    public ArrayList<String> valinnat(int raja) {
        ArrayList<String> lista = new ArrayList();

        for (int i = 1; i < raja; i++) {
            lista.add("" + i);
        }

        return lista;
    }
}
