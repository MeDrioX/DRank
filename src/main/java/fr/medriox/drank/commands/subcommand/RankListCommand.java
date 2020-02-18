package fr.medriox.drank.commands.subcommand;

import fr.medriox.drank.DRank;
import fr.medriox.drank.manager.rank.Rank;
import fr.medriox.drank.utils.command.ICommand;
import fr.medriox.drank.utils.command.ISubCommand;
import org.bukkit.command.CommandSender;

/**
 * Created by MeDrioX on 18/02/2020
 */


public class RankListCommand extends ISubCommand {

    public RankListCommand(String command, ICommand iCommand) {
        super(command, iCommand);
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) throws Exception {
        if(sender.hasPermission("drank.list") || sender.hasPermission("drank.*")){
            DRank.getInstance().getRanks().forEach(rank -> sender.sendMessage(rank.getName()));
        }
    }
}