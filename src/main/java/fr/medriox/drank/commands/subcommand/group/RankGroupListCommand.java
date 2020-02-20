package fr.medriox.drank.commands.subcommand.group;

import fr.medriox.drank.DRank;
import fr.medriox.drank.commands.RankCommand;
import fr.medriox.drank.utils.command.ICommand;
import fr.medriox.drank.utils.command.ISubCommand;
import org.bukkit.command.CommandSender;

import java.util.Objects;

import static org.bukkit.ChatColor.*;

/**
 * Created by MeDrioX on 20/02/2020
 */


public class RankGroupListCommand extends ISubCommand {

    private DRank dRank;

    public RankGroupListCommand(String command, ICommand iCommand, DRank dRank) {
        super(command, iCommand);
        this.dRank = dRank;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) throws Exception {
        if(sender.hasPermission("drank.group.list") || sender.hasPermission("drank.*")){
            sender.sendMessage("§eRank list:");
            dRank.getRanks().stream().filter(Objects::nonNull).forEachOrdered(rank -> sender.sendMessage(GRAY + "- " + GREEN + rank.getName()));
        }
    }
}
