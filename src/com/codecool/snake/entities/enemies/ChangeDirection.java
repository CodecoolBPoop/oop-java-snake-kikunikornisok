package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.Random;

public class ChangeDirection extends GameEntity implements Animatable, Interactable {

    private double direction;

    private Point2D heading;



    public ChangeDirection(Pane pane) {
        super(pane);
        setImage(Globals.diversionEnemy);
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

        setRotate(direction);
        heading = Utils.directionToVector(direction, 1);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

    }

    @Override
    public void apply(SnakeHead player) {
        player.setStartMushroomTime(Globals.gameTimeAtStart);
        if(!player.isShieldActive()) {
            player.setChangeDiversion(true);
        }
        destroy();
    }

    @Override
    public String getMessage() {
        return "Left-right keys swapped";
    }
}
