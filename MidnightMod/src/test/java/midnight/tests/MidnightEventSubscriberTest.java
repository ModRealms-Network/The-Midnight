package midnight.tests;

import net.minecraftforge.eventbus.api.SubscribeEvent;

import midnight.api.event.MidnightInitEvent;
import midnight.api.event.MidnightPostInitEvent;
import midnight.api.event.MidnightPreInitEvent;
import midnight.api.plugin.MidnightEventSubscriber;

@MidnightEventSubscriber
public final class MidnightEventSubscriberTest {
    private MidnightEventSubscriberTest() {
    }

    @SubscribeEvent
    public static void onPreInit(MidnightPreInitEvent event) {
        System.out.println("Midnight pre-init event received on " + event.getRuntimeDist());
    }

    @SubscribeEvent
    public static void onInit(MidnightInitEvent event) {
        System.out.println("Midnight init event received on " + event.getRuntimeDist());
    }

    @SubscribeEvent
    public static void onPostInit(MidnightPostInitEvent event) {
        System.out.println("Midnight post-init event received on " + event.getRuntimeDist());
    }
}
