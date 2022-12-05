package eu.midnightdust.swordblocking;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SwordBlockingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        for (Item item : Registry.ITEM) {
            if (item instanceof SwordItem) {
                ModelPredicateProviderRegistry.register(item, new Identifier("blocking"), (itemStack, clientWorld, livingEntity, i) ->
                        livingEntity instanceof ClientPlayerEntity cpe && cpe.isMainPlayer() && MinecraftClient.getInstance().options.useKey.isPressed() ? 1.0F : 0.0F);
            }
        }
    }
}
