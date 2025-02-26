package org.jukeboxmc.block.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jukeboxmc.item.TierType;
import org.jukeboxmc.item.ToolType;

/**
 * @author LucGamesYT
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class BlockProperties {

    private final double hardness;
    private final boolean solid;
    private final boolean transparent;
    private final ToolType toolType;
    private final TierType tierType;
    private final boolean canBreakWithHand;
    private final boolean canPassThrough;
    private final boolean randomTicking;
}
