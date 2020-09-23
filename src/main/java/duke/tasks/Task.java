package duke.tasks;

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

    public Boolean markAsDone() {
        if(this.isDone){
            return false;
        }
        this.isDone = true;
        return true;
    }


    public abstract String toString();
    public abstract String saveString(String details, String dateTime);
    public abstract String exportTask();
    public abstract String getDescription();


}