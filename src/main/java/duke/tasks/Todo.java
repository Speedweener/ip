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

}