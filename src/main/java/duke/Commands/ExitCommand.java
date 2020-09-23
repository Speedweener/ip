package duke.Commands;

import duke.tasks.Storage;
import duke.tasks.TaskHelper;
import duke.tasks.Ui;

public class ExitCommand extends Command{
    public ExitCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskHelper taskHelper, Ui ui, Storage storage) {
        ui.printExit();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }

}