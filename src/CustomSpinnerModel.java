import javax.swing.*;

// This class represents a custom spinner model
public class CustomSpinnerModel extends AbstractSpinnerModel {
    // Variables to store the minutes and seconds
    private int minutes = 0;
    private int seconds = 0;
    // Variable to store whether the spinner is off
    private boolean isOff = false;
    // Variables to store the maximum minutes and seconds
    private int maxMinutes = 20;
    private int maxSeconds = 0;

    // Method to set the maximum time
    public void setMaxTime() {
        this.minutes = 20;
        this.seconds = 0;
        isOff = false;

        // Notify the listeners that the spinner model has changed
        fireStateChanged();
    }

    // Method to reset the time
    public void resetTime() {
        minutes = 0;
        isOff = false;
        fireStateChanged();
    }

    // Method to set the spinner to the off state
    public void setOffState() {
        isOff = true;
        fireStateChanged();
    }

    // Method to get the current value of the spinner
    @Override
    public Object getValue() {
        if (isOff) {
            return "OFF";
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

    // Method to set the value of the spinner
    @Override
    public void setValue(Object value) {
        fireStateChanged();
    }

    // Method to get the next value of the spinner
    @Override
    public Object getNextValue() {
        if (!isOff) {
            decrement();
        }
        return getValue();
    }

    // Method to get the previous value of the spinner
    @Override
    public Object getPreviousValue() {
        if (!isOff) {
            increment();
        }
        return getValue();
    }

    // Method to increment the time
    private void increment() {
        minutes++;
        if (minutes > 20) {
            minutes = 20;
        }
    }

    // Method to decrement the time
    private void decrement() {
        seconds--;
        if (seconds < 0) {
            minutes--;
            seconds = 59;
        }
        if (minutes < 0) {
            minutes = 0;
            seconds = 0;
        }
    }

    // Method to get the time difference
    public int[] getTimeDifference(int minutes, int seconds) {
        int diffMinutes = maxMinutes - minutes;
        int diffSeconds = maxSeconds - seconds;

        if (diffSeconds < 0) {
            diffMinutes--;
            diffSeconds += 60;
        }

        if (diffMinutes < 0) {
            diffMinutes += 24 * 60;
        }

        return new int[]{diffMinutes, diffSeconds};
    }

    // Method to check if the spinner is off
    public boolean isOff() {
        return isOff;
    }

    // Method to set the maximum time
    public void setMaxTime(int minutes, int seconds) {
        this.maxMinutes = minutes;
        this.maxSeconds = seconds;
    }
}