package org.jukeboxmc.inventory;

import org.cloudburstmc.protocol.bedrock.packet.InventorySlotPacket;
import org.jukeboxmc.item.Item;
import org.jukeboxmc.player.Player;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class CursorInventory extends Inventory{

    public CursorInventory( InventoryHolder holder ) {
        super( holder, 1 );
    }

    @Override
    public Player getInventoryHolder() {
        return (Player) this.holder;
    }

    @Override
    public InventoryType getType() {
        return InventoryType.CURSOR;
    }

    @Override
    public void sendContents( Player player ) {
        this.sendContents( 0, player );
    }

    @Override
    public void sendContents( int slot, Player player ) {
        InventorySlotPacket inventorySlotPacket = new InventorySlotPacket();
        inventorySlotPacket.setContainerId( WindowId.CURSOR_DEPRECATED.getId() );
        inventorySlotPacket.setItem( this.content[slot].toItemData() );
        inventorySlotPacket.setSlot( slot );
        player.getPlayerConnection().sendPacket( inventorySlotPacket );
    }


    public void setItem( Item item ) {
        this.setItem( 0, item );
    }

    public Item getItem() {
        return this.getItem( 0 );
    }
}
