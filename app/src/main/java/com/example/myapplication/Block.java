package com.example.myapplication;

import android.util.Log;

public class Block {

    private int blockWidth;
    private int blockHeight;
    private int blockX, blockY, currentFrame, velocity;
    public static int maxFrame;
    public Block(){
        blockX = AppConstants.SCREEN_WIDTH - AppConstants.getBitmapBank().getBlockWidth() + 500;
        // birdY = AppConstants.SCREEN_HEIGHT/2 - AppConstants.getBitmapBank().getBackgroundHeight()/2;
        blockY = AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getBlockHeight() - 100;
        currentFrame = 0;
        maxFrame = 3;
        velocity = 20;
        blockWidth = AppConstants.getBitmapBank().getBlockWidth();
        blockHeight = AppConstants.getBitmapBank().getBlockHeight();

    }

    public void update() {
        // Aktualisiere die X-Position des Blocks basierend auf der Geschwindigkeit
        blockX -= velocity;

        // Wenn der Block den Bildschirm rechts verlässt, setze ihn wieder links
        if (blockX < 0 - AppConstants.getBitmapBank().getBlockWidth()) {
            blockX = AppConstants.SCREEN_WIDTH;
        }
    }

    public int getWidth() {
        return blockWidth;
    }

    public int getHeight() {
        return blockHeight;
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
        return blockX;
    }

    //Getter method for getting the Y-coordinate of the Bird
    public int getY(){
        return blockY;
    }

    //Setter method for setting the X-coordinate
    public void setX(int birdX){
        this.blockX = birdX;
    }

    //Setter method for setting the Y-coordinate
    public void setY(int birdY){
        this.blockY = birdY;
    }
}