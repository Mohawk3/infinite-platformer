package me.mohawk2.infiniteplatformer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ClickListener implements Listener {
    // Gives me an instance of the plugin
    // Used so I can edit the plugin's config without being in the main class
    Plugin myPlugin;

    ClickListener(Plugin myPlugin) {
        this.myPlugin = myPlugin;
        BlockPlaceRunnable.awareOfClickListener(this);
    }

    @EventHandler
    //Checks if the clicked item is a feather, runs the spawnPlatform method
    public void featherClick(PlayerInteractEvent itemClick) {
        try {
            ItemStack clickedItem = itemClick.getItem();
            if (Objects.requireNonNull(clickedItem).getType() == Material.FEATHER) {
                spawnPlatform(itemClick);
                spawnPlatform(itemClick);
            }
        } catch (Exception ignored) {}
    }

    public static Queue<Location> blocksToPlace = new ArrayBlockingQueue<Location>(1000);

    //Spawns the platform
    private void spawnPlatform(PlayerInteractEvent itemClick) {
        //Calculates where to put the platform (offset from player)

        int[] maxOffset = {4, 2, 4};
        int[] offsetCoords = getOffsetCoords(maxOffset);
        Location location = itemClick.getPlayer().getLocation().add(offsetCoords[0], offsetCoords[1], offsetCoords[2]);

        if (location.getBlock().getType() != Material.DIAMOND_BLOCK) {

            //Adds the placed blocks to the queue for removal upon respawn
            blocksToPlace.add(location.clone());

            //Determines which block to place
            int max = 100;
            int min = 0;
            int randomNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);
            if (randomNumber <= 2) {
                location.getBlock().setType(Material.MAGMA_BLOCK);
            } else if (randomNumber <= 16) {
                location.getBlock().setType(Material.ICE);
            } else {
                location.getBlock().setType(Material.BLUE_TERRACOTTA);
            }
        }
    }
    //Calculates random numbers for each of the coordinate offsets
    int[] getOffsetCoords(int[] xyzMaxOffset){
        int[] xyzCalculatedOffset = new int[3];
        do {
            for (int i = 0; i <= 2; i++) {
                int min = 0;
                int max = xyzMaxOffset[i];
                xyzCalculatedOffset[i] = (int) Math.floor(Math.random() * (max - min + 1) + min);
            }
            xyzCalculatedOffset[1] = xyzCalculatedOffset[1] - 1;
        }
        while (xyzCalculatedOffset[0] == 0 && xyzCalculatedOffset[2] == 0);
        return xyzCalculatedOffset;
    }
    //Gets the queue
    public Queue<Location> getQueue(){
        return blocksToPlace;
    }

    //Adds to the queue
    public void addToQueue(Location location){
        blocksToPlace.add(location);
    }
}
