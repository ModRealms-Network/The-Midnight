package midnight.api;

public class MidnightNotInstalledException extends RuntimeException {
    public MidnightNotInstalledException(String message) {
        super(message);
    }
}
