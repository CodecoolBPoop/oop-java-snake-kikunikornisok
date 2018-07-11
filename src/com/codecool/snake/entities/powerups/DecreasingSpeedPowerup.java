package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class DecreasingSpeedPowerup extends GameEntity implements Interactable {
    private float decreasingAmount = (float) 1;

    public DecreasingSpeedPowerup(Pane pane) {
        super(pane);

        setImage(Globals.snail);
        pane.getChildren().add(this);

        double[] safeCoordinates = generateSafeSpotForEntity();
        setX(safeCoordinates[0]);
        setY(safeCoordinates[1]);
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
