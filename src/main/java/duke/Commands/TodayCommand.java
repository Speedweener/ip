package duke.Commands;

import duke.tasks.*;

public class TodayCommand extends Command{
    public TodayCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskHelper taskHelper, Ui ui, Storage storage) {
        ui.printFiltered(taskHelper.filterToday(), "come today:");
    }
}
