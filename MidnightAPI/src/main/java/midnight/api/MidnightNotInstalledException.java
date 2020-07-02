package midnight.api;

/**
 * Thrown in {@link IMidnight#get()} when the Midnight is being accessed while not installed.
 */
public class MidnightNotInstalledException extends RuntimeException {
    public MidnightNotInstalledException(String message) {
        super(message);
    }
}
