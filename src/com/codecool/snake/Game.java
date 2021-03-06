package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.IncreasingSpeedEnemy;
import com.codecool.snake.entities.enemies.ChangeDirection;
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
        new SnakeHead(this, 400, 500, 1);
        if (Globals.twoPlayers) {
            new SnakeHead(this, 600, 500, 2);
        }
    }

    public void start(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case A: Globals.aKeyDown = true; break;
                case D: Globals.dKeyDown = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case A: Globals.aKeyDown = false; break;
                case D: Globals.dKeyDown = false; break;
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
        int randomNum = random.nextInt(501);

        if (randomNum == 500) {
            new FoodPowerup(this);
                System.out.println(1017);
        } else if (randomNum == 50) {
            new IncreasingSpeedEnemy(this);
        }

        if (Globals.gameTimeAtStart % 500 == 0) {
            if(Globals.newGameObjects.contains(new DecreasingSpeedPowerup(this))) {
                System.out.println("New snail spawned");
            }
            new ChangeDirection(this);
        }
        if(Globals.gameTimeAtStart-Globals.shieldActivated == 699){
            new ShieldPowerup(this);
            System.out.println("New shield spawned");
        }
    }

    public void resetGame() {
        for (GameEntity entity: Globals.getGameObjects()) {
            entity.destroy();
        }
        Globals.score = 0;
        Globals.gameObjects.clear();
        new SnakeHead(this, 400, 500, 1);
        if (Globals.twoPlayers) {
            new SnakeHead(this, 600, 500, 2);
        }
    }
}
