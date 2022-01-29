package me.five.lonia.nms.v1_8_R3;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import me.five.lonia.data.PlayerData;
import me.five.lonia.packet.client.*;
import me.five.lonia.packet.server.*;
import me.five.lonia.util.*;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.inventory.ItemStack;

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

        if (packet instanceof PacketPlayOutEntityEffect) {

            PacketPlayOutEntityEffect nmsPacket = (PacketPlayOutEntityEffect) packet;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("a");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            Field effectIdField = nmsPacket.getClass().getDeclaredField("b");
            effectIdField.setAccessible(true);
            byte effectId = effectIdField.getByte(nmsPacket);
            Field amplifierField = nmsPacket.getClass().getDeclaredField("c");
            amplifierField.setAccessible(true);
            byte amplifier = amplifierField.getByte(nmsPacket);
            Field ticksField = nmsPacket.getClass().getDeclaredField("d");
            ticksField.setAccessible(true);
            int ticks = ticksField.getInt(nmsPacket);
            SPacketEntityEffect loniaEffectPacket = new SPacketEntityEffect(entityId, effectId, amplifier, ticks);
            data.getPacketOutProcessor().processPacket(loniaEffectPacket);
            return;

        }

        if (packet instanceof PacketPlayOutRemoveEntityEffect) {

            PacketPlayOutRemoveEntityEffect nmsPacket = (PacketPlayOutRemoveEntityEffect) packet;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("a");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            Field effectIdField = nmsPacket.getClass().getDeclaredField("b");
            effectIdField.setAccessible(true);
            byte effectId = ((Integer)effectIdField.getInt(nmsPacket)).byteValue();
            SPacketRemoveEffect loniaRemoveEffectPacket = new SPacketRemoveEffect(entityId, effectId);
            data.getPacketOutProcessor().processPacket(loniaRemoveEffectPacket);
            return;

        }

        if (packet instanceof PacketPlayOutAbilities) {

            PacketPlayOutAbilities nmsPacket = (PacketPlayOutAbilities) packet;
            Field flySpeedField = nmsPacket.getClass().getDeclaredField("e");
            flySpeedField.setAccessible(true);
            float flyingSpeed = flySpeedField.getFloat(nmsPacket);
            Field walkSpeedField = nmsPacket.getClass().getDeclaredField("f");
            walkSpeedField.setAccessible(true);
            float walkingSpeed = walkSpeedField.getFloat(nmsPacket);
            SPacketAbilities loniaAbilitiesPacket = new SPacketAbilities(nmsPacket.b(), nmsPacket.c(), nmsPacket.a(), nmsPacket.d(), flyingSpeed, walkingSpeed);
            data.getPacketOutProcessor().processPacket(loniaAbilitiesPacket);
            return;

        }

        if (packet instanceof PacketPlayOutPosition) {

            PacketPlayOutPosition nmsPacket = (PacketPlayOutPosition) packet;
            Field xField = nmsPacket.getClass().getDeclaredField("a");
            xField.setAccessible(true);
            double x = xField.getDouble(nmsPacket);
            Field yField = nmsPacket.getClass().getDeclaredField("b");
            yField.setAccessible(true);
            double y = yField.getDouble(nmsPacket);
            Field zField = nmsPacket.getClass().getDeclaredField("c");
            zField.setAccessible(true);
            double z = zField.getDouble(nmsPacket);
            Field yawField = nmsPacket.getClass().getDeclaredField("d");
            yawField.setAccessible(true);
            float yaw = yawField.getFloat(nmsPacket);
            Field pitchField = nmsPacket.getClass().getDeclaredField("e");
            pitchField.setAccessible(true);
            float pitch = pitchField.getFloat(nmsPacket);
            SPacketPosition loniaPositionPacket = new SPacketPosition(x, y, z, yaw, pitch, false);
            data.getPacketOutProcessor().processPacket(loniaPositionPacket);
            return;

        }

        if (packet instanceof PacketPlayOutExplosion) {

            PacketPlayOutExplosion nmsPacket = (PacketPlayOutExplosion) packet;
            Field xField = nmsPacket.getClass().getDeclaredField("a");
            xField.setAccessible(true);
            double x = xField.getDouble(nmsPacket);
            Field yField = nmsPacket.getClass().getDeclaredField("b");
            yField.setAccessible(true);
            double y = yField.getDouble(nmsPacket);
            Field zField = nmsPacket.getClass().getDeclaredField("c");
            zField.setAccessible(true);
            double z = zField.getDouble(nmsPacket);
            Field powerField = nmsPacket.getClass().getDeclaredField("d");
            powerField.setAccessible(true);
            float power = powerField.getFloat(nmsPacket);
            Field motionXField = nmsPacket.getClass().getDeclaredField("f");
            motionXField.setAccessible(true);
            float motionX = motionXField.getFloat(nmsPacket);
            Field motionYField = nmsPacket.getClass().getDeclaredField("g");
            motionYField.setAccessible(true);
            float motionY = motionYField.getFloat(nmsPacket);
            Field motionZField = nmsPacket.getClass().getDeclaredField("h");
            motionZField.setAccessible(true);
            float motionZ = motionZField.getFloat(nmsPacket);
            SPacketExplosion loniaExplosionPacket = new SPacketExplosion(x, y, z, power, motionX, motionY, motionZ);
            data.getPacketOutProcessor().processPacket(loniaExplosionPacket);
            return;

        }

        if (packet instanceof PacketPlayOutAttachEntity) {

            PacketPlayOutAttachEntity nmsPacket = (PacketPlayOutAttachEntity) packet;
            Field leashField = nmsPacket.getClass().getDeclaredField("a");
            leashField.setAccessible(true);
            int leash = leashField.getInt(nmsPacket);
            if (leash != 0) return;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("b");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            Field vehicleEntityIdField = nmsPacket.getClass().getDeclaredField("c");
            vehicleEntityIdField.setAccessible(true);
            int vehicleEntityId = vehicleEntityIdField.getInt(nmsPacket);
            SPacketMount loniaMountPacket = new SPacketMount(vehicleEntityId, new int[] {entityId});
            data.getPacketOutProcessor().processPacket(loniaMountPacket);
            return;

        }

        if (packet instanceof PacketPlayOutRespawn) {

            PacketPlayOutRespawn nmsPacket = (PacketPlayOutRespawn) packet;
            Field gameModeField = nmsPacket.getClass().getDeclaredField("c");
            gameModeField.setAccessible(true);
            WorldSettings.EnumGamemode nmsGamemode = (WorldSettings.EnumGamemode) gameModeField.get(nmsPacket);
            SPacketRespawn loniaRespawnPacket = new SPacketRespawn(GameMode.getFromId(nmsGamemode.getId()));
            data.getPacketOutProcessor().processPacket(loniaRespawnPacket);
            return;

        }

        if (packet instanceof PacketPlayOutNamedEntitySpawn) {

            PacketPlayOutNamedEntitySpawn nmsPacket = (PacketPlayOutNamedEntitySpawn) packet;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("a");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            Field uuidField = nmsPacket.getClass().getDeclaredField("b");
            uuidField.setAccessible(true);
            UUID playerId = (UUID) uuidField.get(nmsPacket);
            Field xField = nmsPacket.getClass().getDeclaredField("c");
            xField.setAccessible(true);
            double x = xField.getInt(nmsPacket) / 32.0D;
            Field yField = nmsPacket.getClass().getDeclaredField("d");
            yField.setAccessible(true);
            double y = yField.getInt(nmsPacket) / 32.0D;
            Field zField = nmsPacket.getClass().getDeclaredField("e");
            zField.setAccessible(true);
            double z = zField.getInt(nmsPacket) / 32.0D;
            Field yawField = nmsPacket.getClass().getDeclaredField("f");
            yawField.setAccessible(true);
            float yaw = (yawField.getByte(nmsPacket) * 360.0F) / 256.0F;
            Field pitchField = nmsPacket.getClass().getDeclaredField("g");
            pitchField.setAccessible(true);
            float pitch = (pitchField.getByte(nmsPacket) * 360.0F) / 256.0F;
            SPacketSpawnEntity loniaSpawnEntityPacket = new SPacketSpawnEntity(entityId, playerId, x, y, z, yaw, pitch, true);
            data.getPacketOutProcessor().processPacket(loniaSpawnEntityPacket);
            return;

        }

        if (packet instanceof PacketPlayOutSpawnEntityLiving) {

            PacketPlayOutSpawnEntityLiving nmsPacket = (PacketPlayOutSpawnEntityLiving) packet;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("a");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            Field xField = nmsPacket.getClass().getDeclaredField("c");
            xField.setAccessible(true);
            double x = xField.getInt(nmsPacket) / 32.0D;
            Field yField = nmsPacket.getClass().getDeclaredField("d");
            yField.setAccessible(true);
            double y = yField.getInt(nmsPacket) / 32.0D;
            Field zField = nmsPacket.getClass().getDeclaredField("e");
            zField.setAccessible(true);
            double z = zField.getInt(nmsPacket) / 32.0D;
            Field yawField = nmsPacket.getClass().getDeclaredField("i");
            yawField.setAccessible(true);
            float yaw = (yawField.getByte(nmsPacket) * 360.0F) / 256.0F;
            Field pitchField = nmsPacket.getClass().getDeclaredField("j");
            pitchField.setAccessible(true);
            float pitch = (pitchField.getByte(nmsPacket) * 360.0F) / 256.0F;
            SPacketSpawnEntity loniaSpawnEntityPacket = new SPacketSpawnEntity(entityId, UUID.randomUUID(), x, y, z, yaw, pitch, false);
            data.getPacketOutProcessor().processPacket(loniaSpawnEntityPacket);
            return;

        }

        if (packet instanceof PacketPlayOutSpawnEntity) {

            PacketPlayOutSpawnEntity nmsPacket = (PacketPlayOutSpawnEntity) packet;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("a");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            Field xField = nmsPacket.getClass().getDeclaredField("c");
            xField.setAccessible(true);
            double x = xField.getInt(nmsPacket) / 32.0D;
            Field yField = nmsPacket.getClass().getDeclaredField("d");
            yField.setAccessible(true);
            double y = yField.getInt(nmsPacket) / 32.0D;
            Field zField = nmsPacket.getClass().getDeclaredField("e");
            zField.setAccessible(true);
            double z = zField.getInt(nmsPacket) / 32.0D;
            Field yawField = nmsPacket.getClass().getDeclaredField("i");
            yawField.setAccessible(true);
            float yaw = (yawField.getByte(nmsPacket) * 360.0F) / 256.0F;
            Field pitchField = nmsPacket.getClass().getDeclaredField("j");
            pitchField.setAccessible(true);
            float pitch = (pitchField.getByte(nmsPacket) * 360.0F) / 256.0F;
            SPacketSpawnEntity loniaSpawnEntityPacket = new SPacketSpawnEntity(entityId, UUID.randomUUID(), x, y, z, yaw, pitch, false);
            data.getPacketOutProcessor().processPacket(loniaSpawnEntityPacket);
            return;

        }

        if (packet instanceof PacketPlayOutEntityTeleport) {

            PacketPlayOutEntityTeleport nmsPacket = (PacketPlayOutEntityTeleport) packet;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("a");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            Field xField = nmsPacket.getClass().getDeclaredField("b");
            xField.setAccessible(true);
            double x = xField.getInt(nmsPacket) / 32.0D;
            Field yField = nmsPacket.getClass().getDeclaredField("c");
            yField.setAccessible(true);
            double y = yField.getInt(nmsPacket) / 32.0D;
            Field zField = nmsPacket.getClass().getDeclaredField("d");
            zField.setAccessible(true);
            double z = zField.getInt(nmsPacket) / 32.0D;
            Field yawField = nmsPacket.getClass().getDeclaredField("e");
            yawField.setAccessible(true);
            float yaw = (yawField.getByte(nmsPacket) * 360.0F) / 256.0F;
            Field pitchField = nmsPacket.getClass().getDeclaredField("f");
            pitchField.setAccessible(true);
            float pitch = (pitchField.getByte(nmsPacket) * 360.0F) / 256.0F;
            Field groundField = nmsPacket.getClass().getDeclaredField("g");
            groundField.setAccessible(true);
            boolean onGround = groundField.getBoolean(nmsPacket);
            SPacketEntityTeleport loniaEntityTeleportPacket = new SPacketEntityTeleport(entityId, x, y, z, yaw, pitch, onGround);
            data.getPacketOutProcessor().processPacket(loniaEntityTeleportPacket);
            return;

        }

        if (packet instanceof PacketPlayOutEntity) {

            PacketPlayOutEntity nmsPacket = (PacketPlayOutEntity) packet;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("a");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            Field xField = nmsPacket.getClass().getDeclaredField("b");
            xField.setAccessible(true);
            double x = xField.getByte(nmsPacket) / 32.0D;
            Field yField = nmsPacket.getClass().getDeclaredField("c");
            yField.setAccessible(true);
            double y = yField.getByte(nmsPacket) / 32.0D;
            Field zField = nmsPacket.getClass().getDeclaredField("d");
            zField.setAccessible(true);
            double z = zField.getByte(nmsPacket) / 32.0D;
            Field yawField = nmsPacket.getClass().getDeclaredField("e");
            yawField.setAccessible(true);
            float yaw = (yawField.getByte(nmsPacket) * 360.0F) / 256.0F;
            Field pitchField = nmsPacket.getClass().getDeclaredField("f");
            pitchField.setAccessible(true);
            float pitch = (pitchField.getByte(nmsPacket) * 360.0F) / 256.0F;
            Field groundField = nmsPacket.getClass().getDeclaredField("g");
            groundField.setAccessible(true);
            boolean onGround = groundField.getBoolean(nmsPacket);
            Field lookField = nmsPacket.getClass().getDeclaredField("h");
            lookField.setAccessible(true);
            boolean hasLook = lookField.getBoolean(nmsPacket);
            if (hasLook) {
                SPacketEntityMove loniaEntityMovePacket = new SPacketEntityMove(entityId, x, y, z, yaw, pitch, onGround);
                data.getPacketOutProcessor().processPacket(loniaEntityMovePacket);
                return;
            }
            SPacketEntityMove loniaEntityMovePacket = new SPacketEntityMove(entityId, x, y, z, onGround);
            data.getPacketOutProcessor().processPacket(loniaEntityMovePacket);
            return;

        }

        if (packet instanceof PacketPlayOutHeldItemSlot) {

            PacketPlayOutHeldItemSlot nmsPacket = (PacketPlayOutHeldItemSlot) packet;
            Field slotField = nmsPacket.getClass().getDeclaredField("a");
            slotField.setAccessible(true);
            int slot = slotField.getInt(nmsPacket);
            SPacketSetHeldItemSlot loniaSetSlotPacket = new SPacketSetHeldItemSlot(slot);
            data.getPacketOutProcessor().processPacket(loniaSetSlotPacket);
            return;

        }

        if (packet instanceof PacketPlayOutMapChunk) {

            PacketPlayOutMapChunk nmsPacket = (PacketPlayOutMapChunk) packet;
            Field chunkXField = nmsPacket.getClass().getDeclaredField("a");
            chunkXField.setAccessible(true);
            int chunkX = chunkXField.getInt(nmsPacket);
            Field chunkYField = nmsPacket.getClass().getDeclaredField("b");
            chunkYField.setAccessible(true);
            int chunkY = chunkYField.getInt(nmsPacket);
            SPacketLevelChunk loniaLevelChunkPacket = new SPacketLevelChunk(chunkX, chunkY);
            data.getPacketOutProcessor().processPacket(loniaLevelChunkPacket);
            return;

        }

        if (packet instanceof PacketPlayOutMapChunkBulk) {

            SPacketLevelChunk loniaLevelChunkPacket = new SPacketLevelChunk();
            data.getPacketOutProcessor().processPacket(loniaLevelChunkPacket);
            return;

        }

        if (packet instanceof PacketPlayOutCloseWindow) {

            PacketPlayOutCloseWindow nmsPacket = (PacketPlayOutCloseWindow) packet;
            Field windowIdField = nmsPacket.getClass().getDeclaredField("a");
            windowIdField.setAccessible(true);
            int windowId = windowIdField.getInt(nmsPacket);
            SPacketCloseWindow loniaCloseWindowPacket = new SPacketCloseWindow(windowId);
            data.getPacketOutProcessor().processPacket(loniaCloseWindowPacket);
            return;

        }

        if (packet instanceof PacketPlayOutOpenWindow) {

            PacketPlayOutOpenWindow nmsPacket = (PacketPlayOutOpenWindow) packet;
            Field windowIdField = nmsPacket.getClass().getDeclaredField("a");
            windowIdField.setAccessible(true);
            int windowId = windowIdField.getInt(nmsPacket);
            Field typeField = nmsPacket.getClass().getDeclaredField("b");
            typeField.setAccessible(true);
            String type = (String) typeField.get(nmsPacket);
            Field titleField = nmsPacket.getClass().getDeclaredField("c");
            titleField.setAccessible(true);
            IChatBaseComponent title = (IChatBaseComponent) titleField.get(nmsPacket);
            Field slotsField = nmsPacket.getClass().getDeclaredField("d");
            slotsField.setAccessible(true);
            int slots = slotsField.getInt(nmsPacket);
            Field entityIdField = nmsPacket.getClass().getDeclaredField("e");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            SPacketOpenWindow loniaOpenWindowPacket = new SPacketOpenWindow(windowId, type, title.getText(), slots, entityId);
            data.getPacketOutProcessor().processPacket(loniaOpenWindowPacket);
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

        if (packet instanceof PacketPlayInAbilities) {

            PacketPlayInAbilities nmsPacket = (PacketPlayInAbilities) packet;
            Field flySpeedField = nmsPacket.getClass().getDeclaredField("e");
            flySpeedField.setAccessible(true);
            float flyingSpeed = flySpeedField.getFloat(nmsPacket);
            Field walkSpeedField = nmsPacket.getClass().getDeclaredField("f");
            walkSpeedField.setAccessible(true);
            float walkingSpeed = walkSpeedField.getFloat(nmsPacket);
            CPacketAbilities loniaAbilitiesPacket = new CPacketAbilities(nmsPacket.isFlying(), nmsPacket.c(), nmsPacket.a(), nmsPacket.d(), flyingSpeed, walkingSpeed);
            data.getPacketInProcessor().processPacket(loniaAbilitiesPacket);
            return;

        }

        if (packet instanceof PacketPlayInArmAnimation) {

            data.getPacketInProcessor().processPacket(new SPacketArmAnimation(PlayerHand.MAIN));
            return;

        }

        if (packet instanceof PacketPlayInUseEntity) {

            PacketPlayInUseEntity nmsPacket = (PacketPlayInUseEntity) packet;
            Field entityIdField = nmsPacket.getClass().getDeclaredField("a");
            entityIdField.setAccessible(true);
            int entityId = entityIdField.getInt(nmsPacket);
            UseEntityAction action = UseEntityAction.values()[nmsPacket.a().ordinal()];
            CPacketUseEntity loniaUseEntityPacket = new CPacketUseEntity(entityId, action);
            data.getPacketOutProcessor().processPacket(loniaUseEntityPacket);
            return;

        }

        if (packet instanceof PacketPlayInBlockPlace) {

            PacketPlayInBlockPlace nmsPacket = (PacketPlayInBlockPlace) packet;
            CPacketBlockPlace loniaBlockPlacePacket = new CPacketBlockPlace(PlayerHand.MAIN, new LoniaBlockLocation(nmsPacket.a().getX(), nmsPacket.a().getY(), nmsPacket.a().getZ()), nmsPacket.getFace(), new ItemStack(nmsPacket.getItemStack().getItem().b()), nmsPacket.d(), nmsPacket.e(), nmsPacket.f());
            data.getPacketOutProcessor().processPacket(loniaBlockPlacePacket);
            return;

        }

        if (packet instanceof PacketPlayInBlockDig) {

            PacketPlayInBlockDig nmsPacket = (PacketPlayInBlockDig) packet;
            CPacketBlockDig loniaBlockDigPacket = new CPacketBlockDig(new LoniaBlockLocation(nmsPacket.a().getX(), nmsPacket.a().getY(), nmsPacket.a().getZ()), PlayerDigAction.values()[nmsPacket.c().ordinal()]);
            data.getPacketInProcessor().processPacket(loniaBlockDigPacket);
            return;

        }

        if (packet instanceof PacketPlayInClientCommand) {

            PacketPlayInClientCommand nmsPacket = (PacketPlayInClientCommand) packet;
            CPacketClientCommand loniaClientCommandPacket = new CPacketClientCommand(ClientCommand.values()[nmsPacket.a().ordinal()]);
            data.getPacketInProcessor().processPacket(loniaClientCommandPacket);
            return;

        }

        if (packet instanceof PacketPlayInHeldItemSlot) {

            PacketPlayInHeldItemSlot nmsPacket = (PacketPlayInHeldItemSlot) packet;
            CPacketSetHeldItemSlot loniaSetHeldItemSlotPacket = new CPacketSetHeldItemSlot(nmsPacket.a());
            data.getPacketInProcessor().processPacket(loniaSetHeldItemSlotPacket);
            return;

        }

        if (packet instanceof PacketPlayInCloseWindow) {

            PacketPlayInCloseWindow nmsPacket = (PacketPlayInCloseWindow) packet;
            Field windowIdField = nmsPacket.getClass().getDeclaredField("id");
            windowIdField.setAccessible(true);
            int windowId = windowIdField.getInt(nmsPacket);
            CPacketCloseWindow loniaCloseWindowPacket = new CPacketCloseWindow(windowId);
            data.getPacketInProcessor().processPacket(loniaCloseWindowPacket);
            return;

        }

        if (packet instanceof PacketPlayInWindowClick) {

            PacketPlayInWindowClick nmsPacket = (PacketPlayInWindowClick) packet;
            CPacketClickWindow loniaClickWindowPacket = new CPacketClickWindow(nmsPacket.a(), nmsPacket.b(), nmsPacket.c(), nmsPacket.d(), nmsPacket.f());
            data.getPacketInProcessor().processPacket(loniaClickWindowPacket);
            return;

        }

        if (packet instanceof PacketPlayInSteerVehicle) {

            PacketPlayInSteerVehicle nmsPacket = (PacketPlayInSteerVehicle) packet;
            CPacketInput loniaInputPacket = new CPacketInput(nmsPacket.a(), nmsPacket.b(), nmsPacket.c(), nmsPacket.d());
            data.getPacketInProcessor().processPacket(loniaInputPacket);
            return;

        }

    }

}
