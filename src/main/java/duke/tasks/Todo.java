package duke.tasks;

import duke.tasks.Task;

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
    public String saveString(String details, String dateTime) {
        return ("T | 0 | " + details + System.lineSeparator());
    }

    @Override
    public String exportTask() {
        return  "T | " + (isDone? 1:0) + " | " + description;
    }

    @Override
    public LocalDateTime getDateTime() {
        return null;
    }


}