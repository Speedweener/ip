package duke.tasks;

import java.time.LocalDateTime;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return ("[T]" + "[" + this.getStatusIcon() + "] " +this.description);
    }

    @Override
    public String exportTask() {
        return  "T | " + (isDone? 1:0) + " | " + description;
    }

    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    @Override
    public String getDescription() {
        return description;
    }


}