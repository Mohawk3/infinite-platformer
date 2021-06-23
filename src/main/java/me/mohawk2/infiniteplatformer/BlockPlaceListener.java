package me.mohawk2.infiniteplatformer;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    //Allows the class to access the instance of clickListener so it can use its methods
    ClickListener clickListener;
    BlockPlaceListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    //Gives the block location to the addToQueue method for later removal
    @EventHandler
    public void blockPlacedEvent(BlockPlaceEvent blockPlaceEvent){
        if(blockPlaceEvent.getBlock().getType() == Material.BLUE_TERRACOTTA){
            clickListener.addToQueue(blockPlaceEvent.getBlock().getLocation());
        }
    }
}
