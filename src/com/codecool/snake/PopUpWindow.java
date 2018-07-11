package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PopUpWindow extends Application {

    private boolean restartClicked;
    private boolean exitClicked;

    public static void main(String[] args) { launch(args); }

    @Override public void start(final Stage primaryStage) {
        primaryStage.setTitle("Game Over");
        final Popup popup = new Popup(); popup.setX(300); popup.setY(200);

        Button show = new Button("Restart");
        show.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                System.out.println("restart clicked");
                restartClicked = true;
            }
        });

        Button hide = new Button("Exit");
        hide.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                exitClicked = true;
                Platform.exit();
            }
        });

        HBox layout = new HBox(10);
        layout.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-font-size: 20px;");
        layout.getChildren().addAll(show, hide);
        primaryStage.setScene(new Scene(layout));
        primaryStage.show();
    }

    public boolean isRestartClicked() {
        return restartClicked;
    }

    public boolean isExitClicked() {
        return exitClicked;
    }
}