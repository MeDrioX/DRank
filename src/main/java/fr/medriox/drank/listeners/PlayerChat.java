package fr.medriox.drank.listeners;

import fr.medriox.drank.DRank;
import fr.medriox.drank.manager.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by MeDrioX on 17/02/2020
 */


public class PlayerChat implements Listener {

    private final DRank dRank;

    public PlayerChat(DRank dRank) {
        this.dRank = dRank;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        String formatChat = null;

        PlayerData dPlayer = dRank.getPlayers().stream().filter(playerData -> playerData.getUUID().equals(player.getUniqueId())).findFirst().get();

        formatChat = dRank.getFileManager().getConfig("config.yml").get().getConfigurationSection("chat").getString("format").replace("%rank%", dPlayer.getRank().getPrefix()).replace("%player%", player.getDisplayName()).replace("%message%", e.getMessage());

        e.setFormat(formatChat.replace("&", "§"));
    }

}
