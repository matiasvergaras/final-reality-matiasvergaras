package com.github.matiasvergaras.finalreality.gui;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    private GameController gc;
    final int width = 1000;
    final int height = 570;

    //animated variables of toSetParty method (init phase, while building team).
    private Button nextPartyMenuButton;
    private Button createPartyButton;
    private Button removePartyButton;
    private final Label charactersNumLabel = new Label();
    private final Label inPartyCharacterName = new Label();
    private final Label inPartyCharacterHP = new Label();
    private final Label inPartyCharacterDP = new Label();
    private final Label inPartyCharacterMana = new Label();
    private TextField charactersMana;
    private ComboBox currentCharacters;
    private final ImageView selectedCharacterMiniSprite = new ImageView();

    //animated variables of toSetInventory method
    private Button nextWeaponMenuButton;
    private Button createWeaponButton;
    private Button removeWeaponButton;
    private TextField weaponsMagic;
    private final Label weaponNumLabel = new Label();
    private final Label inInventoryWeaponName = new Label();
    private final Label inInventoryWeaponPower = new Label();
    private final Label inInventoryWeaponWeight = new Label();
    private final Label inInventoryWeaponMagic = new Label();
    private ComboBox currentWeapons;
    private final ImageView selectedWeaponMiniSprite = new ImageView();


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
                toSetTeam(nameInput.getText(), (int)numberOfCharacters.getValue(),
                                                                primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        ImageView background = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "entrybackground.png")));

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
        ObservableList<Integer> options = FXCollections.observableArrayList(1, 5, 10, 12);
        final ComboBox optionsBox = new ComboBox(options);
        optionsBox.setLayoutX(740);
        optionsBox.setLayoutY(450);
        return optionsBox;
    }


    private void toSetTeam(String playerName, int numberOfCharacters, Stage stage) throws FileNotFoundException {
        List<String> usedCharacterNames = new ArrayList<>();
        gc = new GameController(playerName, "CPU", numberOfCharacters);
        Group settingTeamView = new Group();
        Scene initializingScene = new Scene(settingTeamView, width, height);
        stage.setScene(initializingScene);
        var initTeamBg = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "selectteambg.png")));
        nextPartyMenuButton = ButtonWithImage("nextbutton.png", 450, 500);
        createPartyButton = ButtonWithImage("addbutton.png", 380, 320);
        removePartyButton = ButtonWithImage("removebutton.png", 850, 500);
        nextPartyMenuButton.setVisible(true);
        createPartyButton.setVisible(false);
        removePartyButton.setVisible(false);

        gc.setSelectedCharacterFactory(0);

        Button EngineerFactoryButton = SelectFactoryButton("engineerfactorylogo.png", 1, 1);
        Button KnightFactoryButton = SelectFactoryButton("knightfactorylogo.png", 1, 2);
        Button BlackMageFactoryButton = SelectFactoryButton("blackmagefactorylogo.png", 2, 1);
        Button WhiteMageFactoryButton = SelectFactoryButton("whitemagefactorylogo.png", 2, 2);
        Button ThiefFactoryButton = SelectFactoryButton("thieffactorylogo.png", 1,3);

        TextField characterName = textField(360, 175);
        TextField charactersHP = textField(360, 210);
        TextField charactersDP = textField(360, 245);
        charactersMana = textField(360, 280);
        charactersMana.setVisible(false);

        EngineerFactoryButton.setOnAction(event -> gc.setSelectedCharacterFactory(0));
        BlackMageFactoryButton.setOnAction(event -> gc.setSelectedCharacterFactory(1));
        WhiteMageFactoryButton.setOnAction(event -> gc.setSelectedCharacterFactory(2));
        ThiefFactoryButton.setOnAction(event -> gc.setSelectedCharacterFactory(3));
        KnightFactoryButton.setOnAction(event -> gc.setSelectedCharacterFactory(4));

        currentCharacters = desplegableCharacterList(gc.getPlayerParty(), 690, 140);
        currentCharacters.setVisible(true);

        charactersNumLabel.setFont(new Font("Arial", 25.0));
        charactersNumLabel.setLayoutX(930);
        charactersNumLabel.setLayoutY(108);

        inPartyCharacterName.setFont(new Font("Arial", 20.0));
        inPartyCharacterName.setLayoutX(780);
        inPartyCharacterName.setLayoutY(345);

        inPartyCharacterHP.setFont(new Font("Arial", 20.0));
        inPartyCharacterHP.setLayoutX(780);
        inPartyCharacterHP.setLayoutY(382);

        inPartyCharacterDP.setFont(new Font("Arial", 20.0));
        inPartyCharacterDP.setLayoutX(780);
        inPartyCharacterDP.setLayoutY(418);

        inPartyCharacterMana.setFont(new Font("Arial", 20.0));
        inPartyCharacterMana.setLayoutX(780);
        inPartyCharacterMana.setLayoutY(455);

        selectedCharacterMiniSprite.setImage(new Image(new FileInputStream(RESOURCE_PATH + "kaneminisprite.png")));
        selectedCharacterMiniSprite.setLayoutX(790);
        selectedCharacterMiniSprite.setLayoutY(170);
        selectedCharacterMiniSprite.setFitWidth(130);
        selectedCharacterMiniSprite.setPreserveRatio(true);
        selectedCharacterMiniSprite.setVisible(false);

        setTeamTimer();

        settingTeamView.getChildren().add(initTeamBg);

        settingTeamView.getChildren().add(nextPartyMenuButton);
        settingTeamView.getChildren().add(createPartyButton);
        settingTeamView.getChildren().add(removePartyButton);

        settingTeamView.getChildren().add(EngineerFactoryButton);
        settingTeamView.getChildren().add(KnightFactoryButton);
        settingTeamView.getChildren().add(BlackMageFactoryButton);
        settingTeamView.getChildren().add(WhiteMageFactoryButton);
        settingTeamView.getChildren().add(ThiefFactoryButton);

        settingTeamView.getChildren().add(characterName);
        settingTeamView.getChildren().add(charactersHP);
        settingTeamView.getChildren().add(charactersDP);
        settingTeamView.getChildren().add(charactersMana);
        settingTeamView.getChildren().add(charactersNumLabel);

        settingTeamView.getChildren().add(currentCharacters);

        settingTeamView.getChildren().add(inPartyCharacterName);
        settingTeamView.getChildren().add(inPartyCharacterHP);
        settingTeamView.getChildren().add(inPartyCharacterDP);
        settingTeamView.getChildren().add(inPartyCharacterMana);

        settingTeamView.getChildren().add(selectedCharacterMiniSprite);


        createPartyButton.setOnAction(
                event -> {if(gc.getSelectedCharacterFactory()!=null){
                    if(!usedCharacterNames.contains(characterName.getText()) && !characterName.getText().equals("")){
                        gc.setSelectedCharacterFactoryHP(Integer.parseInt(charactersHP.getText()));
                        gc.setSelectedCharacterFactoryDP(Integer.parseInt(charactersDP.getText()));
                        if(!charactersMana.getText().equals("")) {
                            gc.setSelectedCharacterFactoryMana(Integer.parseInt(charactersMana.getText()));
                        }
                        gc.selectedCharacterFactoryProduce(characterName.getText());
                        usedCharacterNames.add(characterName.getText());
                        characterName.clear();
                    }
                 }
        });

        removePartyButton.setOnAction(event -> {
            usedCharacterNames.remove(gc.getSelectedCharacterName());
            removePartyButton.setVisible(false);
            gc.removeSelectedCharacterFromItsParty();
            inPartyCharacterName.setText("");
            inPartyCharacterHP.setText("");
            inPartyCharacterDP.setText("");
            inPartyCharacterMana.setText("");
            selectedCharacterMiniSprite.setVisible(false);

        });

        currentCharacters.setOnAction(event -> {
            if(!(currentCharacters.getValue()==null)){
                try {
                    InputStream miniSpriteStream = new FileInputStream(RESOURCE_PATH +getSelectedCharacterMiniSpritePATH());
                    selectedCharacterMiniSprite.setImage(new Image(miniSpriteStream));
                    selectedCharacterMiniSprite.setVisible(true);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                removePartyButton.setVisible(true);
            }
        });

        nextPartyMenuButton.setOnAction(event -> {
            if(gc.getPlayerPartySize()==gc.getCharactersQuantity()){
                try {
                    selectedCharacterMiniSprite.setVisible(false);
                    toSelectInventory(stage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private @NotNull Button ButtonWithImage(String IMAGE_PATH, int x, int y) throws FileNotFoundException {
        Image nextButton = new Image(new FileInputStream(RESOURCE_PATH + IMAGE_PATH));
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

    private @NotNull TextField textField(int x, int y){
        TextField field = new TextField();
        field.setLayoutX(x);
        field.setLayoutY(y);
        field.setFocusTraversable(false);
        return field;
    }

    private @NotNull Button SelectFactoryButton(String IMAGEPATH, int i, int j) throws FileNotFoundException {
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

    private @NotNull ComboBox desplegableCharacterList(List<ICharacter> currentCharacters, int x, int y){
        ObservableList<ICharacter> characters = FXCollections.observableArrayList(currentCharacters);
        final ComboBox optionsBox = new ComboBox(characters);
        optionsBox.setLayoutX(x);
        optionsBox.setLayoutY(y);
        return optionsBox;
    }

    private void setTeamTimer(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                charactersNumLabel.setText(String.valueOf(gc.getPlayerPartySize()));

                if(gc.getPlayerPartySize()==gc.getCharactersQuantity()){
                    createPartyButton.setVisible(false);
                }
                else{
                    createPartyButton.setVisible(true);
                }
                if(gc.getSelectedCharacterFactory()!=null) {
                    charactersMana.setVisible(gc.getSelectedCharacterFactory().isMagicFactory());
                }
                if(gc.getPlayerPartySize()>0){
                    ArrayList <String> names = new ArrayList<>();
                    for(ICharacter c: gc.getPlayerParty()){
                        names.add(c.getName());
                    }
                    ObservableList<String> oList = FXCollections.observableArrayList(names);
                    currentCharacters.setItems(oList);
                    currentCharacters.setVisible(true);
                }
                else{
                    currentCharacters.setVisible(false);
                }

                String selectedInPartyName = (String)currentCharacters.getValue();
                for(int i=0; i<gc.getPlayerPartySize(); i++){
                    gc.setSelectedCharacterFromPlayerParty(i);
                    if(gc.getSelectedCharacterName().equals(selectedInPartyName)){
                        inPartyCharacterName.setText(gc.getSelectedCharacterName());
                        inPartyCharacterName.setVisible(true);
                        inPartyCharacterHP.setText(String.valueOf(gc.getSelectedCharacterMaxHP()));
                        inPartyCharacterName.setVisible(true);
                        inPartyCharacterDP.setText(String.valueOf(gc.getSelectedCharacterDP()));
                        inPartyCharacterDP.setVisible(true);
                        if (gc.selectedCharacterIsMagic()) {
                            inPartyCharacterMana.setText(String.valueOf(gc.getSelectedCharacterMaxMana()));
                            inPartyCharacterMana.setVisible(true);
                        }
                        else{
                            inPartyCharacterMana.setVisible(false);
                        }
                        break;
                    }
                }
            }
        };
        timer.start();
    }

    private String getSelectedCharacterMiniSpritePATH(){
        ArrayList<String> enemiesMiniSprites = new ArrayList<>();
        enemiesMiniSprites.add("ruinknightminisprite.png");
        enemiesMiniSprites.add("wyvernminisprite.png");
        enemiesMiniSprites.add("kaneminisprite.png");
        enemiesMiniSprites.add("pegasusknightminisprite.png");
        String thiefMiniSprite = "thiefminisprite.png";
        String engineerMiniSprite = "engineerminisprite.png";
        String blackMageMiniSprite = "blackmageminisprite.png";
        String whiteMageMiniSprite = "whitemageminisprite.png";
        String knightMiniSprite = "knightminisprite.png";

        if (gc.selectedCharacterIsKnight()){
            return knightMiniSprite;
        }
        if(gc.selectedCharacterIsThief()){
            return thiefMiniSprite;
        }
        if(gc.selectedCharacterIsBlackMage()){
            return blackMageMiniSprite;
        }
        if(gc.selectedCharacterIsWhiteMage()){
            return whiteMageMiniSprite;
        }
        if(gc.selectedCharacterIsEngineer()){
            return engineerMiniSprite;
        }
        else{
            int randomIndex = ThreadLocalRandom.current().nextInt(0, enemiesMiniSprites.size());
            return enemiesMiniSprites.get(randomIndex);
        }
    }

    private void toSelectInventory(Stage stage) throws FileNotFoundException {

        List<String> usedWeaponNames = new ArrayList<>();

        gc.setSelectedCharacterFactory(0);
        nextWeaponMenuButton = ButtonWithImage("nextbutton.png", 350, 500);
        createWeaponButton = ButtonWithImage("addbutton.png", 380, 320);
        removeWeaponButton = ButtonWithImage("removebutton.png", 850, 500);
        nextWeaponMenuButton.setVisible(true);
        createWeaponButton.setVisible(true);
        removeWeaponButton.setVisible(false);


        Group settingInventoryView = new Group();
        Scene settingInventoryScene = new Scene(settingInventoryView, width, height);
        stage.setScene(settingInventoryScene);
        ImageView initTeamBg = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "selectweaponbg.png")));


        settingInventoryView.getChildren().add(initTeamBg);

        gc.setSelectedWeaponFactory(0);

        Button AxeFactoryButton = SelectFactoryButton("axesprite.png", 1, 1);
        Button SwordFactoryButton = SelectFactoryButton("swordsprite.png", 2, 1);
        Button StaffFactoryButton = SelectFactoryButton("staffsprite.png", 1, 2);
        Button KnifeFactoryButton = SelectFactoryButton("knifesprite.png", 2, 2);
        Button BowFactoryButton = SelectFactoryButton("bowsprite.png", 1, 3);

        TextField weaponsName = textField(360, 175);
        TextField weaponsPower = textField(360, 210);
        TextField weaponsWeight = textField(360, 245);

        weaponsMagic = textField(360, 280);
        weaponsMagic.setVisible(false);

        AxeFactoryButton.setOnAction(event -> gc.setSelectedWeaponFactory(4));
        SwordFactoryButton.setOnAction(event -> gc.setSelectedWeaponFactory(3));
        StaffFactoryButton.setOnAction(event -> gc.setSelectedWeaponFactory(1));
        KnifeFactoryButton.setOnAction(event -> gc.setSelectedWeaponFactory(2));
        BowFactoryButton.setOnAction(event -> gc.setSelectedWeaponFactory(0));

        currentWeapons = desplegableWeaponList(gc.getPlayerInventory(), 690, 140);
        currentWeapons.setVisible(true);

        weaponNumLabel.setFont(new Font("Arial", 25.0));
        weaponNumLabel.setLayoutX(930);
        weaponNumLabel.setLayoutY(108);

        inInventoryWeaponName.setFont(new Font("Arial", 20.0));
        inInventoryWeaponName.setLayoutX(780);
        inInventoryWeaponName.setLayoutY(345);

        inInventoryWeaponPower.setFont(new Font("Arial", 20.0));
        inInventoryWeaponPower.setLayoutX(780);
        inInventoryWeaponPower.setLayoutY(382);

        inInventoryWeaponWeight.setFont(new Font("Arial", 20.0));
        inInventoryWeaponWeight.setLayoutX(780);
        inInventoryWeaponWeight.setLayoutY(418);

        inInventoryWeaponMagic.setFont(new Font("Arial", 20.0));
        inInventoryWeaponMagic.setLayoutX(780);
        inInventoryWeaponMagic.setLayoutY(455);

        selectedWeaponMiniSprite.setImage(new Image(new FileInputStream(RESOURCE_PATH + "kaneminisprite.png")));
        selectedWeaponMiniSprite.setLayoutX(810);
        selectedWeaponMiniSprite.setLayoutY(140);
        selectedWeaponMiniSprite.setFitWidth(110);
        selectedWeaponMiniSprite.setPreserveRatio(true);
        selectedWeaponMiniSprite.setVisible(false);

        setWeaponTimer();

        settingInventoryView.getChildren().add(nextWeaponMenuButton);
        settingInventoryView.getChildren().add(createWeaponButton);
        settingInventoryView.getChildren().add(removeWeaponButton);


        settingInventoryView.getChildren().add(AxeFactoryButton);
        settingInventoryView.getChildren().add(SwordFactoryButton);
        settingInventoryView.getChildren().add(StaffFactoryButton);
        settingInventoryView.getChildren().add(KnifeFactoryButton);
        settingInventoryView.getChildren().add(BowFactoryButton);

        settingInventoryView.getChildren().add(weaponsName);
        settingInventoryView.getChildren().add(weaponsPower);
        settingInventoryView.getChildren().add(weaponsWeight);
        settingInventoryView.getChildren().add(weaponsMagic);
        settingInventoryView.getChildren().add(weaponNumLabel);

        settingInventoryView.getChildren().add(currentWeapons);

        settingInventoryView.getChildren().add(inInventoryWeaponName);
        settingInventoryView.getChildren().add(inInventoryWeaponPower);
        settingInventoryView.getChildren().add(inInventoryWeaponWeight);
        settingInventoryView.getChildren().add(inInventoryWeaponMagic);

        settingInventoryView.getChildren().add(selectedWeaponMiniSprite);


        createWeaponButton.setOnAction(
                event -> {if(gc.getSelectedWeaponFactory()!=null){
                    if(!usedWeaponNames.contains(weaponsName.getText()) && !weaponsName.getText().equals("")){
                        gc.setSelectedWeaponFactoryPower(Integer.parseInt(weaponsPower.getText()));
                        gc.setSelectedWeaponFactoryPower(Integer.parseInt(weaponsWeight.getText()));
                        if(!weaponsMagic.getText().equals("")) {
                            gc.setSelectedWeaponFactoryMagicPower(Integer.parseInt(weaponsMagic.getText()));
                        }
                        usedWeaponNames.add(weaponsName.getText());
                        gc.selectedWeaponFactoryProduce(weaponsName.getText());
                        weaponsName.clear();
                    }
                }
                });

        removeWeaponButton.setOnAction(event -> {
            usedWeaponNames.remove(gc.getSelectedWeaponName());
            removeWeaponButton.setVisible(false);
            gc.removeSelectedWeaponFromInventory();
            inInventoryWeaponName.setText("");
            inInventoryWeaponPower.setText("");
            inInventoryWeaponWeight.setText("");
            inInventoryWeaponMagic.setText("");
            selectedWeaponMiniSprite.setVisible(false);


        });

        currentWeapons.setOnAction(event -> {
            if(!(currentWeapons.getValue()==null)){
                try {
                    InputStream miniSpriteStream = new FileInputStream(RESOURCE_PATH + getSelectedWeaponMiniSpritePATH());
                    selectedWeaponMiniSprite.setImage(new Image(miniSpriteStream));
                    selectedWeaponMiniSprite.setVisible(true);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                removeWeaponButton.setVisible(true);
            }
        });



    }


    private @NotNull ComboBox desplegableWeaponList(List<IWeapon> currentWeapons, int x, int y){
        ObservableList<IWeapon> characters = FXCollections.observableArrayList(currentWeapons);
        final ComboBox optionsBox = new ComboBox(characters);
        optionsBox.setLayoutX(x);
        optionsBox.setLayoutY(y);
        return optionsBox;
    }


    private String getSelectedWeaponMiniSpritePATH(){
        String axeMiniSprite = "axesprite.png";
        String swordMiniSprite = "swordsprite.png";
        String bowMiniSprite = "bowsprite.png";
        String staffMiniSprite = "staffsprite.png";
        String knifeMiniSprite = "knifesprite.png";

        if (gc.selectedWeaponIsAxe()){
            return axeMiniSprite;
        }
        if (gc.selectedWeaponIsSword()){
            return swordMiniSprite;
        }
        if (gc.selectedWeaponIsBow()){
            return bowMiniSprite;
        }
        if (gc.selectedWeaponIsStaff()){
            return staffMiniSprite;
        }
        else{
            return knifeMiniSprite;
        }

    }



    private void setWeaponTimer(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                weaponNumLabel.setText(String.valueOf(gc.getInventorySize()));

                if(gc.getSelectedWeaponFactory() !=null) {
                    weaponsMagic.setVisible(gc.getSelectedWeaponFactory().isMagic());
                }
                if(gc.getInventorySize()>0){
                    ArrayList <String> names = new ArrayList<>();
                    for(IWeapon c: gc.getPlayerInventory()){
                        names.add(c.getName());
                    }
                    ObservableList<String> oList = FXCollections.observableArrayList(names);
                    currentWeapons.setItems(oList);
                    currentWeapons.setVisible(true);
                }
                else{
                    currentWeapons.setVisible(false);
                }

                String selectedInInventoryName = (String)currentWeapons.getValue();
                for(int i=0; i<gc.getInventorySize(); i++){
                    gc.setSelectedWeapon(i);
                    if(gc.getSelectedWeaponName().equals(selectedInInventoryName)){
                        inInventoryWeaponName.setText(gc.getSelectedWeaponName());
                        inInventoryWeaponName.setVisible(true);
                        inInventoryWeaponPower.setText(String.valueOf(gc.getSelectedWeaponPower()));
                        inInventoryWeaponPower.setVisible(true);
                        inInventoryWeaponWeight.setText(String.valueOf(gc.getSelectedWeaponWeight()));
                        inInventoryWeaponWeight.setVisible(true);
                        if (gc.selectedWeaponIsStaff()) {
                            inInventoryWeaponMagic.setText(String.valueOf(gc.getSelectedWeaponMagicPower()));
                            inInventoryWeaponMagic.setVisible(true);
                        }
                        else{
                            inInventoryWeaponMagic.setVisible(false);
                        }
                        break;
                    }
                }
            }
        };
        timer.start();
    }


}