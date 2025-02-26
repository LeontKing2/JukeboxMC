package org.jukeboxmc.entity;

import org.apache.commons.math3.util.FastMath;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.packet.EntityEventPacket;
import org.cloudburstmc.protocol.bedrock.packet.MobEffectPacket;
import org.jukeboxmc.Server;
import org.jukeboxmc.block.Block;
import org.jukeboxmc.block.BlockType;
import org.jukeboxmc.entity.attribute.Attribute;
import org.jukeboxmc.entity.attribute.AttributeType;
import org.jukeboxmc.event.entity.*;
import org.jukeboxmc.event.player.PlayerInteractEvent;
import org.jukeboxmc.math.Vector;
import org.jukeboxmc.player.Player;
import org.jukeboxmc.potion.Effect;
import org.jukeboxmc.potion.EffectType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public abstract class EntityLiving extends Entity {

    protected int deadTimer = 0;
    protected float lastDamage = 0;
    protected byte attackCoolDown = 0;

    protected EntityDamageEvent.DamageSource lastDamageSource = EntityDamageEvent.DamageSource.COMMAND;
    protected Entity lastDamageEntity;

    protected final Map<AttributeType, Attribute> attributes = new HashMap<>();
    protected final Map<EffectType, Effect> effects = new HashMap<>();

    public EntityLiving() {
        this.addAttribute( AttributeType.HEALTH );
        this.addAttribute( AttributeType.ABSORPTION );
        this.addAttribute( AttributeType.ATTACK_DAMAGE );
        this.addAttribute( AttributeType.FOLLOW_RANGE );
        this.addAttribute( AttributeType.MOVEMENT );
        this.addAttribute( AttributeType.KNOCKBACK_RESISTENCE );
    }

    @Override
    public void update( long currentTick ) {
        if ( !( this.isDead || this.getHealth() <= 0 ) ) {
            super.update( currentTick );
        }

        if ( this.lastDamageEntity != null && this.lastDamageEntity.isDead() ) {
            this.lastDamageEntity = null;
        }

        if ( this.getHealth() < 1 ) {
            if ( this.deadTimer > 0 && this.deadTimer-- > 1 ) {
                this.despawn();
                this.isDead = true;
                this.deadTimer = 0;
            }
        } else {
            this.deadTimer = 0;
        }

        if ( this.isDead() || this.getHealth() <= 0 ) {
            return;
        }

        if ( this.attackCoolDown > 0 ) {
            this.attackCoolDown--;
        }

        if ( this.isOnLadder() ) {
            this.fallDistance = 0;
        }

        if ( !this.effects.isEmpty() ) {
            for ( Effect effect : this.effects.values() ) {
                effect.update( currentTick );
                if ( effect.canExecute() ) {
                    effect.apply( this );
                }

                effect.setDuration( effect.getDuration() - 1 );
                if ( effect.getDuration() <= 0 ) {
                    this.removeEffect( effect.getEffectType() );
                }
            }
        }
    }

    @Override
    public boolean damage( EntityDamageEvent event ) {
        if ( this.getHealth() <= 0 ) {
            return false;
        }

        if ( this.hasEffect( EffectType.FIRE_RESISTANCE ) &&
                ( event.getDamageSource().equals( EntityDamageEvent.DamageSource.FIRE ) ||
                        event.getDamageSource().equals( EntityDamageEvent.DamageSource.ON_FIRE ) ||
                        event.getDamageSource().equals( EntityDamageEvent.DamageSource.LAVA ) ) ) {
            return false;
        }

        float damage = this.applyArmorReduction( event, false );
        damage = this.applyFeatherFallingReduction( event, damage );
        damage = this.applyProtectionReduction( event, damage );
        damage = this.applyProjectileProtectionReduction( event, damage );
        damage = this.applyFireProtectionReduction( event, damage );
        damage = this.applyResistanceEffectReduction( event, damage );

        float absorption = this.getAbsorption();
        if ( absorption > 0 ) {
            damage = Math.max( damage - absorption, 0 );
        }

        if ( this.attackCoolDown > 0 && damage <= this.lastDamage ) {
            return false;
        }

        event.setFinalDamage( damage );
        if ( !super.damage( event ) ) {
            return false;
        }

        float damageToBeDealt;
        if ( damage != event.getFinalDamage() ) {
            damageToBeDealt = event.getFinalDamage();
        } else {
            damageToBeDealt = this.applyArmorReduction( event, true );
            damageToBeDealt = this.applyFeatherFallingReduction( event, damageToBeDealt );
            damageToBeDealt = this.applyProtectionReduction( event, damageToBeDealt );
            damageToBeDealt = this.applyProjectileProtectionReduction( event, damageToBeDealt );
            damageToBeDealt = this.applyFireProtectionReduction( event, damageToBeDealt );
            damageToBeDealt = this.applyResistanceEffectReduction( event, damageToBeDealt );
            absorption = this.getAbsorption();
            if ( absorption > 0 ) {
                float oldDamage = damageToBeDealt;
                damageToBeDealt = Math.max( damage - absorption, 0 );
                this.setAbsorption( absorption - ( oldDamage - damageToBeDealt ) );
            }
        }

        float health = this.getHealth() - damageToBeDealt;

        if ( health > 0 ) {
            EntityEventPacket entityEventPacket = new EntityEventPacket();
            entityEventPacket.setRuntimeEntityId( this.entityId );
            entityEventPacket.setType( EntityEventType.HURT );
            Server.getInstance().broadcastPacket( entityEventPacket );
        }

        if ( event instanceof EntityDamageByEntityEvent damageByEntityEvent ) {
            Entity damager = damageByEntityEvent.getDamager();
            float diffX = this.getX() - damager.getX();
            float diffZ = this.getZ() - damager.getZ();

            float distance = (float) Math.sqrt( diffX * diffX + diffZ * diffZ );
            if ( distance > 0.0 ) {
                float baseModifier = 0.3F;

                distance = 1 / distance;

                Vector velocity = this.getVelocity();
                velocity = velocity.divide( 2f, 2f, 2f );
                velocity = velocity.add(
                        ( diffX * distance * baseModifier ),
                        baseModifier,
                        ( diffZ * distance * baseModifier )
                );

                if ( velocity.getY() > baseModifier ) {
                    velocity.setY( baseModifier );
                }

                this.setVelocity( velocity, true );
            }
        }

        this.lastDamage = damage;
        this.lastDamageSource = event.getDamageSource();
        this.lastDamageEntity = ( event instanceof EntityDamageByEntityEvent ) ? ( (EntityDamageByEntityEvent) event ).getDamager() : null;
        this.attackCoolDown = 10;
        this.setHealth( health <= 0 ? 0 : health );
        return true;
    }

    public float applyArmorReduction( EntityDamageEvent event, boolean damageArmor ) {
        return event.getDamage();
    }

    public float applyFeatherFallingReduction( EntityDamageEvent event, float damage ) {
        return 0;
    }

    public float applyProtectionReduction( EntityDamageEvent event, float damage ) {
        return 0;
    }

    public float applyProjectileProtectionReduction( EntityDamageEvent event, float damage ) {
        return 0;
    }

    public float applyFireProtectionReduction( EntityDamageEvent event, float damage ) {
        return 0;
    }

    public float applyResistanceEffectReduction( EntityDamageEvent event, float damage ) {
        if ( event.getEntity() instanceof EntityLiving entityLiving ) {
            if ( entityLiving.hasEffect( EffectType.RESISTANCE ) ) {
                int amplifier = entityLiving.getEffect( EffectType.RESISTANCE ).getAmplifier();
                return -( damage * 0.20f * amplifier + 1 );
            }
        }
        return event.getDamage();
    }

    @Override
    public void fall() {
        float distanceReduce = 0.0f;
        if ( this.hasEffect( EffectType.JUMP_BOOST ) ) {
            int jumpAmplifier = this.getEffect( EffectType.JUMP_BOOST ).getAmplifier();
            if ( jumpAmplifier != -1 ) {
                distanceReduce = jumpAmplifier + 1;
            }
        }
        float damage = (float) FastMath.floor( this.fallDistance - 3f - distanceReduce );
        if ( damage > 0 ) {
            this.damage( new EntityDamageEvent( this, damage, EntityDamageEvent.DamageSource.FALL ) );
        }

        if ( this.fallDistance > 0.75 ) {
            Block blockDown = this.location.subtract( 0, 0.1f, 0 ).getBlock();
            if ( blockDown.getType().equals( BlockType.FARMLAND ) ) {
                if ( this instanceof Player player ) {
                    PlayerInteractEvent playerInteractEvent = new PlayerInteractEvent( player, PlayerInteractEvent.Action.PHYSICAL, player.getInventory().getItemInHand(), blockDown );
                    Server.getInstance().getPluginManager().callEvent( playerInteractEvent );
                    if ( !playerInteractEvent.isCancelled() ) {
                        this.location.getWorld().setBlock( blockDown.getLocation(), Block.create( BlockType.DIRT ) );
                    }
                } else {
                    this.location.getWorld().setBlock( blockDown.getLocation(), Block.create( BlockType.DIRT ) );
                }
            }
        }
    }

    protected void killInternal() {
        this.deadTimer = 20;

        EntityEventPacket entityEventPacket = new EntityEventPacket();
        entityEventPacket.setRuntimeEntityId( this.entityId );
        entityEventPacket.setType( EntityEventType.DEATH );
        Server.getInstance().broadcastPacket( entityEventPacket );

        this.removeAllEffects();

        this.fireTicks = 0;
        this.setBurning( false );
    }

    public EntityDamageEvent.DamageSource getLastDamageSource() {
        return this.lastDamageSource;
    }

    public Entity getLastDamageEntity() {
        return this.lastDamageEntity;
    }

    public void addEffect( Effect effect ) {
        EntityAddEffectEvent addEffectEvent = new EntityAddEffectEvent( this, effect );
        Server.getInstance().getPluginManager().callEvent( addEffectEvent );
        if ( addEffectEvent.isCancelled() ) {
            return;
        }
        Effect oldEffect = this.getEffect( addEffectEvent.getEffect().getEffectType() );

        addEffectEvent.getEffect().apply( this );
        if ( this instanceof Player player ) {
            MobEffectPacket mobEffectPacket = new MobEffectPacket();
            mobEffectPacket.setRuntimeEntityId( this.entityId );
            mobEffectPacket.setEffectId( addEffectEvent.getEffect().getId() );
            mobEffectPacket.setAmplifier( addEffectEvent.getEffect().getAmplifier() );
            mobEffectPacket.setParticles( addEffectEvent.getEffect().isVisible() );
            mobEffectPacket.setDuration( addEffectEvent.getEffect().getDuration() );
            if ( oldEffect != null ) {
                mobEffectPacket.setEvent( MobEffectPacket.Event.MODIFY );
            } else {
                mobEffectPacket.setEvent( MobEffectPacket.Event.ADD );
            }
            player.getPlayerConnection().sendPacket( mobEffectPacket );
        }
        this.effects.put( addEffectEvent.getEffect().getEffectType(), addEffectEvent.getEffect() );

        this.calculateEffectColor();
    }

    public void removeEffect( EffectType effectType ) {
        if ( this.effects.containsKey( effectType ) ) {
            Effect effect = this.effects.get( effectType );
            EntityRemoveEffectEvent removeEffectEvent = new EntityRemoveEffectEvent( this, effect );
            Server.getInstance().getPluginManager().callEvent( removeEffectEvent );
            if ( removeEffectEvent.isCancelled() ) {
                return;
            }
            this.effects.remove( effectType );
            effect.remove( this );

            if ( this instanceof Player player ) {
                MobEffectPacket mobEffectPacket = new MobEffectPacket();
                mobEffectPacket.setRuntimeEntityId( this.entityId );
                mobEffectPacket.setEvent( MobEffectPacket.Event.REMOVE );
                mobEffectPacket.setEffectId( effect.getId() );
                player.getPlayerConnection().sendPacket( mobEffectPacket );
            }

            this.calculateEffectColor();
        }
    }

    public void removeAllEffects() {
        for ( EffectType effectType : this.effects.keySet() ) {
            this.removeEffect( effectType );
        }
    }

    public <T extends Effect> T getEffect( EffectType effectType ) {
        return (T) this.effects.get( effectType );
    }

    public boolean hasEffect( EffectType effectType ) {
        return this.effects.containsKey( effectType );
    }

    private void calculateEffectColor() {
        int[] color = new int[3];
        int count = 0;
        for ( Effect effect : this.effects.values() ) {
            if ( effect.isVisible() ) {
                int[] c = effect.getColor();
                color[0] += c[0] * ( effect.getAmplifier() + 1 );
                color[1] += c[1] * ( effect.getAmplifier() + 1 );
                color[2] += c[2] * ( effect.getAmplifier() + 1 );
                count += effect.getAmplifier() + 1;
            }
        }

        if ( count > 0 ) {
            int r = ( color[0] / count ) & 0xff;
            int g = ( color[1] / count ) & 0xff;
            int b = ( color[2] / count ) & 0xff;

            this.updateMetadata( this.metadata.setInt( EntityDataTypes.EFFECT_COLOR, ( r << 16 ) + ( g << 8 ) + b ) );
            this.updateMetadata( this.metadata.setByte( EntityDataTypes.EFFECT_AMBIENCE, (byte) 0 ) );
        } else {
            this.updateMetadata( this.metadata.setInt( EntityDataTypes.EFFECT_COLOR, 0 ) );
            this.updateMetadata( this.metadata.setByte( EntityDataTypes.EFFECT_AMBIENCE, (byte) 0 ) );
        }
    }

    public void addAttribute( AttributeType attributeType ) {
        this.attributes.put( attributeType, attributeType.getAttribute() );
    }

    public Attribute getAttribute( AttributeType attributeType ) {
        return this.attributes.get( attributeType );
    }

    public void setAttributes( AttributeType attributes, float value ) {
        Attribute attribute = this.attributes.get( attributes );
        if ( attribute == null ) {
            return;
        }
        attribute.setCurrentValue( value );
    }

    public float getAttributeValue( AttributeType attributeType ) {
        return this.getAttribute( attributeType ).getCurrentValue();
    }

    public Collection<Attribute> getAttributes() {
        return this.attributes.values();
    }

    public float getHealth() {
        return this.getAttributeValue( AttributeType.HEALTH );
    }

    public float getMaxHealth() {
        return this.getAttribute( AttributeType.HEALTH ).getMaxValue();
    }

    public void setHealth( float value ) {
        if ( value < 1 ) {
            value = 0;
            this.killInternal();
        }
        Attribute attribute = this.getAttribute( AttributeType.HEALTH );
        attribute.setCurrentValue( value );
    }

    public void setHeal( float value ) {
        this.setHealth( Math.min( 20, Math.max( 0, value ) ) );
    }

    public void setHeal( float value, EntityHealEvent.Cause cause ) {
        EntityHealEvent entityHealEvent = new EntityHealEvent( this, this.getHealth() + value, cause );
        Server.getInstance().getPluginManager().callEvent( entityHealEvent );
        if ( entityHealEvent.isCancelled() ) {
            return;
        }
        this.setHealth( Math.min( 20, Math.max( 0, entityHealEvent.getHeal() ) ) );
    }

    public float getAbsorption() {
        return this.getAttributeValue( AttributeType.ABSORPTION );
    }

    public void setAbsorption( float value ) {
        this.setAttributes( AttributeType.ABSORPTION, value );
    }

    public float getAttackDamage() {
        return this.getAttributeValue( AttributeType.ATTACK_DAMAGE );
    }

    public void setAttackDamage( float value ) {
        this.setAttributes( AttributeType.ATTACK_DAMAGE, value );
    }

    public float getFollowRange() {
        return this.getAttributeValue( AttributeType.FOLLOW_RANGE );
    }

    public void setFollowRange( float value ) {
        this.setAttributes( AttributeType.FOLLOW_RANGE, value );
    }

    public float getMovement() {
        return this.getAttributeValue( AttributeType.MOVEMENT );
    }

    public void setMovement( float value ) {
        this.setAttributes( AttributeType.MOVEMENT, value );
    }

    public float getKnockbackResistence() {
        return this.getAttributeValue( AttributeType.KNOCKBACK_RESISTENCE );
    }

    public void setKnockbackResistence( float value ) {
        this.setAttributes( AttributeType.KNOCKBACK_RESISTENCE, value );
    }

}
