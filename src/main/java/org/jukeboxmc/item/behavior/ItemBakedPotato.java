package org.jukeboxmc.item.behavior;

import org.jukeboxmc.item.ItemType;
import org.jukeboxmc.util.Identifier;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class ItemBakedPotato extends ItemFood {

    public ItemBakedPotato( Identifier identifier ) {
        super( identifier );
    }

    public ItemBakedPotato( ItemType itemType ) {
        super( itemType );
    }

    @Override
    public float getSaturation() {
        return 6;
    }

    @Override
    public int getHunger() {
        return 5;
    }
}
