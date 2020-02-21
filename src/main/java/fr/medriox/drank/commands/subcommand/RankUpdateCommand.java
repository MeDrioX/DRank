package fr.medriox.drank.commands.subcommand;

import fr.medriox.drank.DRank;
import fr.medriox.drank.commands.RankCommand;
import fr.medriox.drank.utils.UpdateChecker;
import fr.medriox.drank.utils.command.ICommand;
import fr.medriox.drank.utils.command.ISubCommand;
import org.bukkit.command.CommandSender;

import static org.bukkit.ChatColor.*;

/**
 * Created by MeDrioX on 20/02/2020
 */


public class RankUpdateCommand extends ISubCommand {


    private DRank dRank;

    public RankUpdateCommand(String command, ICommand iCommand, DRank dRank) {
        super(command, iCommand);
        this.dRank = dRank;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) throws Exception {
        if(sender.hasPermission("drank.notify") || sender.hasPermission("drank.*")){
            UpdateChecker updateChecker = new UpdateChecker(dRank);
            if(updateChecker.isAvailable()){
                sender.sendMessage(GREEN + "[DRank] There is a newer version available: " + updateChecker.getLatestVersion() + ", you're on: " + updateChecker.getPluginVersion());
            }else{
                sender.sendMessage(GREEN + "[DRank] No update available");
            }
        }else{
            sender.sendMessage(RED + "You don't have permission");
        }
    }

}
