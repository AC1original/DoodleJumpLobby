package de.ac.doodlejumplobby.events;

import de.ac.doodlejumplobby.DoodleJumpLobby;
import de.ac.doodlejumplobby.util.DoodleJumpHelper;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {
    public void onPlayerQuit(PlayerQuitEvent e) {
        DoodleJumpHelper.doodleJumpers.remove(e.getPlayer());
        DoodleJumpLobby.getInstance().getDoodleJumpBuilder().clearSteps();
    }
}
