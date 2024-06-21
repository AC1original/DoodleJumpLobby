package de.ac.doodlejumplobby.steps;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public abstract class Step {
    private Location location;
    public static final List<Step> steps = new ArrayList<>();
    public abstract Material getMaterial();
    public abstract boolean moveable();

    public final void spawn(Location location) {
        this.location = location;
        steps.add(this);
    }

    public void onJump() {

    }

    public Location getLocation() {
        return location;
    }
}
