package de.ac.doodlejumplobby.commands;

import de.ac.doodlejumplobby.DoodleJumpLobby;
import de.ac.doodlejumplobby.steps.Step;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
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
        if (DoodleJumpLobby.doodleJumpers.isEmpty()) {
            DoodleJumpLobby.getInstance().getDoodleJumpBuilder().clearSteps();
            DoodleJumpLobby.getInstance().getDoodleJumpBuilder().createSteps();
        }

        Location loc = DoodleJumpLobby.getInstance().getDoodleJumpBuilder().getDoodleJumpLocation();
        p.teleport(new Location(loc.getWorld(),loc.getX(), loc.getY()+1, loc.getBlockZ()));
        DoodleJumpLobby.doodleJumpers.add(p);
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
                if (DoodleJumpLobby.doodleJumpers.isEmpty()) DoodleJumpLobby.getInstance().getDoodleJumpBuilder().clearSteps();
                if (DoodleJumpLobby.doodleJumpers.contains(p) && p.getVelocity().getY()<=0 && p.isOnGround()) {

                    //überprüfen ob der spiler auf einer "stufe" bzw step steht
                    Step.steps.forEach(step -> {
                        if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(step.getMaterial()))
                            step.onJump();
                    });

                    p.setVelocity(new Vector(0, 1.2, 0));
                }
            }
        }, 1, 1);
    }
}
