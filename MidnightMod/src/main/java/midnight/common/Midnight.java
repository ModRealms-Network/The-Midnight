package midnight.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import midnight.MidnightInfo;
import midnight.api.IMidnight;
import midnight.api.IMidnightInfo;

public abstract class Midnight implements IMidnight {
    public static final Logger LOGGER = LogManager.getLogger();

    private static Midnight instance;

    public Midnight() {
        instance = this;
    }

    @Override
    public final IMidnightInfo getInfo() {
        return MidnightInfo.INSTANCE;
    }

    public static Midnight get() {
        return instance;
    }
}
