package duke.tasks;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString(){
      return ("[D]" + "[" + this.getStatusIcon() + "] " + this.description
        + String.format(" (by: %s)", by));
    }

    @Override
    public String saveString(String details, String dateTime) {
        return ("D | 0 | " + details + " | " + dateTime +
                System.lineSeparator());
    }

    @Override
    public String exportTask() {
        return "D | " + (isDone? 1:0) +" | " + description + " | " + by;
    }

}
