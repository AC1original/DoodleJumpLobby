package de.ac.doodlejumplobby.events;

import de.ac.doodlejumplobby.DoodleJumpLobby;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    public void onPlayerQuit(PlayerQuitEvent e) {
        DoodleJumpLobby.doodleJumpers.remove(e.getPlayer());
        if (DoodleJumpLobby.doodleJumpers.isEmpty())
            DoodleJumpLobby.getInstance().getDoodleJumpBuilder().clearSteps();
    }
}
