package fr.medriox.drank.utils.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MeDrioX on 17/02/2020
 */


public abstract class ICommand {

    private String command;
    private String description;
    private String usage;
    private String permission;
    private String no_permission;
    private List<String> aliases;
    private HashMap<String, ISubCommand> iSubCommands;
    private ExecutorType executorType;
    private String prefix;
    private List<String> tabComplete;

    public ICommand(String command) {
        this.command = command;
        this.description = "";
        this.usage = "";
        this.permission = "";
        this.prefix = "";
        this.no_permission = "";
        this.aliases =  new ArrayList<>();
        this.iSubCommands = new HashMap<>();
        this.tabComplete = new ArrayList<>();
        this.executorType = ExecutorType.ALL;
    }

    public String getCommand() { return command; }

    String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    String getUsage() { return usage; }
    public void setUsage(String usage) { this.usage = usage; }

    String getPermission() { return permission; }
    public void setPermission(String permission) { this.permission = permission; }



    public HashMap<String, ISubCommand> getISubCommands() { return iSubCommands; }
    void setISubCommands(HashMap<String, ISubCommand> iSubCommands) { this.iSubCommands = iSubCommands; }
    public void addISubCommand(ISubCommand iSubCommand){ this.iSubCommands.put(iSubCommand.getCommand(), iSubCommand); }

    ExecutorType getExecutorType() { return executorType; }
    public void setExecutorType(ExecutorType executorType) { this.executorType = executorType; }

    public String getPrefix() { return prefix; }
    public void setPrefix(String prefix) { this.prefix = prefix; }

    List<String> getTabComplete() { return tabComplete; }
    public void setTabComplete(List<String> tabComplete) { this.tabComplete = tabComplete; }
    public void addTabComplete(String tabComplete){ this.tabComplete.add(tabComplete); }

    public String getNoPermissionMessage() { return no_permission; }
    public void setNoPermissionMessage(String no_permission) { this.no_permission = no_permission; }

    public List<String> getAliases() { return aliases; }
    public void setAliases(List<String> aliases) { this.aliases = aliases; }
    public void addAliases(String alias) { this.aliases.add(alias); }

    public abstract void onCommand(CommandSender sender, String[] args) throws Exception;

    public List<String> getTabComplete(CommandSender sender) { return new ArrayList<>(); }
}

