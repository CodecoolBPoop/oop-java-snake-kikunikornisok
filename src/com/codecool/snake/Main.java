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
        Globals.game = new Game();

        primaryStage.setTitle("Snake Game");
        Scene scene = new Scene(Globals.game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
        Globals.game.setTableBackground(new Image("fű.jpg"));
        Globals.stage = primaryStage;
        Globals.scene = scene;
        Globals.game.start(scene);
    }

}
