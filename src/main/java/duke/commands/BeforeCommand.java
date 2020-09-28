package duke.commands;

import duke.common.DateTimeValidator;
import duke.common.Messages;
import duke.userinterface.Ui;
import duke.tasks.TaskHelper;
import duke.tasks.Storage;


public class BeforeCommand extends Command{
    public BeforeCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskHelper taskHelper, Ui ui, Storage storage) {
        if (!DateTimeValidator.isValid(description)) {
            ui.printToUser(Messages.MESSAGE_INVALID_DATETIME);
            return;
        }
        ui.printFiltered(taskHelper.filterBefore(DateTimeValidator.stringToDateTime(description)),
                "come before your specified date and time: ");
    }
}
