package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected static final int SAFE_SPAWN_DISTANCE = 50;
    protected static final int SAFE_SNAKE_DEATH_DISTANCE = 30;
    protected Pane pane;

    protected GameEntity(Pane pane) {
        this.pane = pane;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    /**
     * Searching snake head for later to check if they would collide
     *
     * @return : The snakeHead object
     */
    public SnakeHead searchSnakeHead() {
        SnakeHead snakeHead = null;

        for (Object child : pane.getChildren()) {
            if (child instanceof SnakeHead) {
                snakeHead = (SnakeHead) child;
                break;
            }
        }

        return snakeHead;
    }

    /**
     * Generating x, y coordinates for entity only until it would not collide with the snake's head
     *
     * @return : Safe x, y coordinates of the entity to spawn
     */
    public double[] generateSafeSpotForEntity() {
        double[] coordinates = new double[2];
        SnakeHead snakeHead = searchSnakeHead();
        Random rnd = new Random();
        coordinates[0] = rnd.nextDouble() * (Globals.WINDOW_WIDTH - SAFE_SPAWN_DISTANCE);
        coordinates[1] = rnd.nextDouble() * (Globals.WINDOW_HEIGHT - SAFE_SPAWN_DISTANCE);
        while (Math.abs(coordinates[0] - snakeHead.getX()) < 32 || Math.abs(coordinates[1] - snakeHead.getY()) < 32 ){
            coordinates[0] = rnd.nextDouble() * (Globals.WINDOW_WIDTH - SAFE_SPAWN_DISTANCE);
            coordinates[1] = rnd.nextDouble() * (Globals.WINDOW_HEIGHT - SAFE_SPAWN_DISTANCE);
        }
        return coordinates;
    }

    public void destroy() {
        if (getParent() != null) {
            pane.getChildren().remove(this);
        }
        Globals.removeGameObject(this);
    }

    protected boolean isOutOfBounds() {
        if (getX() > Globals.WINDOW_WIDTH - SAFE_SNAKE_DEATH_DISTANCE || getX() < 0 ||
            getY() > Globals.WINDOW_HEIGHT - SAFE_SNAKE_DEATH_DISTANCE || getY() < 0) {
            return true;
        }
        return false;
    }
}
