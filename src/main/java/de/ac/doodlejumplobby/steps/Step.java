package de.ac.doodlejumplobby.steps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Step {
    protected Location location = new Location(Bukkit.getWorld("world"), 0, 0, 0);
    public static final CopyOnWriteArrayList<Step> steps = new CopyOnWriteArrayList<>();

    public abstract Material getMaterial();

    public void spawn(Location location) {
        this.location = location;
        steps.add(this);
    }

    public void onJump(Player p, Location blockLoc) {
        //play sound
    }
    public void onBumpHead(Player p, Location blockLoc) {
        //wird momentan nicht gebraucht
    }

    public Location getLocation() {
        return location;
    }


}
