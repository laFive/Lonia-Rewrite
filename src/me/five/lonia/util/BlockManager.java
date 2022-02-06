package me.five.lonia.util;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BlockManager {

    private List<Material> abnormalVelocityBlocks;
    private List<Material> stairBlocks;
    private List<Material> slabBlocks;
    private List<Material> launchBlocks;
    private List<Material> slippyBlocks;
    private List<Material> useItems;
    private List<Material> swordItems;

    public BlockManager() {
        abnormalVelocityBlocks = new ArrayList<>();
        stairBlocks = new ArrayList<>();
        slabBlocks = new ArrayList<>();
        launchBlocks = new ArrayList<>();
        slippyBlocks = new ArrayList<>();
        useItems = new ArrayList<>();
        swordItems = new ArrayList<>();
        createList(Arrays.asList("WEB", "COBWEB", "HONEY_BLOCK", "HONEYCOMB_BLOCK", "WATER", "FLOWING_WATER", "STATIONARY_WATER", "STATIONARY_LAVA", "LAVA", "FLOWING_LAVA", "LADDER", "VINE", "WEEPING_VINES", "TWISTING_VINES", "WEEPING_VINES_PLANT", "TWISTING_VINES_PLANT", "SLIME", "SLIME_BLOCK", "FENCE", "OAK_FENCE", "BIRCH_FENCE", "SPRUCE_FENCE", "JUNGLE_FENCE", "ACACIA_FENCE", "WARPED_FENCE", "DARK_OAK_FENCE", "CRIMSON_FENCE", "NETHER_BRICK_FENCE"), abnormalVelocityBlocks);
        createList(Arrays.asList("OAK_STAIRS", "SANDSTONE_STAIRS", "BRICK_STAIRS", "BIRCH_STAIRS", "STONE_STAIRS", "BLACKSTONE_STAIRS", "PURPUR_STAIRS", "COBBLESTONE_STAIRS", "SPRUCE_STAIRS", "JUNGLE_STAIRS", "QUARTZ_STAIRS", "ACACIA_STAIRS", "WARPED_STAIRS", "RED_SANDSTONE_STAIRS", "DARK_OAK_STAIRS", "GRANITE_STAIRS", "DIORITE_STAIRS", "CRIMSON_STAIRS", "ANDESITE_STAIRS", "SMOOTH_SANDSTONE_STAIRS", "CUT_COPPER_STAIRS", "MOSSY_COBBLESTONE_STAIRS", "STONE_BRICK_STAIRS", "PRISMARINE_STAIRS", "NETHER_BRICK_STAIRS", "SMOOTH_RED_SANDSTONE_STAIRS", "POLISHED_BLACKSTONE_STAIRS", "SMOOTH_QUARTZ_STAIRS", "END_STONE_BRICK_STAIRS", "DEEPSLATE_TILE_STAIRS", "DARK_PRISMARINE_STAIRS", "RED_NETHER_BRICK_STAIRS", "DEEPSLATE_BRICK_STAIRS", "WAXED_CUT_COPPER_STAIRS", "PRISMARINE_BRICK_STAIRS", "POLISHED_GRANITE_STAIRS", "MOSSY_STONE_BRICK_STAIRS", "POLISHED_DIORITE_STAIRS", "POLISHED_BLACKSTONE_BRICK_STAIRS", "POLISHED_ANDESITE_STAIRS", "COBBLED_DEEPSLATE_STAIRS", "EXPOSED_CUT_COPPER_STAIRS", "OXIDIZED_CUT_COPPER_STAIRS", "POLISHED_DEEPSLATE_STAIRS", "WEATHERED_CUT_COPPER_STAIRS", "WAXED_EXPOSED_CUT_COPPER_STAIRS", "WAXED_OXIDIZED_CUT_COPPER_STAIRS", "WAXED_WEATHERED_CUT_COPPER_STAIRS"), stairBlocks);
        createList(Arrays.asList("OAK_SLAB", "SANDSTONE_SLAB", "BRICK_SLAB", "BIRCH_SLAB", "STONE_SLAB", "BLACKSTONE_SLAB", "PURPUR_SLAB", "COBBLESTONE_SLAB", "SPRUCE_SLAB", "JUNGLE_SLAB", "QUARTZ_SLAB", "ACACIA_SLAB", "WARPED_SLAB", "RED_SANDSTONE_SLAB", "DARK_OAK_SLAB", "GRANITE_SLAB", "DIORITE_SLAB", "CRIMSON_SLAB", "ANDESITE_SLAB", "SMOOTH_SANDSTONE_SLAB", "CUT_COPPER_SLAB", "MOSSY_COBBLESTONE_SLAB", "STONE_BRICK_SLAB", "PRISMARINE_SLAB", "NETHER_BRICK_SLAB", "POLISHED_BLACKSTONE_SLAB", "SMOOTH_QUARTZ_SLAB", "END_STONE_BRICK_SLAB", "DEEPSLATE_TILE_SLAB", "DARK_PRISMARINE_SLAB", "RED_NETHER_BRICK_SLAB", "DEEPSLATE_BRICK_SLAB", "WAXED_CUT_COPPER_SLAB", "PRISMARINE_BRICK_SLAB", "POLISHED_GRANITE_SLAB", "MOSSY_STONE_BRICK_SLAB", "POLISHED_DIORITE_SLAB", "POLISHED_BLACKSTONE_BRICK_SLAB", "POLISHED_ANDESITE_SLAB", "COBBLED_DEEPSLATE_SLAB", "EXPOSED_CUT_COPPER_SLAB", "OXIDIZED_CUT_COPPER_SLAB", "POLISHED_DEEPSLATE_SLAB", "WEATHERED_CUT_COPPER_SLAB", "WAXED_EXPOSED_CUT_COPPER_SLAB", "WAXED_OXIDIZED_CUT_COPPER_SLAB", "WAXED_WEATHERED_CUT_COPPER_SLAB", "WOODEN_SLAB", "STONE_SLAB2"), slabBlocks);
        createList(Arrays.asList("SLIME", "SLIME_BLOCK", "HONEY_BLOCK", "HONEYCOMB_BLOCK"), launchBlocks);
        createList(Arrays.asList("SLIME", "SLIME_BLOCK", "HONEY_BLOCK", "HONEYCOMB_BLOCK", "ICE", "PACKED_ICE", "BLUE_ICE", "FROSTED_ICE", "SOUL_SAND"), slippyBlocks);
        createList(Arrays.asList("BREAD", "COOKED_PORKCHOP", "CARROT", "GOLDEN_CARROT", "COOKED_SALMON", "COOKED_COD", "COOKED_FISH", "PUFFERFISH", "MUSHROOM_STEW", "RABBIT_STEW", "BEETROOT_STEW", "MUSHROOM_SOUP", "SOUP", "APPLE", "CHICKEN", "COOKED_CHICKEN", "RAW_CHICKEN", "RAW_PORKCHOP", "RAW_BEEF", "COOKED_BEEF", "BEEF", "SUSPICIOUS_STEW", "MELON", "POTATO", "BAKED_POTATO", "POISONOUS_POTATO", "PUMPKIN_PIE", "MUTTON", "COOKED_MUTTON", "RAW_MUTTON", "COOKED_RABBIT", "RABBIT", "GOLDEN_APPLE", "POTION", "ROTTEN_FLESH", "FISH", "COD", "SALMON", "TROPICAL_FISH", "PORKCHOP", "PORK", "GRILLED_PORK"), useItems);
        createList(Arrays.asList("WOODEN_SWORD", "WOOD_SWORD", "GOLD_SWORD", "STONE_SWORD", "GOLDEN_SWORD", "NETHERITE_SWORD", "DIAMOND_SWORD", "IRON_SWORD"), swordItems);
    }

    public void createList(Collection<String> blockStringList, List<Material> blockList) {

        for (String s : blockStringList) {

            Material block = Material.getMaterial(s);
            if (block == null) continue;
            blockList.add(block);

        }

    }

    public List<Material> getUseItems() {
        return useItems;
    }

    public List<Material> getSwordItems() {
        return swordItems;
    }

    public Collection<Material> getAbnormalVelocityBlocks() {
        return abnormalVelocityBlocks;
    }

    public List<Material> getStairBlocks() {
        return stairBlocks;
    }

    public List<Material> getSlabBlocks() {
        return slabBlocks;
    }

    public List<Material> getLaunchBlocks() {
        return launchBlocks;
    }

    public List<Material> getSlippyBlocks() {
        return slippyBlocks;
    }

}
