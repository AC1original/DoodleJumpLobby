package de.ac.doodlejumplobby.steps.types;

import de.ac.doodlejumplobby.steps.Step;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class BreakableStep extends Step {

    @Override
    public Material getMaterial() {
        return Material.COBBLESTONE;
    }

    @Override
    public void onJump(Player p, Location blockLoc) {
        p.playSound(p.getLocation(), Sound.ZOMBIE_WOODBREAK, 0.5f, 1f);
        p.getLocation().getBlock().getRelative(BlockFace.DOWN).setType(Material.AIR);
        steps.remove(this);
    }
}
