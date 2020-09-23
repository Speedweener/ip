package duke.exceptions;

public class EmptyCommandException extends Exception {
    public EmptyCommandException() {
        super("Whoopsie Daisies!" +System.lineSeparator() + "Try again:");
    }
}
