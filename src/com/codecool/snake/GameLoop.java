package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.enemies.ChangeDirection;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
            if (gameObject instanceof SnakeHead) {
                if (Globals.gameTimeAtStart - ((SnakeHead) gameObject).getStartMushroomTime() > 1000) {
                    ((SnakeHead) gameObject).changeDiversion = false;
                }
            }
        }
        Globals.game.spawnEntities();

        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();

        Globals.gameTimeAtStart++;
    }
}
