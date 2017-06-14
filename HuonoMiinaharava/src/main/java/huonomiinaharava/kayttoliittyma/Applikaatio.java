package huonomiinaharava.kayttoliittyma;

import huonomiinaharava.huonomiinaharava.logiikka.Ruudukko;
import huonomiinaharava.huonomiinaharava.logiikka.Ruudukkotila;
import huonomiinaharava.huonomiinaharava.logiikka.Ruutu;
import huonomiinaharava.huonomiinaharava.logiikka.Ruututyyppi;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Applikaatio extends Application {

    private Ruudukko ruudukko;
    private Image miina = new Image("miina.png");
    private Image nolla = new Image("tyhja0.png");
    private Image ykkonen = new Image("tyhja1.png");
    private Image kakkonen = new Image("tyhja2.png");
    private Image kolmonen = new Image("tyhja3.png");
    private Image nelonen = new Image("tyhja4.png");
    private Image vitonen = new Image("tyhja5.png");
    private Image kutonen = new Image("tyhja6.png");
    private Image seiska = new Image("tyhja7.png");
    private Image kasi = new Image("tyhja8.png");
    private Image klikkaamaton = new Image("klikkaamaton.png");
    private Image lippu = new Image("lippu.png");
    private Image havioruutu = new Image("havioruutu.png");

    /**
     * Muodostaa graafisen käyttöliittymän.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ruudukkovalinta");
        Label leveys = new Label("Leveys:");
        Label korkeus = new Label("Korkeus:");
        ChoiceBox leveysValinta = new ChoiceBox(FXCollections.observableArrayList(valinnat(15)));
        ChoiceBox korkeusValinta = new ChoiceBox(FXCollections.observableArrayList(valinnat(10)));
        Button dimensioNappi = new Button("Jatka");
        GridPane dimensioGrid = new GridPane();
        dimensioGrid.add(leveys, 1, 1);
        dimensioGrid.add(leveysValinta, 2, 1);
        dimensioGrid.add(korkeus, 1, 2);
        dimensioGrid.add(korkeusValinta, 2, 2);
        dimensioGrid.add(dimensioNappi, 1, 3);
        Scene dimensioNakyma = new Scene(dimensioGrid);

        Label miinat = new Label("Miinat");
        Button miinaNappi = new Button("Jatka");
        Label tekstiLeveys = new Label("Leveys: ");
        Label tekstiKorkeus = new Label("Korkeus: ");
        GridPane miinaGrid = new GridPane();
        miinaGrid.add(miinat, 1, 3);
        miinaGrid.add(miinaNappi, 1, 4);
        Scene miinaNakyma = new Scene(miinaGrid);

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

            ruudukko = new Ruudukko(valittuLeveys, valittuKorkeus, valittuMiinaMaara);
            GridPane grid = new GridPane();
            tyhjaRuudukko(grid);
            Scene hienoMiinaharavaNakymaJes = new Scene(grid);
            primaryStage.setScene(hienoMiinaharavaNakymaJes);
        });
    }

    public void tyhjaRuudukko(GridPane grid) {
        for (int i = 0; i < ruudukko.getKorkeus(); i++) {
            for (int j = 0; j < ruudukko.getLeveys(); j++) {
                StackPane stack;
                Ruutu ruutu = ruudukko.getRuudukko()[j][i];
                if (ruutu.isLiputettu()) {
                    stack = new StackPane(new ImageView(lippu));
                } else {
                    stack = new StackPane(new ImageView(klikkaamaton));
                }

                int leveys = j;
                int korkeus = i;
                stack.setOnMouseClicked(e -> {
                    if (e.getButton().equals(MouseButton.PRIMARY)) {
                        if (!ruutu.isLiputettu()) {
                            ruudukko.kokoaRuudukko(leveys, korkeus, ruudukko.getMiinat());
                            paivita(grid);
                        }
                    } else if (e.getButton().equals(MouseButton.SECONDARY)) {
                        ruudukko.liputus(leveys, korkeus);
                        tyhjaRuudukko(grid);
                    }
                });

                grid.add(stack, j, i);
            }
        }
    }

    public void pelinPaatos(GridPane grid, int vikaLeveys, int vikaKorkeus) {
        for (int i = 0; i < ruudukko.getKorkeus(); i++) {
            for (int j = 0; j < ruudukko.getLeveys(); j++) {
                Ruutu ruutu = ruudukko.getRuudukko()[j][i];
                StackPane stack;
                if (ruutu.isKlikattu()) {
                    if (i == vikaKorkeus && j == vikaLeveys && ruudukko.getTila().equals(Ruudukkotila.HAVIOTILA)) {
                        stack = new StackPane(new ImageView(havioruutu));
                    } else {
                        stack = new StackPane(new ImageView(new Image("tyhja" + ruutu.getYmparys() + ".png")));
                    }         
                } else {
                    if (ruudukko.getTila().equals(Ruudukkotila.HAVIOTILA)) {
                        if (ruutu.getTyyppi().equals(Ruututyyppi.MIINA)) {
                            stack = new StackPane(new ImageView(miina));
                        } else {
                            stack = new StackPane(new ImageView(klikkaamaton));
                        }
                    } else {
                        stack = new StackPane(new ImageView(lippu));
                    }
                }

                grid.add(stack, j, i);
            }
        }
    }

    public void paivita(GridPane grid) {
        if (ruudukko.getTila().equals(Ruudukkotila.VOITTOTILA)) {
            pelinPaatos(grid, 1, 1);
            return;
        }

        for (int i = 0; i < ruudukko.getKorkeus(); i++) {
            for (int j = 0; j < ruudukko.getLeveys(); j++) {
                Ruutu ruutu = ruudukko.getRuudukko()[j][i];
                StackPane stack;
                int leveys = j;
                int korkeus = i;
                if (ruutu.isKlikattu()) {
                    if (ruutu.getTyyppi().equals(Ruututyyppi.MIINA)) {
                        pelinPaatos(grid, leveys, korkeus);
                        return;
                    } else {
                        stack = new StackPane(new ImageView(new Image("tyhja" + ruutu.getYmparys() + ".png")));
                    }
                } else if (ruutu.isLiputettu()) {
                    stack = new StackPane(new ImageView(lippu));
                    stack.setOnMouseClicked(e -> {
                        if (e.getButton().equals(MouseButton.SECONDARY)) {
                            ruudukko.liputus(leveys, korkeus);
                            paivita(grid);
                        }
                    });
                } else {
                    stack = new StackPane(new ImageView(klikkaamaton));
                    stack.setOnMouseClicked(e -> {
                        if (e.getButton().equals(MouseButton.SECONDARY)) {
                            ruudukko.liputus(leveys, korkeus);
                            paivita(grid);
                        } else if (e.getButton().equals(MouseButton.PRIMARY)) {
                            ruudukko.klikkaus(leveys, korkeus);
                            paivita(grid);
                        }
                    });
                }

                grid.add(stack, j, i);
            }
        }
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
