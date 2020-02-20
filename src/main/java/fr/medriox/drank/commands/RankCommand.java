package fr.medriox.drank.commands;

import fr.medriox.drank.DRank;
import fr.medriox.drank.commands.subcommand.group.RankGroupListCommand;
import fr.medriox.drank.commands.subcommand.user.RankUserAddCommand;
import fr.medriox.drank.commands.subcommand.user.RankUserRemoveCommand;
import fr.medriox.drank.utils.command.ICommand;
import org.bukkit.command.CommandSender;

/**
 * Created by MeDrioX on 18/02/2020
 */


public class RankCommand extends ICommand {

    public RankCommand(DRank dRank) {
        super("drank");
        this.addISubCommand(new RankUserAddCommand("add", this, dRank));
        this.addISubCommand(new RankUserRemoveCommand("remove", this, dRank));
        this.addISubCommand(new RankGroupListCommand("list", this, dRank));
        /*this.addISubCommand(new RankListCommand("del", this));
        this.addISubCommand(new RankSetCommand("whois", this));*/
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) throws Exception {
        // send help
    }
}
