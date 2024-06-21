package de.ac.doodlejumplobby.events;

import de.ac.doodlejumplobby.DoodleJumpLobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerFall implements Listener {
    @EventHandler
    public void playerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (DoodleJumpLobby.doodleJumpers.contains(p) && p.getFallDistance() >= 9 && !p.isFlying()) {
            p.sendMessage("loosed!");
            DoodleJumpLobby.getInstance().getDoodleJumpBuilder().clearSteps();
            DoodleJumpLobby.doodleJumpers.remove(p);

        }
    }

}
