package org.jukeboxmc.block.behavior;

import org.cloudburstmc.nbt.NbtMap;
import org.jukeboxmc.block.Block;
import org.jukeboxmc.block.BlockType;
import org.jukeboxmc.block.direction.BlockFace;
import org.jukeboxmc.item.Item;
import org.jukeboxmc.math.Vector;
import org.jukeboxmc.player.Player;
import org.jukeboxmc.util.Identifier;
import org.jukeboxmc.world.World;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class BlockVine extends Block implements Waterlogable{

    public BlockVine( Identifier identifier ) {
        super( identifier );
    }

    public BlockVine( Identifier identifier, NbtMap blockStates ) {
        super( identifier, blockStates );
    }

    @Override
    public boolean placeBlock( Player player, World world, Vector blockPosition, Vector placePosition, Vector clickedPosition, Item itemInHand, BlockFace blockFace ) {
        Block block = world.getBlock( blockPosition );
        if ( block.isSolid() && blockFace.getHorizontalIndex() != -1 ) {
            this.setVineDirection( 1 << blockFace.opposite().getHorizontalIndex() );

            if (world.getBlock(placePosition) instanceof BlockWater blockWater && blockWater.getLiquidDepth() == 0) {
                world.setBlock(placePosition, Block.create(BlockType.WATER), 1, false);
            }
            world.setBlock(placePosition, this);
            return true;
        }
        return false;
    }

    @Override
    public int getWaterLoggingLevel() {
        return 1;
    }

    public BlockVine setVineDirection(BlockFace blockFace ) {
        this.setVineDirection( 1 << blockFace.opposite().getHorizontalIndex() );
        return this;
    }

    public BlockVine setVineDirection( int value ) { //0-15 TODO -> Add Direction Class
        this.setState( "vine_direction_bits", value );
        return this;
    }

    public int getVineDirection() {
        return this.stateExists( "vine_direction_bits" ) ? this.getIntState( "vine_direction_bits" ) : 0;
    }
}
