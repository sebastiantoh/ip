package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.InvalidTaskIdException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a command that will mark a task as complete.
 */
public class DoneCommand extends Command {
    /**
     * The ID of the task to be marked as complete.
     */
    private final int taskId;

    /**
     * Initialises a new instance.
     *
     * @param taskId The ID of the task to be marked as complete.
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the Done command by marking the task in the task list as complete, updating the
     * storage, and then printing a message to indicate that the task was marked as done.
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
        Task task = tasks.getTask(taskId);
        task.markAsDone();

        storage.updateExistingTask(taskId, task);

        return String.format("Nice! I've marked this task as done:\n%s", task);
    }
}
