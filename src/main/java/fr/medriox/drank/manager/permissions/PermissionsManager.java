package fr.medriox.drank.manager.permissions;

import fr.medriox.drank.DRank;
import fr.medriox.drank.manager.player.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.UUID;

/**
 * Created by MeDrioX on 16/02/2020
 */


public class PermissionsManager {

    private final DRank dRank;

    public PermissionsManager(DRank dRank) {
        this.dRank = dRank;
    }

    public void setupPermission(Player player){
        PermissionAttachment attachment = player.addAttachment(dRank);
        dRank.getPlayersPermissions().put(player.getUniqueId(), attachment);
        addPermissions(player.getUniqueId());
    }

    public void addPermissions(UUID uuid){
        PermissionAttachment attachment = dRank.getPlayersPermissions().get(uuid);
        PlayerData dPlayer = dRank.getPlayers().stream().filter(playerData -> playerData.getUUID().equals(uuid)).findFirst().get();
        for(String permissions : dPlayer.getRank().getPermissions()){
            attachment.setPermission(permissions, true);
        }
    }

    public void removePermissions(Player player){
        dRank.getPlayersPermissions().remove(player.getUniqueId());
    }
}
