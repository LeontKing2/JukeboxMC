package org.jukeboxmc.potion;

import org.jukeboxmc.entity.EntityLiving;

import java.awt.*;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class InstantDamageEffect extends Effect {

    @Override
    public int getId() {
        return 7;
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.INSTANT_DAMAGE;
    }

    @Override
    public Color getEffectColor() {
        return new Color( 67, 10, 9 );
    }

    @Override
    public void apply( EntityLiving entityLiving ) {

    }

    @Override
    public void update( long currentTick ) {

    }

    @Override
    public void remove( EntityLiving entityLiving ) {

    }
}
