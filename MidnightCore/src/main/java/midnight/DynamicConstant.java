package midnight;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Replaces a certain constant value with a value generated during Gradle build. Usage:<br><br>
 * <code>
 * {@literal @}DynamicConstant("version")<br> public static String VERSION = "-uninjected-";
 * </code>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface DynamicConstant {
    /**
     * The constant name to inject, set in the Gradle buildscript.
     */
    String value();
}
