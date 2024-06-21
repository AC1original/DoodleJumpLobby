package de.ac.doodlejumplobby.commands;

import de.ac.doodlejumplobby.DoodleJumpLobby;
import de.ac.doodlejumplobby.steps.Step;
import de.ac.doodlejumplobby.steps.types.DefaultStep;
import de.ac.doodlejumplobby.util.DoodleJumpBuilder;
import de.ac.doodlejumplobby.util.DoodleJumpHelper;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.List;

public class DoodleJumpCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if (DoodleJumpHelper.doodleJumpers.isEmpty()) {
            DoodleJumpLobby.getInstance().getDoodleJumpBuilder().clearSteps();
            DoodleJumpLobby.getInstance().getDoodleJumpBuilder().createSteps();
        }

        Location loc = DoodleJumpLobby.getInstance().getDoodleJumpBuilder().getDoodleJumpLocation();
        p.teleport(new Location(loc.getWorld(),loc.getX(), loc.getY()+1, loc.getBlockZ()));
        DoodleJumpHelper.doodleJumpers.add(p);
        jump(p);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return Collections.emptyList();
    }

    public void jump(Player p) {
        DoodleJumpLobby.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(DoodleJumpLobby.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (DoodleJumpHelper.doodleJumpers.isEmpty()) DoodleJumpLobby.getInstance().getDoodleJumpBuilder().clearSteps();
                if (DoodleJumpHelper.doodleJumpers.contains(p) && p.getVelocity().getY()<=0 && p.isOnGround())
                    p.setVelocity(new Vector(0, 1.2, 0));
            }
        }, 1, 1);
    }
}
