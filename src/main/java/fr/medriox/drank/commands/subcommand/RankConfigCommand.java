package fr.medriox.drank.commands.subcommand;

import fr.medriox.drank.utils.command.ICommand;
import fr.medriox.drank.utils.command.ISubCommand;
import org.bukkit.command.CommandSender;

/**
 * Created by MeDrioX on 18/02/2020
 */


public class RankConfigCommand extends ISubCommand {

    public RankConfigCommand(String command, ICommand iCommand) {
        super(command, iCommand);
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) throws Exception {
        if(sender.hasPermission("drank.config") || sender.hasPermission("drank.*")){
            // on continue
        }
    }

}
