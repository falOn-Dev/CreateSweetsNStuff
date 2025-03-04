package io.github.falondev.createsweetsnstuff.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static io.github.falondev.createsweetsnstuff.CreateSweetsNStuff.MOD_ID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

    public static final DeferredItem<Item> CARAMEL_CHUNK =
            ITEMS.register("caramel_chunk",
                    () -> new Item(new Item.Properties())
            );

    public static final DeferredItem<Item> CHOCOLATE_CHUNK =
            ITEMS.register("chocolate_chunk",
                    () -> new Item(new Item.Properties())
            );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
