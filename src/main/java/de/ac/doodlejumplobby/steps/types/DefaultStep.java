package de.ac.doodlejumplobby.steps.types;
import de.ac.doodlejumplobby.steps.Step;
import org.bukkit.Material;

public class DefaultStep extends Step {
    @Override
    public Material getMaterial() {
        return Material.QUARTZ_BLOCK;
    }

    @Override
    public boolean updateable() {
        return false;
    }


}
