package duke.Commands;

import duke.tasks.Storage;
import duke.tasks.TaskHelper;
import duke.userinterface.Ui;

public class EventCommand extends Command{
    public EventCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskHelper taskHelper, Ui ui, Storage storage) {
        ui.printToUser(taskHelper.event(description, storage));
    }
}
