package midnight;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Replaces a certain constant value with a value generated during Gradle build. Usage
 * <pre>
 * {@literal @DynamicConstant}("version")
 * public static String VERSION = "-uninjected-";
 * </pre>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface DynamicConstant {
    String value();
}
