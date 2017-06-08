/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonomiinaharava.huonomiinaharava.logiikka;

import java.io.File;
import javafx.scene.image.Image;

/**
 *
 * @author anttikoivurova
 */
public class Ruutu {

    Ruututyyppi tyyppi;
    private int leveys;
    private int korkeus;
    private boolean klikattu;
    private int ymparys;
    private boolean liputettu;

    public Ruutu(Ruututyyppi tyyppi, int leveys, int korkeus) {
        this.tyyppi = tyyppi;
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.klikattu = false;
        this.liputettu = false;
    }

    public void klikkaus() {
        this.setKlikattu(true);
    }

    public String toString() {
        switch (tyyppi) {
            case MIINA:
                if (klikattu) {
                    return "x";
                }
            case TYHJA:
                if (klikattu) {
                    return "" + ymparys;
                }
            default:
                return "o";
        }
    }

    public Ruututyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(Ruututyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public boolean isKlikattu() {
        return klikattu;
    }

    public void setKlikattu(boolean klikattu) {
        this.klikattu = klikattu;
    }

    public int getYmparys() {
        return ymparys;
    }

    public void setYmparys(int ymparys) {
        this.ymparys = ymparys;
    }

    public boolean isLiputettu() {
        return liputettu;
    }

    public void setLiputettu(boolean liputettu) {
        this.liputettu = liputettu;
    }

//    public Image getLippuKuva() {
//        return lippu;
//    }
//
//    public Image getTyhjaKuva() {
//        return klikkaamaton;
//    }
//
//    public Image getOikeaKuva() {
//        if (tyyppi.equals(Ruututyyppi.MIINA)) {
//            return miina;
//        } else if (ymparys == 0) {
//            return nolla;
//        } else if (ymparys == 1) {
//            return ykkonen;
//        } else if (ymparys == 2) {
//            return kakkonen;
//        } else if (ymparys == 3) {
//            return kolmonen;
//        } else if (ymparys == 4) {
//            return nelonen;
//        } else if (ymparys == 5) {
//            return vitonen;
//        } else if (ymparys == 6) {
//            return kutonen;
//        } else if (ymparys == 7) {
//            return seiska;
//        }
//        return kasi;
//    }
    
//    private static Image miina = new Image(new File("/Users/anttikoivurova/NetBeansProjects/HuonoMiinaharava/HuonoMiinaharava/src/main/java/images/miina.png").toURI().toString());
//    private static Image nolla = new Image(new File("/Users/anttikoivurova/NetBeansProjects/HuonoMiinaharava/HuonoMiinaharava/src/main/java/images/tyhja0.png").toURI().toString());
//    private static Image ykkonen = new Image(new File("/Users/anttikoivurova/NetBeansProjects/HuonoMiinaharava/HuonoMiinaharava/src/main/java/images/tyhja1.png").toURI().toString());
//    private static Image kakkonen = new Image(new File("/Users/anttikoivurova/NetBeansProjects/HuonoMiinaharava/HuonoMiinaharava/src/main/java/images/tyhja2.png").toURI().toString());
//    private static Image kolmonen = new Image(new File("/Users/anttikoivurova/NetBeansProjects/HuonoMiinaharava/HuonoMiinaharava/src/main/java/images/tyhja3.png").toURI().toString());
//    private static Image nelonen = new Image(new File("/Users/anttikoivurova/NetBeansProjects/HuonoMiinaharava/HuonoMiinaharava/src/main/java/images/tyhja4.png").toURI().toString());
//    private static Image vitonen = new Image(new File("/Users/anttikoivurova/NetBeansProjects/HuonoMiinaharava/HuonoMiinaharava/src/main/java/images/tyhja5.png").toURI().toString());
//    private static Image kutonen = new Image(new File("/Users/anttikoivurova/NetBeansProjects/HuonoMiinaharava/HuonoMiinaharava/src/main/java/images/tyhja6.png").toURI().toString());
//    private static Image seiska = new Image(new File("/Users/anttikoivurova/NetBeansProjects/HuonoMiinaharava/HuonoMiinaharava/src/main/java/images/tyhja7.png").toURI().toString());
//    private static Image kasi = new Image(new File("/Users/anttikoivurova/NetBeansProjects/HuonoMiinaharava/HuonoMiinaharava/src/main/java/images/tyhja8.png").toURI().toString());
//    private static Image klikkaamaton = new Image(new File("/Users/anttikoivurova/NetBeansProjects/HuonoMiinaharava/HuonoMiinaharava/src/main/java/images/klikkaamaton.png").toURI().toString());
//    private static Image lippu = new Image(new File("/Users/anttikoivurova/NetBeansProjects/HuonoMiinaharava/HuonoMiinaharava/src/main/java/images/lippu.png").toURI().toString());
}
