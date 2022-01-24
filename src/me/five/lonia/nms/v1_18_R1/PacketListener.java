package me.five.lonia.nms.v1_18_R1;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import me.five.lonia.data.PlayerData;
import me.five.lonia.packet.client.*;
import me.five.lonia.packet.server.*;
import me.five.lonia.util.GameMode;
import me.five.lonia.util.PacketUtil;
import net.minecraft.network.protocol.game.*;
import net.minecraft.world.effect.MobEffectList;

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

        if (packet instanceof PacketPlayOutEntityEffect) {

            PacketPlayOutEntityEffect nmsPacket = (PacketPlayOutEntityEffect) packet;
            SPacketEntityEffect loniaEffectPacket = new SPacketEntityEffect(nmsPacket.c(), nmsPacket.d(), nmsPacket.e(), nmsPacket.f());
            data.getPacketOutProcessor().processPacket(loniaEffectPacket);
            return;

        }

        if (packet instanceof PacketPlayOutRemoveEntityEffect) {

            PacketPlayOutRemoveEntityEffect nmsPacket = (PacketPlayOutRemoveEntityEffect) packet;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("a");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            byte effectId = ((Integer)MobEffectList.a(nmsPacket.b())).byteValue();
            SPacketRemoveEffect loniaRemoveEffectPacket = new SPacketRemoveEffect(entityId, effectId);
            data.getPacketOutProcessor().processPacket(loniaRemoveEffectPacket);
            return;

        }

        if (packet instanceof PacketPlayOutAbilities) {

            PacketPlayOutAbilities nmsPacket = (PacketPlayOutAbilities) packet;
            SPacketAbilities loniaAbilitiesPacket = new SPacketAbilities(nmsPacket.c(), nmsPacket.d(), nmsPacket.b(), nmsPacket.e(), nmsPacket.f(), nmsPacket.g());
            data.getPacketOutProcessor().processPacket(loniaAbilitiesPacket);
            return;

        }

        if (packet instanceof PacketPlayOutPosition) {

            PacketPlayOutPosition nmsPacket = (PacketPlayOutPosition) packet;
            SPacketPosition loniaPositionPacket = new SPacketPosition(nmsPacket.b(), nmsPacket.c(), nmsPacket.d(), nmsPacket.e(), nmsPacket.f(), nmsPacket.h());
            data.getPacketOutProcessor().processPacket(loniaPositionPacket);
            return;

        }

        if (packet instanceof PacketPlayOutExplosion) {

            PacketPlayOutExplosion nmsPacket = (PacketPlayOutExplosion) packet;
            SPacketExplosion loniaExplosionPacket = new SPacketExplosion(nmsPacket.e(), nmsPacket.f(), nmsPacket.g(), nmsPacket.h(), nmsPacket.b(), nmsPacket.c(), nmsPacket.d());
            data.getPacketOutProcessor().processPacket(loniaExplosionPacket);
            return;

        }

        if (packet instanceof PacketPlayOutMount) {

            PacketPlayOutMount nmsPacket = (PacketPlayOutMount) packet;
            SPacketMount loniaMountPacket = new SPacketMount(nmsPacket.c(), nmsPacket.b());
            data.getPacketOutProcessor().processPacket(loniaMountPacket);
            return;

        }

        if (packet instanceof PacketPlayOutRespawn) {

            PacketPlayOutRespawn nmsPacket = (PacketPlayOutRespawn) packet;
            SPacketRespawn loniaRespawnPacket = new SPacketRespawn(GameMode.getFromId(nmsPacket.e().a()));
            data.getPacketOutProcessor().processPacket(loniaRespawnPacket);
            return;

        }

        if (packet instanceof PacketPlayOutNamedEntitySpawn) {

            PacketPlayOutNamedEntitySpawn nmsPacket = (PacketPlayOutNamedEntitySpawn) packet;
            SPacketSpawnEntity loniaSpawnEntityPacket = new SPacketSpawnEntity(nmsPacket.b(), nmsPacket.c(), nmsPacket.d(), nmsPacket.e(), nmsPacket.f(), (nmsPacket.g() * 360.0F) / 256.0F, (nmsPacket.h() * 360.0F) / 256.0F, true);
            data.getPacketOutProcessor().processPacket(loniaSpawnEntityPacket);
            return;

        }

        if (packet instanceof PacketPlayOutSpawnEntityLiving) {

            PacketPlayOutSpawnEntityLiving nmsPacket = (PacketPlayOutSpawnEntityLiving) packet;
            SPacketSpawnEntity loniaSpawnEntityPacket = new SPacketSpawnEntity(nmsPacket.b(), nmsPacket.c(), nmsPacket.e(), nmsPacket.f(), nmsPacket.g(), (nmsPacket.k() * 360.0F) / 256.0F, (nmsPacket.l() * 360.0F) / 256.0F, false);
            data.getPacketOutProcessor().processPacket(loniaSpawnEntityPacket);
            return;

        }

        if (packet instanceof PacketPlayOutSpawnEntity) {

            PacketPlayOutSpawnEntity nmsPacket = (PacketPlayOutSpawnEntity) packet;
            SPacketSpawnEntity loniaSpawnEntityPacket = new SPacketSpawnEntity(nmsPacket.b(), nmsPacket.c(), nmsPacket.d(), nmsPacket.e(), nmsPacket.f(), (nmsPacket.j() * 360.0F) / 256.0F, (nmsPacket.k() * 360.0F) / 256.0F, false);
            data.getPacketOutProcessor().processPacket(loniaSpawnEntityPacket);
            return;

        }

        if (packet instanceof PacketPlayOutEntityTeleport) {

            PacketPlayOutEntityTeleport nmsPacket = (PacketPlayOutEntityTeleport) packet;
            SPacketEntityTeleport loniaEntityTeleportPacket = new SPacketEntityTeleport(nmsPacket.b(), nmsPacket.c(), nmsPacket.d(), nmsPacket.e(), (nmsPacket.f() * 360.0F) / 256.0F, (nmsPacket.g() * 360.0F) / 256.0F, nmsPacket.h());
            data.getPacketOutProcessor().processPacket(loniaEntityTeleportPacket);
            return;

        }

        if (packet instanceof PacketPlayOutEntity) {

            PacketPlayOutEntity nmsPacket = (PacketPlayOutEntity) packet;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("a");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            double distanceX = nmsPacket.b() / 4096.0D;
            double distanceY = nmsPacket.c() / 4096.0D;
            double distanceZ = nmsPacket.d() / 4096.0D;
            float yaw = (nmsPacket.e() * 360.0F) / 256.0F;
            float pitch = (nmsPacket.f() * 360.0F) / 256.0F;
            boolean onGround = nmsPacket.g();
            if (nmsPacket.h() && nmsPacket.i()) {
                SPacketEntityMove loniaEntityPacket = new SPacketEntityMove(entityId, distanceX, distanceY, distanceZ, yaw, pitch, onGround);
                data.getPacketOutProcessor().processPacket(loniaEntityPacket);
                return;
            }
            if (nmsPacket.h()) {
                SPacketEntityMove loniaEntityPacket = new SPacketEntityMove(entityId, yaw, pitch, onGround);
                data.getPacketOutProcessor().processPacket(loniaEntityPacket);
                return;
            }
            if (nmsPacket.i()) {
                SPacketEntityMove loniaEntityPacket = new SPacketEntityMove(entityId, distanceX, distanceY, distanceZ, onGround);
                data.getPacketOutProcessor().processPacket(loniaEntityPacket);
                return;
            }

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

        if (packet instanceof PacketPlayInAbilities) {

            PacketPlayInAbilities nmsPacket = (PacketPlayInAbilities) packet;
            CPacketAbilities loniaAbilitiesPacket = new CPacketAbilities(nmsPacket.b());
            data.getPacketInProcessor().processPacket(loniaAbilitiesPacket);
            return;

        }

        if (packet instanceof PacketPlayInArmAnimation) {

            data.getPacketInProcessor().processPacket(new SPacketArmAnimation());
            return;

        }

    }

}
