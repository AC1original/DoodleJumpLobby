package de.ac.doodlejumplobby.commands;
import de.ac.doodlejumplobby.DoodleJumpLobby;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import java.util.Collections;
import java.util.List;

public class DoodleJumpCommand implements CommandExecutor, TabCompleter {
    private final DoodleJumpLobby plugin = DoodleJumpLobby.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if (DoodleJumpLobby.doodleJumpers.isEmpty()) {
            plugin.getDoodleJumpBuilder().clearSteps();
            plugin.getDoodleJumpBuilder().createSteps();
        }
        Location loc = plugin.getDoodleJumpBuilder().getDoodleJumpLocation();

        //spieler zentrieren
        p.teleport(new Location(loc.getWorld(),loc.getX()+0.5, loc.getY()+1, loc.getZ()+0.5));

        DoodleJumpLobby.doodleJumpers.add(p);
        plugin.startJump(p);
        p.playSound(p.getLocation(), Sound.CLICK, 1, 1f);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return Collections.emptyList();
    }

}
