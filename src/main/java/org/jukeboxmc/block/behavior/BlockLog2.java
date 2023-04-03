package org.jukeboxmc.block.behavior;

import org.cloudburstmc.nbt.NbtMap;
import org.jukeboxmc.block.Block;
import org.jukeboxmc.block.data.Axis;
import org.jukeboxmc.block.data.LogType2;
import org.jukeboxmc.block.direction.BlockFace;
import org.jukeboxmc.item.Item;
import org.jukeboxmc.item.ItemType;
import org.jukeboxmc.item.behavior.ItemLog2;
import org.jukeboxmc.math.Vector;
import org.jukeboxmc.player.Player;
import org.jukeboxmc.util.Identifier;
import org.jukeboxmc.world.World;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class BlockLog2 extends Block {

    public BlockLog2( Identifier identifier ) {
        super( identifier );
    }

    public BlockLog2( Identifier identifier, NbtMap blockStates ) {
        super( identifier, blockStates );
    }

    @Override
    public boolean placeBlock( Player player, World world, Vector blockPosition, Vector placePosition, Vector clickedPosition, Item itemInHand, BlockFace blockFace ) {
        if ( blockFace == BlockFace.UP || blockFace == BlockFace.DOWN ) {
            this.setAxis( Axis.Y );
        } else if ( blockFace == BlockFace.NORTH || blockFace == BlockFace.SOUTH ) {
            this.setAxis( Axis.Z );
        } else {
            this.setAxis( Axis.X );
        }

        world.setBlock( placePosition, this, 0);
        return true;
    }

    @Override
    public Item toItem() {
        return Item.<ItemLog2>create( ItemType.LOG2 ).setLogType( this.getLogType() );
    }

    public void setAxis( Axis axis ) {
        this.setState( "pillar_axis", axis.name().toLowerCase() );
    }

    public Axis getAxis() {
        return this.stateExists( "pillar_axis" ) ? Axis.valueOf( this.getStringState( "pillar_axis" ) ) : Axis.Y;
    }

    public BlockLog2 setLogType( LogType2 logType ) {
        return this.setState( "new_log_type", logType.name().toLowerCase() );
    }

    public LogType2 getLogType() {
        return this.stateExists( "new_log_type" ) ? LogType2.valueOf( this.getStringState( "new_log_type" ) ) : LogType2.ACACIA;
    }
}
