package org.jukeboxmc.item.behavior;

import org.jukeboxmc.item.ItemType;
import org.jukeboxmc.util.Identifier;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class ItemEnchantedGoldenApple extends ItemFood {

    public ItemEnchantedGoldenApple( Identifier identifier ) {
        super( identifier );
    }

    public ItemEnchantedGoldenApple( ItemType itemType ) {
        super( itemType );
    }

    @Override
    public float getSaturation() {
        return 9.6f;
    }

    @Override
    public int getHunger() {
        return 4;
    }
}
