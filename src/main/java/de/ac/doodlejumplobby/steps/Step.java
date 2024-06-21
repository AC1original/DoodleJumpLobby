package de.ac.doodlejumplobby.steps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Step {
    protected Location location = new Location(Bukkit.getWorld("world"), 0, 0, 0);
    public static final CopyOnWriteArrayList<Step> steps = new CopyOnWriteArrayList<>();

    protected boolean soundPlayed = false;
    public abstract Material getMaterial();

    public void spawn(Location location) {
        this.location = location;
        steps.add(this);
    }

    public void onJump(Player p, Location blockLoc) {
            p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 0.2f, 1f);
    }
    public void onBumpHead(Player p, Location blockLoc) {
        //wird momentan nicht gebraucht
    }

    public Location getLocation() {
        return location;
    }
}
