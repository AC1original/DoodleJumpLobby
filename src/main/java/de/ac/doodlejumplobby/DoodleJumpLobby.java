package de.ac.doodlejumplobby;
import de.ac.doodlejumplobby.commands.ClearStepsCommand;
import de.ac.doodlejumplobby.commands.DeleteDoodleJumpLevelCommand;
import de.ac.doodlejumplobby.commands.DoodleJumpCommand;
import de.ac.doodlejumplobby.events.PlayerFall;
import de.ac.doodlejumplobby.events.PlayerLeave;
import de.ac.doodlejumplobby.steps.Step;
import de.ac.doodlejumplobby.util.DoodleJumpBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public final class DoodleJumpLobby extends JavaPlugin implements Listener {
    public static final List<Player> doodleJumpers = new ArrayList<>();
    private final PluginManager manager = Bukkit.getPluginManager();
    private DoodleJumpBuilder doodleJumpBuilder;
    private static DoodleJumpLobby instance;

    @Override
    public void onEnable() {
        instance = this;
        this.doodleJumpBuilder = new DoodleJumpBuilder(new Location(Bukkit.getWorld("world"), 300, 50, 0), 6);
        doodleJumpBuilder.build();

        getCommand("doodlejump").setExecutor(new DoodleJumpCommand());
        getCommand("deletelevel").setExecutor(new DeleteDoodleJumpLevelCommand());
        getCommand("clearsteps").setExecutor(new ClearStepsCommand());

        manager.registerEvents(new PlayerLeave(), this);
        manager.registerEvents(new PlayerFall(), this);
    }

    public static DoodleJumpLobby getInstance() {
        return instance;
    }

    public DoodleJumpBuilder getDoodleJumpBuilder() {
        return doodleJumpBuilder;
    }

    public void startJump(Player p) {
        getServer().getScheduler().scheduleSyncRepeatingTask(DoodleJumpLobby.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (DoodleJumpLobby.doodleJumpers.contains(p) && p.getVelocity().getY()<=0 && p.isOnGround()) {
                    //überprüfen ob der spiler auf einer "stufe" bzw step steht
                    Step.steps.forEach(step -> {
                        if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(step.getMaterial()))
                            step.onJump(p, p.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                    });
                    boost(p, 1.2);
                }
                //überprüfen ob der spieler sich den "kopf anschlägt"
                Step.steps.forEach(step -> {
                    if (p.getLocation().getBlock().getRelative(BlockFace.UP).getType().equals(step.getMaterial()))
                        step.onJump(p, p.getLocation().getBlock().getRelative(BlockFace.UP).getLocation());
                });
                if (p.getLocation().getBlock().getRelative(BlockFace.SELF).getType().equals(Material.SLIME_BLOCK))
                    p.sendMessage("slime");
            }
        }, 1, 1);
    }

    public void boost(Player p, double strength) {
        p.setVelocity(new Vector(0, strength, 0));
    }
}
