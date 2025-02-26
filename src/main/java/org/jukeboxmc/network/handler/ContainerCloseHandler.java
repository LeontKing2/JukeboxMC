package org.jukeboxmc.network.handler;

import org.cloudburstmc.protocol.bedrock.packet.ContainerClosePacket;
import org.jukeboxmc.Server;
import org.jukeboxmc.player.Player;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class ContainerCloseHandler implements PacketHandler<ContainerClosePacket>{

    @Override
    public void handle( ContainerClosePacket packet, Server server, Player player ) {
        player.setClosingWindowId( packet.getId() );
        player.closeInventory( packet.getId(), packet.isServerInitiated() );
        player.setClosingWindowId( Integer.MIN_VALUE );
    }
}
