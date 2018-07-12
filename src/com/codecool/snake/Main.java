package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Globals.startStage = new Stage();
        StartPopUpWindow startPopUpWindow = new StartPopUpWindow();
        startPopUpWindow.start(Globals.startStage);
        Globals.stage = primaryStage;
    }

    public static void startGame() {
        Globals.game = new Game();

        Globals.stage.setTitle("Snake Game");
        Scene scene = new Scene(Globals.game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        Globals.stage.setScene(scene);
        Globals.stage.show();
        Globals.game.setTableBackground(new Image("background_grass.jpg"));
        Globals.scene = scene;
        Globals.game.start(scene);
    }
}
