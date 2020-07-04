package midnight.data.blockstates;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;
import midnight.common.registry.RegistryManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class MnBlockstateProvider implements IDataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    private final Map<Block, IBlockstateGen> blockstateData = new HashMap<>();

    private final DataGenerator datagen;

    public MnBlockstateProvider(DataGenerator datagen) {
        this.datagen = datagen;
    }

    @Override
    public void act(DirectoryCache cache) {
        blockstateData.clear();
        BlockstateTable.collectBlockstates(blockstateData::put);

        for (Block block : getKnownBlocks()) {
            if (!blockstateData.containsKey(block)) {
                throw new IllegalStateException("No block state specified for block " + block.getRegistryName());
            }
        }

        Path path = datagen.getOutputFolder();
        blockstateData.forEach((block, model) -> {
            ResourceLocation id = block.getRegistryName();
            assert id != null;

            Path out = getPath(path, id);

            try {
                IDataProvider.save(GSON, cache, model.makeJson(id, block), out);
            } catch (IOException exc) {
                LOGGER.error("Couldn't save blockstate {}", out, exc);
            }
        });
    }

    protected Iterable<Block> getKnownBlocks() {
        return RegistryManager.BLOCKS;
    }

    @Override
    public String getName() {
        return "MnBlockstates";
    }

    private static Path getPath(Path path, ResourceLocation id) {
        return path.resolve(String.format("assets/%s/blockstates/%s.json", id.getNamespace(), id.getPath()));
    }
}
