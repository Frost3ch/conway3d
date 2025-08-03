package net.frosty.conway3d.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.frosty.conway3d.Conway3D;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block CONWAY_BLOCK = registerBlock("conway_block",
            new ConwayBlock(AbstractBlock.Settings.create()
                    .strength(4f)
                    .sounds(BlockSoundGroup.COPPER)
            ));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Conway3D.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Conway3D.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Conway3D.LOGGER.info("Registering Mod Blocks for " + Conway3D.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.CONWAY_BLOCK);
        });
    }
}
