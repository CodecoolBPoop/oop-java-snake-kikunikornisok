package com.codecool.snake;

import com.codecool.snake.entities.enemies.IncreasingSpeedEnemy;
import com.codecool.snake.entities.enemies.ChangeDirection;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.DecreasingSpeedPowerup;
import com.codecool.snake.entities.powerups.ShieldPowerup;
import com.codecool.snake.entities.powerups.FoodPowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Random;

public class Game extends Pane {

    public Game() {
        new SnakeHead(this, 500, 500);
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    public void spawnEntities() {
        Random random = new Random();
        int randomNum = random.nextInt(1017);

        if (randomNum == 500) {
            new FoodPowerup(this);
                System.out.println(1017);
        } else if (randomNum == 50) {
            new IncreasingSpeedEnemy(this);
        }

        if (Globals.gameTimeAtStart % 500 == 0) {
            if(Globals.newGameObjects.contains(new DecreasingSpeedPowerup(this))) {
                System.out.println("New CSIGA in da house");
            }
            new ChangeDirection(this);
        }
        if(Globals.gameTimeAtStart-Globals.shieldActivated == 699){
            new ShieldPowerup(this);
            System.out.println("Shield on the map bitch");
        }
    }
}
