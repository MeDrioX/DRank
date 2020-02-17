package fr.medriox.drank.listeners;

import fr.medriox.drank.DRank;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

/**
 * Created by MeDrioX on 17/02/2020
 */


public class EventsManager {

    public EventsManager(DRank dRank) {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(dRank), dRank);
        pm.registerEvents(new PlayerQuit(dRank), dRank);
        pm.registerEvents(new PlayerChat(dRank), dRank);
    }
}
