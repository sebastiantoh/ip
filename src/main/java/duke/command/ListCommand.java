package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command that will list all the tasks of the chat bot.
 */
public class ListCommand extends Command {
    /**
     * Executes the List command by printing out all tasks in the task list.
     *
     * @param tasks   The list of tasks known by the chat bot.
     * @param ui      The UI that is used by the chat bot.
     * @param storage The storage that is used by the chat bot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.print("No tasks found.");
        } else {
            ui.print(String.format("Here are the tasks in your list:\n%s", tasks));
        }
    }
}
