package me.mohawk2.infiniteplatformer;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TeamCommands implements CommandExecutor {

    //Command that sets a player's team
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.getInventory().clear();
            player.setHealth(20);
            //Thanks stelar7 for this code which I do not understand
            for (PotionEffect effect : player.getActivePotionEffects())
                player.removePotionEffect(effect.getType());

            switch (args[0]) {
                case "runner":
                    Material[] runnerKitMaterials = {Material.FEATHER, Material.STONE_PICKAXE, Material.BLUE_TERRACOTTA,Material.COOKED_BEEF};
                    int[] itemCounts = {1, 1, 16, 16};
                    giveItems(player, runnerKitMaterials, itemCounts);

                    PotionEffectType[] runnerKitEffects = {PotionEffectType.SPEED, PotionEffectType.JUMP};
                    int[] runnerEffectStrengths = {0, 0};
                    setEffects(player, runnerKitEffects, runnerEffectStrengths);
                    return true;

                case "hunter":
                    Material[] hunterKitMaterials = {Material.STONE_SWORD, Material.IRON_PICKAXE, Material.BLUE_TERRACOTTA, Material.COOKED_BEEF};
                    int[] hunterItemCounts = {1, 1, 64, 16};
                    giveItems(player, hunterKitMaterials, hunterItemCounts);
                    return true;

                case "leave":
                    player.getInventory().clear();
                    return true;

                default:
                    return false;
            }
        } else
            sender.getServer().getConsoleSender().sendMessage("This command can only be run by players");
        return false;
    }

    private void giveItems(Player givenPlayer, Material[] kitMaterials, int[] itemCounts) {
        for (int i = 0; i < kitMaterials.length; i++) {
            ItemStack kitItems = new ItemStack(kitMaterials[i], itemCounts[i]);
            givenPlayer.getInventory().addItem(kitItems);
        }
    }
    private void setEffects(Player affectedPlayer, PotionEffectType[] potionEffectTypes, int[] effectStrengths){
        for (int i = 0; i < potionEffectTypes.length; i++) {
            PotionEffect potionEffect = new PotionEffect(potionEffectTypes[i], Integer.MAX_VALUE, effectStrengths[i]);
            affectedPlayer.addPotionEffect(potionEffect);
        }
    }
}


