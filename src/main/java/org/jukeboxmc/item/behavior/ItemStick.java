package org.jukeboxmc.item.behavior;

import org.jukeboxmc.item.Burnable;
import org.jukeboxmc.item.Item;
import org.jukeboxmc.item.ItemType;
import org.jukeboxmc.util.Identifier;

import java.time.Duration;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class ItemStick extends Item implements Burnable {

    public ItemStick( Identifier identifier ) {
        super( identifier );
    }

    public ItemStick( ItemType itemType ) {
        super( itemType );
    }

    @Override
    public Duration getBurnTime() {
        return Duration.ofMillis( 100 );
    }
}
