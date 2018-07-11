package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class SimplePowerup extends GameEntity implements Interactable {

    public SimplePowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupBerry);
        pane.getChildren().add(this);

        double[] safeCoordinates = generateSafeSpotForEntity();
        setX(safeCoordinates[0]);
        setY(safeCoordinates[1]);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(10);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
