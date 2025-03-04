package io.github.falondev.createsweetsnstuff.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static io.github.falondev.createsweetsnstuff.CreateSweetsNStuff.MOD_ID;

public class ModTags {
    public static class Blocks {
        public static TagKey<Block> CHILLING_CATALYSTS = createTag("chilling_catalysts");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
        }
    }
}
