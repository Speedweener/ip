package duke.tasks;

import duke.tasks.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public void printTask(){
        System.out.println("[T]" + "[" + this.getStatusIcon() + "] " +this.description);
    }

    @Override
    public String saveString(String details, String dateTime) {
        return "T | 0 | " + details + System.lineSeparator();
    }

    @Override
    public String toString() {
        return  "T | " + (isDone? 1:0) + " | " + description;
    }


}