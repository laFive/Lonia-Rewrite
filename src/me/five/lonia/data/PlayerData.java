package me.five.lonia.data;

import me.five.lonia.check.Check;
import me.five.lonia.data.processor.PacketInProcessor;
import me.five.lonia.data.processor.PacketOutProcessor;
import me.five.lonia.data.tracker.TrackerManager;
import me.five.lonia.transaction.TransactionManager;
import me.five.lonia.util.*;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerData {

    private Player player;
    private ClientVersion clientVersion;
    private LoniaAbilities loniaAbilities;
    private PacketInProcessor packetInProcessor;
    private PacketOutProcessor packetOutProcessor;
    private Map<Ticker, Integer> tickerMap;
    private TransactionManager transactionManager;
    private short tickNumber;
    private Map<Short, Long> transactionMap;
    private long transactionPing;
    private long lastTransactionPing;
    private long averageTransactionPing;
    private boolean ridingEntity;
    private int ridingEntityId;
    private Map<EntityEffectType, EntityEffectData> activeEffects;
    private List<Teleport> teleportList;
    private TrackerManager entityTrackerManager;
    private CustomLocation location;
    private CustomLocation lastLocation;
    private Cuboid boundingBox;
    private Cuboid lastBoundingBox;
    private boolean sprinting;
    private boolean sneaking;
    private boolean banned;
    private Map<Long, Long> keepAliveMap;
    private long keepAlivePing;
    private List<Check> loadedChecks;
    private int groundTicks;
    private int airTicks;
    private boolean usingItem;
    private int foodLevel;
    private int itemSlot;

    public PlayerData(Player player, int versionNumber) {
        this.player = player;
        this.clientVersion = ClientVersion.getFromVersionNumber(versionNumber);
        this.packetInProcessor = new PacketInProcessor(this);
        this.packetOutProcessor = new PacketOutProcessor(this);
        this.loniaAbilities = new LoniaAbilities(false, false, false, false, 0.05f, 0.1f);
        this.tickerMap = new HashMap<>();
        this.transactionManager = new TransactionManager(this);
        this.tickNumber = 0;
        this.transactionMap = new HashMap<>();
        this.activeEffects = new HashMap<>();
        this.teleportList = new ArrayList<>();
        this.entityTrackerManager = new TrackerManager(this);
        this.location = new CustomLocation();
        this.lastLocation = new CustomLocation();
        this.boundingBox = new Cuboid();
        this.lastBoundingBox = new Cuboid();
        this.keepAliveMap = new HashMap<>();
        this.loadedChecks = new ArrayList<>();
    }

    public void clientTick() {
        Iterator<Map.Entry<Ticker, Integer>> tickerIterator = tickerMap.entrySet().iterator();
        while (tickerIterator.hasNext()) {
            Map.Entry<Ticker, Integer> tickerEntry = tickerIterator.next();
            if (tickerEntry.getKey().equals(Ticker.WORLD_LOADED)) continue;
            tickerMap.put(tickerEntry.getKey(), tickerEntry.getValue() - 1);
            if (tickerMap.get(tickerEntry.getKey()) == 0) tickerIterator.remove();
        }
    }

    public int getPingTicks() {
        return (int) (Math.ceil((Math.max(Math.max(transactionPing, lastTransactionPing), averageTransactionPing)) / 50) + 2);
    }

    public boolean isFlying() {
        if (tickerMap.getOrDefault(Ticker.ABILITIES, 0) > 0) {
            return true;
        }
        return loniaAbilities.isFlying();
    }

    public Map<Long, Long> getKeepAliveMap() {
        return keepAliveMap;
    }

    public short getTickNumber() {
        return tickNumber;
    }

    public Map<Short, Long> getTransactionMap() {
        return transactionMap;
    }

    public void setTickNumber(short tickNumber) {
        this.tickNumber = tickNumber;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public List<Teleport> getTeleportList() {
        return teleportList;
    }

    public long getKeepAlivePing() {
        return keepAlivePing;
    }

    public void setKeepAlivePing(long keepAlivePing) {
        this.keepAlivePing = keepAlivePing;
    }

    public float getWalkSpeed() {
        return loniaAbilities.getWalkSpeed();
    }

    public Map<EntityEffectType, EntityEffectData> getActiveEffects() {
        return activeEffects;
    }

    public boolean isRidingEntity() {
        if (tickerMap.getOrDefault(Ticker.DISMOUNT_TICKS, 0) > 0) return true;
        return ridingEntity;
    }


    public boolean isUsingItem() {
        return usingItem;
    }

    public void setUsingItem(boolean usingItem) {
        this.usingItem = usingItem;
    }

    public long getMaxPing() {
        return Math.max(Math.max(averageTransactionPing, transactionPing), lastTransactionPing);
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    public int getItemSlot() {
        return itemSlot;
    }

    public void setItemSlot(int itemSlot) {
        this.itemSlot = itemSlot;
    }

    public boolean isRidingEntityServer() {
        return ridingEntity;
    }

    public void setRidingEntity(boolean ridingEntity) {
        this.ridingEntity = ridingEntity;
    }

    public boolean isSprinting() {
        return sprinting;
    }

    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }

    public boolean isSneaking() {
        return sneaking;
    }

    public void setSneaking(boolean sneaking) {
        this.sneaking = sneaking;
    }

    public int getRidingEntityId() {
        return ridingEntityId;
    }

    public void setRidingEntityId(int ridingEntityId) {
        this.ridingEntityId = ridingEntityId;
    }

    public void setLocation(CustomLocation location) {
        this.location = location;
    }

    public void setLastLocation(CustomLocation lastLocation) {
        this.lastLocation = lastLocation;
    }

    public Cuboid getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Cuboid boundingBox) {
        this.boundingBox = boundingBox;
    }

    public int getGroundTicks() {
        return groundTicks;
    }

    public void setGroundTicks(int groundTicks) {
        this.groundTicks = groundTicks;
    }

    public int getAirTicks() {
        return airTicks;
    }

    public void setAirTicks(int airTicks) {
        this.airTicks = airTicks;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public List<Check> getLoadedChecks() {
        return loadedChecks;
    }

    public void setLoadedChecks(List<Check> loadedChecks) {
        this.loadedChecks = loadedChecks;
    }

    public Cuboid getLastBoundingBox() {
        return lastBoundingBox;
    }

    public void setLastBoundingBox(Cuboid lastBoundingBox) {
        this.lastBoundingBox = lastBoundingBox;
    }

    public Map<Ticker, Integer> getTickerMap() {
        return tickerMap;
    }

    public Player getPlayer() {
        return player;
    }

    public void setAbilities(LoniaAbilities loniaAbilities) {
        this.loniaAbilities = loniaAbilities;
    }

    public long getTransactionPing() {
        return transactionPing;
    }

    public TrackerManager getEntityTrackerManager() {
        return entityTrackerManager;
    }

    public void setTransactionPing(long transactionPing) {
        this.transactionPing = transactionPing;
    }

    public long getLastTransactionPing() {
        return lastTransactionPing;
    }

    public void setLastTransactionPing(long lastTransactionPing) {
        this.lastTransactionPing = lastTransactionPing;
    }

    public boolean isTeleporting() {
        if (tickerMap.getOrDefault(Ticker.TELEPORT, 0) > 0) return true;
        return !teleportList.isEmpty();
    }

    public long getAverageTransactionPing() {
        return averageTransactionPing;
    }

    public void setAverageTransactionPing(long averageTransactionPing) {
        this.averageTransactionPing = averageTransactionPing;
    }

    public ClientVersion getClientVersion() {
        return clientVersion;
    }

    public CustomLocation getLocation() {
        return location;
    }

    public CustomLocation getLastLocation() {
        return lastLocation;
    }

    public LoniaAbilities getLoniaAbilities() {
        return loniaAbilities;
    }

    public PacketInProcessor getPacketInProcessor() {
        return packetInProcessor;
    }

    public PacketOutProcessor getPacketOutProcessor() {
        return packetOutProcessor;
    }
}
