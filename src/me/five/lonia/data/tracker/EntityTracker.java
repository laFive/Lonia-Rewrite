package me.five.lonia.data.tracker;

import me.five.lonia.Lonia;
import me.five.lonia.data.PlayerData;
import me.five.lonia.util.VectorLocation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.*;

public class EntityTracker {

    private int entityId;
    private Entity entity;
    private PlayerData data;
    private VectorLocation serverLocation;
    private List<VectorLocation> clientLocations;
    private Map<Short, VectorLocation> pendingLocations;

    public EntityTracker(int entityId, VectorLocation currentLocation, PlayerData data) {
        this.clientLocations = new ArrayList<>();
        this.pendingLocations = new HashMap<>();
        this.entityId = entityId;
        Bukkit.getScheduler().runTask(Lonia.getInstance(), new Runnable() {
            @Override
            public void run() {
                EntityTracker.this.entity = data.getPlayer().getWorld().getEntities().stream().filter(e -> e.getEntityId() == entityId).findFirst().orElse(null);
            }
        });
        this.serverLocation = currentLocation;
        while (clientLocations.size() < 20) {
            clientLocations.add(currentLocation);
        }
        this.data = data;
    }

    public void handleTeleport(VectorLocation vecLoc) {

        pendingLocations.put(data.getTickNumber(), vecLoc);
        serverLocation = vecLoc;

    }

    public void relMove(double x, double y, double z) {

        VectorLocation newLoc = new VectorLocation(serverLocation.getX() + x, serverLocation.getY() + y, serverLocation.getZ() + z);
        serverLocation = newLoc;
        pendingLocations.put(data.getTickNumber(), newLoc);

    }

    public void handleTransaction(short uid) {

        Iterator<Map.Entry<Short, VectorLocation>> pendingIterator = pendingLocations.entrySet().iterator();
        while (pendingIterator.hasNext()) {
            Map.Entry<Short, VectorLocation> e = pendingIterator.next();
            if (e.getKey() != uid) return;
            clientLocations.add(e.getValue());
            pendingIterator.remove();
            clientLocations.remove(0);
        }

    }

    public Collection<VectorLocation> getRecentLocations(int amount) {

        List<VectorLocation> locList = new ArrayList<>();
        if (clientLocations.size() < 20) return locList;
        for (int i = clientLocations.size() - ++amount; i < clientLocations.size(); i++) {
            locList.add(clientLocations.get(i));
        }
        return locList;

    }

}
