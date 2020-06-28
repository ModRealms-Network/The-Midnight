package midnight.api.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Classes annotated with {@link MidnightPlugin} specify a generic plugin for the Midnight. Plugins are intended to
 * interfere with the Midnight through the Midnight API, and are supposed to be only loaded by the Midnight itself. Do
 * not try to load your plugins yourself as it might result in random {@link NoClassDefFoundError}s and {@link
 * ClassNotFoundException}s when the Midnight is not installed. Do not make a plugin only to subscribe to the Midnight
 * event bus. Use {@link MidnightEventSubscriber} instead.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MidnightPlugin {
    /**
     * The {@link Side} this plugin is exclusive to.
     */
    Side side() default Side.COMMON;
}
