package org.jukeboxmc.block.behavior;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.jukeboxmc.block.Block;
import org.jukeboxmc.block.BlockType;
import org.jukeboxmc.block.direction.BlockFace;
import org.jukeboxmc.block.direction.Direction;
import org.jukeboxmc.item.Item;
import org.jukeboxmc.math.Vector;
import org.jukeboxmc.player.Player;
import org.jukeboxmc.util.Identifier;
import org.jukeboxmc.world.World;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class BlockTrapdoor extends Block implements Waterlogable{

    public BlockTrapdoor( Identifier identifier ) {
        super( identifier );
    }

    public BlockTrapdoor( Identifier identifier, NbtMap blockStates ) {
        super( identifier, blockStates );
    }

    @Override
    public boolean placeBlock( Player player, World world, Vector blockPosition, Vector placePosition, Vector clickedPosition, Item itemInHand, BlockFace blockFace ) {
        Direction playerDirection = player.getDirection();

        if ( ( clickedPosition.getY() > 0.5 && blockFace != BlockFace.UP ) || blockFace == BlockFace.DOWN ) {
            this.setUpsideDown( true );
        }

        if ( playerDirection == Direction.NORTH ) {
            this.setDirection( Direction.NORTH );
        } else if ( playerDirection == Direction.EAST ) {
            this.setDirection( Direction.WEST );
        } else if ( playerDirection == Direction.SOUTH ) {
            this.setDirection( Direction.EAST );
        } else if ( playerDirection == Direction.WEST ) {
            this.setDirection( Direction.SOUTH );
        }
        this.setOpen( false );

        if (world.getBlock(placePosition) instanceof BlockWater blockWater && blockWater.getLiquidDepth() == 0) {
            world.setBlock(placePosition, Block.create(BlockType.WATER), 1, false);
        }
        world.setBlock(placePosition, this);
        return true;
    }
    @Override
    public boolean interact( Player player, Vector blockPosition, Vector clickedPosition, BlockFace blockFace, Item itemInHand ) {
        this.setOpen( !this.isOpen()  );
        this.location.getWorld().sendLevelEvent( this.location, LevelEvent.SOUND_DOOR_OPEN, 0 );
        return true;
    }

    @Override
    public int getWaterLoggingLevel() {
        return 1;
    }

    public void setOpen(boolean value ) {
        this.setState( "open_bit", value ? (byte) 1 : (byte) 0 );
    }

    public boolean isOpen() {
        return this.stateExists( "open_bit" ) && this.getByteState( "open_bit" ) == 1;
    }

    public void setUpsideDown( boolean value ) {
        this.setState( "upside_down_bit", value ? (byte) 1 : (byte) 0 );
    }

    public boolean isUpsideDown() {
        return this.stateExists( "upside_down_bit" ) && this.getByteState( "upside_down_bit" ) == 1;
    }

    public void setDirection( Direction direction ) {
        switch ( direction ) {
            case SOUTH -> this.setState( "direction", 0 );
            case WEST -> this.setState( "direction", 1 );
            case NORTH -> this.setState( "direction", 2 );
            case EAST -> this.setState( "direction", 3 );
        }
    }

    public Direction getDirection() {
        int value = this.stateExists( "direction" ) ? this.getIntState( "direction" ) : 0;
        return switch ( value ) {
            case 0 -> Direction.SOUTH;
            case 1 -> Direction.WEST;
            case 2 -> Direction.NORTH;
            default -> Direction.EAST;
        };
    }
}