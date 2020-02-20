package fr.medriox.drank.commands.subcommand.user;

import fr.medriox.drank.DRank;
import fr.medriox.drank.commands.RankCommand;
import fr.medriox.drank.manager.player.PlayerDataManager;
import fr.medriox.drank.utils.command.ICommand;
import fr.medriox.drank.utils.command.ISubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.RED;

/**
 * Created by MeDrioX on 19/02/2020
 */


public class RankUserRemoveCommand extends ISubCommand {


    private DRank dRank;

    public RankUserRemoveCommand(String command, ICommand iCommand, DRank dRank) {
        super(command, iCommand);
        this.dRank = dRank;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) throws Exception {
        if(sender.hasPermission("drank.user.remove") || sender.hasPermission("drank.*")){
            if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    new PlayerDataManager(dRank, Bukkit.getPlayer(sender.getName()), target).removeRank();
                }else{
                    sender.sendMessage(RED + "Unable to find " + args[0]);
                }
            }
            
            if(args.length == 0){
                sender.sendMessage(RED + "Please specify a player");
            }

        }else{
            sender.sendMessage(RED + "You don't have permission");
        }
    }
}
