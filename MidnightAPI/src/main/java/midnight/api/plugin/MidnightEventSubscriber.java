package midnight.api.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Subscribes a class to the Midnight event bus when the Midnight is being loaded. Make sure you don't load this class
 * yourself as it might result in random {@link NoClassDefFoundError}s and {@link ClassNotFoundException}s when the
 * Midnight is not installed.
 *
 * @author Shadew
 * @since 0.6.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MidnightEventSubscriber {
    /**
     * The {@link Side} this event handler is exclusive to.
     */
    Side side() default Side.COMMON;
}
