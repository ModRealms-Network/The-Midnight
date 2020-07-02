package midnight.server.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import midnight.MidnightInfo;
import midnight.MidnightMod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * TODO: Make this the overall event handler or have multiple event handler classes?
 *  I originally made two event handler classes for the two different events in this class.
 */
@Mod.EventBusSubscriber(Dist.DEDICATED_SERVER)
public final class ServerEventHandler {
    private static final Logger LOGGER = LogManager.getLogger("Midnight Mod");

    // empty constructor.
    private ServerEventHandler() {
    }

    /*
     * This method is fired whenever FMLServerStartedEvent is called.
     * It is currently used to display the warning message to the server owner when the world has loaded.
     * The message is sent at WARN level.
     */
    @SubscribeEvent
    public static void serverStarting(FMLServerStartingEvent event)
    {
        // TODO This event fires twice. I don't know why.
        LOGGER.warn(MidnightMod.DEV_WARNING);
    }

    // This event is meant for GitHub Actions so that it is not in an infinite loop of keeping the server open.
    @SubscribeEvent
    public static void serverStarted(FMLServerStartedEvent event) throws Exception {
        File testServerProof = new File("./TESTSERVER.txt");
        boolean isTestServer = testServerProof.exists();

        // Only fire if ./TESTSERVER.txt exists and the server is run via Gradle.
        if (isTestServer && MidnightInfo.IDE) {
            // Delete the old file and create a new one saying "TEST SUCCESS".
            testServerProof.delete();
            File testServerSuccess = new File("./TESTSERVER.txt");
            try {
                FileWriter fileWriter = new FileWriter(testServerSuccess, false);
                fileWriter.write("TEST SUCCESS");
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            LOGGER.warn("GitHub Actions server test successful. The game will now crash.");
            // This Exception is intentional. Do not report it.
            throw new Exception("Crash intended for GitHub Actions. If you are trying to run this server normally, delete the TESTSERVER.txt file from your directory.");
        }
    }
}
