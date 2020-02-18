package fr.medriox.drank.utils.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MeDrioX on 17/02/2020
 */


public abstract class ISubCommand {

    private String command;
    private String description;
    private ICommand iCommand;
    private String prefix;
    private List<String> tabComplete;
    private String beforeCommand;

    public ISubCommand(String command, ICommand iCommand) {
        this.command = command;
        this.iCommand = iCommand;
        this.beforeCommand = "";
        this.tabComplete = new ArrayList<>();
        this.description = "";
        this.prefix = iCommand.getPrefix();
    }

    public String getCommand() { return command; }

    HashMap<String, ISubCommand> getISubCommands() { return iCommand.getISubCommands(); }
    public void addISubCommand(ISubCommand iSubCommand){
        iSubCommand.setBeforeCommand((this.getBeforeCommand() != null? this.getBeforeCommand()+" "+command: command));
        iCommand.getISubCommands().put(iSubCommand.getCommand(), iSubCommand);
    }

    public String getPrefix() { return prefix; }

    List<String> getTabComplete() { return tabComplete; }
    public void setTabComplete(List<String> tabComplete) { this.tabComplete = tabComplete; }
    public void addTabComplete(String tabComplete){ this.tabComplete.add(tabComplete); }

    public String getBeforeCommand() { return beforeCommand; }
    void setBeforeCommand(String beforeCommand) { this.beforeCommand = beforeCommand; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public abstract void onCommand(CommandSender sender, String[] args) throws Exception;

    public List<String> getTabComplete(CommandSender sender) { return new ArrayList<>(); }
}

