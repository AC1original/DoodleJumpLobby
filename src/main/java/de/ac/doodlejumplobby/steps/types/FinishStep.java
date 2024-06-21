package de.ac.doodlejumplobby.steps.types;

import de.ac.doodlejumplobby.DoodleJumpLobby;
import de.ac.doodlejumplobby.steps.Step;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class FinishStep extends Step {
    @Override
    public Material getMaterial() {
        return Material.GOLD_BLOCK;
    }

    @Override
    public void onJump(Player p, Location blockLoc) {
        DoodleJumpLobby.doodleJumpers.remove(p);
        if (DoodleJumpLobby.doodleJumpers.isEmpty())
            DoodleJumpLobby.getInstance().getDoodleJumpBuilder().clearSteps();
        p.sendMessage("Jump finished!");

    }
}
