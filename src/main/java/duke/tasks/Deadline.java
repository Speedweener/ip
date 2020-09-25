package duke.tasks;
import duke.Common.DateTimeValidator;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString(){
      return ("[D]" + "[" + this.getStatusIcon() + "] " + this.description
        + String.format(" (by: %s)", DateTimeValidator.dateTimeToPrint(by)));
    }

    @Override
    public String exportTask() {
        return "D | " + (isDone? 1:0) +" | " + description + " | " + DateTimeValidator.dateTimeExport(by);
    }

    @Override
    public LocalDateTime getDateTime() {
        return by;
    }



    @Override
    public String getDescription() {
        return description;
    }

}
