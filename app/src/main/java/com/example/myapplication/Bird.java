package com.example.myapplication;

import android.util.Log;

public class Bird {

    private int birdX, birdY, currentFrame, velocity;
    public static int maxFrame;
    private boolean isJumping;
    private int originalBirdY;
    private static final int DEFAULT_JUMP_HEIGHT = 200; // Hier die gewünschte Sprunghöhe einstellen
    public Bird(){
        birdX = AppConstants.SCREEN_WIDTH/2 - AppConstants.getBitmapBank().getBirdWidth()/2;
        // birdY = AppConstants.SCREEN_HEIGHT/2 - AppConstants.getBitmapBank().getBackgroundHeight()/2;
        birdY = AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getBirdHeight() - 100;
        originalBirdY = birdY;
        currentFrame = 0;
        maxFrame = 3;
        velocity = 0;
        isJumping = true;
    }

    public void jump() {
        if (!isJumping && birdY == originalBirdY) {
            isJumping = true;
            velocity = AppConstants.VELOCITY_WHEN_JUMPED;
        }
    }

    public void update() {
        if (isJumping) {
            // Der Vogel ist in der Luft und springt
            velocity -= AppConstants.gravity;
            birdY += velocity;

            // Überprüfen, ob der Vogel den oberen Bildschirmrand erreicht hat
            if (birdY < originalBirdY - DEFAULT_JUMP_HEIGHT) {
                birdY = originalBirdY - DEFAULT_JUMP_HEIGHT; // Setze den Vogel auf die Sprunghöhe
                velocity = 0; // Stoppe die Bewegung
                isJumping = false; // Setze den Sprungstatus zurück
            }
        } else {
            // Der Vogel fällt nach unten
            velocity += AppConstants.gravity;
            birdY += velocity;

            // Überprüfen, ob der Vogel die Startposition erreicht hat
            if (birdY > originalBirdY) {
                birdY = originalBirdY; // Setze den Vogel auf die Startposition zurück
                velocity = 0; // Stoppe die Bewegung
            }
        }
    }


    // Getter und Setter für die isJumping-Variable
    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    //Getter method for velocity
    public int getVelocity(){
        return velocity;
    }

    //Setter method for setting the X-coordinate
    public void setVelocity(int velocity){
        this.velocity = velocity;
    }

    //Getter method for current frame
    public int getCurrentFrame(){
        return currentFrame;
    }

    //Setter method for current frame
    public void setCurrentFrame(int currentFrame){
        this.currentFrame = currentFrame;
    }

    //Getter method for getting X-coordinate of the Bird

    public int getX(){
        return birdX;
    }

    //Getter method for getting the Y-coordinate of the Bird
    public int getY(){
        return birdY;
    }

    //Setter method for setting the X-coordinate
    public void setX(int birdX){
        this.birdX = birdX;
    }

    //Setter method for setting the Y-coordinate
    public void setY(int birdY){
        this.birdY = birdY;
    }
}