package duke.commands;

import duke.userinterface.Ui;
import duke.tasks.TaskHelper;
import duke.tasks.Storage;

public class TodayCommand extends Command{
    public TodayCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskHelper taskHelper, Ui ui, Storage storage) {
        ui.printFiltered(taskHelper.filterToday(), "come today:");
    }
}
