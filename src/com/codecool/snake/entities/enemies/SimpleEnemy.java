package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    private double direction;

    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
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
        if (isOutOfBounds()) {
            if (this.getY() < 0 || this.getY() > Globals.WINDOW_HEIGHT) {
                this.setY(this.getY());
                direction = 180 - direction;
            }
            if (this.getX() < 0 || this.getX() > Globals.WINDOW_WIDTH) {
                this.setX(this.getX());
                direction = 360 - direction;
            }
            setRotate(direction);
            heading = Utils.directionToVector(direction, 1);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        if(player.isShieldActice() == false) {
            Globals.score -= 10;
        }
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
