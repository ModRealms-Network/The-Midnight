package midnight;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import midnight.api.IMidnight;
import midnight.client.MidnightClient;
import midnight.common.Midnight;
import midnight.core.plugin.PluginManager;
import midnight.server.MidnightServer;

@Mod(MidnightInfo.MODID)
public class MidnightMod {
    private static final Logger LOGGER = LogManager.getLogger("Midnight Mod");

    public static final Midnight MIDNIGHT = DistExecutor.safeRunForDist(() -> MidnightClient::new, () -> MidnightServer::new);

    public MidnightMod() {
        printVersion();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);

        MinecraftForge.EVENT_BUS.register(MIDNIGHT);
        FMLJavaModLoadingContext.get().getModEventBus().register(MIDNIGHT);

        IMidnight.EVENT_BUS.start();
        PluginManager.INSTANCE.loadPlugins();
        MIDNIGHT.preInit();
        LOGGER.debug("Midnight pre-initialized");
    }

    private void printVersion() {
        LOGGER.info("Initializing The Midnight");
        LOGGER.info(" - Version: " + MidnightInfo.VERSION);
        LOGGER.info(" - Build Date: " + MidnightInfo.BUILD_DATE);
        if (MidnightInfo.IDE) {
            LOGGER.info(" - Running in an IDE or via Gradle");
        }
    }

    private void setup(FMLCommonSetupEvent event) {
        MIDNIGHT.init();
        LOGGER.debug("Midnight initialized");
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
        MIDNIGHT.postInit();
        LOGGER.debug("Midnight post-initialized");
    }
}