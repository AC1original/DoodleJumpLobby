package de.ac.doodlejumplobby;

import de.ac.doodlejumplobby.commands.ClearStepsCommand;
import de.ac.doodlejumplobby.commands.DeleteDoodleJumpLevel;
import de.ac.doodlejumplobby.commands.DoodleJumpCommand;
import de.ac.doodlejumplobby.events.PlayerLeave;
import de.ac.doodlejumplobby.util.DoodleJumpBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DoodleJumpLobby extends JavaPlugin implements Listener {
    private PluginManager manager = Bukkit.getPluginManager();
    private DoodleJumpBuilder doodleJumpBuilder;
    private static DoodleJumpLobby instance;

    @Override
    public void onEnable() {
        instance = this;
        this.doodleJumpBuilder = new DoodleJumpBuilder(new Location(Bukkit.getWorld("world"), 0, 100, 0), 7);
        doodleJumpBuilder.build();

        getCommand("doodlejump").setExecutor(new DoodleJumpCommand());
        getCommand("deletelevel").setExecutor(new DeleteDoodleJumpLevel());
        getCommand("clearsteps").setExecutor(new ClearStepsCommand());

        manager.registerEvents(new PlayerLeave(), this);
    }

    public static DoodleJumpLobby getInstance() {
        return instance;
    }

    public DoodleJumpBuilder getDoodleJumpBuilder() {
        return doodleJumpBuilder;
    }
}
