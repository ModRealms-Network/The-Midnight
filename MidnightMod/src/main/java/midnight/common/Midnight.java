package midnight.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.ResourceLocation;
import midnight.MidnightInfo;
import midnight.MidnightMod;
import midnight.api.IMidnight;
import midnight.api.IMidnightInfo;
import midnight.api.event.MidnightInitEvent;
import midnight.api.event.MidnightPostInitEvent;
import midnight.api.event.MidnightPreInitEvent;
import midnight.common.proxy.BlockItemProxy;
import midnight.core.plugin.PluginManager;

public abstract class Midnight implements IMidnight {
    public static final Logger LOGGER = LogManager.getLogger("Midnight Mod");

    private final PluginManager pluginManager = new PluginManager();
    private BlockItemProxy blockItemProxy;



    /*
     * INITIALIZATION HANDLERS
     */

    public void preInit() {
        blockItemProxy = makeBlockItemProxy();

        if (!MidnightInfo.DATAGEN) {
            EVENT_BUS.start(); // Start the event bus manually when we're available
            pluginManager.loadPlugins();
            EVENT_BUS.post(new MidnightPreInitEvent(this, getRuntimeDist()));
        }
    }

    public void init() {
        EVENT_BUS.post(new MidnightInitEvent(this, getRuntimeDist()));
    }

    public void postInit() {
        EVENT_BUS.post(new MidnightPostInitEvent(this, getRuntimeDist()));
    }



    /*
     * BLOCK ITEM PROXY
     */

    protected BlockItemProxy makeBlockItemProxy() {
        return new BlockItemProxy();
    }

    public BlockItemProxy getBlockItemProxy() {
        return blockItemProxy;
    }



    /*
     * PLUGIN MANAGER
     */

    public PluginManager getPluginManager() {
        return pluginManager;
    }





    @Override
    public final IMidnightInfo getInfo() {
        return MidnightInfo.INSTANCE;
    }

    public static Midnight get() {
        return MidnightMod.MIDNIGHT;
    }

    /**
     * Create a {@link ResourceLocation} with {@link MidnightInfo#MODID midnight} as default namespace.
     * <ul>
     * <li>{@code "minecraft:path"} will yield {@code minecraft:path}</li>
     * <li>{@code "midnight:path"} will yield {@code midnight:path}</li>
     * <li>{@code "path"} will yield {@code midnight:path} (unlike the {@link ResourceLocation#ResourceLocation(String) ResourceLocation} constructor will yield {@code minecraft:path})</li>
     * </ul>
     *
     * @param path The resource path.
     * @return The created {@link ResourceLocation} instance.
     */
    public static ResourceLocation resLoc(String path) {
        int colon = path.indexOf(':');
        if (colon >= 0) {
            return new ResourceLocation(path.substring(0, colon), path.substring(colon + 1));
        }
        return new ResourceLocation(MidnightInfo.MODID, path);
    }

    /**
     * Create a stringified {@link ResourceLocation} with {@link MidnightInfo#MODID midnight} as default namespace. See
     * {@link #resLoc}.
     *
     * @param path The resource path.
     * @return The created resource id.
     */
    public static String resStr(String path) {
        if (path.indexOf(':') >= 0) {
            return path;
        }
        return MidnightInfo.MODID + ":" + path;
    }
}
