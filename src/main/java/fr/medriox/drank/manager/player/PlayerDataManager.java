package fr.medriox.drank.manager.player;

import fr.medriox.drank.DRank;
import fr.medriox.drank.manager.rank.Rank;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

import static org.bukkit.ChatColor.*;

/**
 * Created by MeDrioX on 19/02/2020
 */


public class PlayerDataManager {

    private DRank dRank;
    private PlayerData playerData;
    private Player sender;
    private Player target;

    public PlayerDataManager(DRank dRank, Player sender, Player target) {
        this.dRank = dRank;
        this.playerData = dRank.getPlayers().stream().filter(playerData1 -> playerData1.getUUID().equals(target.getUniqueId())).findFirst().get();
        this.sender = sender;
        this.target = target;
    }

    public void setRank(String rankName){
        Rank rank = dRank.getRanks().stream().filter(rank1 -> rank1.getName().equals(rankName)).findFirst().orElse(null);

        if(rank != null){
            if(!getPlayerData().getRank().getName().equals(rank.getName())){
                getPlayerData().setRank(rank);
                sender.sendMessage(GREEN + target.getName() + " is now " + rank.getName());
                target.sendMessage(GREEN + "You are now " + rank.getName());
                dRank.getFileManager().getConfig("players.yml").set(target.getUniqueId().toString() + ".rank", rank.getName()).save();
            }else{
                sender.sendMessage(RED + "This player already has " + rank.getName() + " rank");
            }
        }else{
            sender.sendMessage(RED + "This rank does not exist.");
            if(sender.hasPermission("drank.group.list") || sender.hasPermission("drank.*")){
                sender.sendMessage("§eRank list:");
                dRank.getRanks().stream().filter(Objects::nonNull).forEachOrdered(rankl -> sender.sendMessage(GRAY + "- " + GREEN + rankl.getName()));
            }
        }
    }

    public void removeRank(){
        Rank rank = dRank.getRanks().stream().filter(Rank::isRankDefault).findFirst().get();
        if(rank != null){
            setRank(rank.getName());
        }else{
            sender.sendMessage(RED + "Unable to find default rank");
        }
    }

    public PlayerData getPlayerData() {
        return playerData;
    }
}