package ch.bbcag.minesweeper.navigation;

import javafx.animation.AnimationTimer;

public abstract class CustomAnimationTimer extends AnimationTimer {

    private long lastTime;

    @Override
    public void handle(long now) {

        long deltaInNanoSec = now - lastTime;
        double deltaInSec = deltaInNanoSec / 1000000000d;
        doHandle(deltaInSec);
        lastTime = now;
    }

    @Override
    public void start() {

        super.start();
        lastTime = System.nanoTime();
    }

    public abstract void doHandle(double deltaInSec);
}
