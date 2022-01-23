package me.five.lonia.nms.v1_18_R1;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import me.five.lonia.data.PlayerData;
import me.five.lonia.packet.client.CPacketEntityAction;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.packet.client.CPacketKeepAlive;
import me.five.lonia.packet.client.CPacketTransaction;
import me.five.lonia.packet.server.SPacketEntityVelocity;
import me.five.lonia.packet.server.SPacketKeepAlive;
import me.five.lonia.util.PacketUtil;
import net.minecraft.network.protocol.game.*;

public class PacketListener extends ChannelDuplexHandler {

    private PlayerData data;

    public PacketListener(PlayerData playerData) {
        this.data = playerData;
    }

    @Override
    public void write(ChannelHandlerContext chc, Object packet, ChannelPromise promise) throws Exception {

        super.write(chc, packet, promise);

        if (packet instanceof PacketPlayOutKeepAlive) {

            PacketPlayOutKeepAlive nmsPacket = (PacketPlayOutKeepAlive) packet;
            SPacketKeepAlive loniaKeepAlivePacket = new SPacketKeepAlive(nmsPacket.b());
            data.getPacketOutProcessor().processPacket(loniaKeepAlivePacket);
            return;

        }

        if (packet instanceof PacketPlayOutEntityVelocity) {

            PacketPlayOutEntityVelocity nmsPacket = (PacketPlayOutEntityVelocity) packet;
            SPacketEntityVelocity loniaEntityVelocityPacket = new SPacketEntityVelocity(nmsPacket.b(), nmsPacket.c(), nmsPacket.d(), nmsPacket.e());
            data.getPacketOutProcessor().processPacket(loniaEntityVelocityPacket);
            return;

        }

    }

    @Override
    public void channelRead(ChannelHandlerContext chc, Object packet) throws Exception {

        super.channelRead(chc, packet);

        if (packet instanceof PacketPlayInFlying) {

            PacketPlayInFlying nmsFlyingPacket = (PacketPlayInFlying) packet;

            if (nmsFlyingPacket.g && nmsFlyingPacket.h) {

                CPacketFlying loniaFlyingPacket = new CPacketFlying(nmsFlyingPacket.a, nmsFlyingPacket.b,
                        nmsFlyingPacket.c, nmsFlyingPacket.d, nmsFlyingPacket.e, nmsFlyingPacket.b());

                data.getPacketInProcessor().processPacket(loniaFlyingPacket);
                return;

            }

            if (nmsFlyingPacket.g) {

                CPacketFlying loniaFlyingPacket = new CPacketFlying(nmsFlyingPacket.a, nmsFlyingPacket.b,
                        nmsFlyingPacket.c, nmsFlyingPacket.b());

                data.getPacketInProcessor().processPacket(loniaFlyingPacket);
                return;

            }

            if (nmsFlyingPacket.h) {

                CPacketFlying loniaFlyingPacket = new CPacketFlying(nmsFlyingPacket.d, nmsFlyingPacket.e, nmsFlyingPacket.b());

                data.getPacketInProcessor().processPacket(loniaFlyingPacket);
                return;

            }

            CPacketFlying loniaFlyingPacket = new CPacketFlying(nmsFlyingPacket.b());
            data.getPacketInProcessor().processPacket(loniaFlyingPacket);
            return;

        }

        if (packet instanceof PacketPlayInKeepAlive) {

            PacketPlayInKeepAlive nmsPacket = (PacketPlayInKeepAlive) packet;
            CPacketKeepAlive loniaKeepAlivePacket = new CPacketKeepAlive(nmsPacket.b());
            data.getPacketInProcessor().processPacket(loniaKeepAlivePacket);
            return;

        }

        if (packet instanceof ServerboundPongPacket) {

            ServerboundPongPacket nmsPacket = (ServerboundPongPacket) packet;
            if (Math.abs(nmsPacket.b()) > Short.MAX_VALUE) return;
            CPacketTransaction loniaTransactionPacket = new CPacketTransaction(0, ((Integer)nmsPacket.b()).shortValue(), true);
            data.getPacketInProcessor().processPacket(loniaTransactionPacket);
            return;

        }

        if (packet instanceof PacketPlayInEntityAction) {

            PacketPlayInEntityAction nmsPacket = (PacketPlayInEntityAction) packet;
            int enumId = PacketUtil.getEnumValue(nmsPacket.c());
            CPacketEntityAction loniaEntityActionPacket = new CPacketEntityAction(enumId);
            data.getPacketInProcessor().processPacket(loniaEntityActionPacket);
            return;

        }

    }

}
