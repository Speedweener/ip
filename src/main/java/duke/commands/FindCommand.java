package duke.commands;

import duke.tasks.Storage;
import duke.tasks.TaskHelper;
import duke.userinterface.Ui;

public class FindCommand extends Command {

    public FindCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskHelper taskHelper, Ui ui, Storage storage) {
        ui.printFiltered(taskHelper.filterKeyword(description), "match the keyword entered:");
    }

}
