package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import java.util.Date;

public class ShieldPowerup extends GameEntity implements Interactable {

    public ShieldPowerup(Pane pane) {
        super(pane);
        setImage(Globals.shield);
        pane.getChildren().add(this);

        double[] safeCoordinates = generateSafeSpotForEntity();
        setX(safeCoordinates[0]);
        setY(safeCoordinates[1]);
    }
    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.activateShield();
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got shield-up";
    }
}
