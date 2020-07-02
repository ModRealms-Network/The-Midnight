package midnight.data;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import net.minecraft.data.DataGenerator;
import midnight.client.MidnightClient;
import midnight.common.proxy.BlockItemProxy;
import midnight.data.blockstates.MnBlockstateProvider;
import midnight.data.models.BlockItemModelTable;
import midnight.data.models.MnModelProvider;
import midnight.data.proxy.DataBlockItemProxy;

public class MidnightData extends MidnightClient {
    @Override
    public Dist getRuntimeDist() {
        return Dist.CLIENT;
    }

    @Override
    protected BlockItemProxy makeBlockItemProxy() {
        return new DataBlockItemProxy();
    }

    @SubscribeEvent
    public void onGenerateData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        if (event.includeClient()) {
            gen.addProvider(new MnBlockstateProvider(gen));
            gen.addProvider(new MnModelProvider(gen).withTable(new BlockItemModelTable()));
        }
    }
}
