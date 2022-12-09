package jar.lesson1.employeespring.exception;

public class WrongDataException extends RuntimeException {
    public WrongDataException(String message) {
        super(message);
    }
}
