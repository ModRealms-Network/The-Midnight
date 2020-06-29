package midnight.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import midnight.MidnightInfo;
import midnight.api.IMidnight;
import midnight.api.IMidnightInfo;
import midnight.api.event.MidnightInitEvent;
import midnight.api.event.MidnightPostInitEvent;
import midnight.api.event.MidnightPreInitEvent;
import midnight.core.plugin.PluginManager;

public abstract class Midnight implements IMidnight {
    public static final Logger LOGGER = LogManager.getLogger();
    private static Midnight instance;

    private final PluginManager pluginManager = new PluginManager();

    public Midnight() {
        instance = this;
    }

    public void preInit() {
        EVENT_BUS.start();
        pluginManager.loadPlugins();
        EVENT_BUS.post(new MidnightPreInitEvent(this, getRuntimeDist()));
    }

    public void init() {
        EVENT_BUS.post(new MidnightInitEvent(this, getRuntimeDist()));
    }

    public void postInit() {
        EVENT_BUS.post(new MidnightPostInitEvent(this, getRuntimeDist()));
    }



    @Override
    public final IMidnightInfo getInfo() {
        return MidnightInfo.INSTANCE;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public static Midnight get() {
        return instance;
    }
}
