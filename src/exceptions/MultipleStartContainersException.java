package exceptions;

public class MultipleStartContainersException extends RuntimeException {

    public MultipleStartContainersException() {
        super("Multiple start containers have been created");
    }
}
