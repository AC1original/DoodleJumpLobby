package de.ac.doodlejumplobby.util;
import de.ac.doodlejumplobby.steps.Step;
import de.ac.doodlejumplobby.steps.types.DefaultStep;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Random;

public class DoodleJumpBuilder {
    private final Location doodleJumpLocation;
    private final int size;
    private final World world;
    private final int centerX , centerY , centerZ;

    private final int HEIGHT = 500;
    private final Random rnd = new Random();

    public DoodleJumpBuilder(Location doodleJumpLocation, int size) {
        this.doodleJumpLocation = doodleJumpLocation;
        this.size = size;
        this.world = doodleJumpLocation.getWorld();
        this.centerX = doodleJumpLocation.getBlockX();
        this.centerY = doodleJumpLocation.getBlockY();
        this.centerZ = doodleJumpLocation.getBlockZ();
    }

    public void build() {

        //build floor
        for (int x = -size / 2; x <= size / 2; x++)
            for (int z = -size / 2; z <= size / 2; z++)
                world.getBlockAt(centerX + x, centerY, centerZ + z).setType(Material.GRASS);

        //build walls
        for (int x = -size / 2; x <= size / 2; x++)
            for (int y = 0; y < HEIGHT; y++) {
                world.getBlockAt(centerX + x, centerY + y, centerZ - size / 2).setType(Material.BARRIER);
                world.getBlockAt(centerX + x, centerY + y, centerZ + size / 2).setType(Material.BARRIER);
            }
        for (int z = -size / 2; z <= size / 2; z++)
            for (int y = 0; y < HEIGHT; y++) {
                world.getBlockAt(centerX - size / 2, centerY + y, centerZ + z).setType(Material.BARRIER);
                world.getBlockAt(centerX + size / 2, centerY + y, centerZ + z).setType(Material.BARRIER);
            }

        clearSteps();
    }

    public void createSteps() {
        final DefaultStep defaultStep = new DefaultStep();
        for (int y = centerY + 5; y < centerY + HEIGHT; y += 5) {
            int randomX = centerX + rnd.nextInt(size - 2) - (size / 3);
            int randomZ = centerZ + rnd.nextInt(size - 2) - (size / 3);
            defaultStep.spawn(new Location(world, randomX, y, randomZ));
            buildSteps();
        }
    }

    private void buildSteps() {
        Step.steps.forEach(step -> {
            world.getBlockAt(step.getLocation().getBlockX(), step.getLocation().getBlockY(), step.getLocation().getBlockZ()).setType(step.getMaterial());
        });
    }

    public void clearSteps() {
        for (int x = -size / 2 + 1; x < size / 2; x++)
            for (int y = 1; y < HEIGHT; y++)
                for (int z = -size / 2 + 1; z < size / 2; z++)
                    world.getBlockAt(centerX + x, centerY + y, centerZ + z).setType(Material.AIR);
        Step.steps.clear();
    }

    public void deleteLevel() {
        for (int x = -size / 2; x <= size / 2; x++) {
            for (int y = 0; y <= HEIGHT; y++) {
                for (int z = -size / 2; z <= size / 2; z++) {
                    world.getBlockAt(centerX + x, centerY + y, centerZ + z).setType(Material.AIR);
                }
            }
        }
    }



    public Location getDoodleJumpLocation() {
        return doodleJumpLocation;
    }

    public int getSize() {
        return size;
    }
}