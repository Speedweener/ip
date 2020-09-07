package duke.tasks;

public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public void printTask(){
        System.out.println("[E]" + "[" + this.getStatusIcon() + "] " + this.description
                + String.format(" (at: %s)", at));
    }

}
