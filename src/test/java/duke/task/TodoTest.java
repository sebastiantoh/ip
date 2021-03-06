package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TodoTest {
    @Test
    public void serialise_uncompletedTodo_success() {
        Todo todo = new Todo("todo description");

        assertEquals("T | 0 | todo description", todo.serialise());
    }

    @Test
    public void serialise_completedTodo_success() {
        Todo todo = new Todo("todo description", true);

        assertEquals("T | 1 | todo description", todo.serialise());
    }
}
