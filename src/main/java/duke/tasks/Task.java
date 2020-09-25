package duke.tasks;

import java.time.LocalDateTime;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /** Changes the isDone value of the task to true.
     * Returns false if isDone value was already true, else returns true
     */
    public Boolean markAsDone() {
        if(this.isDone){
            return false;
        }
        this.isDone = true;
        return true;
    }


    /** Returns the task in the list as a string for visualization to user */
    public abstract String toString();


    /** Returns the string representation of the task for the txt file*/
    public abstract String exportTask();

    /** Getter for the date and time for a task
     * Since Todo does not store date and time, it returns null instead
     */
    public abstract LocalDateTime getDateTime();

    /** Getter for description for a task*/
    public abstract String getDescription();


}