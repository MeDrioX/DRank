package fr.medriox.drank.listeners;

import fr.medriox.drank.DRank;
import fr.medriox.drank.manager.permissions.PermissionsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by MeDrioX on 17/02/2020
 */


public class PlayerQuit implements Listener {

    private final DRank dRank;

    public PlayerQuit(DRank dRank) {
        this.dRank = dRank;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        dRank.getPlayers().remove(dRank.getPlayers().stream().filter(playerData -> playerData.getUUID().equals(player.getUniqueId())).findFirst().get());
        new PermissionsManager(dRank).removePermissions(player);
    }

}
