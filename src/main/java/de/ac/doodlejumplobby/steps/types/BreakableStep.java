package de.ac.doodlejumplobby.steps.types;

import de.ac.doodlejumplobby.steps.Step;
import org.bukkit.Material;

public class BreakableStep extends Step {
    @Override
    public Material getMaterial() {
        return Material.COBBLESTONE;
    }

    @Override
    public boolean moveable() {
        return false;
    }
}
