package fr.medriox.drank;

import fr.medriox.drank.commands.DRankCommands;
import fr.medriox.drank.listeners.EventsManager;
import fr.medriox.drank.manager.player.PlayerData;
import fr.medriox.drank.manager.rank.Rank;
import fr.medriox.drank.utils.FileManager;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * Created by MeDrioX on 16/02/2020
 */


public class DRank extends JavaPlugin {

    private DRank instance;
    private FileManager fileManager;
    private HashSet<Rank> ranks;
    private HashSet<PlayerData> players;
    private HashMap<UUID, PermissionAttachment> playersPermissions;

    @Override
    public void onEnable() {
        instance = this;
        this.fileManager = new FileManager(this);
        this.ranks = new HashSet<>();
        this.players = new HashSet<>();
        this.playersPermissions = new HashMap<>();
        fileManager.getConfig("config.yml").copyDefaults(true).save();
        fileManager.getConfig("ranks.yml").copyDefaults(true).save();
        fileManager.getConfig("players.yml").copyDefaults(false).save();
        setupRanks();
        updateAll();
        getCommand("drank").setExecutor(new DRankCommands());
        new EventsManager(this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void setupRanks(){
       for(String rank : fileManager.getConfig("ranks.yml").get().getConfigurationSection("Ranks").getKeys(false)){
           String rank_name = rank;
           String prefix = fileManager.getConfig("ranks.yml").get().getConfigurationSection("Ranks." + rank).getString("prefix");
           boolean defaultRank = fileManager.getConfig("ranks.yml").get().getConfigurationSection("Ranks." + rank).getBoolean("default");
           String suffix = fileManager.getConfig("ranks.yml").get().getConfigurationSection("Ranks." + rank).getString("suffix");
           List<String> permissionsList = fileManager.getConfig("ranks.yml").get().getConfigurationSection("Ranks." + rank).getStringList("permissions");
           Rank rank1 = new Rank(rank_name, prefix, defaultRank, suffix);
           rank1.setPermissions((ArrayList<String>) permissionsList);
           this.getRanks().add(rank1);
       }
    }

    public HashSet<Rank> getRanks() {
        return ranks;
    }

    public HashSet<PlayerData> getPlayers() {
        return players;
    }

    public HashMap<UUID, PermissionAttachment> getPlayersPermissions() {
        return playersPermissions;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void updateAll(){
        getServer().getScheduler().runTaskTimer(this, () -> {
            this.getFileManager().getConfig("ranks.yml").reload();
            this.getFileManager().getConfig("config.yml").reload();
            this.getFileManager().getConfig("players.yml").reload();
            this.setupRanks();
        }, 0, 20*60*5);
    }

    public DRank getInstance() {
        return instance;
    }
}
