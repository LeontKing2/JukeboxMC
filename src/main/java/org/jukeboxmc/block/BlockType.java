package org.jukeboxmc.block;

import java.util.Arrays;
import java.util.List;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public enum BlockType {

    ACACIA_BUTTON,
    ACACIA_DOOR,
    ACACIA_FENCE_GATE,
    ACACIA_PRESSURE_PLATE,
    ACACIA_STAIRS,
    ACACIA_STANDING_SIGN,
    ACACIA_TRAPDOOR,
    ACACIA_WALL_SIGN,
    ACTIVATOR_RAIL,
    AIR,
    ALLOW,
    AMETHYST_BLOCK,
    AMETHYST_CLUSTER,
    ANCIENT_DEBRIS,
    ANDESITE_STAIRS,
    ANVIL,
    AZALEA,
    AZALEA_LEAVES,
    AZALEA_LEAVES_FLOWERED,
    BAMBOO,
    BAMBOO_SAPLING,
    BARREL,
    BARRIER,
    BASALT,
    BEACON,
    BED,
    BEDROCK,
    BEEHIVE,
    BEETROOT,
    BEE_NEST,
    BELL,
    BIG_DRIPLEAF,
    BIRCH_BUTTON,
    BIRCH_DOOR,
    BIRCH_FENCE_GATE,
    BIRCH_PRESSURE_PLATE,
    BIRCH_STAIRS,
    BIRCH_STANDING_SIGN,
    BIRCH_TRAPDOOR,
    BIRCH_WALL_SIGN,
    BLACKSTONE,
    BLACKSTONE_DOUBLE_SLAB,
    BLACKSTONE_SLAB,
    BLACKSTONE_STAIRS,
    BLACKSTONE_WALL,
    BLACK_CANDLE,
    BLACK_CANDLE_CAKE,
    BLACK_GLAZED_TERRACOTTA,
    BLAST_FURNACE,
    BLUE_CANDLE,
    BLUE_CANDLE_CAKE,
    BLUE_GLAZED_TERRACOTTA,
    BLUE_ICE,
    BONE_BLOCK,
    BOOKSHELF,
    BORDER_BLOCK,
    BREWING_STAND,
    BRICK_BLOCK,
    BRICK_STAIRS,
    BROWN_CANDLE,
    BROWN_CANDLE_CAKE,
    BROWN_GLAZED_TERRACOTTA,
    BROWN_MUSHROOM,
    BROWN_MUSHROOM_BLOCK,
    BUBBLE_COLUMN,
    BUDDING_AMETHYST,
    CACTUS,
    CAKE,
    CALCITE,
    CAMERA,
    CAMPFIRE,
    CANDLE,
    CANDLE_CAKE,
    CARPET,
    CARROTS,
    CARTOGRAPHY_TABLE,
    CARVED_PUMPKIN,
    CAULDRON,
    CAVE_VINES,
    CAVE_VINES_BODY_WITH_BERRIES,
    CAVE_VINES_HEAD_WITH_BERRIES,
    CHAIN,
    CHAIN_COMMAND_BLOCK,
    CHEMICAL_HEAT,
    CHEMISTRY_TABLE,
    CHEST,
    CHISELED_DEEPSLATE,
    CHISELED_NETHER_BRICKS,
    CHISELED_POLISHED_BLACKSTONE,
    CHORUS_FLOWER,
    CHORUS_PLANT,
    CLAY,
    CLIENT_REQUEST_PLACEHOLDER_BLOCK,
    COAL_BLOCK,
    COAL_ORE,
    COBBLED_DEEPSLATE,
    COBBLED_DEEPSLATE_DOUBLE_SLAB,
    COBBLED_DEEPSLATE_SLAB,
    COBBLED_DEEPSLATE_STAIRS,
    COBBLED_DEEPSLATE_WALL,
    COBBLESTONE,
    COBBLESTONE_WALL,
    COCOA,
    COLORED_TORCH_BP,
    COLORED_TORCH_RG,
    COMMAND_BLOCK,
    COMPOSTER,
    CONCRETE,
    CONCRETE_POWDER,
    CONDUIT,
    COPPER_BLOCK,
    COPPER_ORE,
    CORAL,
    CORAL_BLOCK,
    CORAL_FAN,
    CORAL_FAN_DEAD,
    CORAL_FAN_HANG,
    CORAL_FAN_HANG2,
    CORAL_FAN_HANG3,
    CRACKED_DEEPSLATE_BRICKS,
    CRACKED_DEEPSLATE_TILES,
    CRACKED_NETHER_BRICKS,
    CRACKED_POLISHED_BLACKSTONE_BRICKS,
    CRAFTING_TABLE,
    CRIMSON_BUTTON,
    CRIMSON_DOOR,
    CRIMSON_DOUBLE_SLAB,
    CRIMSON_FENCE,
    CRIMSON_FENCE_GATE,
    CRIMSON_FUNGUS,
    CRIMSON_HYPHAE,
    CRIMSON_NYLIUM,
    CRIMSON_PLANKS,
    CRIMSON_PRESSURE_PLATE,
    CRIMSON_ROOTS,
    CRIMSON_SLAB,
    CRIMSON_STAIRS,
    CRIMSON_STANDING_SIGN,
    CRIMSON_STEM,
    CRIMSON_TRAPDOOR,
    CRIMSON_WALL_SIGN,
    CRYING_OBSIDIAN,
    CUT_COPPER,
    CUT_COPPER_SLAB,
    CUT_COPPER_STAIRS,
    CYAN_CANDLE,
    CYAN_CANDLE_CAKE,
    CYAN_GLAZED_TERRACOTTA,
    DARKOAK_STANDING_SIGN,
    DARKOAK_WALL_SIGN,
    DARK_OAK_BUTTON,
    DARK_OAK_DOOR,
    DARK_OAK_FENCE_GATE,
    DARK_OAK_PRESSURE_PLATE,
    DARK_OAK_STAIRS,
    DARK_OAK_TRAPDOOR,
    DARK_PRISMARINE_STAIRS,
    DAYLIGHT_DETECTOR,
    DAYLIGHT_DETECTOR_INVERTED,
    DEADBUSH,
    DEEPSLATE,
    DEEPSLATE_BRICKS,
    DEEPSLATE_BRICK_DOUBLE_SLAB,
    DEEPSLATE_BRICK_SLAB,
    DEEPSLATE_BRICK_STAIRS,
    DEEPSLATE_BRICK_WALL,
    DEEPSLATE_COAL_ORE,
    DEEPSLATE_COPPER_ORE,
    DEEPSLATE_DIAMOND_ORE,
    DEEPSLATE_EMERALD_ORE,
    DEEPSLATE_GOLD_ORE,
    DEEPSLATE_IRON_ORE,
    DEEPSLATE_LAPIS_ORE,
    DEEPSLATE_REDSTONE_ORE,
    DEEPSLATE_TILES,
    DEEPSLATE_TILE_DOUBLE_SLAB,
    DEEPSLATE_TILE_SLAB,
    DEEPSLATE_TILE_STAIRS,
    DEEPSLATE_TILE_WALL,
    DENY,
    DETECTOR_RAIL,
    DIAMOND_BLOCK,
    DIAMOND_ORE,
    DIORITE_STAIRS,
    DIRT,
    DIRT_WITH_ROOTS,
    DISPENSER,
    DOUBLE_CUT_COPPER_SLAB,
    DOUBLE_PLANT,
    DOUBLE_STONE_BLOCK_SLAB,
    DOUBLE_STONE_BLOCK_SLAB2,
    DOUBLE_STONE_BLOCK_SLAB3,
    DOUBLE_STONE_BLOCK_SLAB4,
    DOUBLE_WOODEN_SLAB,
    DRAGON_EGG,
    DRIED_KELP_BLOCK,
    DRIPSTONE_BLOCK,
    DROPPER,
    ELEMENT_0,
    ELEMENT_1,
    ELEMENT_10,
    ELEMENT_100,
    ELEMENT_101,
    ELEMENT_102,
    ELEMENT_103,
    ELEMENT_104,
    ELEMENT_105,
    ELEMENT_106,
    ELEMENT_107,
    ELEMENT_108,
    ELEMENT_109,
    ELEMENT_11,
    ELEMENT_110,
    ELEMENT_111,
    ELEMENT_112,
    ELEMENT_113,
    ELEMENT_114,
    ELEMENT_115,
    ELEMENT_116,
    ELEMENT_117,
    ELEMENT_118,
    ELEMENT_12,
    ELEMENT_13,
    ELEMENT_14,
    ELEMENT_15,
    ELEMENT_16,
    ELEMENT_17,
    ELEMENT_18,
    ELEMENT_19,
    ELEMENT_2,
    ELEMENT_20,
    ELEMENT_21,
    ELEMENT_22,
    ELEMENT_23,
    ELEMENT_24,
    ELEMENT_25,
    ELEMENT_26,
    ELEMENT_27,
    ELEMENT_28,
    ELEMENT_29,
    ELEMENT_3,
    ELEMENT_30,
    ELEMENT_31,
    ELEMENT_32,
    ELEMENT_33,
    ELEMENT_34,
    ELEMENT_35,
    ELEMENT_36,
    ELEMENT_37,
    ELEMENT_38,
    ELEMENT_39,
    ELEMENT_4,
    ELEMENT_40,
    ELEMENT_41,
    ELEMENT_42,
    ELEMENT_43,
    ELEMENT_44,
    ELEMENT_45,
    ELEMENT_46,
    ELEMENT_47,
    ELEMENT_48,
    ELEMENT_49,
    ELEMENT_5,
    ELEMENT_50,
    ELEMENT_51,
    ELEMENT_52,
    ELEMENT_53,
    ELEMENT_54,
    ELEMENT_55,
    ELEMENT_56,
    ELEMENT_57,
    ELEMENT_58,
    ELEMENT_59,
    ELEMENT_6,
    ELEMENT_60,
    ELEMENT_61,
    ELEMENT_62,
    ELEMENT_63,
    ELEMENT_64,
    ELEMENT_65,
    ELEMENT_66,
    ELEMENT_67,
    ELEMENT_68,
    ELEMENT_69,
    ELEMENT_7,
    ELEMENT_70,
    ELEMENT_71,
    ELEMENT_72,
    ELEMENT_73,
    ELEMENT_74,
    ELEMENT_75,
    ELEMENT_76,
    ELEMENT_77,
    ELEMENT_78,
    ELEMENT_79,
    ELEMENT_8,
    ELEMENT_80,
    ELEMENT_81,
    ELEMENT_82,
    ELEMENT_83,
    ELEMENT_84,
    ELEMENT_85,
    ELEMENT_86,
    ELEMENT_87,
    ELEMENT_88,
    ELEMENT_89,
    ELEMENT_9,
    ELEMENT_90,
    ELEMENT_91,
    ELEMENT_92,
    ELEMENT_93,
    ELEMENT_94,
    ELEMENT_95,
    ELEMENT_96,
    ELEMENT_97,
    ELEMENT_98,
    ELEMENT_99,
    EMERALD_BLOCK,
    EMERALD_ORE,
    ENCHANTING_TABLE,
    ENDER_CHEST,
    END_BRICKS,
    END_BRICK_STAIRS,
    END_GATEWAY,
    END_PORTAL,
    END_PORTAL_FRAME,
    END_ROD,
    END_STONE,
    EXPOSED_COPPER,
    EXPOSED_CUT_COPPER,
    EXPOSED_CUT_COPPER_SLAB,
    EXPOSED_CUT_COPPER_STAIRS,
    EXPOSED_DOUBLE_CUT_COPPER_SLAB,
    FARMLAND,

    OAK_FENCE,
    SPRUCE_FENCE,
    BIRCH_FENCE,
    JUNGLE_FENCE,
    ACACIA_FENCE,
    DARK_OAK_FENCE,

    OAK_FENCE_GATE,
    FIRE,
    FLETCHING_TABLE,
    FLOWERING_AZALEA,
    FLOWER_POT,
    FLOWING_LAVA,
    FLOWING_WATER,
    FRAME,
    FROG_SPAWN,
    FROSTED_ICE,
    FURNACE,
    GILDED_BLACKSTONE,
    GLASS,
    GLASS_PANE,
    GLOWINGOBSIDIAN,
    GLOWSTONE,
    GLOW_FRAME,
    GLOW_LICHEN,
    GOLDEN_RAIL,
    GOLD_BLOCK,
    GOLD_ORE,
    GRANITE_STAIRS,
    GRASS,
    GRASS_PATH,
    GRAVEL,
    GRAY_CANDLE,
    GRAY_CANDLE_CAKE,
    GRAY_GLAZED_TERRACOTTA,
    GREEN_CANDLE,
    GREEN_CANDLE_CAKE,
    GREEN_GLAZED_TERRACOTTA,
    GRINDSTONE,
    HANGING_ROOTS,
    HARDENED_CLAY,
    HARD_GLASS,
    HARD_GLASS_PANE,
    HARD_STAINED_GLASS,
    HARD_STAINED_GLASS_PANE,
    HAY_BLOCK,
    HEAVY_WEIGHTED_PRESSURE_PLATE,
    HONEYCOMB_BLOCK,
    HONEY_BLOCK,
    HOPPER,
    ICE,
    INFESTED_DEEPSLATE,
    INFO_UPDATE,
    INFO_UPDATE2,
    INVISIBLE_BEDROCK,
    IRON_BARS,
    IRON_BLOCK,
    IRON_DOOR,
    IRON_ORE,
    IRON_TRAPDOOR,
    JIGSAW,
    JUKEBOX,
    JUNGLE_BUTTON,
    JUNGLE_DOOR,
    JUNGLE_FENCE_GATE,
    JUNGLE_PRESSURE_PLATE,
    JUNGLE_STAIRS,
    JUNGLE_STANDING_SIGN,
    JUNGLE_TRAPDOOR,
    JUNGLE_WALL_SIGN,
    KELP,
    LADDER,
    LANTERN,
    LAPIS_BLOCK,
    LAPIS_ORE,
    LARGE_AMETHYST_BUD,
    LAVA,
    LAVA_CAULDRON,
    LEAVES,
    LEAVES2,
    LECTERN,
    LEVER,
    LIGHTNING_ROD,
    LIGHT_BLOCK,
    LIGHT_BLUE_CANDLE,
    LIGHT_BLUE_CANDLE_CAKE,
    LIGHT_BLUE_GLAZED_TERRACOTTA,
    LIGHT_GRAY_CANDLE,
    LIGHT_GRAY_CANDLE_CAKE,
    LIGHT_WEIGHTED_PRESSURE_PLATE,
    LIME_CANDLE,
    LIME_CANDLE_CAKE,
    LIME_GLAZED_TERRACOTTA,
    LIT_BLAST_FURNACE,
    LIT_DEEPSLATE_REDSTONE_ORE,
    LIT_FURNACE,
    LIT_PUMPKIN,
    LIT_REDSTONE_LAMP,
    LIT_REDSTONE_ORE,
    LIT_SMOKER,
    LODESTONE,

    OAK_LOG,
    SPRUCE_LOG,
    BIRCH_LOG,
    JUNGLE_LOG,
    ACACIA_LOG,
    DARK_OAK_LOG,

    LOOM,
    MAGENTA_CANDLE,
    MAGENTA_CANDLE_CAKE,
    MAGENTA_GLAZED_TERRACOTTA,
    MAGMA,
    MANGROVE_BUTTON,
    MANGROVE_DOOR,
    MANGROVE_DOUBLE_SLAB,
    MANGROVE_FENCE,
    MANGROVE_FENCE_GATE,
    MANGROVE_LEAVES,
    MANGROVE_LOG,
    MANGROVE_PLANKS,
    MANGROVE_PRESSURE_PLATE,
    MANGROVE_PROPAGULE,
    MANGROVE_ROOTS,
    MANGROVE_SLAB,
    MANGROVE_STAIRS,
    MANGROVE_STANDING_SIGN,
    MANGROVE_TRAPDOOR,
    MANGROVE_WALL_SIGN,
    MANGROVE_WOOD,
    MEDIUM_AMETHYST_BUD,
    MELON_BLOCK,
    MELON_STEM,
    MOB_SPAWNER,
    INFESTED_STONE,
    MOSSY_COBBLESTONE,
    MOSSY_COBBLESTONE_STAIRS,
    MOSSY_STONE_BRICK_STAIRS,
    MOSS_BLOCK,
    MOSS_CARPET,
    MOVING_BLOCK,
    MUD,
    MUDDY_MANGROVE_ROOTS,
    MUD_BRICKS,
    MUD_BRICK_DOUBLE_SLAB,
    MUD_BRICK_SLAB,
    MUD_BRICK_STAIRS,
    MUD_BRICK_WALL,
    MYCELIUM,
    NETHERITE_BLOCK,
    NETHERRACK,
    NETHERREACTOR,
    NETHER_BRICK,
    NETHER_BRICK_FENCE,
    NETHER_BRICK_STAIRS,
    NETHER_GOLD_ORE,
    NETHER_SPROUTS,
    NETHER_WART,
    NETHER_WART_BLOCK,
    NORMAL_STONE_STAIRS,
    NOTEBLOCK,
    OAK_STAIRS,
    OBSERVER,
    OBSIDIAN,
    OCHRE_FROGLIGHT,
    ORANGE_CANDLE,
    ORANGE_CANDLE_CAKE,
    ORANGE_GLAZED_TERRACOTTA,
    OXIDIZED_COPPER,
    OXIDIZED_CUT_COPPER,
    OXIDIZED_CUT_COPPER_SLAB,
    OXIDIZED_CUT_COPPER_STAIRS,
    OXIDIZED_DOUBLE_CUT_COPPER_SLAB,
    PACKED_ICE,
    PACKED_MUD,
    PEARLESCENT_FROGLIGHT,
    PINK_CANDLE,
    PINK_CANDLE_CAKE,
    PINK_GLAZED_TERRACOTTA,
    PISTON,
    PISTON_ARM_COLLISION,
    PLANKS,
    PODZOL,
    POINTED_DRIPSTONE,
    POLISHED_ANDESITE_STAIRS,
    POLISHED_BASALT,
    POLISHED_BLACKSTONE,
    POLISHED_BLACKSTONE_BRICKS,
    POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB,
    POLISHED_BLACKSTONE_BRICK_SLAB,
    POLISHED_BLACKSTONE_BRICK_STAIRS,
    POLISHED_BLACKSTONE_BRICK_WALL,
    POLISHED_BLACKSTONE_BUTTON,
    POLISHED_BLACKSTONE_DOUBLE_SLAB,
    POLISHED_BLACKSTONE_PRESSURE_PLATE,
    POLISHED_BLACKSTONE_SLAB,
    POLISHED_BLACKSTONE_STAIRS,
    POLISHED_BLACKSTONE_WALL,
    POLISHED_DEEPSLATE,
    POLISHED_DEEPSLATE_DOUBLE_SLAB,
    POLISHED_DEEPSLATE_SLAB,
    POLISHED_DEEPSLATE_STAIRS,
    POLISHED_DEEPSLATE_WALL,
    POLISHED_DIORITE_STAIRS,
    POLISHED_GRANITE_STAIRS,
    PORTAL,
    POTATOES,
    POWDER_SNOW,
    POWERED_COMPARATOR,
    POWERED_REPEATER,
    PRISMARINE,
    PRISMARINE_BRICKS_STAIRS,
    PRISMARINE_STAIRS,
    PUMPKIN,
    PUMPKIN_STEM,
    PURPLE_CANDLE,
    PURPLE_CANDLE_CAKE,
    PURPLE_GLAZED_TERRACOTTA,
    PURPUR_BLOCK,
    PURPUR_STAIRS,
    QUARTZ_BLOCK,
    QUARTZ_BRICKS,
    QUARTZ_ORE,
    QUARTZ_STAIRS,
    RAIL,
    RAW_COPPER_BLOCK,
    RAW_GOLD_BLOCK,
    RAW_IRON_BLOCK,
    REDSTONE_BLOCK,
    REDSTONE_LAMP,
    REDSTONE_ORE,
    REDSTONE_TORCH,
    REDSTONE_WIRE,
    RED_CANDLE,
    RED_CANDLE_CAKE,
    RED_FLOWER,
    RED_GLAZED_TERRACOTTA,
    RED_MUSHROOM,
    RED_MUSHROOM_BLOCK,
    RED_NETHER_BRICK,
    RED_NETHER_BRICK_STAIRS,
    RED_SANDSTONE,
    RED_SANDSTONE_STAIRS,
    REINFORCED_DEEPSLATE,
    REPEATING_COMMAND_BLOCK,
    RESERVED6,
    RESPAWN_ANCHOR,
    SAND,
    SANDSTONE,
    SANDSTONE_STAIRS,
    SAPLING,
    SCAFFOLDING,
    SCULK,
    SCULK_CATALYST,
    SCULK_SENSOR,
    SCULK_SHRIEKER,
    SCULK_VEIN,
    SEAGRASS,
    SEA_LANTERN,
    SEA_PICKLE,
    SHROOMLIGHT,
    SHULKER_BOX,
    SILVER_GLAZED_TERRACOTTA,
    SKULL,
    SLIME,
    SMALL_AMETHYST_BUD,
    SMALL_DRIPLEAF_BLOCK,
    SMITHING_TABLE,
    SMOKER,
    SMOOTH_BASALT,
    SMOOTH_QUARTZ_STAIRS,
    SMOOTH_RED_SANDSTONE_STAIRS,
    SMOOTH_SANDSTONE_STAIRS,
    SMOOTH_STONE,
    SNOW,
    SNOW_LAYER,
    SOUL_CAMPFIRE,
    SOUL_FIRE,
    SOUL_LANTERN,
    SOUL_SAND,
    SOUL_SOIL,
    SOUL_TORCH,
    SPONGE,
    SPORE_BLOSSOM,
    SPRUCE_BUTTON,
    SPRUCE_DOOR,
    SPRUCE_FENCE_GATE,
    SPRUCE_PRESSURE_PLATE,
    SPRUCE_STAIRS,
    SPRUCE_STANDING_SIGN,
    SPRUCE_TRAPDOOR,
    SPRUCE_WALL_SIGN,
    STAINED_GLASS,
    STAINED_GLASS_PANE,
    STAINED_HARDENED_CLAY,
    STANDING_BANNER,
    OAK_STANDING_SIGN,
    STICKY_PISTON,
    STICKY_PISTON_ARM_COLLISION,
    STONE,
    STONEBRICK,
    STONECUTTER,
    STONECUTTER_BLOCK,
    STONE_BLOCK_SLAB,
    STONE_BLOCK_SLAB2,
    STONE_BLOCK_SLAB3,
    STONE_BLOCK_SLAB4,
    STONE_BRICK_STAIRS,
    STONE_BUTTON,
    STONE_PRESSURE_PLATE,
    STONE_STAIRS,
    STRIPPED_ACACIA_LOG,
    STRIPPED_BIRCH_LOG,
    STRIPPED_CRIMSON_HYPHAE,
    STRIPPED_CRIMSON_STEM,
    STRIPPED_DARK_OAK_LOG,
    STRIPPED_JUNGLE_LOG,
    STRIPPED_MANGROVE_LOG,
    STRIPPED_MANGROVE_WOOD,
    STRIPPED_OAK_LOG,
    STRIPPED_SPRUCE_LOG,
    STRIPPED_WARPED_HYPHAE,
    STRIPPED_WARPED_STEM,
    STRUCTURE_BLOCK,
    STRUCTURE_VOID,
    SUGAR_CANE,
    SWEET_BERRY_BUSH,
    TALLGRASS,
    TARGET,
    TINTED_GLASS,
    TNT,
    TORCH,
    TRAPDOOR,
    TRAPPED_CHEST,
    TRIPWIRE_HOOK,
    TRIP_WIRE,
    TUFF,
    TURTLE_EGG,
    TWISTING_VINES,
    UNDERWATER_TORCH,
    UNDYED_SHULKER_BOX,
    UNKNOWN,
    UNLIT_REDSTONE_TORCH,
    UNPOWERED_COMPARATOR,
    UNPOWERED_REPEATER,
    VERDANT_FROGLIGHT,
    VINE,
    WALL_BANNER,
    OAK_WALL_SIGN,
    WARPED_BUTTON,
    WARPED_DOOR,
    WARPED_DOUBLE_SLAB,
    WARPED_FENCE,
    WARPED_FENCE_GATE,
    WARPED_FUNGUS,
    WARPED_HYPHAE,
    WARPED_NYLIUM,
    WARPED_PLANKS,
    WARPED_PRESSURE_PLATE,
    WARPED_ROOTS,
    WARPED_SLAB,
    WARPED_STAIRS,
    WARPED_STANDING_SIGN,
    WARPED_STEM,
    WARPED_TRAPDOOR,
    WARPED_WALL_SIGN,
    WARPED_WART_BLOCK,
    WATER,
    WATERLILY,
    WAXED_COPPER,
    WAXED_CUT_COPPER,
    WAXED_CUT_COPPER_SLAB,
    WAXED_CUT_COPPER_STAIRS,
    WAXED_DOUBLE_CUT_COPPER_SLAB,
    WAXED_EXPOSED_COPPER,
    WAXED_EXPOSED_CUT_COPPER,
    WAXED_EXPOSED_CUT_COPPER_SLAB,
    WAXED_EXPOSED_CUT_COPPER_STAIRS,
    WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB,
    WAXED_OXIDIZED_COPPER,
    WAXED_OXIDIZED_CUT_COPPER,
    WAXED_OXIDIZED_CUT_COPPER_SLAB,
    WAXED_OXIDIZED_CUT_COPPER_STAIRS,
    WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB,
    WAXED_WEATHERED_COPPER,
    WAXED_WEATHERED_CUT_COPPER,
    WAXED_WEATHERED_CUT_COPPER_SLAB,
    WAXED_WEATHERED_CUT_COPPER_STAIRS,
    WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB,
    WEATHERED_COPPER,
    WEATHERED_CUT_COPPER,
    WEATHERED_CUT_COPPER_SLAB,
    WEATHERED_CUT_COPPER_STAIRS,
    WEATHERED_DOUBLE_CUT_COPPER_SLAB,
    WEB,
    WEEPING_VINES,
    WHEAT,
    WHITE_CANDLE,
    WHITE_CANDLE_CAKE,
    WHITE_GLAZED_TERRACOTTA,
    WITHER_ROSE,
    WOOD,
    OAK_BUTTON,
    WOODEN_DOOR,
    WOODEN_PRESSURE_PLATE,
    WOODEN_SLAB,
    WHITE_WOOL,
    ORANGE_WOOL,
    MAGENTA_WOOL,
    LIGHT_BLUE_WOOL,
    YELLOW_WOOL,
    LIME_WOOL,
    PINK_WOOL,
    GRAY_WOOL,
    SILVER_WOOL,
    CYAN_WOOL,
    PURPLE_WOOL,
    BLUE_WOOL,
    BROWN_WOOL,
    GREEN_WOOL,
    RED_WOOL,
    BLACK_WOOL,
    YELLOW_CANDLE,
    YELLOW_CANDLE_CAKE,
    YELLOW_FLOWER,
    YELLOW_GLAZED_TERRACOTTA,
    BAMBOO_MOSAIC,
    BAMBOO_MOSAIC_STAIRS,
    BAMBOO_MOSAIC_SLAB,
    BAMBOO_MOSAIC_DOUBLE_SLAB,
    BAMBOO_PLANKS,
    BAMBOO_BUTTON,
    BAMBOO_DOOR,
    BAMBOO_FENCE,
    BAMBOO_FENCE_GATE,
    BAMBOO_PRESSURE_PLATE,
    BAMBOO_WALL_SIGN,
    BAMBOO_STANDING_SIGN,
    BAMBOO_SLAB,
    BAMBOO_STAIRS,
    BAMBOO_TRAPDOOR,
    BAMBOO_RAFT,
    BAMBOO_CHEST_RAFT,
    BAMBOO_BLOCK,
    STRIPPED_BAMBOO_BLOCK,
    CALIBRATED_SCULK_SENSOR,
    CHERRY_LEAVES,
    CHERRY_LOG,
    STRIPPED_CHERRY_LOG,
    CHERRY_WOOD,
    STRIPPED_CHERRY_WOOD,
    CHERRY_PLANKS,
    CHERRY_BUTTON,
    CHERRY_DOOR,
    CHERRY_FENCE,
    CHERRY_FENCE_GATE,
    CHERRY_PRESSURE_PLATE,
    CHERRY_WALL_SIGN,
    CHERRY_STANDING_SIGN,
    CHERRY_SLAB,
    CHERRY_DOUBLE_SLAB,
    CHERRY_STAIRS,
    CHERRY_TRAPDOOR,
    CHERRY_BOAT,
    CHERRY_CHEST_BOAT,
    CHERRY_SAPLING,
    CHISELED_BOOKSHELF,
    DECORATED_POT,
    OAK_HANGING_SIGN,
    SPRUCE_HANGING_SIGN,
    BIRCH_HANGING_SIGN,
    JUNGLE_HANGING_SIGN,
    ACACIA_HANGING_SIGN,
    DARK_OAK_HANGING_SIGN,
    MANGROVE_HANGING_SIGN,
    CRIMSON_HANGING_SIGN,
    WARPED_HANGING_SIGN,
    BAMBOO_HANGING_SIGN,
    CHERRY_HANGING_SIGN,
    PIGLIN_HEAD,
    PINK_PETALS,
    PITCHER_PLANT,
    PITCHER_POD,
    SNIFFER_EGG,
    SUSPICIOUS_GRAVEL,
    SUSPICIOUS_SAND,
    TORCHFLOWER;

    private static final List<BlockType> woolTypes = Arrays.asList(WHITE_WOOL, ORANGE_WOOL, LIGHT_BLUE_WOOL,
            MAGENTA_WOOL, YELLOW_WOOL, LIME_WOOL, PINK_WOOL, GRAY_WOOL, SILVER_WOOL, CYAN_WOOL, PURPLE_WOOL,
            BLUE_WOOL, BROWN_WOOL, GREEN_WOOL, RED_WOOL, BLACK_WOOL);

    public boolean isWool(BlockType blockType) {
        return BlockType.woolTypes.contains(blockType);
    }
}
