package de.ac.doodlejumplobby.steps.types;

import de.ac.doodlejumplobby.steps.Step;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class BreakableStep extends Step {
    @Override
    public Material getMaterial() {
        return Material.COBBLESTONE;
    }

    @Override
    public boolean updateable() {
        return false;
    }

    @Override
    public void onJump(Player p, Location blockLoc) {
        p.getLocation().getBlock().getRelative(BlockFace.DOWN).setType(Material.AIR);
        Step.steps.remove(this);
    }
}
