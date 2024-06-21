package de.ac.doodlejumplobby.commands;

import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player p = (Player) sender;
        p.sendMessage(String.valueOf(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation()));
        return false;
    }
}
