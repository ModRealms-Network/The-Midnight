package midnight.data.blockstates;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.util.ResourceLocation;
import midnight.common.Midnight;

public class ModelVariant {
    private final ResourceLocation model;
    private int x;
    private int y;
    private boolean uvlock;
    private int weight = 1;

    public ModelVariant(ResourceLocation model) {
        this.model = model;
    }

    public ModelVariant(String model) {
        this.model = Midnight.resLoc(model);
    }

    public ModelVariant rotate(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public ModelVariant uvlock(boolean uvlock) {
        this.uvlock = uvlock;
        return this;
    }

    public ModelVariant weight(int weight) {
        this.weight = weight;
        return this;
    }

    public JsonObject makeJson(boolean withWeight) {
        JsonObject obj = new JsonObject();
        obj.addProperty("model", model.toString());
        if (x != 0) {
            obj.addProperty("x", x);
        }
        if (y != 0) {
            obj.addProperty("y", y);
        }
        if (uvlock) {
            obj.addProperty("uvlock", true);
        }
        if (withWeight && weight != 1) {
            obj.addProperty("weight", weight);
        }
        return obj;
    }

    public static JsonElement makeJson(ModelVariant... variants) {
        if (variants.length == 0) return new JsonArray();
        if (variants.length == 1) return variants[0].makeJson(false);
        JsonArray arr = new JsonArray();
        for (ModelVariant variant : variants) {
            arr.add(variant.makeJson(true));
        }
        return arr;
    }
}
