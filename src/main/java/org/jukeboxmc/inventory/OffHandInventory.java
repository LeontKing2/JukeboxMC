package org.jukeboxmc.inventory;

import org.cloudburstmc.protocol.bedrock.packet.InventoryContentPacket;
import org.cloudburstmc.protocol.bedrock.packet.InventorySlotPacket;
import org.jukeboxmc.item.Item;
import org.jukeboxmc.player.Player;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class OffHandInventory extends Inventory{

    public OffHandInventory( InventoryHolder holder ) {
        super( holder, 1 );
    }

    @Override
    public void sendContents( Player player ) {
        InventoryContentPacket inventoryContentPacket = new InventoryContentPacket();
        inventoryContentPacket.setContainerId( WindowId.OFFHAND_DEPRECATED.getId() );
        inventoryContentPacket.setContents( this.getItemDataContents() );
        player.getPlayerConnection().sendPacket( inventoryContentPacket );
    }

    @Override
    public void sendContents( int slot, Player player ) {
        InventorySlotPacket inventorySlotPacket = new InventorySlotPacket();
        inventorySlotPacket.setContainerId( WindowId.OFFHAND_DEPRECATED.getId() );
        inventorySlotPacket.setItem( this.content[slot].toItemData() );
        inventorySlotPacket.setSlot( slot );
        player.getPlayerConnection().sendPacket( inventorySlotPacket );
    }

    @Override
    public Player getInventoryHolder() {
        return (Player) this.holder;
    }

    @Override
    public InventoryType getType() {
        return InventoryType.OFFHAND;
    }

    @Override
    public void setItem( int slot, Item item, boolean sendContent ) {
        super.setItem( slot - 1, item, sendContent );
    }

    @Override
    public Item getItem( int slot ) {
        return super.getItem( slot - 1 );
    }
}
