package duke.tasks;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void printTask(){
        System.out.println("[D]" + "[" + this.getStatusIcon() + "] " + this.description
        + String.format(" (by: %s)", by));
    }

    @Override
    public String saveString(String details, String dateTime) {
        return System.lineSeparator() + "D | 0 | "
                + details + " | " + dateTime;
    }

    @Override
    public String toString() {
        return "D | " + (isDone? 1:0) +" | " + description + " | " + by;
    }

}
