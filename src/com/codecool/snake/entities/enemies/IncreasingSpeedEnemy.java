package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class IncreasingSpeedEnemy extends GameEntity implements Interactable {
    private float increasingAmount = (float) 0.5;

    public IncreasingSpeedEnemy(Pane pane) {
        super(pane);

        setImage(Globals.redBull);
        pane.getChildren().add(this);
        setX();
        setY();
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        float newSpeed = snakeHead.getSpeed() + increasingAmount;
        snakeHead.setSpeed(newSpeed);
        destroy();
    }

    @Override
    public String getMessage() {
        return "speed increased";
    }
}