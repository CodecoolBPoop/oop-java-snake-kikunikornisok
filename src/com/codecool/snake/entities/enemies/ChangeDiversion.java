package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class ChangeDiversion extends GameEntity implements Animatable, Interactable {

    private double direction;
    private Point2D heading;



    public ChangeDiversion(Pane pane) {
        super(pane);
        setImage(Globals.diversionEnemy);
        pane.getChildren().add(this);
        int speed = 1;

        double[] safeCoordinates = generateSafeSpotForEntity();
        setX(safeCoordinates[0]);
        setY(safeCoordinates[1]);

        Random rnd = new Random();
        direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {


    }

    @Override
    public void apply(SnakeHead snakeHead) {

    }

    @Override
    public String getMessage() {
        return null;
    }
}
