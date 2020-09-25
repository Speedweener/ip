package duke.Commands;

import duke.Common.DateTimeValidator;
import duke.Common.Messages;
import duke.userinterface.Ui;
import duke.tasks.*;

public class AfterCommand extends Command{
    public AfterCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskHelper taskHelper, Ui ui, Storage storage) {
        if (!DateTimeValidator.isValid(description)) {
            ui.printToUser(Messages.MESSAGE_INVALID_DATETIME);
            return;
        }
        ui.printFiltered(taskHelper.filterAfter(DateTimeValidator.stringToDateTime(description)),
                "come after your specified date and time: ");
    }
}
