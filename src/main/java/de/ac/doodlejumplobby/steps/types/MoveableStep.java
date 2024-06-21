package de.ac.doodlejumplobby.steps.types;

import de.ac.doodlejumplobby.steps.Step;
import org.bukkit.Material;

public class MoveableStep extends Step {
    @Override
    public Material getMaterial() {
        return Material.SPONGE;
    }

    @Override
    public boolean moveable() {
        return true;
    }

}
