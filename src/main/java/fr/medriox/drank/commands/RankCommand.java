package fr.medriox.drank.commands;

import fr.medriox.drank.commands.subcommand.RankConfigCommand;
import fr.medriox.drank.commands.subcommand.RankCreateCommand;
import fr.medriox.drank.commands.subcommand.RankListCommand;
import fr.medriox.drank.commands.subcommand.RankSetCommand;
import fr.medriox.drank.utils.command.ICommand;
import org.bukkit.command.CommandSender;

/**
 * Created by MeDrioX on 18/02/2020
 */


public class RankCommand extends ICommand {

    public RankCommand() {
        super("drank");
        this.addISubCommand(new RankCreateCommand("create", this));
        this.addISubCommand(new RankListCommand("list", this));
        this.addISubCommand(new RankSetCommand("set", this));
        this.addISubCommand(new RankConfigCommand("config", this));
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) throws Exception {
        // send help
    }
}
