package org.jukeboxmc.world.gamerule;

import java.util.Arrays;

public enum GameRule {

    COMMAND_BLOCKS_ENABLED( "commandBlocksEnabled", true, Type.BOOLEAN ),
    COMMAND_BLOCK_OUTPUT( "commandBlockOutput", true, Type.BOOLEAN ),
    DO_DAYLIGHT_CYCLE( "doDaylightCycle", true, Type.BOOLEAN ), //Implemented
    DO_ENTITY_DROPS( "doEntityDrops", true, Type.BOOLEAN ),
    DO_FIRE_TICK( "doFireTick", true, Type.BOOLEAN ),
    DO_INSOMNIA( "doInsomnia", true, Type.BOOLEAN ),
    DO_IMMEDIATE_RESPAWN( "doImmediateRespawn", false, Type.BOOLEAN ),
    DO_MOB_LOOT( "doMobLoot", true, Type.BOOLEAN ),
    DO_MOB_SPAWNING( "doMobSpawning", true, Type.BOOLEAN ),
    DO_TILE_DROPS( "doTileDrops", true, Type.BOOLEAN ),
    DO_WEATHER_CYCLE( "doWeatherCycle", true, Type.BOOLEAN ),
    DROWNING_DAMAGE( "drowningDamage", true, Type.BOOLEAN ),
    FALL_DAMAGE( "fallDamage", true, Type.BOOLEAN ),
    FIRE_DAMAGE( "fireDamage", true, Type.BOOLEAN ),
    KEEP_INVENTORY( "keepInventory", false, Type.BOOLEAN ),
    MAX_COMMAND_CHAIN_LENGTH( "maxCommandChainLength", 65536, Type.INT ),
    MOB_GRIEFING( "mobGriefing", true, Type.BOOLEAN ),
    NATURAL_REGENERATION( "naturalRegeneration", true, Type.BOOLEAN ),
    PVP( "pvp", true, Type.BOOLEAN ),
    RANDOM_TICK_SPEED( "randomTickSpeed", 1, Type.INT ),
    SEND_COMMAND_FEEDBACK( "sendCommandFeedback", true, Type.BOOLEAN ),
    SHOW_COORDINATES( "showCoordinates", true, Type.BOOLEAN ), //Implemented
    SHOW_DEATH_MESSAGES( "showDeathMessages", true, Type.BOOLEAN ),
    SPAWN_RADIUS( "spawnRadius", 5, Type.INT),
    TNT_EXPLODES( "tntExplodes", true, Type.BOOLEAN ),
    SHOW_TAGS( "showTags", true, Type.BOOLEAN ),
    FREEZE_DAMAGE( "freezeDamage", true, Type.BOOLEAN ),
    RESPAWN_BLOCKS_EXPLODE( "respawnBlocksExplode", true, Type.BOOLEAN ),
    SHOW_BORDER_EFFECT( "showBorderEffect", true, Type.BOOLEAN ),
    FUNCTION_COMMAND_LIMIT( "functionCommandLimit", 10000, Type.BOOLEAN );

    private final String identifier;
    private final Object defaultValue;
    private final Type type;

    GameRule( String identifier, Object defaultValue, Type type ) {
        this.identifier = identifier;
        this.defaultValue = defaultValue;
        this.type = type;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public Object getDefaultValue() {
        return this.defaultValue;
    }

    public Type getType() {
        return this.type;
    }

    public static GameRule fromIdentifier( String identifier ) {
        return Arrays.stream( values() ).filter( gameRule -> gameRule.getIdentifier().equalsIgnoreCase( identifier ) ).findFirst().orElseGet( null );
    }

    public static boolean parseByteToBoolean( byte value ) {
        return value == 1;
    }

    public enum Type {
        INT,
        BOOLEAN
    }
}
