package midnight.api;

/**
 * Thrown in {@link IMidnight#get()} when the Midnight is being accessed while not installed.
 * This is scientifically the best exception in the game. - Jonathing
 *
 * @author Shadew
 * @since 0.6.0
 */
public class MidnightNotInstalledException extends RuntimeException {
    public MidnightNotInstalledException(String message) {
        super(message);
    }
}
