package org.jukeboxmc.block.behavior;

import org.cloudburstmc.nbt.NbtMap;
import org.jukeboxmc.block.Block;
import org.jukeboxmc.block.BlockType;
import org.jukeboxmc.block.data.CoralColor;
import org.jukeboxmc.block.direction.BlockFace;
import org.jukeboxmc.block.direction.RotationDirection;
import org.jukeboxmc.item.Item;
import org.jukeboxmc.item.ItemType;
import org.jukeboxmc.item.behavior.ItemCoralFan;
import org.jukeboxmc.math.Vector;
import org.jukeboxmc.player.Player;
import org.jukeboxmc.util.Identifier;
import org.jukeboxmc.world.World;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class BlockCoralFan extends Block implements Waterlogable {

    public BlockCoralFan( Identifier identifier ) {
        super( identifier );
    }

    public BlockCoralFan( Identifier identifier, NbtMap blockStates ) {
        super( identifier, blockStates );
    }

    @Override
    public boolean placeBlock(Player player, World world, Vector blockPosition, Vector placePosition, Vector clickedPosition, Item itemInHand, BlockFace blockFace) {
        if (world.getBlock(placePosition) instanceof BlockWater blockWater && blockWater.getLiquidDepth() == 0) {
            world.setBlock(placePosition, Block.create(BlockType.WATER), 1, false);
        }
        world.setBlock(placePosition, this);
        return true;
    }

    @Override
    public Item toItem() {
        return Item.<ItemCoralFan>create( ItemType.CORAL_FAN ).setCoralColor( this.getCoralColor() );
    }

    @Override
    public int getWaterLoggingLevel() {
        return 1;
    }

    public void setCoralDirection(RotationDirection rotationDirection ) {
        this.setState( "coral_fan_direction", rotationDirection.ordinal() );
    }

    public RotationDirection getRotationDirection() {
        return this.stateExists( "coral_fan_direction" ) ? RotationDirection.values()[this.getIntState( "coral_fan_direction" )] : RotationDirection.EAST_WEST;
    }

    public BlockCoralFan setCoralColor( CoralColor coralColor ) {
        return this.setState( "coral_color", coralColor.name().toLowerCase() );
    }

    public CoralColor getCoralColor() {
        return this.stateExists( "coral_color" ) ? CoralColor.valueOf( this.getStringState( "coral_color" ) ) : CoralColor.BLUE;
    }
}
