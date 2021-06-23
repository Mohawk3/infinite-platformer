package me.mohawk2.infiniteplatformer;

import jdk.nashorn.internal.ir.Block;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockPlaceRunnable extends BukkitRunnable {

    private Plugin myPlugin;

    BlockPlaceRunnable(Plugin myPlugin) {
        this.myPlugin = myPlugin;
    }

    Queue<Location> blocksToPlace = new ArrayBlockingQueue<Location>(1000);

    //Gets the clickListener
    static ClickListener clickListener;

    public static void awareOfClickListener(ClickListener clickListener) {
        BlockPlaceRunnable.clickListener = clickListener;
    }

    @Override
    public void run() {
        blocksToPlace = clickListener.getQueue();
        for (Location location : blocksToPlace) {
            location.getBlock().setType(Material.AIR);
            blocksToPlace.remove();
        }
        this.cancel();
    }
}
