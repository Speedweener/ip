package duke.Commands;

import duke.tasks.Storage;
import duke.tasks.TaskHelper;
import duke.tasks.Ui;

public class FindCommand extends Command {

    public FindCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskHelper taskHelper, Ui ui, Storage storage) {

        ui.printList(taskHelper.filterKeyword(description));
    }

}
