package duke;

import duke.Commands.Command;
import duke.Commands.ExitCommand;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.IncompleteCommandException;
import duke.exceptions.UnknownCommandException;
import duke.userinterface.Ui;
import duke.tasks.Storage;
import duke.tasks.TaskHelper;
import duke.userinterface.Parser;

public class Duke {
    private TaskHelper taskHelper;
    private Ui ui;
    private Storage storage;


    public Duke(String filePath) {
        ui = new Ui();
        ui.printWelcome();
        storage = new Storage(filePath);
        taskHelper = new TaskHelper(storage.readFromFile(ui));
        ui.printList(taskHelper.list());
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskHelper, ui, storage);
                isExit = ExitCommand.isExit(c);
            } catch (EmptyCommandException | IncompleteCommandException | UnknownCommandException e) {
                ui.printToUser(e.getMessage());
            }
        }
        ui.printGoodbye();
    }

    public static void main(String[] args) {

        new Duke("data/list.txt").run();
    }

}
