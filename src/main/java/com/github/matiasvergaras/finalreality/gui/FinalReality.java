package com.github.matiasvergaras.finalreality.gui;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private Group currentGroup = new Group();
    AnimationTimer timer;

    //animated variables of SetParty scene (init phase).
    private Button nextPlayerPartyMenuButton;
    private Button createPlayerPartyButton;
    private Button removePlayerPartyButton;
    private final Label playerCharactersNumLabel = new Label();
    private final Label inPlayerPartyCharacterName = new Label();
    private final Label inPlayerPartyCharacterHP = new Label();
    private final Label inPlayerPartyCharacterDP = new Label();
    private final Label inPlayerPartyCharacterMana = new Label();
    private final Label inPlayerPartyCharacterClass = new Label();
    private TextField charactersMana;
    private ComboBox playerCurrentCharacters;
    private final ImageView playerSelectedCharacterMiniSprite = new ImageView();

    //animated variables of SetInventory scene (init phase).
    private Button nextWeaponMenuButton;
    private Button createWeaponButton;
    private Button removeWeaponButton;
    private Button backWeaponMenuButton;
    private TextField weaponsMagic;
    private final Label weaponNumLabel = new Label();
    private final Label inInventoryWeaponClass = new Label();
    private final Label inInventoryWeaponName = new Label();
    private final Label inInventoryWeaponPower = new Label();
    private final Label inInventoryWeaponWeight = new Label();
    private final Label inInventoryWeaponMagic = new Label();
    private ComboBox currentWeapons;
    private final ImageView selectedWeaponMiniSprite = new ImageView();

    //animated variables of SetCPUParty scene (init phase).
    Map<String, Integer> CPUNamesAndSkinsID = new HashMap<>();
    ArrayList<String> enemiesMiniSprites = new ArrayList<>();
    private Button nextCPUPartyMenuButton;
    private Button createCPUPartyButton;
    private Button removeCPUPartyButton;
    private Button backCPUPartyButton;
    private final Label CPUCharactersNumLabel = new Label();
    private final Label inCPUPartyCharacterName = new Label();
    private final Label inCPUPartyCharacterHP = new Label();
    private final Label inCPUPartyCharacterDP = new Label();
    private final Label inCPUPartyCharacterPower = new Label();
    private final Label inCPUPartyCharacterWeight = new Label();
    private final Label inCPUPartyCharacterClass = new Label();
    private ComboBox currentCPUCharacters;
    private final ImageView CPUSelectedCharacterMiniSprite = new ImageView();

    //animated variables of Equip Weapon scene (init or active phase).
    private Button nextEquipWeaponMenuButton;
    private Button equipButton;
    private Button backEquipWeaponMenuButton;
    private final Label equipMenuCharacterClass = new Label();
    private final Label equipMenuCharacterName = new Label();
    private final Label equipMenuCharacterHP = new Label();
    private final Label equipMenuCharacterDP = new Label();
    private final Label equipMenuCharacterMana = new Label();
    private final Label equipMenuWeaponClass = new Label();
    private final Label equipMenuWeaponName = new Label();
    private final Label equipMenuWeaponOwner = new Label();
    private final Label equipMenuWeaponPower = new Label();
    private final Label equipMenuWeaponWeight = new Label();
    private final Label equipMenuWeaponMagic = new Label();
    private ComboBox alivePlayerCharacters;
    private ComboBox equipMenuWeapons;
    private final ImageView equipMenuCharacterSprite = new ImageView();
    private final ImageView equipMenuWeaponSprite = new ImageView();

    //animated variables of Battle scene - player turn
    private Button changeWeaponsButton;
    private Button startAttackButton;
    private Button cancelAttackButton;
    private Button sendAttackButton;
    private final Label battleActiveCharacterHP = new Label();
    private final Label battleActiveCharacterDP = new Label();
    private final Label battleActiveCharacterMana = new Label();
    private final Label battleEquippedWeaponName = new Label();
    private final Label battleEquippedWeaponPower = new Label();
    private final Label battleEquippedWeaponWeight = new Label();
    private final Label battleEquippedWeaponMagic = new Label();
    private final ImageView battleActiveCharacterSprite = new ImageView();
    private final ImageView battleEquippedWeaponSprite = new ImageView();

    //animated variables of Battle scene - select target
    private ComboBox battleCPUAliveCharacters;
    private final Label battleTargetCharacterHP = new Label();
    private final Label battleTargetCharacterDP = new Label();
    private final Label battleTargetCharacterPower = new Label();
    private final Label battleTargetCharacterWeight = new Label();
    private final ImageView battleTargetCharacterSprite = new ImageView();

    //animated variables of Battle scene - attack resume
    private final ImageView resumeAttackerCharacterSprite = new ImageView();
    private final ImageView resumeDefenderSprite = new ImageView();
    private final Label resumeAttackerHP = new Label();
    private final Label resumeAttackerDP = new Label();
    private final Label resumeAttackerPower = new Label();
    private final Label resumeDefenderHP = new Label();
    private final Label resumeDefenderDP = new Label();
    private Button resumeOKButton;

    @Override
    public void start(@NotNull Stage primaryStage) throws FileNotFoundException {
        gc.setGUI(this);
        primaryStage.setTitle("Final reality");
        currentGroup = new Group();

        Scene scene = new Scene(currentGroup, width, height);

        //In order to have enemies of different aspect, we will set a list with some skins.
        enemiesMiniSprites.add("ruinknightminisprite.png");
        enemiesMiniSprites.add("wyvernminisprite.png");
        enemiesMiniSprites.add("kaneminisprite.png");
        enemiesMiniSprites.add("pegasusknightminisprite.png");

        //Name form
        TextField nameInput = nameInput();
        Button startButton = startButton();
        ComboBox numberOfCharacters = numOfCharacterOptions();
        startButton.setOnAction(event -> {
            try {
                gc = new GameController(nameInput.getText(), "CPU", (int)numberOfCharacters.getValue());
                toSetTeam(primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        ImageView background = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "entrybackground.png")));

        currentGroup.getChildren().add(background);
        currentGroup.getChildren().add(nameInput);
        currentGroup.getChildren().add(startButton);
        currentGroup.getChildren().add(numberOfCharacters);

        primaryStage.setScene(scene);
        primaryStage.show();


    }


    private void toSetTeam(Stage stage) throws FileNotFoundException {
        List<String> usedCharacterNames = new ArrayList<>();
        currentGroup = new Group();
        Scene initializingScene = new Scene(currentGroup, width, height);
        stage.setScene(initializingScene);
        var initTeamBg = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "selectteambg.png")));
        nextPlayerPartyMenuButton = ButtonWithImage("nextbutton.png", 450, 500);
        createPlayerPartyButton = ButtonWithImage("addbutton.png", 380, 320);
        removePlayerPartyButton = ButtonWithImage("removebutton.png", 850, 500);
        nextPlayerPartyMenuButton.setVisible(true);
        createPlayerPartyButton.setVisible(false);
        removePlayerPartyButton.setVisible(false);

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

        playerCurrentCharacters = desplegableCharacterList(gc.getPlayerParty(), 690, 140);
        playerCurrentCharacters.setVisible(true);

        playerCharactersNumLabel.setFont(new Font("Arial", 25.0));
        playerCharactersNumLabel.setLayoutX(930);
        playerCharactersNumLabel.setLayoutY(108);

        inPlayerPartyCharacterClass.setFont(new Font("Arial", 20.0));
        inPlayerPartyCharacterClass.setLayoutX(780);
        inPlayerPartyCharacterClass.setLayoutY(308);

        inPlayerPartyCharacterName.setFont(new Font("Arial", 20.0));
        inPlayerPartyCharacterName.setLayoutX(780);
        inPlayerPartyCharacterName.setLayoutY(345);

        inPlayerPartyCharacterHP.setFont(new Font("Arial", 20.0));
        inPlayerPartyCharacterHP.setLayoutX(780);
        inPlayerPartyCharacterHP.setLayoutY(382);

        inPlayerPartyCharacterDP.setFont(new Font("Arial", 20.0));
        inPlayerPartyCharacterDP.setLayoutX(780);
        inPlayerPartyCharacterDP.setLayoutY(418);

        inPlayerPartyCharacterMana.setFont(new Font("Arial", 20.0));
        inPlayerPartyCharacterMana.setLayoutX(780);
        inPlayerPartyCharacterMana.setLayoutY(455);

        playerSelectedCharacterMiniSprite.setImage(new Image(new FileInputStream(RESOURCE_PATH + "kaneminisprite.png")));
        playerSelectedCharacterMiniSprite.setLayoutX(790);
        playerSelectedCharacterMiniSprite.setLayoutY(170);
        playerSelectedCharacterMiniSprite.setFitWidth(130);
        playerSelectedCharacterMiniSprite.setPreserveRatio(true);
        playerSelectedCharacterMiniSprite.setVisible(false);

        setTeamTimer();

        currentGroup.getChildren().add(initTeamBg);

        currentGroup.getChildren().add(nextPlayerPartyMenuButton);
        currentGroup.getChildren().add(createPlayerPartyButton);
        currentGroup.getChildren().add(removePlayerPartyButton);

        currentGroup.getChildren().add(EngineerFactoryButton);
        currentGroup.getChildren().add(KnightFactoryButton);
        currentGroup.getChildren().add(BlackMageFactoryButton);
        currentGroup.getChildren().add(WhiteMageFactoryButton);
        currentGroup.getChildren().add(ThiefFactoryButton);

        currentGroup.getChildren().add(characterName);
        currentGroup.getChildren().add(charactersHP);
        currentGroup.getChildren().add(charactersDP);
        currentGroup.getChildren().add(charactersMana);
        currentGroup.getChildren().add(playerCharactersNumLabel);

        currentGroup.getChildren().add(playerCurrentCharacters);

        currentGroup.getChildren().add(inPlayerPartyCharacterClass);
        currentGroup.getChildren().add(inPlayerPartyCharacterName);
        currentGroup.getChildren().add(inPlayerPartyCharacterHP);
        currentGroup.getChildren().add(inPlayerPartyCharacterDP);
        currentGroup.getChildren().add(inPlayerPartyCharacterMana);

        currentGroup.getChildren().add(playerSelectedCharacterMiniSprite);


        createPlayerPartyButton.setOnAction(
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

        removePlayerPartyButton.setOnAction(event -> {
            usedCharacterNames.remove(gc.getSelectedCharacterName());
            removePlayerPartyButton.setVisible(false);
            gc.removeSelectedCharacterFromItsParty();
            inPlayerPartyCharacterClass.setText("");
            inPlayerPartyCharacterName.setText("");
            inPlayerPartyCharacterHP.setText("");
            inPlayerPartyCharacterDP.setText("");
            inPlayerPartyCharacterMana.setText("");
            playerSelectedCharacterMiniSprite.setVisible(false);

        });

        playerCurrentCharacters.setOnAction(event -> {
            if(!(playerCurrentCharacters.getValue()==null)){
                try {
                    InputStream miniSpriteStream = new FileInputStream(RESOURCE_PATH + getSelectedPlayerCharacterModeSpritePATH("mini"));
                    playerSelectedCharacterMiniSprite.setImage(new Image(miniSpriteStream));
                    playerSelectedCharacterMiniSprite.setVisible(true);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                removePlayerPartyButton.setVisible(true);
            }
        });

        nextPlayerPartyMenuButton.setOnAction(event -> {
            if(gc.getPlayerPartySize()==gc.getCharactersQuantity()){
                try {
                    timer.stop();
                    toSelectInventory(stage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void setTeamTimer(){
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerCharactersNumLabel.setText(String.valueOf(gc.getPlayerPartySize()));

                if(gc.getPlayerPartySize()==gc.getCharactersQuantity()){
                    createPlayerPartyButton.setVisible(false);
                }
                else{
                    createPlayerPartyButton.setVisible(true);
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
                    playerCurrentCharacters.setItems(oList);
                    playerCurrentCharacters.setVisible(true);
                }
                else{
                    playerCurrentCharacters.setVisible(false);
                }

                String selectedInPartyName = (String) playerCurrentCharacters.getValue();
                for(int i=0; i<gc.getPlayerPartySize(); i++){
                    gc.setSelectedCharacterFromPlayerParty(i);

                    if(gc.getSelectedCharacterName().equals(selectedInPartyName)){
                        inPlayerPartyCharacterClass.setText(getSelectedCharacterClassAsString());
                        inPlayerPartyCharacterClass.setVisible(true);
                        inPlayerPartyCharacterName.setText(gc.getSelectedCharacterName());
                        inPlayerPartyCharacterName.setVisible(true);
                        inPlayerPartyCharacterHP.setText(String.valueOf(gc.getSelectedCharacterMaxHP()));
                        inPlayerPartyCharacterHP.setVisible(true);
                        inPlayerPartyCharacterDP.setText(String.valueOf(gc.getSelectedCharacterDP()));
                        inPlayerPartyCharacterDP.setVisible(true);
                        if (gc.selectedCharacterIsMagic()) {
                            inPlayerPartyCharacterMana.setText(String.valueOf(gc.getSelectedCharacterMaxMana()));
                            inPlayerPartyCharacterMana.setVisible(true);
                        }
                        else{
                            inPlayerPartyCharacterMana.setVisible(false);
                        }
                        break;
                    }
                }
            }
        };
        timer.start();
    }

    private void toSelectInventory(Stage stage) throws FileNotFoundException {

        List<String> usedWeaponNames = new ArrayList<>();

        gc.setSelectedCharacterFactory(0);
        nextWeaponMenuButton = ButtonWithImage("nextbutton.png", 450, 500);
        createWeaponButton = ButtonWithImage("addbutton.png", 380, 320);
        removeWeaponButton = ButtonWithImage("removebutton.png", 850, 500);
        backWeaponMenuButton = ButtonWithImage("backbutton.png", 200, 500);
        nextWeaponMenuButton.setVisible(true);
        createWeaponButton.setVisible(true);
        removeWeaponButton.setVisible(false);
        backWeaponMenuButton.setVisible(true);


        currentGroup = new Group();
        Scene settingInventoryScene = new Scene(currentGroup, width, height);
        stage.setScene(settingInventoryScene);
        ImageView initTeamBg = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "selectweaponbg.png")));


        currentGroup.getChildren().add(initTeamBg);

        gc.setSelectedWeaponFactory(4);

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

        inInventoryWeaponClass.setFont(new Font("Arial", 20.0));
        inInventoryWeaponClass.setLayoutX(780);
        inInventoryWeaponClass.setLayoutY(308);

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

        currentGroup.getChildren().add(nextWeaponMenuButton);
        currentGroup.getChildren().add(createWeaponButton);
        currentGroup.getChildren().add(removeWeaponButton);
        currentGroup.getChildren().add(backWeaponMenuButton);


        currentGroup.getChildren().add(AxeFactoryButton);
        currentGroup.getChildren().add(SwordFactoryButton);
        currentGroup.getChildren().add(StaffFactoryButton);
        currentGroup.getChildren().add(KnifeFactoryButton);
        currentGroup.getChildren().add(BowFactoryButton);

        currentGroup.getChildren().add(weaponsName);
        currentGroup.getChildren().add(weaponsPower);
        currentGroup.getChildren().add(weaponsWeight);
        currentGroup.getChildren().add(weaponsMagic);
        currentGroup.getChildren().add(weaponNumLabel);

        currentGroup.getChildren().add(currentWeapons);

        currentGroup.getChildren().add(inInventoryWeaponClass);
        currentGroup.getChildren().add(inInventoryWeaponName);
        currentGroup.getChildren().add(inInventoryWeaponPower);
        currentGroup.getChildren().add(inInventoryWeaponWeight);
        currentGroup.getChildren().add(inInventoryWeaponMagic);

        currentGroup.getChildren().add(selectedWeaponMiniSprite);


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
            inInventoryWeaponClass.setText("");
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

        backWeaponMenuButton.setOnAction(event -> {
            try {
                timer.stop();
                toSetTeam(stage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        nextWeaponMenuButton.setOnAction(event -> {
            if(gc.getPlayerInventory().size()>0){
                try {
                    timer.stop();
                    toSetCPUTeam(stage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    private void setWeaponTimer(){
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                weaponNumLabel.setText(String.valueOf(gc.getInventorySize()));

                if(gc.getSelectedWeaponFactory() !=null) {
                    weaponsMagic.setVisible(gc.getSelectedWeaponFactory().isMagic());
                }
                updateList(currentWeapons);

                String selectedInInventoryName = (String)currentWeapons.getValue();

                for(int i=0; i<gc.getInventorySize(); i++){
                    gc.setSelectedWeapon(i);
                    if(gc.getSelectedWeaponName().equals(selectedInInventoryName)){
                        inInventoryWeaponClass.setText(getSelectedWeaponClassAsString());
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

    private void toSetCPUTeam(Stage stage) throws FileNotFoundException {
        currentGroup = new Group();
        Scene initializingScene = new Scene(currentGroup, width, height);
        stage.setScene(initializingScene);
        var initCPUTeamBg = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "setcputeambg.png")));
        nextCPUPartyMenuButton = ButtonWithImage("nextbutton.png", 450, 500);
        createCPUPartyButton = ButtonWithImage("addbutton.png", 380, 380);
        removeCPUPartyButton = ButtonWithImage("removebutton.png", 850, 500);
        backCPUPartyButton = ButtonWithImage("backbutton.png", 200, 500);
        nextCPUPartyMenuButton.setVisible(true);
        createCPUPartyButton.setVisible(true);
        removeCPUPartyButton.setVisible(false);

        gc.setSelectedCharacterFactory(5);

        Button EnemyFactoryButton = SelectFactoryButton("darksol.png", 1, 1);

        TextField CPUCharacterName = textField(360, 175);
        TextField CPUCharactersHP = textField(360, 210);
        TextField CPUCharactersDP = textField(360, 245);
        TextField CPUCharactersWeight = textField(360,280);
        TextField CPUCharactersPower = textField(360, 315);

        EnemyFactoryButton.setOnAction(event -> gc.setSelectedCharacterFactory(5));

        currentCPUCharacters = desplegableCharacterList(gc.getCPUParty(), 690, 140);
        currentCPUCharacters.setVisible(true);

        CPUCharactersNumLabel.setFont(new Font("Arial", 25.0));
        CPUCharactersNumLabel.setLayoutX(930);
        CPUCharactersNumLabel.setLayoutY(108);

        inCPUPartyCharacterClass.setFont(new Font("Arial", 20.0));
        inCPUPartyCharacterClass.setLayoutX(780);
        inCPUPartyCharacterClass.setLayoutY(308);

        inCPUPartyCharacterName.setFont(new Font("Arial", 20.0));
        inCPUPartyCharacterName.setLayoutX(780);
        inCPUPartyCharacterName.setLayoutY(345);

        inCPUPartyCharacterHP.setFont(new Font("Arial", 20.0));
        inCPUPartyCharacterHP.setLayoutX(780);
        inCPUPartyCharacterHP.setLayoutY(382);

        inCPUPartyCharacterDP.setFont(new Font("Arial", 20.0));
        inCPUPartyCharacterDP.setLayoutX(780);
        inCPUPartyCharacterDP.setLayoutY(418);

        inCPUPartyCharacterPower.setFont(new Font("Arial", 20.0));
        inCPUPartyCharacterPower.setLayoutX(780);
        inCPUPartyCharacterPower.setLayoutY(455);

        inCPUPartyCharacterWeight.setFont(new Font("Arial", 20.0));
        inCPUPartyCharacterWeight.setLayoutX(780);
        inCPUPartyCharacterWeight.setLayoutY(492);

        CPUSelectedCharacterMiniSprite.setImage(new Image(new FileInputStream(RESOURCE_PATH + "kaneminisprite.png")));
        CPUSelectedCharacterMiniSprite.setLayoutX(790);
        CPUSelectedCharacterMiniSprite.setLayoutY(170);
        CPUSelectedCharacterMiniSprite.setFitWidth(130);
        CPUSelectedCharacterMiniSprite.setPreserveRatio(true);
        CPUSelectedCharacterMiniSprite.setVisible(false);

        setCPUTeamTimer();

        currentGroup.getChildren().add(initCPUTeamBg);

        currentGroup.getChildren().add(nextCPUPartyMenuButton);
        currentGroup.getChildren().add(createCPUPartyButton);
        currentGroup.getChildren().add(removeCPUPartyButton);
        currentGroup.getChildren().add(backCPUPartyButton);

        currentGroup.getChildren().add(EnemyFactoryButton);

        currentGroup.getChildren().add(CPUCharacterName);
        currentGroup.getChildren().add(CPUCharactersHP);
        currentGroup.getChildren().add(CPUCharactersDP);
        currentGroup.getChildren().add(CPUCharactersPower);
        currentGroup.getChildren().add(CPUCharactersWeight);
        currentGroup.getChildren().add(CPUCharactersNumLabel);

        currentGroup.getChildren().add(currentCPUCharacters);

        currentGroup.getChildren().add(inCPUPartyCharacterClass);
        currentGroup.getChildren().add(inCPUPartyCharacterName);
        currentGroup.getChildren().add(inCPUPartyCharacterHP);
        currentGroup.getChildren().add(inCPUPartyCharacterDP);
        currentGroup.getChildren().add(inCPUPartyCharacterPower);
        currentGroup.getChildren().add(inCPUPartyCharacterWeight);

        currentGroup.getChildren().add(CPUSelectedCharacterMiniSprite);


        createCPUPartyButton.setOnAction(
                event -> {if(gc.getSelectedCharacterFactory()!=null){
                    if(!CPUNamesAndSkinsID.containsKey(CPUCharacterName.getText()) && !CPUCharacterName.getText().equals("")){
                        gc.setSelectedCharacterFactoryHP(Integer.parseInt(CPUCharactersHP.getText()));
                        gc.setSelectedCharacterFactoryDP(Integer.parseInt(CPUCharactersDP.getText()));
                        gc.setSelectedCharacterFactoryWeight(Integer.parseInt(CPUCharactersWeight.getText()));
                        gc.setSelectedCharacterFactoryPower(Integer.parseInt(CPUCharactersPower.getText()));
                        gc.selectedCharacterFactoryProduce(CPUCharacterName.getText());
                        CPUNamesAndSkinsID.put(CPUCharacterName.getText(),
                                ThreadLocalRandom.current().nextInt(0, enemiesMiniSprites.size()));
                        CPUCharacterName.clear();
                    }
                }
                });

        removeCPUPartyButton.setOnAction(event -> {
            CPUNamesAndSkinsID.remove(gc.getSelectedCharacterName());
            removeCPUPartyButton.setVisible(false);
            gc.removeSelectedCharacterFromItsParty();
            inCPUPartyCharacterClass.setText("");
            inCPUPartyCharacterName.setText("");
            inCPUPartyCharacterHP.setText("");
            inCPUPartyCharacterDP.setText("");
            inCPUPartyCharacterPower.setText("");
            inCPUPartyCharacterWeight.setText("");
            CPUSelectedCharacterMiniSprite.setVisible(false);

        });

        currentCPUCharacters.setOnAction(event -> {
            if(!(currentCPUCharacters.getValue()==null)){
                try {
                    InputStream miniSpriteStream = new FileInputStream(RESOURCE_PATH +
                            enemiesMiniSprites.get(CPUNamesAndSkinsID.get(currentCPUCharacters.getValue())));
                    CPUSelectedCharacterMiniSprite.setImage(new Image(miniSpriteStream));
                    CPUSelectedCharacterMiniSprite.setVisible(true);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                removeCPUPartyButton.setVisible(true);
            }
        });

        nextCPUPartyMenuButton.setOnAction(event -> {
            try {
                timer.stop();
                toEquipWeapons(stage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });

        backCPUPartyButton.setOnAction(event -> {
            try {
                timer.stop();
                toSelectInventory(stage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setCPUTeamTimer(){
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                CPUCharactersNumLabel.setText(String.valueOf(gc.getCPUPartySize()));

                if(gc.getCPUPartySize()>0){
                    ArrayList <String> names = new ArrayList<>();
                    for(ICharacter c: gc.getCPUParty()){
                        names.add(c.getName());
                    }
                    ObservableList<String> oList = FXCollections.observableArrayList(names);
                    currentCPUCharacters.setItems(oList);
                    currentCPUCharacters.setVisible(true);
                }
                else{
                    currentCPUCharacters.setVisible(false);
                }

                String selectedInPartyName = (String)currentCPUCharacters.getValue();
                for(int i=0; i<gc.getCPUPartySize(); i++){
                    gc.setSelectedCharacterFromCPUParty(i);
                    if(gc.getSelectedCharacterName().equals(selectedInPartyName)){
                        inCPUPartyCharacterClass.setText(getSelectedCharacterClassAsString());
                        inCPUPartyCharacterClass.setVisible(true);
                        inCPUPartyCharacterName.setText(gc.getSelectedCharacterName());
                        inCPUPartyCharacterName.setVisible(true);
                        inCPUPartyCharacterHP.setText(String.valueOf(gc.getSelectedCharacterMaxHP()));
                        inCPUPartyCharacterHP.setVisible(true);
                        inCPUPartyCharacterDP.setText(String.valueOf(gc.getSelectedCharacterDP()));
                        inCPUPartyCharacterDP.setVisible(true);
                        inCPUPartyCharacterPower.setText(String.valueOf(gc.getSelectedCharacterPower()));
                        inCPUPartyCharacterPower.setVisible(true);
                        inCPUPartyCharacterWeight.setText(String.valueOf(gc.getSelectedCharacterWeight()));
                        inCPUPartyCharacterWeight.setVisible(true);
                        break;
                    }
                }
            }
        };
        timer.start();
    }

    private void toEquipWeapons(Stage stage) throws FileNotFoundException {

        nextEquipWeaponMenuButton = ButtonWithImage("nextbutton.png", 790, 500);
        equipButton = ButtonWithImage("equipbutton.png", 740, 320);
        backEquipWeaponMenuButton = ButtonWithImage("backbutton.png", 660, 500);
        nextEquipWeaponMenuButton.setVisible(true);
        equipButton.setVisible(true);
        backEquipWeaponMenuButton.setVisible(true);


        currentGroup = new Group();
        Scene equippingScene = new Scene(currentGroup, width, height);
        stage.setScene(equippingScene);
        ImageView equippingBg = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "equipbackground.png")));



        equipMenuWeapons = desplegableWeaponList(gc.getPlayerInventory(), 750, 205);
        equipMenuWeapons.setVisible(true);

        equipMenuWeaponOwner.setFont(new Font("Arial", 20.0));
        equipMenuWeaponOwner.setLayoutX(430);
        equipMenuWeaponOwner.setLayoutY(320);

        equipMenuWeaponClass.setFont(new Font("Arial", 20.0));
        equipMenuWeaponClass.setLayoutX(430);
        equipMenuWeaponClass.setLayoutY(355);

        equipMenuWeaponName.setFont(new Font("Arial", 20.0));
        equipMenuWeaponName.setLayoutX(430);
        equipMenuWeaponName.setLayoutY(390);

        equipMenuWeaponPower.setFont(new Font("Arial", 20.0));
        equipMenuWeaponPower.setLayoutX(430);
        equipMenuWeaponPower.setLayoutY(427);

        equipMenuWeaponWeight.setFont(new Font("Arial", 20.0));
        equipMenuWeaponWeight.setLayoutX(430);
        equipMenuWeaponWeight.setLayoutY(463);

        equipMenuWeaponMagic.setFont(new Font("Arial", 20.0));
        equipMenuWeaponMagic.setLayoutX(430);
        equipMenuWeaponMagic.setLayoutY(499);

        equipMenuWeaponSprite.setImage(new Image(new FileInputStream(RESOURCE_PATH + "kaneminisprite.png")));
        equipMenuWeaponSprite.setLayoutX(390);
        equipMenuWeaponSprite.setLayoutY(180);
        equipMenuWeaponSprite.setFitWidth(70);
        equipMenuWeaponSprite.setPreserveRatio(true);
        equipMenuWeaponSprite.setVisible(false);

        alivePlayerCharacters = desplegableAliveCharacterList(gc.getPlayerParty(), 750, 140);
        alivePlayerCharacters.setVisible(true);

        equipMenuCharacterClass.setFont(new Font("Arial", 20.0));
        equipMenuCharacterClass.setLayoutX(130);
        equipMenuCharacterClass.setLayoutY(320);

        equipMenuCharacterName.setFont(new Font("Arial", 20.0));
        equipMenuCharacterName.setLayoutX(130);
        equipMenuCharacterName.setLayoutY(355);

        equipMenuCharacterHP.setFont(new Font("Arial", 20.0));
        equipMenuCharacterHP.setLayoutX(130);
        equipMenuCharacterHP.setLayoutY(392);

        equipMenuCharacterDP.setFont(new Font("Arial", 20.0));
        equipMenuCharacterDP.setLayoutX(130);
        equipMenuCharacterDP.setLayoutY(428);

        equipMenuCharacterMana.setFont(new Font("Arial", 20.0));
        equipMenuCharacterMana.setLayoutX(130);
        equipMenuCharacterMana.setLayoutY(464);

        equipMenuCharacterSprite.setImage(new Image(new FileInputStream(RESOURCE_PATH + "kaneminisprite.png")));
        equipMenuCharacterSprite.setLayoutX(100);
        equipMenuCharacterSprite.setLayoutY(150);
        equipMenuCharacterSprite.setFitWidth(110);
        equipMenuCharacterSprite.setPreserveRatio(true);
        equipMenuCharacterSprite.setVisible(false);

        setEquipMenuTimer();

        currentGroup.getChildren().add(equippingBg);
        currentGroup.getChildren().add(equipMenuWeapons);
        currentGroup.getChildren().add(equipMenuWeaponClass);
        currentGroup.getChildren().add(equipMenuWeaponOwner);
        currentGroup.getChildren().add(equipMenuWeaponName);
        currentGroup.getChildren().add(equipMenuWeaponPower);
        currentGroup.getChildren().add(equipMenuWeaponWeight);
        currentGroup.getChildren().add(equipMenuWeaponMagic);

        currentGroup.getChildren().add(alivePlayerCharacters);
        currentGroup.getChildren().add(equipMenuCharacterClass);
        currentGroup.getChildren().add(equipMenuCharacterName);
        currentGroup.getChildren().add(equipMenuCharacterHP);
        currentGroup.getChildren().add(equipMenuCharacterDP);
        currentGroup.getChildren().add(equipMenuCharacterMana);

        currentGroup.getChildren().add(equipMenuWeaponSprite);
        currentGroup.getChildren().add(equipMenuCharacterSprite);

        currentGroup.getChildren().add(nextEquipWeaponMenuButton);
        currentGroup.getChildren().add(equipButton);
        currentGroup.getChildren().add(backEquipWeaponMenuButton);


        equipButton.setOnAction(
                event-> {
                    if (gc.getSelectedCharacter() != null && gc.getSelectedWeapon() != null) {
                        gc.equipSelectedWeaponToSelectedCharacter();
                    }
                });


        alivePlayerCharacters.setOnAction(event -> {
            if(alivePlayerCharacters.getValue()!=null){
                try {
                    InputStream chibiSpriteStream = new FileInputStream(RESOURCE_PATH +
                            getSelectedPlayerCharacterModeSpritePATH("chibi"));
                    equipMenuCharacterSprite.setImage(new Image(chibiSpriteStream));
                    equipMenuCharacterSprite.setVisible(true);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        equipMenuWeapons.setOnAction(event -> {
            if(!(equipMenuWeapons.getValue()==null)){
                try {
                    InputStream miniSpriteStream = new FileInputStream(RESOURCE_PATH +
                            getSelectedWeaponMiniSpritePATH());
                    equipMenuWeaponSprite.setImage(new Image(miniSpriteStream));
                    equipMenuWeaponSprite.setVisible(true);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        //Make sure that the player will not bug the game by unequipping every character by checking that
        //at least one of them is equipped before sending the startGame message.
        nextEquipWeaponMenuButton.setOnAction(event -> {
            timer.stop();
            //Check that at least one character is still equipped
            for(int i = 0; i <gc.getPlayerPartySize(); i++){
                gc.setSelectedCharacterFromPlayerParty(i);
                if(!gc.selectedCharacterEquippedWeaponIsNull()){
                    //If this is the first time that this scene appears, then start game
                    if (gc.isInitializing()){
                        System.out.println("STARTING GAME");
                        gc.startGame();
                    }
                    //Else, it has been called in a player turn, so let's go back there.
                    else if (gc.isActive()) {
                        //toPlayerTurn(stage);
                        try {
                            toSetCPUTeam(stage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            }


        });

        backEquipWeaponMenuButton.setOnAction(event -> {
            try {
                timer.stop();
                toSetCPUTeam(stage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setEquipMenuTimer(){
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if(gc.getPlayerAliveNumber()>0){
                    ArrayList <String> names = new ArrayList<>();
                    for(ICharacter c: gc.getPlayerParty()){
                        if(c.isAlive()) names.add(c.getName());
                    }
                    ObservableList<String> oList = FXCollections.observableArrayList(names);
                    alivePlayerCharacters.setItems(oList);
                    alivePlayerCharacters.setVisible(true);
                }
                else{
                    alivePlayerCharacters.setVisible(false);
                }

                String selectedCharacterName = (String)alivePlayerCharacters.getValue();
                for(int i=0; i<gc.getPlayerPartySize(); i++){
                    gc.setSelectedCharacterFromPlayerParty(i);
                    if(gc.getSelectedCharacterName().equals(selectedCharacterName)){
                        equipMenuCharacterClass.setText(getSelectedCharacterClassAsString());
                        equipMenuCharacterClass.setVisible(true);
                        equipMenuCharacterName.setText(gc.getSelectedCharacterName());
                        equipMenuCharacterName.setVisible(true);
                        equipMenuCharacterHP.setText(String.valueOf(gc.getSelectedCharacterCurrentHP()));
                        equipMenuCharacterHP.setVisible(true);
                        equipMenuCharacterDP.setText(String.valueOf(gc.getSelectedCharacterDP()));
                        equipMenuCharacterDP.setVisible(true);
                        equipMenuCharacterMana.setText(String.valueOf(gc.getSelectedCharacterCurrentMana()));
                        equipMenuCharacterMana.setVisible(true);
                        break;
                    }
                }

                updateList(equipMenuWeapons);


                String selectedWeaponName = (String) equipMenuWeapons.getValue();


                for(int i=0; i<gc.getInventorySize(); i++){
                    gc.setSelectedWeapon(i);
                    if(gc.getSelectedWeaponName().equals(selectedWeaponName)){
                        equipMenuWeaponOwner.setText(gc.getSelectedWeaponOwnersName());
                        equipMenuWeaponOwner.setVisible(true);
                        equipMenuWeaponClass.setText(getSelectedWeaponClassAsString());
                        equipMenuWeaponClass.setVisible(true);
                        equipMenuWeaponName.setText(gc.getSelectedWeaponName());
                        equipMenuWeaponName.setVisible(true);
                        equipMenuWeaponPower.setText(String.valueOf(gc.getSelectedWeaponPower()));
                        equipMenuWeaponPower.setVisible(true);
                        equipMenuWeaponWeight.setText(String.valueOf(gc.getSelectedWeaponWeight()));
                        equipMenuWeaponWeight.setVisible(true);
                        equipMenuWeaponMagic.setText(String.valueOf(gc.getSelectedWeaponMagicPower()));
                        equipMenuWeaponMagic.setVisible(true);
                        break;
                    }
                }


            }
        };
        timer.start();
    }

    private void updateList(ComboBox equipMenuWeapons) {
        if(gc.getInventorySize()>0){
            ArrayList<String> names = new ArrayList<>();
            for(IWeapon c: gc.getPlayerInventory()){
                names.add(c.getName());
            }
            ObservableList<String> oList = FXCollections.observableArrayList(names);
            equipMenuWeapons.setItems(oList);
            equipMenuWeapons.setVisible(true);
        }
        else{
            equipMenuWeapons.setVisible(false);
        }
    }

    private String getSelectedCharacterClassAsString(){
        if(gc.selectedCharacterIsEngineer()){
            return "Engineer";
        }
        else if(gc.selectedCharacterIsBlackMage()){
            return "Black Mage";
        }
        else if(gc.selectedCharacterIsWhiteMage()){
            return "White Mage";
        }
        else if(gc.selectedCharacterIsThief()){
            return "Thief";
        }
        else if(gc.selectedCharacterIsKnight()){
            return "Knight";
        }
        else if(gc.selectedCharacterIsEnemy()){
            return "Enemy";
        }
        else{
            return "";
        }
    }

    private String getSelectedWeaponClassAsString(){
        if(gc.selectedWeaponIsAxe()){
            return "Axe";
        }
        else if(gc.selectedWeaponIsBow()){
            return "Bow";
        }
        else if(gc.selectedWeaponIsStaff()){
            return "Staff";
        }
        else if(gc.selectedWeaponIsSword()){
            return "Sword";
        }
        else if(gc.selectedWeaponIsKnife()){
            return "Knife";
        }
        else{
            return "";
        }
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

    private @NotNull ComboBox desplegableAliveCharacterList(List<ICharacter> currentCharacters, int x, int y){
        ArrayList<ICharacter> aliveCharacters = new ArrayList<>();
        for(ICharacter c: currentCharacters){
            if(c.isAlive()){
                aliveCharacters.add(c);
            };
        }
        ObservableList<ICharacter> characters = FXCollections.observableArrayList(aliveCharacters);
        final ComboBox optionsBox = new ComboBox(characters);
        optionsBox.setLayoutX(x);
        optionsBox.setLayoutY(y);
        return optionsBox;
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

    private String getSelectedPlayerCharacterModeSpritePATH(String mode){
        String thiefMiniSprite = "thief"+ mode + "sprite.png";
        String engineerMiniSprite = "engineer" + mode + "sprite.png";
        String blackMageMiniSprite = "blackmage" +mode + "sprite.png";
        String whiteMageMiniSprite = "whitemage" + mode + "sprite.png";
        String knightMiniSprite = "knight" + mode + "sprite.png";

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
        else return engineerMiniSprite;
    }


}