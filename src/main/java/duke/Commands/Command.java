package duke.Commands;

import duke.tasks.Storage;
import duke.tasks.TaskHelper;
import duke.userinterface.Ui;

public abstract class Command {
    protected String description;
    protected TaskHelper taskHelper;
    protected Ui ui;
    protected Storage storage;


    public Command(String description) {
        this.description = description;
    }


    public abstract void execute(TaskHelper taskHelper, Ui ui, Storage storage);

}
