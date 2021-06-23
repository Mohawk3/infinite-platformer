package me.mohawk2.infiniteplatformer;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

import java.util.Collection;

public class RespawnListener implements Listener {
    private Plugin myPlugin;
    RespawnListener(Plugin myPlugin) {
        this.myPlugin = myPlugin;
    }
    @EventHandler
    public void deathListener(PlayerRespawnEvent playerRespawn){
        //Teleports players to the play area
        Collection<? extends Player> players = playerRespawn.getPlayer().getServer().getOnlinePlayers();

        Location location = new Location(playerRespawn.getPlayer().getWorld(), 5.5, 100, 5.5);
        Player[] players1 = new Player[players.size()];
        players1 = players.toArray(players1);
        //Makes the dying player respawn at the play area
        playerRespawn.setRespawnLocation(location);
        //Makes all other players respawn at the play area
        for(int i = 0; i < players1.length; i++) {
            players1[i].teleport(location);
        }

        //Calls the method to reset the play area
        //BlockPlaceRunnable blockPlaceRunnable = new BlockPlaceRunnable(myPlugin);
        BlockPlaceRunnable blockPlaceRunnable = new BlockPlaceRunnable(myPlugin);
        blockPlaceRunnable.runTask(myPlugin);
    }
}
