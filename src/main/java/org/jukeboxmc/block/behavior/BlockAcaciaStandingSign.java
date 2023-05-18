package org.jukeboxmc.block.behavior;

import org.apache.commons.math3.util.FastMath;
import org.cloudburstmc.nbt.NbtMap;
import org.jukeboxmc.block.Block;
import org.jukeboxmc.block.BlockType;
import org.jukeboxmc.block.direction.BlockFace;
import org.jukeboxmc.block.direction.SignDirection;
import org.jukeboxmc.blockentity.BlockEntitySign;
import org.jukeboxmc.blockentity.BlockEntityType;
import org.jukeboxmc.item.Item;
import org.jukeboxmc.item.ItemType;
import org.jukeboxmc.math.Vector;
import org.jukeboxmc.player.Player;
import org.jukeboxmc.util.Identifier;
import org.jukeboxmc.world.World;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class BlockAcaciaStandingSign extends BlockSign {

    public BlockAcaciaStandingSign( Identifier identifier ) {
        super( identifier );
    }

    public BlockAcaciaStandingSign( Identifier identifier, NbtMap blockStates ) {
        super( identifier, blockStates );
    }

    @Override
    public boolean placeBlock( Player player, World world, Vector blockPosition, Vector placePosition, Vector clickedPosition, Item itemInHand, BlockFace blockFace ) {
        Block block = world.getBlock( placePosition );
        if ( blockFace == BlockFace.UP ) {
            this.setSignDirection( SignDirection.values()[(int) FastMath.floor( ( ( player.getLocation().getYaw() + 180 ) * 16 / 360 ) + 0.5 ) & 0x0f] );
            world.setBlock( placePosition, this, 0 );
        } else {
            BlockAcaciaWallSign blockWallSign = Block.create( BlockType.ACACIA_WALL_SIGN );
            blockWallSign.setBlockFace( blockFace );
            world.setBlock( placePosition, blockWallSign, 0 );
        }
        BlockEntitySign.create( BlockEntityType.SIGN, this ).spawn();
        return super.placeBlock(player, world, blockPosition, placePosition, clickedPosition, itemInHand, blockFace);
    }

    @Override
    public Item toItem() {
        return Item.create( ItemType.ACACIA_SIGN );
    }
}
