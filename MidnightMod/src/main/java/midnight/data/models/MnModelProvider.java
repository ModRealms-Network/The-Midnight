package midnight.data.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MnModelProvider implements IDataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    private final List<ModelTable> modelTables = new ArrayList<>();

    private final Map<ResourceLocation, IModelGen> modelData = new HashMap<>();

    private final DataGenerator datagen;

    public MnModelProvider(DataGenerator datagen) {
        this.datagen = datagen;
    }

    @Override
    public void act(DirectoryCache cache) {
        modelData.clear();
        for (ModelTable table : modelTables) {
            table.collectModels(modelData::put);
        }

        Path path = datagen.getOutputFolder();
        modelData.forEach((id, model) -> {
            Path out = getPath(path, id);

            try {
                IDataProvider.save(GSON, cache, model.makeJson(id), out);
            } catch (IOException exc) {
                LOGGER.error("Couldn't save model {}", out, exc);
            }
        });
    }

    @Override
    public String getName() {
        return "MnModels";
    }

    public MnModelProvider withTable(ModelTable table) {
        modelTables.add(table);
        return this;
    }

    private static Path getPath(Path path, ResourceLocation id) {
        return path.resolve(String.format("assets/%s/models/%s.json", id.getNamespace(), id.getPath()));
    }
}
