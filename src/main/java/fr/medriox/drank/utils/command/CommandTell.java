package fr.medriox.drank.utils.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MeDrioX on 17/02/2020
 */


class CommandTell extends BukkitCommand {


    private CommandManager commandManager;
    private CommandCompleter commandCompleter;
    private ICommand iCommand;
    CommandTell(ICommand iCommand ,CommandManager commandManager) {
        super(iCommand.getCommand());
        this.setAliases(iCommand.getAliases());
        this.setDescription(iCommand.getDescription());
        this.setLabel(iCommand.getCommand());
        this.setPermission(iCommand.getPermission());
        this.setPermissionMessage(iCommand.getNoPermissionMessage());
        this.setName(iCommand.getCommand());
        this.setUsage(iCommand.getUsage());
        this.iCommand = iCommand;
        this.commandManager = commandManager;
        this.commandCompleter = new CommandCompleter(commandManager);
    }


    @Override
    public boolean execute(CommandSender commandSender, String command, String[] args) {
        try {
            if (commandManager.hasExecutorType(commandSender, iCommand.getExecutorType())) {
                if (args.length != 0) {
                    ISubCommand iSubCommand = getSubCommand(iCommand, args);
                    if(iSubCommand != null) {
                        List<String> list = new ArrayList<>(Arrays.asList(args));
                        int size = getNumberOfSubCommand(iSubCommand, args);
                        for (int i = 0; i < size; i++) {
                            list.remove(i);
                        }
                        if(size == 0) list.remove(0);
                        iSubCommand.onCommand(commandSender, gerateArgs(list));
                    }else {
                        iCommand.onCommand(commandSender, args);
                    }
                    return true;
                }
                iCommand.onCommand(commandSender, args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String commannd, String[] args) throws IllegalArgumentException {
        return commandCompleter.onTabComplete(sender, iCommand, args);
    }

    private String[] gerateArgs(List<String> strings){
        int size = strings.size();
        String[] args = new String[size];
        for (int i = 0; i < size; i++) {
            args[i] = strings.get(i);
        }
        return args;
    }


    private ISubCommand getSubCommand(ICommand iCommand, String[] args){
        int size = (args.length - 1);
        for (int i = size; 0 <= i; i--) {
            if (iCommand.getISubCommands().containsKey(args[i]) && getBeforeCommand(i-1,args).equals(iCommand.getISubCommands().get(args[i]).getBeforeCommand())) {
                return iCommand.getISubCommands().get(args[i]);
            }
        }
        return null;
    }

    private String getBeforeCommand(int i, String[] args){
        String s = "";
        for (int z = i; 0 <= z; z--) {
            s += args[z];
        }
        return s;
    }

    private int getNumberOfSubCommand(ISubCommand iSubCommand, String[] args){
        int size = (args.length -1);
        int i = 0;
        while (iSubCommand.getCommand().equals(args[i]) && i != size){
            i++;
        }
        return i;
    }


}
