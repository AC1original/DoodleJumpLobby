package de.ac.doodlejumplobby.commands;
import de.ac.doodlejumplobby.DoodleJumpLobby;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import java.util.Collections;
import java.util.List;

public class DoodleJumpCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if (DoodleJumpLobby.doodleJumpers.isEmpty()) {
            DoodleJumpLobby.getInstance().getDoodleJumpBuilder().clearSteps();
            DoodleJumpLobby.getInstance().getDoodleJumpBuilder().createSteps();
        }

        Location loc = DoodleJumpLobby.getInstance().getDoodleJumpBuilder().getDoodleJumpLocation();
        p.teleport(new Location(loc.getWorld(),loc.getX(), loc.getY()+1, loc.getBlockZ()));
        DoodleJumpLobby.doodleJumpers.add(p);
        DoodleJumpLobby.getInstance().startJump(p);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return Collections.emptyList();
    }

}
