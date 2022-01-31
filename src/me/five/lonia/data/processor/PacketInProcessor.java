package me.five.lonia.data.processor;

import me.five.lonia.Lonia;
import me.five.lonia.data.PlayerData;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.*;
import me.five.lonia.util.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.Arrays;
import java.util.Iterator;

public class PacketInProcessor {

    private PlayerData playerData;

    public PacketInProcessor(PlayerData data) {
        this.playerData = data;
    }

    public void processPacket(LoniaPacket packet) {

        if (packet instanceof CPacketTransaction) {

            CPacketTransaction transaction = (CPacketTransaction) packet;
            if (playerData.getTransactionMap().containsKey(transaction.getUid())) {
                playerData.getTransactionManager().handleTransaction(transaction.getUid());
                playerData.setLastTransactionPing(playerData.getTransactionPing());
                playerData.setTransactionPing(System.currentTimeMillis() - playerData.getTransactionMap().remove(transaction.getUid()));
                playerData.setAverageTransactionPing(((playerData.getAverageTransactionPing() * 4) + playerData.getTransactionPing()) / 5);
                playerData.getEntityTrackerManager().handleTransaction(transaction.getUid());
            }

        }

        if (packet instanceof CPacketAbilities) {

            CPacketAbilities abilities = (CPacketAbilities) packet;
            if (playerData.getLoniaAbilities().isAllowFlying()) {

                if (playerData.getLoniaAbilities().isFlying() && !abilities.isFlying()) playerData.getTickerMap().put(Ticker.ABILITIES, 70);
                playerData.getLoniaAbilities().setFlying(abilities.isFlying());
                if (!abilities.onlyContainsFlying()) {

                    playerData.getLoniaAbilities().setAllowFlying(abilities.isAllowFlying());

                }

            }

        }

        if (packet instanceof CPacketFlying) {

            CPacketFlying flying = (CPacketFlying) packet;
            playerData.clientTick();
            playerData.setLastLocation(playerData.getLocation());
            CustomLocation playerLocation = new CustomLocation(playerData.getLastLocation().getPosX(),
                    playerData.getLastLocation().getPosY(), playerData.getLastLocation().getPosZ(),
                    playerData.getLastLocation().getYaw(), playerData.getLastLocation().getPitch(),
                    playerData.getLastLocation().isOnGround());
            if (flying.hasPos()) {
                playerLocation.setPosX(flying.getX());
                playerLocation.setPosY(flying.getY());
                playerLocation.setPosZ(flying.getZ());
            }
            if (flying.hasLook()) {
                playerLocation.setYaw(flying.getYaw());
                playerLocation.setPitch(flying.getPitch());
            }
            if (playerLocation.isOnGround() != flying.isOnGround()) {
                playerData.setAirTicks(0);
                playerData.setGroundTicks(0);
            }
            if (flying.isOnGround()) playerData.setGroundTicks(playerData.getGroundTicks() + 1);
            if (!flying.isOnGround()) playerData.setAirTicks(playerData.getAirTicks() + 1);
            playerLocation.setOnGround(flying.isOnGround());
            playerData.setLocation(playerLocation);

            if (playerLocation.isOnGround() && playerData.getTeleportList().isEmpty() && playerData.getTickerMap().getOrDefault(Ticker.WORLD_LOADED, 0) < 200) {
                playerData.getTickerMap().put(Ticker.WORLD_LOADED, 200);
            }
            if (playerLocation.getPosY() >= playerData.getLastLocation().getPosY() && playerData.getTeleportList().isEmpty() && !playerLocation.positionEquals(playerData.getLastLocation()) && playerData.getTickerMap().getOrDefault(Ticker.WORLD_LOADED, 0) < 200) {
                playerData.getTickerMap().put(Ticker.WORLD_LOADED, playerData.getTickerMap().getOrDefault(Ticker.WORLD_LOADED, 0) + 10);
            }

            playerData.setLastBoundingBox(playerData.getBoundingBox());
            Cuboid boundingBox = new Cuboid(playerLocation.getPosX() - (Lonia.getInstance().getNMSManager().getEntityWidth(playerData.getPlayer()) / 2),
                    playerLocation.getPosX() + (Lonia.getInstance().getNMSManager().getEntityWidth(playerData.getPlayer()) / 2),
                    playerLocation.getPosY(), playerLocation.getPosY() + Lonia.getInstance().getNMSManager().getEntityHeight(playerData.getPlayer()),
                    playerLocation.getPosZ() - (Lonia.getInstance().getNMSManager().getEntityWidth(playerData.getPlayer()) / 2),
                    playerLocation.getPosZ() + (Lonia.getInstance().getNMSManager().getEntityWidth(playerData.getPlayer()) / 2));
            playerData.setBoundingBox(boundingBox);

            if (boundingBox.modify(0, 0, -1, 0, 0, 0).checkBlocks(playerData.getPlayer(), Lonia.getInstance().getBlockManager().getAbnormalVelocityBlocks())) {
                playerData.getTickerMap().put(Ticker.ABNORMAL_VELOCITY, 3);
            }
            if (boundingBox.modify(0, 0, -1, 0, 0, 0).checkBlocks(playerData.getPlayer(), Lonia.getInstance().getBlockManager().getStairBlocks())) {
                playerData.getTickerMap().put(Ticker.STAIRS, 4);
            }
            if (boundingBox.modify(0, 0, -1, 0, 0, 0).checkBlocks(playerData.getPlayer(), Lonia.getInstance().getBlockManager().getSlabBlocks())) {
                playerData.getTickerMap().put(Ticker.SLABS, 4);
            }
            if (boundingBox.modify(0, 0, -1, 0, 0, 0).checkBlocks(playerData.getPlayer(), Lonia.getInstance().getBlockManager().getLaunchBlocks())) {
                playerData.getTickerMap().put(Ticker.LAUNCH_BLOCK, 100);
            }
            if (boundingBox.modify(0, 0, -1, 0, 0, 0).checkBlocks(playerData.getPlayer(), Lonia.getInstance().getBlockManager().getSlippyBlocks())) {
                playerData.getTickerMap().put(Ticker.SLIPPY_BLOCK, 30);
            }
            if (boundingBox.modify(0, 0, 1.8, 3.8, 0, 0).checkBlocksExcept(playerData.getPlayer(), Arrays.asList(Material.AIR))) {
                playerData.getTickerMap().put(Ticker.UNDER_BLOCK, 5);
            }

            if (Lonia.getInstance().getNMSManager().getVersion().isNewerOrEqual(ServerVersion.v1_9_R1)) {
                if (playerData.getPlayer().getInventory().getChestplate() != null
                        && playerData.getPlayer().getInventory().getChestplate().getType().equals(Material.ELYTRA)
                        && playerData.getPlayer().isGliding()) {
                    playerData.getTickerMap().put(Ticker.GLIDING, 100);
                }
            }

            if (Lonia.getInstance().getNMSManager().getVersion().isNewerOrEqual(ServerVersion.v1_13_R1)) {
                if (playerData.getPlayer().getInventory().getItemInMainHand() != null
                        && playerData.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.RIPTIDE)) {
                    playerData.getTickerMap().put(Ticker.RIPTIDE, 150);
                }
            }

            Iterator<Teleport> teleportIterator = playerData.getTeleportList().iterator();
            while (teleportIterator.hasNext()) {
                Teleport tp = teleportIterator.next();
                if (tp.locationEquals(playerLocation)) {
                    teleportIterator.remove();
                    Bukkit.broadcastMessage("removed! " + playerData.getTeleportList().size());
                    playerData.getTickerMap().put(Ticker.TELEPORT, 5);
                }
            }

        }

        if (packet instanceof CPacketEntityAction) {

            CPacketEntityAction entityAction = (CPacketEntityAction) packet;
            if (entityAction.getAction().equals(CPacketEntityAction.Action.START_SPRINTING)) {
                playerData.setSprinting(true);
            }
            if (entityAction.getAction().equals(CPacketEntityAction.Action.STOP_SPRINTING)) {
                playerData.setSprinting(false);
            }
            if (entityAction.getAction().equals(CPacketEntityAction.Action.PRESS_SHIFT_KEY)) {
                playerData.setSneaking(true);
            }
            if (entityAction.getAction().equals(CPacketEntityAction.Action.RELEASE_SHIFT_KEY)) {
                playerData.setSneaking(false);
            }

        }

        if (packet instanceof CPacketKeepAlive) {

            CPacketKeepAlive keepAlive = (CPacketKeepAlive) packet;
            if (playerData.getKeepAliveMap().containsKey(keepAlive.getId())) {
                long ping = System.currentTimeMillis() - playerData.getKeepAliveMap().remove(keepAlive.getId());
                playerData.setKeepAlivePing((playerData.getKeepAlivePing() * 4 + ping) / 5);
            }

        }

        playerData.getLoadedChecks().forEach(c -> c.handle(packet));

    }

}
