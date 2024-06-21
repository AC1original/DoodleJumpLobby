package de.ac.doodlejumplobby.steps.types;
import de.ac.doodlejumplobby.steps.Step;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MoveableStep extends Step {
    @Override
    public Material getMaterial() {
        return Material.SPONGE;
    }

    @Override
    public boolean updateable() {
        return true;
    }

    @Override
    public void spawn(Location location) {
        super.spawn(location);
    }

    @Override
    public void onJump(Player p, Location blockLoc) {
        super.onJump(p, blockLoc);
        p.sendMessage(String.valueOf(blockLoc));
    }
}
