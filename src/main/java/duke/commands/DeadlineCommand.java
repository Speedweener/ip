package duke.commands;

import duke.tasks.Storage;
import duke.tasks.TaskHelper;
import duke.userinterface.Ui;

public class DeadlineCommand extends Command{
    public DeadlineCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskHelper taskHelper, Ui ui, Storage storage) {
        ui.printToUser(taskHelper.deadline(description, storage));
    }
}
