package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.powerups.ShieldPowerup;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class SnakeHead extends GameEntity implements Animatable {

    private float originalSpeed = 2;
    private float actualSpeed = originalSpeed;
    private boolean changeDiversion = false;
    private static final float turnRate = 2;
    private int snakeMainBodyLength = 10;
    private boolean shieldActive = false;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private float startMushroomTime;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(snakeMainBodyLength);
    }

    public void step() {
        double dir = getRotate();
        changeDiversion(dir, changeDiversion);
        if(Globals.gameTimeAtStart-Globals.shieldActivated == 700) {
            diActivateShield();
        }

        // check if collided with an enemy or a powerup
        List<GameEntity> gameObjectCopy = new ArrayList<>(Globals.gameObjects);
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
                else if (entity instanceof SnakeBody && gameObjectCopy.indexOf(entity) > snakeMainBodyLength){
                    if(isShieldActive() == false){
                        Globals.gameLoop.stop();
                    }
                    System.out.println("You hit your tale! Game Over");
                }
                else if (entity instanceof ShieldPowerup){
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            System.out.println(Globals.score);
            Globals.gameLoop.stop();
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public void activateShield(){
        shieldActive = true;
        Globals.shieldActivated = Globals.gameTimeAtStart;
    ; }

    public void diActivateShield(){
        System.out.println("Shield OFF");
        Globals.shieldActivated = 0;
        shieldActive = false;
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public void changeDiversion(double dir, boolean change) {
        if (change) {
            if (Globals.leftKeyDown) {
                dir = dir + turnRate;
            }
            if (Globals.rightKeyDown) {
                dir = dir - turnRate;
            }
        } else {
            if (Globals.leftKeyDown) {
                dir = dir - turnRate;
            }
            if (Globals.rightKeyDown) {
                dir = dir + turnRate;
            }
        }
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, actualSpeed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    public boolean isShieldActive() { return shieldActive; }

    public float getActualSpeed() {
        return this.actualSpeed;
    }

    public void setActualSpeed(float newSpeed) {
        this.actualSpeed = newSpeed;
    }

    public float getOriginalSpeed() {
        return this.originalSpeed;
    }

    public float getStartMushroomTime() {
        return startMushroomTime;
    }

    public void setStartMushroomTime(float startMushroomTime) {
        this.startMushroomTime = startMushroomTime;
    }

    public boolean isChangeDiversion() {
        return changeDiversion;
    }

    public void setChangeDiversion(boolean changeDiversion) {
        this.changeDiversion = changeDiversion;
    }

}
