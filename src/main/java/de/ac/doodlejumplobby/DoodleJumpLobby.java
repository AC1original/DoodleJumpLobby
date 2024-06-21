package de.ac.doodlejumplobby;

import de.ac.doodlejumplobby.commands.ClearStepsCommand;
import de.ac.doodlejumplobby.commands.DeleteDoodleJumpLevel;
import de.ac.doodlejumplobby.commands.DoodleJumpCommand;
import de.ac.doodlejumplobby.events.PlayerFall;
import de.ac.doodlejumplobby.events.PlayerLeave;
import de.ac.doodlejumplobby.util.DoodleJumpBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
        this.doodleJumpBuilder = new DoodleJumpBuilder(new Location(Bukkit.getWorld("world"), 0, 100, 0), 6);
        doodleJumpBuilder.build();

        getCommand("doodlejump").setExecutor(new DoodleJumpCommand());
        getCommand("deletelevel").setExecutor(new DeleteDoodleJumpLevel());
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
}
