package de.ac.doodlejumplobby.steps.types;

import de.ac.doodlejumplobby.steps.Step;
import org.bukkit.Material;

public class BoostStep extends Step {
    @Override
    public Material getMaterial() {
        return Material.LAPIS_BLOCK;
    }

    @Override
    public boolean moveable() {
        return false;
    }
}
