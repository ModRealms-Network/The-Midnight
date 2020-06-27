package midnight.api;

import java.lang.reflect.Field;

public interface IMidnight {
    static IMidnight get() {
        try {
            Class<?> modClass = Class.forName("midnight.MidnightMod");
            Field midnightField = modClass.getField("MIDNIGHT");
            midnightField.setAccessible(true);
            return (IMidnight) midnightField.get(null);
        } catch (ClassNotFoundException exc) {
            throw new IllegalStateException("The Midnight is not avaiable! Make sure you only interface with the Midnight from a plugin.");
        } catch (NoSuchFieldException exc) {
            throw new RuntimeException("Couldn't find field 'MidnightMod.MIDNIGHT'! What implementation is used?", exc);
        } catch (IllegalAccessException exc) {
            throw new RuntimeException("Couldn't access field 'MidnightMod.MIDNIGHT'! What implementation is used?", exc);
        } catch (ClassCastException exc) {
            throw new RuntimeException("Field 'MidnightMod.MIDNIGHT' is not an 'IMidnight'! What implementation is used?", exc);
        } catch (Throwable exc) {
            throw new RuntimeException("Unable to get field value of 'MidnightMod.MIDNIGHT'! What implementation is used?", exc);
        }
    }
}
