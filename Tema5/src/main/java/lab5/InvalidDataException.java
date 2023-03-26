package lab5;

public class InvalidDataException extends Exception {
    public InvalidDataException(Exception ex) {
        super("Invalid data.", ex);
    }
}
