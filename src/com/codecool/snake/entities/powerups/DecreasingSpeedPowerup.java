package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class DecreasingSpeedPowerup extends GameEntity implements Interactable, Animatable {
    private float decreasingAmount = (float) 0.2;
    private double direction;

    private Point2D heading;

    public DecreasingSpeedPowerup(Pane pane) {
        super(pane);
        setImage(Globals.snail);
        pane.getChildren().add(this);
        int speed = 0;

        double[] safeCoordinates = generateSafeSpotForEntity();
        setX(safeCoordinates[0]);
        setY(safeCoordinates[1]);

        Random rnd = new Random();
        direction = rnd.nextDouble() * 360;
        setRotate(0);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        //setRotate(direction);
        heading = Utils.directionToVector(direction, 0.3);
        setY(getY() + heading.getY());
        setX(getX() + heading.getX());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        if (snakeHead.getActualSpeed() > snakeHead.getOriginalSpeed()) {
            float newSpeed = snakeHead.getActualSpeed() - decreasingAmount;
            snakeHead.setActualSpeed(newSpeed);
        }
        destroy();
    }

    @Override
    public String getMessage() {
        return "speed decreased";
    }
}
