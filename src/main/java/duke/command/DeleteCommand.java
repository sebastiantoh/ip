package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.InvalidTaskIdException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a command that will delete a task.
 */
public class DeleteCommand extends Command {
    /**
     * The ID of the task to be deleted.
     */
    private final int taskId;

    /**
     * Initialises a new instance.
     *
     * @param taskId The ID of the task to be deleted.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the Delete command by deleting the task from the task list, updating the storage,
     * and then printing a message indicating that the task was successfully deleted.
     *
     * @param tasks   The list of tasks known by the chat bot.
     * @param storage The storage that is used by the chat bot.
     * @return A string detailing the outcome of the execution.
     * @throws DukeException If the execution fails at any step.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (taskId > tasks.size()) {
            throw new InvalidTaskIdException("No task with the given ID was found!");
        }
        Task task = tasks.deleteTask(taskId);

        storage.deleteExistingTask(taskId);

        return String
                .format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                        task, tasks.size());
    }
}
