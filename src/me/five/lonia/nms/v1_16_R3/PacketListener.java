/*
package me.five.lonia.nms.v1_16_R3;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import me.five.lonia.data.PlayerData;
import me.five.lonia.packet.client.*;
import me.five.lonia.packet.server.*;
import me.five.lonia.util.*;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.entity.Mob;

import java.lang.reflect.Field;
import java.util.UUID;

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
            long id = idField.getLong(nmsPacket);
            SPacketKeepAlive loniaKeepAlivePacket = new SPacketKeepAlive(id);
            data.getPacketOutProcessor().processPacket(loniaKeepAlivePacket);
            return;

        }

        if (packet instanceof PacketPlayOutEntityVelocity) {

            PacketPlayOutEntityVelocity nmsPacket = (PacketPlayOutEntityVelocity) packet;
            int entityId = (int) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "a");
            int velX = (int) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "b");
            int velY = (int) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "c");
            int velZ = (int) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "d");
            SPacketEntityVelocity loniaEntityVelocityPacket = new SPacketEntityVelocity(entityId, velX, velY, velZ);
            data.getPacketOutProcessor().processPacket(loniaEntityVelocityPacket);
            return;

        }

        if (packet instanceof PacketPlayOutEntityEffect) {

            PacketPlayOutEntityEffect nmsPacket = (PacketPlayOutEntityEffect) packet;
            int i = (int) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "a");
            int i1 = (int) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "d");
            byte b = (byte) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "b");
            byte b1 = (byte) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "c");
            SPacketEntityEffect loniaEffectPacket = new SPacketEntityEffect(i, b, b1, i1);
            data.getPacketOutProcessor().processPacket(loniaEffectPacket);
            return;

        }

        if (packet instanceof PacketPlayOutRemoveEntityEffect) {

            PacketPlayOutRemoveEntityEffect nmsPacket = (PacketPlayOutRemoveEntityEffect) packet;
            int entityId = (int) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "a");
            MobEffectList mel = (MobEffectList) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "b");
            byte effectId = ((Integer)MobEffectList.getId(mel)).byteValue();
            SPacketRemoveEffect loniaRemoveEffectPacket = new SPacketRemoveEffect(entityId, effectId);
            data.getPacketOutProcessor().processPacket(loniaRemoveEffectPacket);
            return;

        }

        if (packet instanceof PacketPlayOutAbilities) {

            PacketPlayOutAbilities nmsPacket = (PacketPlayOutAbilities) packet;
            boolean isFlying = (Boolean) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "b");
            boolean isInvulnerable = (Boolean) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "a");
            boolean allowFlying = (Boolean) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "c");
            boolean instaBuild = (Boolean) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "d");
            float walkSpeed = (Float) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "f");
            float flySpeed = (Float) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "e");
            SPacketAbilities loniaAbilitiesPacket = new SPacketAbilities(isFlying, allowFlying, isInvulnerable, instaBuild, flySpeed, walkSpeed);
            data.getPacketOutProcessor().processPacket(loniaAbilitiesPacket);
            return;

        }

        if (packet instanceof PacketPlayOutPosition) {

            PacketPlayOutPosition nmsPacket = (PacketPlayOutPosition) packet;
            double d = (Double) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "a");
            double d1 = (Double) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "b");
            double d2 = (Double) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "c");
            float f = (Float) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "d");
            float f1 = (Float) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "e");
            SPacketPosition loniaPositionPacket = new SPacketPosition(d, d1, d2, f, f1, false);
            data.getPacketOutProcessor().processPacket(loniaPositionPacket);
            return;

        }

        if (packet instanceof PacketPlayOutExplosion) {

            PacketPlayOutExplosion nmsPacket = (PacketPlayOutExplosion) packet;
            double d = (Double) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "a");
            double d1 = (Double) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "b");
            double d2 = (Double) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "c");
            float f = (Float) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "d");
            float f1 = (Float) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "f");
            float f2 = (Float) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "g");
            float f3 = (Float) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "h");
            SPacketExplosion loniaExplosionPacket = new SPacketExplosion(d, d1, d2, f, f1, f2, f3);
            data.getPacketOutProcessor().processPacket(loniaExplosionPacket);
            return;

        }

        if (packet instanceof PacketPlayOutMount) {

            PacketPlayOutMount nmsPacket = (PacketPlayOutMount) packet;
            int i = (Integer) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "a");
            int[] ia = (int[]) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "b");
            SPacketMount loniaMountPacket = new SPacketMount(i, ia);
            data.getPacketOutProcessor().processPacket(loniaMountPacket);
            return;

        }

        if (packet instanceof PacketPlayOutRespawn) {

            PacketPlayOutRespawn nmsPacket = (PacketPlayOutRespawn) packet;
            EnumGamemode gm = (EnumGamemode) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "e");
            SPacketRespawn loniaRespawnPacket = new SPacketRespawn(GameMode.getFromId(gm.getId()));
            data.getPacketOutProcessor().processPacket(loniaRespawnPacket);
            return;

        }

        if (packet instanceof PacketPlayOutNamedEntitySpawn) {

            PacketPlayOutNamedEntitySpawn nmsPacket = (PacketPlayOutNamedEntitySpawn) packet;
            int i = (Integer) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "a");
            UUID u = (UUID) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "b");
            double d = (Double) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "c");
            double d1 = (Double) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "d");
            double d2 = (Double) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "e");
            byte b = (Byte) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "f");
            byte b1 = (Byte) ReflectionUtil.getField(nmsPacket.getClass(), nmsPacket, "g");
            SPacketSpawnEntity loniaSpawnEntityPacket = new SPacketSpawnEntity(i, u, d, d1, d2, (b * 360.0F) / 256.0F, (b1 * 360.0F) / 256.0F, true);
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
            Field entityIdField = nmsPacket.getClass().getSuperclass().getDeclaredField("a");
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

        if (packet instanceof PacketPlayOutHeldItemSlot) {

            PacketPlayOutHeldItemSlot nmsPacket = (PacketPlayOutHeldItemSlot) packet;
            SPacketSetHeldItemSlot loniaSetSlotPacket = new SPacketSetHeldItemSlot(nmsPacket.b());
            data.getPacketOutProcessor().processPacket(loniaSetSlotPacket);
            return;

        }

        if (packet instanceof PacketPlayOutMapChunk) {

            PacketPlayOutMapChunk nmsPacket = (PacketPlayOutMapChunk) packet;
            SPacketLevelChunk loniaLevelChunkPacket = new SPacketLevelChunk(nmsPacket.c(), nmsPacket.d());
            data.getPacketOutProcessor().processPacket(loniaLevelChunkPacket);
            return;

        }

        if (packet instanceof PacketPlayOutCloseWindow) {

            PacketPlayOutCloseWindow nmsPacket = (PacketPlayOutCloseWindow) packet;
            SPacketCloseWindow loniaCloseWindowPacket = new SPacketCloseWindow(nmsPacket.b());
            data.getPacketOutProcessor().processPacket(loniaCloseWindowPacket);
            return;

        }

        if (packet instanceof PacketPlayOutOpenWindow) {

            PacketPlayOutOpenWindow nmsPacket = (PacketPlayOutOpenWindow) packet;
            SPacketOpenWindow loniaOpenWindowPacket = new SPacketOpenWindow(nmsPacket.b(), nmsPacket.d().getString());
            data.getPacketOutProcessor().processPacket(loniaOpenWindowPacket);
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

        if (packet instanceof PacketPlayInAbilities) {

            PacketPlayInAbilities nmsPacket = (PacketPlayInAbilities) packet;
            CPacketAbilities loniaAbilitiesPacket = new CPacketAbilities((Boolean) nmsPacket.getClass().getMethod("isFlying").invoke(nmsPacket));
            data.getPacketInProcessor().processPacket(loniaAbilitiesPacket);
            return;

        }

        if (packet instanceof PacketPlayInArmAnimation) {

            PacketPlayInArmAnimation nmsPacket = (PacketPlayInArmAnimation) packet;
            data.getPacketInProcessor().processPacket(new SPacketArmAnimation(PlayerHand.getFromId(nmsPacket.b().ordinal())));
            return;

        }

        if (packet instanceof PacketPlayInUseEntity) {

            PacketPlayInUseEntity nmsPacket = (PacketPlayInUseEntity) packet;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("a");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            CPacketUseEntity loniaUseEntityPacket = new CPacketUseEntity(entityId, UseEntityAction.ATTACK);
            data.getPacketInProcessor().processPacket(loniaUseEntityPacket);
            return;

        }

        if (packet instanceof PacketPlayInBlockPlace) {

            PacketPlayInBlockPlace nmsPacket = (PacketPlayInBlockPlace) packet;
            CPacketBlockPlace loniaBlockPlacePacket = new CPacketBlockPlace(PlayerHand.getFromId(nmsPacket.b().ordinal()));
            data.getPacketInProcessor().processPacket(loniaBlockPlacePacket);
            return;

        }

        if (packet instanceof PacketPlayInBlockDig) {

            PacketPlayInBlockDig nmsPacket = (PacketPlayInBlockDig) packet;
            CPacketBlockDig loniaBlockDigPacket = new CPacketBlockDig(PlayerDigAction.values()[nmsPacket.d().ordinal()]);
            data.getPacketInProcessor().processPacket(loniaBlockDigPacket);
            return;

        }

        if (packet instanceof PacketPlayInClientCommand) {

            PacketPlayInClientCommand nmsPacket = (PacketPlayInClientCommand) packet;
            CPacketClientCommand loniaClientCommandPacket = new CPacketClientCommand(ClientCommand.values()[nmsPacket.b().ordinal()]);
            data.getPacketInProcessor().processPacket(loniaClientCommandPacket);
            return;

        }

        if (packet instanceof PacketPlayInHeldItemSlot) {

            PacketPlayInHeldItemSlot nmsPacket = (PacketPlayInHeldItemSlot) packet;
            CPacketSetHeldItemSlot loniaSetHeldItemSlotPacket = new CPacketSetHeldItemSlot(nmsPacket.b());
            data.getPacketInProcessor().processPacket(loniaSetHeldItemSlotPacket);
            return;

        }

        if (packet instanceof PacketPlayInCloseWindow) {

            PacketPlayInCloseWindow nmsPacket = (PacketPlayInCloseWindow) packet;
            CPacketCloseWindow loniaCloseWindowPacket = new CPacketCloseWindow(nmsPacket.b());
            data.getPacketInProcessor().processPacket(loniaCloseWindowPacket);
            return;

        }

        if (packet instanceof PacketPlayInWindowClick) {

            PacketPlayInWindowClick nmsPacket = (PacketPlayInWindowClick) packet;
            CPacketClickWindow loniaClickWindowPacket = new CPacketClickWindow(nmsPacket.b(), nmsPacket.c(), nmsPacket.d(), (short) -1, nmsPacket.h());
            data.getPacketInProcessor().processPacket(loniaClickWindowPacket);
            return;

        }

        if (packet instanceof PacketPlayInSteerVehicle) {

            PacketPlayInSteerVehicle nmsPacket = (PacketPlayInSteerVehicle) packet;
            CPacketInput loniaInputPacket = new CPacketInput(nmsPacket.b(), nmsPacket.c(), nmsPacket.d(), nmsPacket.e());
            data.getPacketInProcessor().processPacket(loniaInputPacket);
            return;

        }

    }

}
*/
