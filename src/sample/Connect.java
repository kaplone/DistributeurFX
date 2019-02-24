package sample;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Map;

public class Connect {

    private static Integer positon;
    private static String code;
    private static Boolean isOk = false;
    private static Boolean annule = false;
    private static Boolean traitement = false;
    private static int essaisRestants;

    private static void setTraitement(Boolean b){
        traitement = b;
    }

    private static void addChifre(String s){
        code += s;
    }

    private static void initCode(){
        code = "";
    }

    private static void positionInit() {
        setPositon(0);
    }

    private static void setPositon(int p) {
        positon = p;
    }

    private static void incrementPositon() {
        positon++;
    }

    private static boolean isPositonFinal() {
        return positon == 4;
    }


    private static void initEssaisRestant(){
        essaisRestants = 3;
    }
    private static void decrementEssaisRestant(){
        essaisRestants--;
    }
    private static Boolean isEssaisRestant(){
        return essaisRestants > 0;
    }

    public static void changeClavier(int index, Map<Integer, ImageView> boutons) {
        for (int x = 0; x < 10; x++) {
            boutons.get(x).setOpacity(1d);
        }
        boutons.get(index).setOpacity(0.3d);
    }

    public static void initClavier(Map<Integer, ImageView> boutons) {
        for (int x = 0; x < 10; x++) {
            boutons.get(x).setOpacity(1d);
        }
    }

    private static void initHandler(int i, BorderPane distributeur, Map<Integer, ImageView> boutons, GridPane clavier, VBox infos, Compte compte){
        boutons.get(i).setOnMouseClicked(event -> {
            changeClavier(i, boutons);
            incrementPositon();
            addChifre(i + "");
            setTraitement(true);
            if (isPositonFinal()){
                checkOk(distributeur, boutons, clavier, infos, compte);
            }
        });
    }

    public static Boolean connect(String nom, BorderPane distributeur, Map<Integer, ImageView> boutons, GridPane clavier, VBox infos, Compte compte) {

        positionInit();
        initCode();
        initEssaisRestant();

        for (int i = 0; i < 10; i++){
            initHandler(i, distributeur, boutons, clavier, infos, compte);
        }

        return isOk;
    }

    private static boolean checkOk(BorderPane distributeur, Map<Integer, ImageView> boutons, GridPane clavier, VBox infos, Compte compte){
        if (!isOk && isEssaisRestant() && !annule) {

            if (traitement && isPositonFinal()) {
                System.out.println("Saisie --> " + code);
                isOk = check(compte);
                if (isOk) {
                    System.out.println("Code OK.");
                    initClavier(boutons);
                    clavier.setVisible(false);
                    infos.setVisible(true);
                } else {
                    decrementEssaisRestant();
                    System.out.println("Mauvais code. " + essaisRestants + " éssais restant.");
                    if (isEssaisRestant()) {
                        incrementPositon();
                        positionInit();
                        initCode();
                        initClavier(boutons);
                    } else {
                        distributeur.setOnKeyPressed(null);
                        clavier.setVisible(false);
                        initClavier(boutons);
                    }
                }
                setTraitement(false);
            }
        }
        return isOk;
    }

    private static Boolean check(Compte compte) {
        System.out.println(code);
        return code.equals(compte.getMdp());
    }
}
