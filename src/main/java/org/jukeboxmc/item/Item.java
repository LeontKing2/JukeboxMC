package org.jukeboxmc.item;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import lombok.ToString;
import org.cloudburstmc.nbt.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jukeboxmc.block.Block;
import org.jukeboxmc.block.BlockType;
import org.jukeboxmc.block.direction.BlockFace;
import org.jukeboxmc.item.enchantment.Enchantment;
import org.jukeboxmc.item.enchantment.EnchantmentRegistry;
import org.jukeboxmc.item.enchantment.EnchantmentType;
import org.jukeboxmc.math.Location;
import org.jukeboxmc.math.Vector;
import org.jukeboxmc.player.GameMode;
import org.jukeboxmc.player.Player;
import org.jukeboxmc.util.*;
import org.jukeboxmc.world.Sound;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * @author LucGamesYT
 * @version 1.0
 */
@ToString
public class Item implements Cloneable {

    public static int stackNetworkCount = 0;

    protected ItemType itemType;
    protected Identifier identifier;
    protected int runtimeId;
    protected int blockRuntimeId;
    protected int stackNetworkId;

    protected int amount;
    protected int meta;
    protected int durability;
    protected String displayname;
    protected List<String> lore;
    protected NbtMap nbt;

    protected List<String> canPlace;
    protected List<String> canBreak;
    protected Map<EnchantmentType, Enchantment> enchantments;

    protected boolean emptyEnchanted;
    protected boolean unbreakable;

    private ItemProperties itemProperties;

    protected ItemLockType itemLockType;

    public Item( Identifier identifier ) {
        this( identifier, true );
    }

    public Item( Identifier identifier, boolean withNetworkId ) {
        this.itemType = ItemRegistry.getItemType( identifier );
        ItemRegistry.ItemRegistryData itemRegistryData = ItemRegistry.getItemRegistryData( itemType );
        this.identifier = itemRegistryData.getIdentifier();
        this.runtimeId = ItemPalette.getRuntimeId( this.identifier );
        this.blockRuntimeId = 0;
        if ( withNetworkId ) {
            this.stackNetworkId = stackNetworkCount++;
        }
        this.amount = 1;
        this.meta = 0;
        this.durability = 0;
        this.displayname = "";
        this.lore = new LinkedList<>();
        this.nbt = null;
        this.canPlace = new LinkedList<>();
        this.canBreak = new LinkedList<>();
        this.enchantments = new HashMap<>();
        this.itemProperties = ItemRegistry.getItemProperties( identifier );
        this.itemLockType = ItemLockType.NONE;
    }

    public Item( ItemType itemType ) {
        this( itemType, true );
    }

    public Item( ItemType itemType, boolean withNetworkId ) {
        this.itemType = itemType;
        ItemRegistry.ItemRegistryData itemRegistryData = ItemRegistry.getItemRegistryData( itemType );
        this.identifier = itemRegistryData.getIdentifier();
        this.runtimeId = ItemPalette.getRuntimeId( this.identifier );
        if ( withNetworkId ) {
            this.stackNetworkId = stackNetworkCount++;
        }

        try {
            this.blockRuntimeId = Block.create( BlockType.valueOf( itemType.name() ) ).getRuntimeId();
        } catch ( Exception ignored ) {
            this.blockRuntimeId = 0;
        }

        this.amount = 1;
        this.meta = 0;
        this.durability = 0;
        this.displayname = "";
        this.lore = new LinkedList<>();
        this.nbt = null;
        this.canPlace = new LinkedList<>();
        this.canBreak = new LinkedList<>();
        this.enchantments = new HashMap<>();
        this.itemProperties = ItemRegistry.getItemProperties( this.identifier );
        this.itemLockType = ItemLockType.NONE;
    }

    public Item( ItemData itemData ) {
        this( itemData, true );
    }

    public Item( ItemData itemData, boolean withNetworkId ) {
        this.itemType = ItemRegistry.getItemType( ItemPalette.getIdentifier( (short) itemData.getDefinition().getRuntimeId() ) );
        ItemRegistry.ItemRegistryData itemRegistryData = ItemRegistry.getItemRegistryData( this.itemType );
        this.identifier = itemRegistryData.getIdentifier();
        this.runtimeId = ItemPalette.getRuntimeId( this.identifier );
        this.blockRuntimeId = itemData.getBlockDefinition() != null ? itemData.getBlockDefinition().getRuntimeId() : 0;
        if ( withNetworkId ) {
            this.stackNetworkId = stackNetworkCount++;
        }
        this.amount = itemData.getCount();
        this.meta = itemData.getDamage();
        this.durability = 0;
        this.displayname = "";
        this.lore = new LinkedList<>();
        this.nbt = itemData.getTag();
        this.canPlace = new LinkedList<>();
        this.canBreak = new LinkedList<>();
        this.enchantments = new HashMap<>();
        this.itemProperties = ItemRegistry.getItemProperties( identifier );
        this.itemLockType = ItemLockType.NONE;

        if ( this.nbt != null ) {
            this.fromNbt( this.nbt );
        }
    }

    public static <T extends Item> T create( ItemData itemData ) {
        if ( itemData.getDefinition() == ItemDefinition.AIR ) {
            return Item.create( ItemType.AIR );
        }
        Identifier identifier1 = ItemPalette.getIdentifier((short) itemData.getDefinition().getRuntimeId());
        T item = create(identifier1);
        item.setBlockRuntimeId( itemData.getBlockDefinition() != null ? itemData.getBlockDefinition().getRuntimeId() : 0 );
        item.setMeta( itemData.getDamage() );
        item.setAmount( itemData.getCount() );
        item.setNbt( itemData.getTag() );
        if ( item.getNbt() != null ) {
            item.fromNbt( item.getNbt() );
        }
        return item;
    }

    public static <T extends Item> T create( Identifier identifier ) {
        ItemType itemType = ItemRegistry.getItemType( identifier );
        if ( ItemRegistry.itemClassExists( itemType ) ) {
            try {
                Constructor<? extends Item> constructor = ItemRegistry.getItemClass( itemType ).getConstructor( ItemType.class );
                constructor.setAccessible( true );
                return (T) constructor.newInstance( itemType );
            } catch ( Exception e ) {
                throw new RuntimeException( e );
            }
        }
        return (T) new Item( itemType );
    }

    public static <T extends Item> T create( ItemType itemType ) {
        if ( ItemRegistry.itemClassExists( itemType ) ) {
            try {
                Constructor<? extends Item> constructor = ItemRegistry.getItemClass( itemType ).getConstructor( ItemType.class );
                constructor.setAccessible( true );
                return (T) constructor.newInstance( itemType );
            } catch ( Exception e ) {
                throw new RuntimeException( e );
            }
        }
        return (T) new Item( itemType );
    }

    public static <T extends Item> T create( ItemType itemType, int amount ) {
        if ( ItemRegistry.itemClassExists( itemType ) ) {
            try {
                Constructor<? extends Item> constructor = ItemRegistry.getItemClass( itemType ).getConstructor( ItemType.class );
                constructor.setAccessible( true );
                return (T) constructor.newInstance( itemType ).setAmount( amount );
            } catch ( Exception e ) {
                throw new RuntimeException( e );
            }
        }
        return (T) new Item( itemType, true ).setAmount( amount );
    }

    public static <T extends Item> T create( ItemType itemType, int amount, int meta ) {
        if ( ItemRegistry.itemClassExists( itemType ) ) {
            try {
                Constructor<? extends Item> constructor = ItemRegistry.getItemClass( itemType ).getConstructor( ItemType.class );
                constructor.setAccessible( true );
                return (T) constructor.newInstance( itemType ).setAmount( amount ).setMeta( meta );
            } catch ( Exception e ) {
                throw new RuntimeException( e );
            }
        }
        return (T) new Item( itemType, true ).setAmount( amount ).setMeta( meta );
    }

    public static String toBase64( Item item ) {
        NbtMap itemNbt = NbtMap.builder()
                .putString( "Name", item.getIdentifier().getFullName() )
                .putInt( "Count", item.getAmount() )
                .putInt( "Meta", item.getMeta() )
                .putBoolean( "Unbreakable", item.isUnbreakable() )
                .putCompound( "BlockState", item.toBlock().getBlockStates() )
                .putCompound( "tag", item.toNbt() != null ? item.toNbt() : NbtMap.EMPTY )
                .build();
        ByteBuf buffer = Unpooled.buffer();
        try ( NBTOutputStream networkWriter = NbtUtils.createWriterLE( new ByteBufOutputStream( buffer ) ) ) {
            networkWriter.writeTag( itemNbt );
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
        return Base64.getMimeEncoder().encodeToString( Utils.array( buffer ) );
    }

    public static <T extends Item> T fromBase64( String json ) {
        byte[] decode = Base64.getMimeDecoder().decode( json );

        try ( NBTInputStream reader = NbtUtils.createReaderLE( new ByteBufInputStream( Unpooled.wrappedBuffer( decode ) ) ) ) {
            NbtMap compound = (NbtMap) reader.readTag();
            Identifier identifier = Identifier.fromString( compound.getString( "Name" ) );
            int amount = compound.getInt( "Count" );
            int meta = compound.getInt( "Meta" );
            boolean unbreakable = compound.getBoolean( "Unbreakable" );
            NbtMap blockStates = compound.getCompound( "BlockState" );
            NbtMap itemTag = compound.getCompound( "tag" );

            int blockRuntimeId = 0;
            for ( NbtMap blockNbt : BlockPalette.searchBlocks( nbtMap -> nbtMap.getString( "name" ).equalsIgnoreCase( identifier.getFullName() ) ) ) {
                if ( blockNbt.getCompound( "states" ).equals( blockStates ) ) {
                    blockRuntimeId = BlockPalette.getRuntimeId( blockNbt );
                }
            }
            Item item = Item.create( identifier ).setAmount( amount ).setMeta( meta ).setUnbreakable( unbreakable ).setBlockRuntimeId( blockRuntimeId );
            if ( !itemTag.isEmpty() ) {
                item.setNbt( itemTag );
            }
            return (T) item;
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }

    public ItemType getType() {
        return this.itemType;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public int getRuntimeId() {
        return this.runtimeId;
    }

    public Item setRuntimeId( int runtimeId ) {
        this.runtimeId = runtimeId;
        return this;
    }

    public int getBlockRuntimeId() {
        return this.blockRuntimeId;
    }

    public Item setBlockRuntimeId( int blockRuntimeId ) {
        this.blockRuntimeId = blockRuntimeId;
        return this;
    }

    public int getStackNetworkId() {
        return this.stackNetworkId;
    }

    public Item setStackNetworkId( int stackNetworkId ) {
        this.stackNetworkId = stackNetworkId;
        return this;
    }

    public int getAmount() {
        return this.amount;
    }

    public Item setAmount( int amount ) {
        if ( amount > this.getMaxStackSize() ) {
            this.amount = this.getMaxStackSize();
            return this;
        }
        this.amount = Math.max( amount, 0 );
        return this;
    }

    public int getMeta() {
        return this.meta;
    }

    public Item setMeta( int meta ) {
        this.meta = meta;
        return this;
    }

    public int getDurability() {
        return this.durability;
    }

    public Item setDurability( int durability ) {
        this.durability = durability;
        return this;
    }

    public String getDisplayname() {
        return this.displayname;
    }

    public Item setDisplayname( String displayname ) {
        this.displayname = displayname;
        return this;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public Item setLore( List<String> lore ) {
        this.lore = lore;
        return this;
    }

    public NbtMap getNbt() {
        return this.nbt;
    }

    public Item setNbt( NbtMap nbt ) {
        this.nbt = nbt;
        if ( this.nbt != null ) {
            this.fromNbt( this.nbt );
        }
        return this;
    }

    public List<String> getCanPlace() {
        return this.canPlace;
    }

    public Item setCanPlace( List<String> canPlace ) {
        this.canPlace = canPlace;
        return this;
    }

    public List<String> getCanBreak() {
        return this.canBreak;
    }

    public Item setCanBreak( List<String> canBreak ) {
        this.canBreak = canBreak;
        return this;
    }

    public int getMaxStackSize() {
        return this.itemProperties.getMaxStackSize();
    }

    public Item decreaseAmount() {
        return this.setAmount( this.getAmount() - 1 );
    }

    public Item addEnchantment( EnchantmentType enchantmentType, int level ) {
        Enchantment enchantment = Enchantment.create( enchantmentType );
        this.enchantments.put( enchantmentType, enchantment.setLevel( (short) ( Math.min( level, enchantment.getMaxLevel() ) ) ) );
        return this;
    }

    public Enchantment getEnchantment( EnchantmentType enchantmentType ) {
        return this.enchantments.get( enchantmentType );
    }

    public boolean hasEnchantment( EnchantmentType enchantmentType ) {
        return this.enchantments.containsKey( enchantmentType );
    }

    public Collection<Enchantment> getEntchantments() {
        return this.enchantments.values();
    }

    public boolean isEmptyEnchanted() {
        return emptyEnchanted;
    }

    public Item setEmptyEnchanted( boolean emptyEnchanted ) {
        this.emptyEnchanted = emptyEnchanted;
        return this;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }

    public Item setUnbreakable( boolean unbreakable ) {
        this.unbreakable = unbreakable;
        return this;
    }

    public Item setItemLockType( ItemLockType locktype ) {
        this.itemLockType = locktype;
        return this;
    }

    private Item setItemLockType( int locktype ) {
        this.itemLockType = ItemLockType.values()[locktype];
        return this;
    }

    public ItemLockType getItemLockType() {
        return this.itemLockType;
    }

    public Block toBlock() {
        if ( this.blockRuntimeId == 0 ) {
            return Block.create( BlockType.AIR ).clone();
        }
        return BlockPalette.getBlockByNBT( BlockPalette.getBlockNbt( this.blockRuntimeId ) ).clone();
    }

    public NbtMap toNbt() {
        NbtMapBuilder nbtBuilder = this.nbt == null ? NbtMap.builder() : this.nbt.toBuilder();

        if ( this.durability > 0 ) {
            nbtBuilder.putInt( "Damage", this.durability );
        }

        NbtMapBuilder displayBuilder = NbtMap.builder();
        if ( !this.displayname.isEmpty() ) {
            displayBuilder.put( "Name", this.displayname );
        }
        if ( !this.lore.isEmpty() ) {
            displayBuilder.putList( "Lore", NbtType.STRING, this.lore );
        }
        if ( !displayBuilder.isEmpty() ) {
            nbtBuilder.putCompound( "display", displayBuilder.build() );
        }
        if ( !this.enchantments.isEmpty() ) {
            List<NbtMap> enchantmentNBT = new ArrayList<>();
            for ( Enchantment enchantment : this.enchantments.values() ) {
                enchantmentNBT.add( NbtMap.builder()
                        .putShort( "id", enchantment.getId() )
                        .putShort( "lvl", enchantment.getLevel() )
                        .build()
                );
            }
            nbtBuilder.putList( "ench", NbtType.COMPOUND, enchantmentNBT );
        } else {
            if ( this.emptyEnchanted ) {
                nbtBuilder.putList( "ench", NbtType.COMPOUND, Collections.emptyList() );
            }
        }
        if ( !itemLockType.equals( ItemLockType.NONE ) ) {
            nbtBuilder.putByte( "minecraft:item_lock", (byte) itemLockType.ordinal() );
        }
        return nbtBuilder.isEmpty() ? null : nbtBuilder.build();
    }

    protected void fromNbt( NbtMap nbtMap ) {
        nbtMap.listenForByte( "Count", this::setAmount );
        nbtMap.listenForInt( "Damage", this::setDurability );
        nbtMap.listenForCompound( "display", displayTag -> {
            displayTag.listenForString( "Name", this::setDisplayname );
            displayTag.listenForList( "Lore", NbtType.STRING, this.lore::addAll );
        } );
        nbtMap.listenForList( "ench", NbtType.COMPOUND, compound -> {
            for ( NbtMap map : compound ) {
                short id = map.getShort( "id" );
                short level = map.getShort( "lvl" );
                this.addEnchantment( EnchantmentRegistry.getEnchantmentType( id ), level );
            }
        } );
        if ( nbtMap.containsKey( "minecraft:item_lock", NbtType.BYTE ) ) {
            nbtMap.listenForByte( "minecraft:item_lock", this::setItemLockType );
        }
    }

    public ItemData toItemData() {
        return ItemData.builder()
                .netId( this.stackNetworkId )
                .usingNetId( this.stackNetworkId > 0 )
                .definition( new SimpleItemDefinition( this.identifier.getFullName(), this.runtimeId, false ) )
                .blockDefinition( new RuntimeBlockDefination( this.blockRuntimeId ) )
                .damage( this.meta )
                .count( this.amount )
                .tag( this.toNbt() )
                .canPlace( this.canPlace.toArray( new String[0] ) )
                .canBreak( this.canBreak.toArray( new String[0] ) )
                .build();
    }

    public void updateItem( Player player, int amount ) {
        this.updateItem( player, amount, true );
    }

    public void updateItem( Player player, int amount, boolean playSound ) {
        if ( player.getGameMode().equals( GameMode.SURVIVAL ) ) {
            if ( this.calculateDurability( amount ) ) {
                player.getInventory().setItemInHand( Item.create( ItemType.AIR ) );
                if ( playSound ) {
                    player.playSound( Sound.RANDOM_BREAK, 1, 1 );
                }
            } else {
                player.getInventory().setItemInHand( this );
            }
        }
    }

    public boolean calculateDurability( int durability ) {
        if ( this instanceof Durability ) {
            if ( this.unbreakable ) {
                return false;
            }
            Enchantment enchantment = this.getEnchantment( EnchantmentType.UNBREAKING );
            if ( enchantment != null ) {
                int chance = new Random().nextInt( 100 );
                int percent = ( 100 / ( enchantment.getLevel() + 1 ) );
                if ( !( enchantment.getLevel() > 0 && percent <= chance ) ) {
                    this.durability += durability;
                }
            } else {
                this.durability += durability;
            }

            return this.durabilityAndCheckAmount( this.durability );
        }
        return false;
    }

    private boolean durabilityAndCheckAmount( int durability ) {
        if ( this instanceof Durability value ) {
            int maxDurability = value.getMaxDurability();
            int intdurability = durability;
            if ( intdurability >= maxDurability ) {
                if ( --this.amount <= 0 ) {
                    return true;
                }
                intdurability = 0;
            }
            this.durability = intdurability;
        }
        return false;
    }

    public boolean isSword() {
        return this.itemType.equals( ItemType.WOODEN_SWORD ) ||
                this.itemType.equals( ItemType.STONE_SWORD ) ||
                this.itemType.equals( ItemType.GOLDEN_SWORD ) ||
                this.itemType.equals( ItemType.IRON_SWORD ) ||
                this.itemType.equals( ItemType.DIAMOND_SWORD ) ||
                this.itemType.equals( ItemType.NETHERITE_SWORD );
    }

    public boolean isAxe() {
        return this.itemType.equals( ItemType.WOODEN_AXE ) ||
                this.itemType.equals( ItemType.STONE_AXE ) ||
                this.itemType.equals( ItemType.GOLDEN_AXE ) ||
                this.itemType.equals( ItemType.IRON_AXE ) ||
                this.itemType.equals( ItemType.DIAMOND_AXE ) ||
                this.itemType.equals( ItemType.NETHERITE_AXE );
    }

    public boolean isPickaxe() {
        return this.itemType.equals( ItemType.WOODEN_PICKAXE ) ||
                this.itemType.equals( ItemType.STONE_PICKAXE ) ||
                this.itemType.equals( ItemType.GOLDEN_PICKAXE ) ||
                this.itemType.equals( ItemType.IRON_PICKAXE ) ||
                this.itemType.equals( ItemType.DIAMOND_PICKAXE ) ||
                this.itemType.equals( ItemType.NETHERITE_PICKAXE );
    }

    public boolean isShovel() {
        return this.itemType.equals( ItemType.WOODEN_SHOVEL ) ||
                this.itemType.equals( ItemType.STONE_SHOVEL ) ||
                this.itemType.equals( ItemType.GOLDEN_SHOVEL ) ||
                this.itemType.equals( ItemType.IRON_SHOVEL ) ||
                this.itemType.equals( ItemType.DIAMOND_SHOVEL ) ||
                this.itemType.equals( ItemType.NETHERITE_SHOVEL );
    }

    public boolean isHoe() {
        return this.itemType.equals( ItemType.WOODEN_HOE ) ||
                this.itemType.equals( ItemType.STONE_HOE ) ||
                this.itemType.equals( ItemType.GOLDEN_HOE ) ||
                this.itemType.equals( ItemType.IRON_HOE ) ||
                this.itemType.equals( ItemType.DIAMOND_HOE ) ||
                this.itemType.equals( ItemType.NETHERITE_HOE );
    }

    public boolean interact( Player player, BlockFace blockFace, Vector clickedPosition, Block clickedBlock ) {
        return false;
    }

    public boolean useOnBlock( Player player, Block block, Location placeLocation ) {
        return false;
    }

    public boolean useInAir( Player player, Vector clickVector ) {
        return false;
    }

    public boolean onUse( Player player ) {
        return false;
    }

    public void addToHand( Player player ) {
    }

    public void removeFromHand( Player player ) {
    }

    public ToolType getToolType() {
        return ToolType.NONE;
    }

    public TierType getTierType() {
        return TierType.NONE;
    }

    @Override
    public Item clone() {
        try {
            Item clone = (Item) super.clone();
            clone.itemType = this.itemType;
            clone.identifier = this.identifier;
            clone.runtimeId = this.runtimeId;
            clone.blockRuntimeId = this.blockRuntimeId;
            clone.stackNetworkId = this.stackNetworkId;
            clone.amount = this.amount;
            clone.meta = this.meta;
            clone.durability = this.durability;
            clone.displayname = this.displayname;
            clone.lore = this.lore;
            clone.nbt = this.nbt;
            clone.canPlace = this.canPlace;
            clone.canBreak = this.canBreak;
            clone.enchantments = this.enchantments;
            clone.itemProperties = this.itemProperties;
            return clone;
        } catch ( CloneNotSupportedException e ) {
            throw new AssertionError();
        }
    }

    public boolean equalsExact( Item item ) {
        return this.equals( item ) && this.amount == item.amount;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj instanceof Item item ) {
            return item.getRuntimeId() == this.runtimeId && item.meta == this.meta && ( item.blockRuntimeId == this.blockRuntimeId || item.blockRuntimeId == 0 || this.blockRuntimeId == 0 );
        }
        return false;
    }
}
