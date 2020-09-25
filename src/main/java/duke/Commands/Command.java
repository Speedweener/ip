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



    /**
     * This method performs different actions according to the type of command
     * Inputs are for the method to interact with a certain TaskHelper, Ui and Storage object
     */
    public abstract void execute(TaskHelper taskHelper, Ui ui, Storage storage);

}
