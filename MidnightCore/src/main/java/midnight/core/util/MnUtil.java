package midnight.core.util;

import midnight.MidnightInfo;

import javax.annotation.Nonnull;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public final class MnUtil {
    private MnUtil() {
    }

    @Nonnull
    @SuppressWarnings("ConstantConditions")
    public static <T> T injected() {
        return null;
    }

    public static <T> T callForDatagen(Supplier<Callable<T>> datagen, Supplier<Callable<T>> production) {
        try {
            if (MidnightInfo.DATAGEN) {
                return datagen.get().call();
            } else {
                return production.get().call();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
