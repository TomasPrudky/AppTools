package cs.prudkytomas.frontend.stopwatch;

public class Stopwatch {
    private long startTime = System.currentTimeMillis();
    private long stopTime = System.currentTimeMillis();
    private boolean isRunning;

    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            stopTime = System.currentTimeMillis();
            isRunning = false;
        }
    }

    public String getActualTime() {
        if (isRunning) {
            long currentTime = System.currentTimeMillis() - startTime;
            return formatTime(currentTime);
        } else {
            long elapsedTime = stopTime - startTime;
            return formatTime(elapsedTime);
        }
    }

    private String formatTime(long timeInMillis) {
        long minutes = timeInMillis / 60000;
        long seconds = (timeInMillis % 60000) / 1000;
        long milliseconds = timeInMillis % 1000;

        return String.format("%02d:%02d,%02d", minutes, seconds, milliseconds);
    }

    public boolean isRunning(){
        return isRunning;
    }
}
