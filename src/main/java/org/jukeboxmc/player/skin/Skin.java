package org.jukeboxmc.player.skin;

import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.skin.*;
import org.jukeboxmc.util.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author LucGamesYT
 * @version 1.0
 */
@ToString
public class Skin {

    public static final int SINGLE_SKIN_SIZE = 8192;
    public static final int DOUBLE_SKIN_SIZE = 16384;
    public static final int SKIN_128_64_SIZE = 32768;
    public static final int SKIN_128_128_SIZE = 65536;

    private String skinId;
    private String resourcePatch = legacyGeometryName( "geometry.humanoid.custom" );
    private String geometryName = "";
    private String geometryData = "";
    private String animationData = "";
    private String capeId = "";
    private String fullSkinId = UUID.randomUUID().toString();
    private String skinColor = "#0";
    private String armSize = "wide";
    private String playFabId = "";

    private Image skinData = new Image( 0, 0, new byte[0] );
    private Image capeData = new Image( 0, 0, new byte[0] );

    private boolean isPremium;
    private boolean isPersona;
    private boolean isCapeOnClassic;
    private boolean isTrusted = false;
    private boolean isPrimaryUser = true;

    private List<SkinAnimation> skinAnimations = new ArrayList<>();
    private List<PersonaPiece> personaPieces = new ArrayList<>();
    private List<PersonaPieceTint> personaPieceTints = new ArrayList<>();

    public String getSkinId() {
        return this.skinId;
    }

    public void setSkinId( String skinId ) {
        this.skinId = skinId;
    }

    public String getResourcePatch() {
        return this.resourcePatch == null ? "" : this.resourcePatch;
    }

    public void setResourcePatch( String resourcePatch ) {
        this.resourcePatch = resourcePatch;
    }

    public String getGeometryName() {
        return this.geometryName;
    }

    public void setGeometryName( String geometryName ) {
        this.geometryName = geometryName;
    }

    public String getGeometryData() {
        return this.geometryData == null ? "" : this.geometryData;
    }

    public void setGeometryData( String geometryData ) {
        this.geometryData = geometryData;
    }

    public String getAnimationData() {
        return this.animationData == null ? "" : this.animationData;
    }

    public void setAnimationData( String animationData ) {
        this.animationData = animationData;
    }

    public String getCapeId() {
        return this.capeId == null ? "" : this.capeId;
    }

    public void setCapeId( String capeId ) {
        this.capeId = capeId;
    }

    public String getSkinColor() {
        return this.skinColor;
    }

    public void setSkinColor( String skinColor ) {
        this.skinColor = skinColor;
    }

    public String getArmSize() {
        return this.armSize;
    }

    public void setArmSize( String armSize ) {
        this.armSize = armSize;
    }

    public String getPlayFabId() {
        return this.playFabId;
    }

    public void setPlayFabId( String playFabId ) {
        this.playFabId = playFabId;
    }

    public String getFullSkinId() {
        return this.fullSkinId;
    }

    public void setFullSkinId( String fullSkinId ) {
        this.fullSkinId = fullSkinId;
    }

    public Image getSkinData() {
        return this.skinData;
    }

    public void setSkinData( Image skinData ) {
        this.skinData = skinData;
    }

    public void setSkinData( BufferedImage bufferedImage ) {
        this.skinData = parseBufferedImage( bufferedImage );
    }

    public Image getCapeData() {
        return this.capeData != null ? this.capeData : new Image( 0, 0, new byte[0] );
    }

    public void setCapeData( Image capeData ) {
        this.capeData = capeData;
    }

    public boolean isPremium() {
        return this.isPremium;
    }

    public void setPremium( boolean premium ) {
        this.isPremium = premium;
    }

    public boolean isPersona() {
        return this.isPersona;
    }

    public void setPersona( boolean persona ) {
        this.isPersona = persona;
    }

    public boolean isCapeOnClassic() {
        return this.isCapeOnClassic;
    }

    public void setCapeOnClassic( boolean capeOnClassic ) {
        this.isCapeOnClassic = capeOnClassic;
    }

    public boolean isTrusted() {
        return this.isTrusted;
    }

    public void setTrusted( boolean trusted ) {
        this.isTrusted = trusted;
    }

    public boolean isPrimaryUser() {
        return this.isPrimaryUser;
    }

    public void setPrimaryUser( boolean primaryUser ) {
        this.isPrimaryUser = primaryUser;
    }

    public List<SkinAnimation> getSkinAnimations() {
        return this.skinAnimations;
    }

    public void setSkinAnimations( List<SkinAnimation> skinAnimations ) {
        this.skinAnimations = skinAnimations;
    }

    public List<PersonaPiece> getPersonaPieces() {
        return this.personaPieces;
    }

    public void setPersonaPieces( List<PersonaPiece> personaPieces ) {
        this.personaPieces = personaPieces;
    }

    public List<PersonaPieceTint> getPersonaPieceTints() {
        return this.personaPieceTints;
    }

    public void setPersonaPieceTints( List<PersonaPieceTint> personaPieceTints ) {
        this.personaPieceTints = personaPieceTints;
    }

    public static Skin fromNetwork( SerializedSkin serializedSkin ) {
        Skin skin = new Skin();
        skin.setSkinId( serializedSkin.getSkinId() );
        skin.setPlayFabId( serializedSkin.getPlayFabId() );
        skin.setGeometryName( serializedSkin.getGeometryName() );
        skin.setResourcePatch( serializedSkin.getSkinResourcePatch() );
        skin.setSkinData( new Image( serializedSkin.getSkinData().getWidth(), serializedSkin.getSkinData().getHeight(), serializedSkin.getSkinData().getImage() ) );
        List<SkinAnimation> skinAnimations = new ArrayList<>();
        for ( AnimationData animation : serializedSkin.getAnimations() ) {
            Image image = new Image( animation.getImage().getWidth(), animation.getImage().getHeight(), animation.getImage().getImage() );
            skinAnimations.add( new SkinAnimation( image, animation.getTextureType().ordinal(), animation.getFrames(), animation.getExpressionType().ordinal() ) );
        }
        skin.setSkinAnimations( skinAnimations );
        skin.setCapeData( new Image( serializedSkin.getCapeData().getWidth(), serializedSkin.getCapeData().getHeight(), serializedSkin.getCapeData().getImage() ) );
        skin.setGeometryData( serializedSkin.getGeometryData() );
        skin.setAnimationData( serializedSkin.getAnimationData() );
        skin.setPremium( serializedSkin.isPremium() );
        skin.setPersona( serializedSkin.isPersona() );
        skin.setCapeOnClassic( serializedSkin.isCapeOnClassic() );
        skin.setCapeId( serializedSkin.getCapeId() );
        skin.setFullSkinId( serializedSkin.getFullSkinId() );
        skin.setArmSize( serializedSkin.getArmSize() );
        skin.setSkinColor( serializedSkin.getSkinColor() );
        List<PersonaPiece> personaPieces = new ArrayList<>();
        for ( PersonaPieceData personaPiece : serializedSkin.getPersonaPieces() ) {
            personaPieces.add( new PersonaPiece( personaPiece.getId(), personaPiece.getType(), personaPiece.getPackId(), personaPiece.getProductId(), personaPiece.isDefault() ) );
        }
        skin.setPersonaPieces( personaPieces );
        List<PersonaPieceTint> pieceTints = new ArrayList<>();
        for ( PersonaPieceTintData tintColor : serializedSkin.getTintColors() ) {
            pieceTints.add( new PersonaPieceTint( tintColor.getType(), tintColor.getColors() ) );
        }
        skin.setPersonaPieceTints( pieceTints );
        return skin;
    }

    public SerializedSkin toNetwork() {
        List<AnimationData> animationDataList = new ArrayList<>();
        for ( SkinAnimation animation : this.skinAnimations ) {
            animationDataList.add( new AnimationData( ImageData.of( animation.getImage().getWidth(), animation.getImage().getHeight(), animation.getImage().getData() ), AnimatedTextureType.values()[animation.getType()], animation.getFrames() ) );
        }
        List<PersonaPieceData> personaPieceDataList = new ArrayList<>();
        for ( PersonaPiece piece : this.personaPieces ) {
            personaPieceDataList.add( new PersonaPieceData( piece.getPieceId(), piece.getPieceType(), piece.getPackId(), piece.isDefault(), piece.getProductId() ) );
        }
        List<PersonaPieceTintData> personaPieceTintList = new ArrayList<>();
        for ( PersonaPieceTint pieceTint : this.personaPieceTints ) {
            personaPieceTintList.add( new PersonaPieceTintData( pieceTint.getPieceType(), pieceTint.getColors() ) );
        }
        if ( this.skinId == null) {
            this.skinId = generateSkinId( "Custom" );
        }
        return SerializedSkin.builder()
                .skinId( this.skinId )
                .playFabId( this.playFabId )
                .geometryName( this.geometryName )
                .skinResourcePatch( this.resourcePatch )
                .skinData( ImageData.of( this.skinData.getWidth(), this.skinData.getHeight(), this.skinData.getData() ) )
                .animations( animationDataList )
                .capeData( ImageData.of( this.capeData.getWidth(), this.capeData.getHeight(), this.capeData.getData() ) )
                .geometryData( this.geometryData )
                .animationData( this.animationData )
                .premium( this.isPremium )
                .persona( this.isPersona )
                .capeOnClassic( this.isCapeOnClassic )
                .capeId( this.capeId )
                .fullSkinId( this.fullSkinId )
                .armSize( this.armSize )
                .skinColor( this.skinColor )
                .personaPieces( personaPieceDataList )
                .tintColors( personaPieceTintList )
                .build();
    }

    public static Skin fromInputStream( InputStream inputStream ) {
        Skin skin = new Skin();
        skin.setTrusted( true );
        BufferedImage skinData = null;
        try {
            skinData = ImageIO.read( inputStream );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if ( skinData != null ) {
            skin.setSkinData( skinData );
        }
        return skin;
    }

    public static Skin fromFile( File file ) {
        Skin skin = new Skin();
        skin.setTrusted( true );
        BufferedImage skinData = null;
        try {
            skinData = ImageIO.read( file );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if ( skinData != null ) {
            skin.setSkinData( skinData );
        }
        return skin;
    }

    public static String legacyGeometryName( String geometryName ) {
        return "{\"geometry\" : {\"default\" : \"" + geometryName + "\"}}";
    }

    public static Image parseBufferedImage( BufferedImage image ) {
        try (FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream()){
            for ( int y = 0; y < image.getHeight(); y++ ) {
                for ( int x = 0; x < image.getWidth(); x++ ) {
                    Color color = new Color( image.getRGB( x, y ), true );
                    outputStream.write( color.getRed() );
                    outputStream.write( color.getGreen() );
                    outputStream.write( color.getBlue() );
                    outputStream.write( color.getAlpha() );
                }
            }
            image.flush();
            return new Image( image.getWidth(), image.getHeight(), outputStream.array );
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }

    public String generateSkinId( String name ) {
        byte[] data = Utils.appendBytes(this.skinData.getData(), this.resourcePatch.getBytes( StandardCharsets.UTF_8));
        return UUID.nameUUIDFromBytes(data) + "." + name;
    }
}