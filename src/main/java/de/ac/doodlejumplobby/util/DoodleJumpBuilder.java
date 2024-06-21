package de.ac.doodlejumplobby.util;
import de.ac.doodlejumplobby.steps.Step;
import de.ac.doodlejumplobby.steps.types.BreakableStep;
import de.ac.doodlejumplobby.steps.types.DefaultStep;
import de.ac.doodlejumplobby.steps.types.FinishStep;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import java.util.Random;

public class DoodleJumpBuilder {
    private final Location doodleJumpLocation;
    private final int size;
    private final World world;
    private final int centerX , centerY , centerZ;

    private final int HEIGHT = 250;

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
        Location stepBefore = new Location(world, 0, 0, 0);

        FinishStep finishStep = new FinishStep();
        Random rnd = new Random();
        Step[] steps = {new DefaultStep(), new BreakableStep()};
        Step step = steps[0];

        for (int y = centerY + 5; y < HEIGHT; y += 5) {
            if (rnd.nextInt(100) <= 65)
                step = steps[0];
            else step = steps[rnd.nextInt(2)];


            int randomX;
            int randomZ;
            //verhindern, dass zwei blöcke direkt übereinander sind
            do {
                randomX = centerX + rnd.nextInt(size - 1) - (size / 3);
                randomZ = centerZ + rnd.nextInt(size - 1) - (size / 3);

            } while (randomX == stepBefore.getBlockX() && randomZ == stepBefore.getBlockZ());
            step.spawn(new Location(world, randomX, y, randomZ));
            stepBefore = new Location(world, randomX, y, randomZ);
            buildSteps();
        }
        //"finish step" spawnen

        finishStep.spawn(new Location(world, stepBefore.getBlockX()==centerX?centerX+1:centerX , stepBefore.getBlockY()+5, centerZ));
        buildSteps();
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
        for (int x = -size / 2; x <= size / 2; x++)
            for (int y = 0; y <= HEIGHT; y++)
                for (int z = -size / 2; z <= size / 2; z++)
                    world.getBlockAt(centerX + x, centerY + y, centerZ + z).setType(Material.AIR);
        Step.steps.clear();
    }

    public Location getDoodleJumpLocation() {
        return doodleJumpLocation;
    }

    public int getSize() {
        return size;
    }
}
