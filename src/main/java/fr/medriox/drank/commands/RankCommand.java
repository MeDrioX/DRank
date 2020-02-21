package fr.medriox.drank.commands;

import fr.medriox.drank.DRank;
import fr.medriox.drank.commands.subcommand.RankUpdateCommand;
import fr.medriox.drank.commands.subcommand.group.RankGroupListCommand;
import fr.medriox.drank.commands.subcommand.user.RankUserAddCommand;
import fr.medriox.drank.commands.subcommand.user.RankUserRemoveCommand;
import fr.medriox.drank.utils.command.ICommand;
import org.bukkit.command.CommandSender;

import static org.bukkit.ChatColor.*;

/**
 * Created by MeDrioX on 18/02/2020
 */


public class RankCommand extends ICommand {

    private DRank dRank;

    public RankCommand(DRank dRank) {
        super("drank");
        this.addISubCommand(new RankUserAddCommand("add", this, dRank));
        this.addISubCommand(new RankUserRemoveCommand("remove", this, dRank));
        this.addISubCommand(new RankGroupListCommand("list", this, dRank));
        this.addISubCommand(new RankUpdateCommand("update", this, dRank));
        this.dRank = dRank;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) throws Exception {
        sender.sendMessage(GREEN + "DRank " + GOLD + dRank.getDescription().getVersion());
        if(sender.hasPermission("drank.help") || sender.hasPermission("drank.*")){
            sender.sendMessage(YELLOW + "/drank add <player> <rank>" + GRAY + " - " + GREEN + "Add a player to a group");
            sender.sendMessage(YELLOW + "/drank remove <player>" + GRAY + " - " + GREEN + "Give the default rank to a player");
            sender.sendMessage(YELLOW + "/drank list" + GRAY + " - " + GREEN + "See the list of ranks");
            sender.sendMessage(YELLOW + "/drank update" + GRAY + " - " + GREEN + "Check if an update is available");
        }
    }
}
