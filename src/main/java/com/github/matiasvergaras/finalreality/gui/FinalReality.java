package com.github.matiasvergaras.finalreality.gui;

import com.github.matiasvergaras.finalreality.controller.GameController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Main entry point for the application.
 * <p>
 * <Complete here with the details of the implemented application>
 *
 * @since Homework 1
 * @author Ignacio Slater Muñoz.
 * @author Matías Vergara Silva.
 */
public class FinalReality extends Application {
    private static final String RESOURCE_PATH = "src/main/resources/";
    public static void main(String[] args) {
        launch(args);
    }
    int width = 1000;
    int height = 570;

    @Override
    public void start(@NotNull Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Final reality");
        Group entryView = new Group();
        Scene scene = new Scene(entryView, width, height);

        //Name form
        TextField nameInput = nameInput();
        Button startButton = startButton();
        ComboBox numberOfCharacters = numOfCharacterOptions();
        startButton.setOnAction(event -> {
            try {
                enterToGame(nameInput.getText(), (int)numberOfCharacters.getValue(),
                                                                primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        var background = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "entrybackground.png")));
        entryView.getChildren().add(background);
        entryView.getChildren().add(nameInput);
        entryView.getChildren().add(startButton);
        entryView.getChildren().add(numberOfCharacters);

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private @NotNull TextField nameInput() {
        TextField nameInput = new TextField();
        nameInput.setBlendMode(BlendMode.LIGHTEN);
        nameInput.setLayoutX(400);
        nameInput.setLayoutY(450);
        return nameInput;
    }

    private @NotNull Button startButton() {
        Button button = new Button("Start Fighting Evil!");
        button.setLayoutX(440);
        button.setLayoutY(500);
        button.setFocusTraversable(false);
        button.setBlendMode(BlendMode.LIGHTEN);
        return button;
    }

    private ComboBox numOfCharacterOptions(){
        ObservableList<Integer> options = FXCollections.observableArrayList(5, 10, 12);
        final ComboBox optionsBox = new ComboBox(options);
        optionsBox.setLayoutX(740);
        optionsBox.setLayoutY(450);
        return optionsBox;
    }


    private void enterToGame(String playerName, int numberOfCharacters, Stage stage) throws FileNotFoundException {
        GameController gc = new GameController(playerName, "CPU", numberOfCharacters);
        Group settingTeamView = new Group();
        Scene initializingScene = new Scene(settingTeamView, width, height);
        stage.setScene(initializingScene);

        var initTeamBg = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "buildteambackground.png")));
        Button toWeaponSelectionButton = InitMenuButton("nextbutton.png", 450, 500);
        Button createCharacterButton = InitMenuButton("addbutton.png", 380, 320);
        Button EngineerFactoryButton = selectCharacterFactoryButton("engineerfactorylogo.png", 1, 1);
        Button KnightFactoryButton = selectCharacterFactoryButton("knightfactorylogo.png", 1, 2);
        Button BlackMageFactoryButton = selectCharacterFactoryButton("blackmagefactorylogo.png", 2, 1);
        Button WhiteMageFactoryButton = selectCharacterFactoryButton("whitemagefactorylogo.png", 2, 2);
        Button ThiefFactoryButton = selectCharacterFactoryButton("thieffactorylogo.png", 1,3);
        String selectedFactory = "None";

        EngineerFactoryButton.setOnAction(event -> gc.setSelectedCharacterFactory(0));
        BlackMageFactoryButton.setOnAction(event -> gc.setSelectedCharacterFactory(1));
        WhiteMageFactoryButton.setOnAction(event -> gc.setSelectedCharacterFactory(2));
        ThiefFactoryButton.setOnAction(event -> gc.setSelectedCharacterFactory(3));
        KnightFactoryButton.setOnAction(event -> gc.setSelectedCharacterFactory(4));

        settingTeamView.getChildren().add(initTeamBg);
        settingTeamView.getChildren().add(toWeaponSelectionButton);
        settingTeamView.getChildren().add(createCharacterButton);
        settingTeamView.getChildren().add(EngineerFactoryButton);
        settingTeamView.getChildren().add(KnightFactoryButton);
        settingTeamView.getChildren().add(BlackMageFactoryButton);
        settingTeamView.getChildren().add(WhiteMageFactoryButton);
        settingTeamView.getChildren().add(ThiefFactoryButton);

    }

    private @NotNull Button InitMenuButton(String IMAGEPATH, int x, int y) throws FileNotFoundException {
        Image nextButton = new Image(new FileInputStream(RESOURCE_PATH + IMAGEPATH));
        ImageView nextButtonView = new ImageView(nextButton);
        nextButtonView.setFitHeight(40);
        nextButtonView.setPreserveRatio(true);
        Button button = new Button();
        button.setGraphic(nextButtonView);
        button.setFocusTraversable(false);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

    private @NotNull Button selectCharacterFactoryButton(String IMAGEPATH, int i, int j) throws FileNotFoundException {
        Image factoryButton = new Image(new FileInputStream(RESOURCE_PATH + IMAGEPATH));
        ImageView factoryButtonView = new ImageView(factoryButton);
        factoryButtonView.setFitHeight(80);
        factoryButtonView.setPreserveRatio(true);
        Button button = new Button();
        button.setGraphic(factoryButtonView);
        button.setFocusTraversable(true);
        button.setLayoutX(40*i + 60*(i-1));
        button.setLayoutY(150*j - 40*(j-1));
        return button;
    }
}