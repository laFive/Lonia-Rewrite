package me.five.lonia.data.tracker;

import me.five.lonia.data.PlayerData;
import me.five.lonia.packet.server.SPacketEntityMove;
import me.five.lonia.packet.server.SPacketEntityTeleport;
import me.five.lonia.packet.server.SPacketSpawnEntity;
import me.five.lonia.util.VectorLocation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TrackerManager {

    private PlayerData playerData;
    private Map<Integer, EntityTracker> trackerMap;

    public TrackerManager(PlayerData data) {
        this.playerData = data;
        this.trackerMap = new HashMap<>();
    }

    public void handleEntityAdd(SPacketSpawnEntity packet, PlayerData data) {
        trackerMap.put(packet.getEntityId(), new EntityTracker(packet.getEntityId(), new VectorLocation(packet.getX(), packet.getY(), packet.getZ()), data));
    }

    public void handleEntityTeleport(SPacketEntityTeleport packet) {
        if (!trackerMap.containsKey(packet.getEntityId())) return;
        trackerMap.get(packet.getEntityId()).handleTeleport(new VectorLocation(packet.getX(), packet.getY(), packet.getZ()));
    }

    public void handleRelMove(SPacketEntityMove packet) {
        if (!trackerMap.containsKey(packet.getEntityId())) return;
        if (!packet.hasPos()) return;
        trackerMap.get(packet.getEntityId()).relMove(packet.getDistanceX(), packet.getDistanceY(), packet.getDistanceZ());
    }

    public void handleTransaction(short uid) {
        trackerMap.values().forEach(t -> t.handleTransaction(uid));
    }

    public EntityTracker getTracker(int entityId) {
        return trackerMap.getOrDefault(entityId, null);
    }

    public Collection<EntityTracker> getTrackers() {
        return trackerMap.values();
    }

    public void clearTrackers() {
        trackerMap.clear();
    }

}
