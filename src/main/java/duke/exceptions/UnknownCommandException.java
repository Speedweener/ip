package duke.exceptions;

public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super("Whoopsie Daisies! You have entered an unknown command!"
                + System.lineSeparator() + "What the heck is '" + message + "'?");
    }

}