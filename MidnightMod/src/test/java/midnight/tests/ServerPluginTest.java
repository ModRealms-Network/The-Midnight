package midnight.tests;

import midnight.api.plugin.MidnightPlugin;
import midnight.api.plugin.Side;

@MidnightPlugin(side = Side.SERVER)
public class ServerPluginTest {
    public ServerPluginTest() {
        System.out.println("Server plugin loaded");
    }
}
