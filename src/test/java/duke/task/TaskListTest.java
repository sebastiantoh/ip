package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDateInputException;


public class TaskListTest {

    @Test
    public void addTask_taskListWithSomeItems_taskAddedAsLastItem()
            throws InvalidDateInputException {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo desc1"));
        testInputTasks.add(new Todo("todo desc2"));
        testInputTasks.add(new Deadline("deadline desc1", "2020-12-30"));
        testInputTasks.add(new Event("event desc1", "2020-12-30"));

        TaskList tasks = new TaskList(testInputTasks);
        Task task = new Todo("todo desc3");
        tasks.addTask(task);

        assertEquals(task, tasks.getTask(tasks.size()));
    }

    @Test
    public void deleteTask_taskListWithSomeItems_success() throws InvalidDateInputException {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo desc1"));
        Task taskToDelete = new Todo("todo desc3");
        testInputTasks.add(taskToDelete);
        testInputTasks.add(new Deadline("deadline desc1", "2020-12-30"));
        testInputTasks.add(new Event("event desc1", "2020-12-30"));

        TaskList tasks = new TaskList(testInputTasks);
        int numItemsInitial = tasks.size();
        tasks.deleteTask(2);
        int numItemsAfter = tasks.size();

        assertEquals(numItemsInitial - 1, numItemsAfter);
    }

    @Test
    public void size_emptyTaskList_zero() throws InvalidDateInputException {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo desc1"));
        testInputTasks.add(new Deadline("deadline desc1", "2020-12-30"));
        testInputTasks.add(new Event("event desc1", "2020-12-30"));

        TaskList tasks = new TaskList(testInputTasks);
        assertEquals(3, tasks.size());
    }

    @Test
    public void size_nonEmptyTaskList_nonZero() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
    }

    @Test
    public void search_onEmptyTaskList_emptyTaskList() {
        TaskList tasks = new TaskList();
        TaskList filteredTasks = tasks.search("blahblah");
        assertEquals(0, filteredTasks.size());
    }

    @Test
    public void search_keywordIsSubstring_correctlyFilteredTaskList()
            throws InvalidDateInputException {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo blahblah"));
        testInputTasks.add(new Deadline("deadline blah", "2020-12-30"));
        testInputTasks.add(new Event("event blah blah", "2020-12-30"));
        testInputTasks.add(new Todo("todo asdf"));

        TaskList tasks = new TaskList(testInputTasks);
        TaskList filteredTasks = tasks.search("blah");
        assertEquals("1. [T][✘] todo blahblah\n2. [D][✘] deadline blah (by: Dec 30 2020)\n"
                + "3. [E][✘] event blah blah (at: Dec 30 2020)", filteredTasks.toString());
    }

    @Test
    public void search_caseInsensitiveKeyword_correctlyFilteredTaskList()
            throws InvalidDateInputException {
        List<Task> testInputTasks = new ArrayList<>();
        testInputTasks.add(new Todo("todo blaH"));
        testInputTasks.add(new Deadline("deadline BLAH", "2020-12-30"));
        testInputTasks.add(new Event("event blah blah", "2020-12-30"));
        testInputTasks.add(new Todo("todo asdf"));

        TaskList tasks = new TaskList(testInputTasks);
        TaskList filteredTasks = tasks.search("BLAh");
        assertEquals("1. [T][✘] todo blaH\n2. [D][✘] deadline BLAH (by: Dec 30 2020)\n"
                + "3. [E][✘] event blah blah (at: Dec 30 2020)", filteredTasks.toString());
    }

    @Test
    public void toString_emptyTaskList_emptyString() {
        TaskList tasks = new TaskList();
        assertEquals("", tasks.toString());
    }

    @Test
    public void toString_nonEmptyTaskList_noTrailingNewLine() throws InvalidDateInputException {
        List<Task> testInputTasks = new ArrayList<>();

        testInputTasks.add(new Todo("todo desc1"));
        testInputTasks.add(new Todo("todo desc2"));
        testInputTasks.add(new Deadline("deadline desc1", "2020-12-30"));
        testInputTasks.add(new Event("event desc1", "2020-12-30"));

        TaskList tasks = new TaskList(testInputTasks);
        assertEquals("1. [T][✘] todo desc1\n" + "2. [T][✘] todo desc2\n" + "3. [D][✘] deadline "
                        + "desc1 (by: Dec 30 2020)\n" + "4. [E][✘] event desc1 (at: Dec 30 2020)",
                tasks.toString());
    }
}
