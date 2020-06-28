package midnight.common.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import midnight.MidnightInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Mod.EventBusSubscriber(Dist.DEDICATED_SERVER)
public final class BuildTestHandler {
    private static final Logger LOGGER = LogManager.getLogger("MidnightMod");

    private BuildTestHandler() {
    }

    @SubscribeEvent
    public static void serverStarted(FMLServerStartedEvent event) throws Exception {
        File testServerProof = new File("./TESTSERVER.txt");
        boolean isTestServer = testServerProof.exists();

        if (isTestServer && MidnightInfo.IDE) {
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
            throw new Exception("Crash intended for GitHub Actions. If you are trying to run this server normally, delete the TESTSERVER.txt file from your directory.");
        }
    }
}
