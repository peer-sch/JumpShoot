package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.system.SystemCleaner;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread  extends Thread {

    SurfaceHolder surfaceHolder; //Surfaceholder object reference
    //SurfaceHolder ermöglicht es einer Anwendung, auf die Bildschirmoberfläche zuzugreifen
    // und sie zu zeichnen, indem es Methoden bereitstellt, um den Zugriff auf den Canvas zu steuern, auf dem die Grafiken angezeigt werden
    boolean isRunning; //Flag to detect whether the thread is running or not
    long startTime, loopTime; //Loop start time and loop duration
    //loopTime speichert die Zeit, die ein Durchlauf der Spielschleife benötigt, um sicherzustellen, dass das Spiel mit einer konstanten Aktualisierungsrate läuft,
    // während der Thread pausiert wird, um die gewünschte Verzögerung zwischen den Schleifendurchläufen einzuhalten
    long DELAY = 33; // Delay in milliseconds between screen refreshes
    Context context;

    public GameThread (SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        isRunning = true;
    }

    @Override
    public void run(){
        // looping until the boolean is false
        while(isRunning){
            startTime = SystemClock.uptimeMillis();
            //locking the canvas
            Canvas canvas = surfaceHolder.lockCanvas(null);
            //Mit surfaceHolder zugriff auf Canvas, um zu "zeichnen"
            if(canvas != null){
                synchronized (surfaceHolder){
                    AppConstants.getGameEngine().updateAndDrawBackgroundImage(canvas);
                    AppConstants.getGameEngine().updateAndDrawBird(canvas);
                    AppConstants.getGameEngine().updateAndDrawBlock(canvas, context);

                    //Hier wird der Spieler, der Gegner und das Hintergrundbild gezeichnet

                    // Überprüfen Sie die Kollisionen nach dem Zeichnen der Spielobjekte
                    AppConstants.getGameEngine().checkCollisions();

                    //unlocking the canvas
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            //loop time
            loopTime = SystemClock.uptimeMillis() - startTime;
            //Pausing here to make sure we update the right amount per second
            if(loopTime < DELAY){
                try{
                    Thread.sleep(DELAY - loopTime);
                }catch(InterruptedException e){
                    Log.e("Interrupted", "Interrupted while sleeping");
                }
            }
        }
    }

    // Return whether the thread is running
    public boolean isRunning(){
        return isRunning;
    }

    // Sets the thread state, false = stopped, true = running
    public void setIsRunning(boolean state){
        isRunning = state;
    }
}
