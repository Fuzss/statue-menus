package fuzs.statuemenus.fabric.impl.client;

import fuzs.statuemenus.common.impl.StatueMenus;
import fuzs.statuemenus.common.impl.client.StatueMenusClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class StatueMenusFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(StatueMenus.MOD_ID, StatueMenusClient::new);
    }
}
