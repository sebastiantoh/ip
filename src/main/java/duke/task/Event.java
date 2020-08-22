package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateInputException;

public class Event extends Task {
    private static final String identifier = "E";
    private final LocalDate at;

    public Event(String description, String at) throws InvalidDateInputException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInputException(at);
        }
    }

    public Event(String description, String at, boolean isDone) throws InvalidDateInputException {
        super(description, isDone);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInputException(at);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", Event.identifier, super.toString(),
                this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String serialise() {
        return String.format("%s | %s | %s", Event.identifier, super.serialise(),
                this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
