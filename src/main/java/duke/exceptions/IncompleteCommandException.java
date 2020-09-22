package duke.exceptions;

public class IncompleteCommandException extends Exception {
      public IncompleteCommandException(String message) {
          super("Whoopsie Daisies! You have an empty description for " + message
          +System.lineSeparator() + "Try again:");
      }
}
