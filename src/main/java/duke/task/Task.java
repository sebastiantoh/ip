package duke.task;

/**
 * Encapsulates a task.
 */
public abstract class Task implements Serialisable {
    /**
     * The description of the Task
     */
    private final String description;

    /**
     * A flag to indicate if the task is done
     */
    private boolean isDone;

    /**
     * Initialises a new {@link Task}.
     * The newly initialised task defaults to being incomplete.
     *
     * @param description The description of the {@link Task}
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initialises a new {@link Task}.
     *
     * @param description The description of the {@link Task}.
     * @param isDone      Whether the {@link Task} is done.
     */
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status of the task.
     *
     * @return true if the task is complete, fals e otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a <code>✓</code> if the task is done, <code>✘</code> otherwise.
     *
     * @return <code>✓</code> if the task is done, <code>✘</code> otherwise.
     */
    private String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks if the task description contains the given keyword.
     * The check is case-insensitive.
     *
     * @param keyword The keyword to check.
     * @return <code>true</code> if the task contains the keyword, <code>false</code> otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns a string representation of the task.
     * The string representation consists of a icon to represent the status of the task (whether
     * it is done or not), followed by the description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Serialises the task to a string.
     * The serialised task consists of a number to indicate the completion status of the task
     * (<code>1</code> if the task is done, <code>0</code> otherwise), followed by a
     * <code>|</code>, and then the description of the task.
     *
     * @return A string representing the serialised task.
     */
    @Override
    public String serialise() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

}
