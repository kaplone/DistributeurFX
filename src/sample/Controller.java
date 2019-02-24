package sample;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private GridPane clavier;

    @FXML
    private VBox infos;

    @FXML
    private Button louise;
    @FXML
    private Button camille;
    @FXML
    private Button david;
    @FXML
    private Button marius;

    @FXML
    private ImageView un;
    @FXML
    private ImageView deux;
    @FXML
    private ImageView trois;
    @FXML
    private ImageView quatre;
    @FXML
    private ImageView cinq;
    @FXML
    private ImageView six;
    @FXML
    private ImageView sept;
    @FXML
    private ImageView huit;
    @FXML
    private ImageView neuf;
    @FXML
    private ImageView zero;
    @FXML
    private BorderPane distributeur;

    @FXML
    private TextField solde;

    private Map<Integer, ImageView> boutons;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clavier.setVisible(false);
        infos.setVisible(false);

        boutons = new HashMap<>();
        boutons.put(0, zero);
        boutons.put(1, un);
        boutons.put(2, deux);
        boutons.put(3, trois);
        boutons.put(4, quatre);
        boutons.put(5, cinq);
        boutons.put(6, six);
        boutons.put(7, sept);
        boutons.put(8, huit);
        boutons.put(9, neuf);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Compte compte = mapper.readValue(new File("resources/compte.yml"), Compte.class);
            solde.setText(compte.getSolde().toString());

            david.setOnAction(event -> {
                clavier.setVisible(true);
                Boolean isOk = Connect.connect("David", distributeur, boutons, clavier, infos, compte);
                System.out.println(isOk);
            });
            marius.setOnAction(event -> {
                clavier.setVisible(true);
                Boolean isOk = Connect.connect("Marius", distributeur, boutons, clavier, infos, compte);
                System.out.println(isOk);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
