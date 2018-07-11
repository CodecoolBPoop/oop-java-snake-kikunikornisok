package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import java.util.Date;

public class ShieldPowerUP extends GameEntity implements Interactable {

    public ShieldPowerUP(Pane pane) {
        super(pane);
        setImage(Globals.shieldPowerUP);
        pane.getChildren().add(this);

        double[] safeCoordinates = generateSafeSpotForEntity();
        setX(safeCoordinates[0]);
        setY(safeCoordinates[1]);
    }
    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.activateShield();
        destroy();

        /*
        long startTime = System.currentTimeMillis();
        long elapsedTime = startTime + 10*1000;

        while (elapsedTime != startTime) {
            System.out.println(startTime);
            //perform db poll/check
            //elapsedTime = (new Date()).getTime() - startTime;
        }
        */
        System.out.println(123);
    }

    public long timer(){
        long startTime = System.currentTimeMillis();
        long elapsedTime = startTime + 10*1000;

        return elapsedTime;
    }

    @Override
    public String getMessage() {
        return "Got szupcsi Shield 8===D";
    }
}
