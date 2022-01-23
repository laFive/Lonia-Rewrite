package me.five.lonia.nms.v1_8_R3;

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
import net.minecraft.server.v1_8_R3.*;

import java.lang.reflect.Field;

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
            Field idField = nmsPacket.getClass().getDeclaredField("a");
            idField.setAccessible(true);
            int id = idField.getInt(nmsPacket);
            SPacketKeepAlive loniaKeepAlivePacket = new SPacketKeepAlive(id);
            data.getPacketOutProcessor().processPacket(loniaKeepAlivePacket);
            return;

        }

        if (packet instanceof PacketPlayOutEntityVelocity) {

            PacketPlayOutEntityVelocity nmsPacket = (PacketPlayOutEntityVelocity) packet;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("a");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            Field velocityXField = nmsPacket.getClass().getDeclaredField("b");
            velocityXField.setAccessible(true);
            int velocityX = velocityXField.getInt(nmsPacket);
            Field velocityYField = nmsPacket.getClass().getDeclaredField("c");
            velocityYField.setAccessible(true);
            int velocityY = velocityYField.getInt(nmsPacket);
            Field velocityZField = nmsPacket.getClass().getDeclaredField("d");
            velocityZField.setAccessible(true);
            int velocityZ = velocityZField.getInt(nmsPacket);
            SPacketEntityVelocity loniaEntityVelocityPacket = new SPacketEntityVelocity(entityId, velocityX, velocityY, velocityZ);
            data.getPacketOutProcessor().processPacket(loniaEntityVelocityPacket);
            return;

        }

    }

    @Override
    public void channelRead(ChannelHandlerContext chc, Object packet) throws Exception {

        super.channelRead(chc, packet);

        if (packet instanceof PacketPlayInFlying) {

            PacketPlayInFlying nmsFlyingPacket = (PacketPlayInFlying) packet;

            if (nmsFlyingPacket.g() && nmsFlyingPacket.h()) {

                CPacketFlying loniaFlyingPacket = new CPacketFlying(nmsFlyingPacket.a(), nmsFlyingPacket.b(),
                        nmsFlyingPacket.c(), nmsFlyingPacket.d(), nmsFlyingPacket.e(), nmsFlyingPacket.f());

                data.getPacketInProcessor().processPacket(loniaFlyingPacket);
                return;

            }

            if (nmsFlyingPacket.g()) {

                CPacketFlying loniaFlyingPacket = new CPacketFlying(nmsFlyingPacket.a(), nmsFlyingPacket.b(),
                        nmsFlyingPacket.c(), nmsFlyingPacket.f());

                data.getPacketInProcessor().processPacket(loniaFlyingPacket);
                return;

            }

            if (nmsFlyingPacket.h()) {

                CPacketFlying loniaFlyingPacket = new CPacketFlying(nmsFlyingPacket.d(), nmsFlyingPacket.e(), nmsFlyingPacket.f());

                data.getPacketInProcessor().processPacket(loniaFlyingPacket);
                return;

            }

            CPacketFlying loniaFlyingPacket = new CPacketFlying(nmsFlyingPacket.f());
            data.getPacketInProcessor().processPacket(loniaFlyingPacket);
            return;

        }

        if (packet instanceof PacketPlayInKeepAlive) {

            PacketPlayInKeepAlive nmsPacket = (PacketPlayInKeepAlive) packet;
            CPacketKeepAlive loniaKeepAlivePacket = new CPacketKeepAlive(nmsPacket.a());
            data.getPacketInProcessor().processPacket(loniaKeepAlivePacket);
            return;

        }

        if (packet instanceof PacketPlayInTransaction) {

            PacketPlayInTransaction nmsPacket = (PacketPlayInTransaction) packet;
            Field acceptedField = nmsPacket.getClass().getDeclaredField("c");
            acceptedField.setAccessible(true);
            boolean accepted = acceptedField.getBoolean(nmsPacket);
            CPacketTransaction loniaTransactionPacket = new CPacketTransaction(nmsPacket.a(), nmsPacket.b(), accepted);
            data.getPacketInProcessor().processPacket(loniaTransactionPacket);
            return;

        }

        if (packet instanceof PacketPlayInEntityAction) {

            PacketPlayInEntityAction nmsPacket = (PacketPlayInEntityAction) packet;
            int enumId = PacketUtil.getEnumValue(nmsPacket.b());
            CPacketEntityAction loniaEntityActionPacket = new CPacketEntityAction(enumId);
            data.getPacketInProcessor().processPacket(loniaEntityActionPacket);
            return;

        }

    }

}
