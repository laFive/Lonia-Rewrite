package me.five.lonia.data.processor;

import me.five.lonia.Lonia;
import me.five.lonia.data.PlayerData;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketAbilities;
import me.five.lonia.packet.server.*;
import me.five.lonia.transaction.*;
import me.five.lonia.util.*;
import net.minecraft.network.protocol.Packet;
import org.bukkit.Bukkit;

import java.util.Arrays;

public class PacketOutProcessor {

    private PlayerData playerData;

    public PacketOutProcessor(PlayerData data) {
        this.playerData = data;
    }

    public void processPacket(LoniaPacket packet) {

        if (packet instanceof SPacketAbilities) {

            int abilitiesTicks = 0;
            SPacketAbilities abilities = (SPacketAbilities) packet;
            if ((!abilities.isFlying() && playerData.getLoniaAbilities().isFlying())
                    || (!abilities.isAllowFlying() && playerData.getLoniaAbilities().isAllowFlying())
                    || (abilities.getWalkingSpeed() < playerData.getLoniaAbilities().getWalkSpeed())) {
                abilitiesTicks = 100;
            }

            if (abilities.isFlying()) playerData.getLoniaAbilities().setFlying(abilities.isFlying());
            if (abilities.isAllowFlying()) playerData.getLoniaAbilities().setAllowFlying(abilities.isAllowFlying());
            if (abilities.getWalkingSpeed() > playerData.getWalkSpeed()) playerData.getLoniaAbilities().setWalkSpeed(abilities.getWalkingSpeed());

            LoniaAbilities packetAbilities = new LoniaAbilities();
            packetAbilities.setFlying(abilities.isFlying());
            packetAbilities.setAllowFlying(abilities.isAllowFlying());
            packetAbilities.setInvulnerable(abilities.isInvulnerable());
            packetAbilities.setInstantBuild(abilities.isInstantBuilding());
            packetAbilities.setFlySpeed(abilities.getFlyingSpeed());
            packetAbilities.setWalkSpeed(abilities.getWalkingSpeed());
            AbilitiesTransaction abilitiesTransaction = new AbilitiesTransaction(playerData.getTickNumber(), packetAbilities, abilitiesTicks);
            playerData.getTransactionManager().addTransaction(abilitiesTransaction);

        }

        if (packet instanceof SPacketEntityVelocity) {

            SPacketEntityVelocity velocity = (SPacketEntityVelocity) packet;
            if (velocity.getEntityId() != playerData.getPlayer().getEntityId()) return;
            int velocityTicks = (int) ((Math.abs(velocity.getVelocityX() + Math.abs(velocity.getVelocityY()) + Math.abs(velocity.getVelocityZ())) / 2 + 2) * 7);
            VelocityTransaction velocityTransaction = new VelocityTransaction(playerData.getTickNumber(), velocityTicks);
            playerData.getTransactionManager().addTransaction(velocityTransaction);
            playerData.getTickerMap().put(Ticker.VELOCITY, playerData.getPingTicks() + 1);
            playerData.getTickerMap().put(Ticker.VELOCITY_TICK, playerData.getPingTicks() + 1);
            playerData.getTickerMap().put(Ticker.SERVER_VELOCITY, velocityTicks);

        }

        if (packet instanceof SPacketExplosion) {

            SPacketExplosion explosion = (SPacketExplosion) packet;
            int velocityTicks = playerData.getTickerMap().getOrDefault(Ticker.SERVER_VELOCITY, 0) + (int) ((Math.abs(explosion.getMotionX() + Math.abs(explosion.getMotionY()) + Math.abs(explosion.getMotionZ())) / 2 + 2) * 13);
            VelocityTransaction velocityTransaction = new VelocityTransaction(playerData.getTickNumber(), velocityTicks);
            playerData.getTransactionManager().addTransaction(velocityTransaction);
            playerData.getTickerMap().put(Ticker.VELOCITY, playerData.getPingTicks() + 1);
            playerData.getTickerMap().put(Ticker.SERVER_VELOCITY, velocityTicks);

        }

        if (packet instanceof SPacketMount) {

            SPacketMount mount = (SPacketMount) packet;
            int entityId = Arrays.stream(mount.getPassengerIds()).filter(i -> i == playerData.getPlayer().getEntityId()).findFirst().orElse(-1);
            if (entityId != -1) {

                if (mount.getEntityId() == -1) {
                    playerData.getTransactionManager().addTransaction(new DismountTransaction(playerData.getTickNumber()));
                    return;
                }

                playerData.setRidingEntity(true);
                playerData.setRidingEntityId(mount.getEntityId());
                return;

            }

            if (playerData.isRidingEntityServer() && mount.getEntityId() == playerData.getRidingEntityId()) {

                playerData.getTransactionManager().addTransaction(new DismountTransaction(playerData.getTickNumber()));

            }

        }

        if (packet instanceof SPacketEntityEffect) {

            SPacketEntityEffect entityEffect = (SPacketEntityEffect) packet;
            if (entityEffect.getEntityId() != playerData.getPlayer().getEntityId()) return;
            EntityEffectData effectData = new EntityEffectData(entityEffect.getEffectType(), entityEffect.getEffectTicks(), entityEffect.getEffectAmplifier());
            EffectTransaction effectTransaction = new EffectTransaction(playerData.getTickNumber(), effectData);
            playerData.getTransactionManager().addTransaction(effectTransaction);

        }

        if (packet instanceof SPacketRemoveEffect) {

            SPacketRemoveEffect removeEffect = (SPacketRemoveEffect) packet;
            if (removeEffect.getEntityId() != playerData.getPlayer().getEntityId()) return;
            RemoveEffectTransaction removeEffectTransaction = new RemoveEffectTransaction(playerData.getTickNumber(), removeEffect.getEffectType());
            playerData.getTransactionManager().addTransaction(removeEffectTransaction);

        }

        if (packet instanceof SPacketPosition) {

            SPacketPosition positionPacket = (SPacketPosition) packet;
            Teleport tp = new Teleport(positionPacket.getX(), positionPacket.getY(), positionPacket.getZ());
            playerData.getTeleportList().add(tp);
            playerData.getTransactionManager().addTransaction(new TeleportTransaction(playerData.getTickNumber(), tp));
            if (Math.abs(playerData.getLocation().getPosX() - tp.getX()) >= 88
                    || Math.abs(playerData.getLocation().getPosY() - tp.getY()) >= 88) {
                playerData.getTickerMap().put(Ticker.WORLD_LOADED, 0);
            }

        }

        if (packet instanceof SPacketSpawnEntity) {

            SPacketSpawnEntity spawnEntity = (SPacketSpawnEntity) packet;
            playerData.getEntityTrackerManager().handleEntityAdd(spawnEntity, playerData);

        }

        if (packet instanceof SPacketEntityTeleport) {

            SPacketEntityTeleport teleportEntity = (SPacketEntityTeleport) packet;
            playerData.getEntityTrackerManager().handleEntityTeleport(teleportEntity);

        }

        if (packet instanceof SPacketEntityMove) {

            SPacketEntityMove moveEntity = (SPacketEntityMove) packet;
            playerData.getEntityTrackerManager().handleRelMove(moveEntity);

        }

        if (packet instanceof SPacketRespawn) {

            playerData.getEntityTrackerManager().clearTrackers();
            playerData.getTickerMap().put(Ticker.WORLD_LOADED, 0);
            if (((SPacketRespawn) packet).getGameMode().equals(GameMode.CREATIVE)
                    || ((SPacketRespawn) packet).getGameMode().equals(GameMode.SPECTATOR)) {
                playerData.getLoniaAbilities().setAllowFlying(true);
            }

        }

        if (packet instanceof SPacketKeepAlive) {

            SPacketKeepAlive keepAlive = (SPacketKeepAlive) packet;
            playerData.getKeepAliveMap().put(keepAlive.getId(), System.currentTimeMillis());

        }

        playerData.getLoadedChecks().forEach(c -> c.handle(packet));

    }

}
