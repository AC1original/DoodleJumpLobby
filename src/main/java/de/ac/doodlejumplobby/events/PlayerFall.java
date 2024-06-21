package de.ac.doodlejumplobby.events;

import de.ac.doodlejumplobby.DoodleJumpLobby;
import org.bukkit.Sound;
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
            DoodleJumpLobby.doodleJumpers.remove(p);
            p.playSound(p.getLocation(), Sound.ANVIL_LAND, 0.5f, 1f);
            if (DoodleJumpLobby.doodleJumpers.isEmpty())
                DoodleJumpLobby.getInstance().getDoodleJumpBuilder().clearSteps();
        }
    }

}
