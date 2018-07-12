package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Sound;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class IncreasingSpeedEnemy extends GameEntity implements Interactable {
    private float increasingAmount = (float) 0.2;

    public IncreasingSpeedEnemy(Pane pane) {
        super(pane);

        setImage(Globals.squirrel);
        pane.getChildren().add(this);

        double[] safeCoordinates = generateSafeSpotForEntity();
        setX(safeCoordinates[0]);
        setY(safeCoordinates[1]);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        Sound sound = new Sound();
        sound.playShortSound("resources/eat.wav");
        float newSpeed = snakeHead.getActualSpeed() + increasingAmount;
        if(!snakeHead.isShieldActive()) {
            snakeHead.setActualSpeed(newSpeed);
        }
        destroy();
    }

    @Override
    public String getMessage() {
        return "Speed increased";
    }
}
