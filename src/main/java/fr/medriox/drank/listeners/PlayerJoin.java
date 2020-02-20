package fr.medriox.drank.listeners;


import fr.medriox.drank.DRank;
import fr.medriox.drank.manager.permissions.PermissionsManager;
import fr.medriox.drank.manager.player.PlayerData;
import fr.medriox.drank.manager.rank.Rank;
import fr.medriox.drank.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.ChatColor.GREEN;

/**
 * Created by MeDrioX on 16/02/2020
 */


public class PlayerJoin implements Listener {

    private final DRank dRank;

    public PlayerJoin(DRank dRank) {
        this.dRank = dRank;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        Bukkit.getScheduler().runTaskLater(dRank, () -> {
            if(player.hasPermission("drank.notify") || player.hasPermission("drank.*")){
                UpdateChecker updateChecker = new UpdateChecker(dRank);
                if(updateChecker.isAvailable()){
                    player.sendMessage(GREEN + "[DRank] There is a newer version available: " + updateChecker.getLatestVersion() + ", you're on: " + updateChecker.getPluginVersion());
                }else{
                    player.sendMessage(GREEN + "[DRank] No update available");
                }
            }
        }, 5 * 20L);

        dRank.getFileManager().getConfig("players.yml").reload();
        if(dRank.getFileManager().getConfig("players.yml").get(player.getUniqueId().toString()) == null){
            Rank rank = dRank.getRanks().stream().filter(Rank::isRankDefault).findFirst().get();
            dRank.getFileManager().getConfig("players.yml").get().set(player.getUniqueId().toString() + ".name", player.getName());
            dRank.getFileManager().getConfig("players.yml").get().set(player.getUniqueId().toString() + ".rank", rank.getName());
            dRank.getFileManager().getConfig("players.yml").save();
            PlayerData dPlayer = new PlayerData(player.getUniqueId(), rank);
            dRank.getPlayers().add(dPlayer);
            new PermissionsManager(dRank).setupPermission(player);
        }else{
            String rankName = dRank.getFileManager().getConfig("players.yml").get().getConfigurationSection(player.getUniqueId().toString()).getString("rank");
            Rank rank = dRank.getRanks().stream().filter(rank1 -> rank1.getName().equals(rankName)).findFirst().get();
            PlayerData dPlayer = new PlayerData(player.getUniqueId(), rank);
            dRank.getPlayers().add(dPlayer);
            new PermissionsManager(dRank).setupPermission(player);
        }
    }
}
