package net.frosty.conway3d.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.frosty.conway3d.Conway3D;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item GAME_CONTROLLER = registerItem("game_controller", new ControllerItem(new Item.Settings().maxCount(1)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Conway3D.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Conway3D.LOGGER.info("Registering Mod Items for " + Conway3D.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(GAME_CONTROLLER);
        });
    }
}
