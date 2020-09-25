package duke.tasks;

import duke.Common.DateTimeValidator;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString(){
        return ("[E]" + "[" + this.getStatusIcon() + "] " + this.description
                + String.format(" (at: %s)", DateTimeValidator.dateTimeToPrint(at)));
    }

    @Override
    public String exportTask() {
        return  "E | " + (isDone? 1:0) + " | " + description + " | " + DateTimeValidator.dateTimeExport(at);
    }

    @Override
    public LocalDateTime getDateTime() {
        return at;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
