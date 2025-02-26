package org.jukeboxmc.block.behavior;

import org.cloudburstmc.nbt.NbtMap;
import org.jukeboxmc.block.data.StoneSlab4Type;
import org.jukeboxmc.util.Identifier;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class BlockDoubleStoneSlab4 extends BlockSlab {

    public BlockDoubleStoneSlab4( Identifier identifier ) {
        super( identifier );
    }

    public BlockDoubleStoneSlab4( Identifier identifier, NbtMap blockStates ) {
        super( identifier, blockStates );
    }

    public BlockDoubleStoneSlab4 setStoneSlabType( StoneSlab4Type stoneSlabType ) {
        return this.setState( "stone_slab_type_4", stoneSlabType.name().toLowerCase() );
    }

    public StoneSlab4Type getStoneSlabType() {
        return this.stateExists( "stone_slab_type_4" ) ? StoneSlab4Type.valueOf( this.getStringState( "stone_slab_type_4" ) ) : StoneSlab4Type.STONE;
    }
}
