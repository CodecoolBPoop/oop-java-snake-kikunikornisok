package com.codecool.snake;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class StartPopUpWindow extends Application {

    public static void main(String[] args) { launch(args); }

    @Override public void start(final Stage primaryStage) {
        primaryStage.setTitle("Start Game");
        final Popup popup = new Popup();
        popup.setX(300);
        popup.setY(200);
        Button onePlayer = new Button("1 Player");
        onePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                System.out.println("1 player clicked");
                Globals.twoPlayers = false;
                Main.startGame();
                Globals.startStage.close();
            }
        });

        Button twoPlayer = new Button("2 Player");
        twoPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                System.out.println("2 player clicked");
                Globals.twoPlayers = true;
                Main.startGame();
                Globals.startStage.close();
            }
        });

        HBox layout = new HBox(10);
        layout.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-font-size: 20px;");
        layout.getChildren().addAll(onePlayer, twoPlayer);
        primaryStage.setScene(new Scene(layout));
        primaryStage.show();
    }

}
