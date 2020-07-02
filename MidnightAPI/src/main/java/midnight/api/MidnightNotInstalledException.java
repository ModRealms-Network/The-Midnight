package midnight.api;

// This should be pretty self-explanatory.
public class MidnightNotInstalledException extends RuntimeException {
    public MidnightNotInstalledException(String message) {
        super(message);
    }
}
