package fr.medriox.drank.utils.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

class CommandCompleter  {


    private CommandManager commandManager;
    CommandCompleter(CommandManager commandManager) {
        this.commandManager = commandManager;
    }


    public List<String> onTabComplete(CommandSender commandSender, ICommand iCommand, String[] args) {
        if (commandManager.hasExecutorType(commandSender, iCommand.getExecutorType())) {
            if (args.length == 1) {
                List<String> tab = iCommand.getTabComplete(commandSender);
                List<String> commands = new ArrayList<>((tab.size() != 0 ? tab : iCommand.getTabComplete()));
                iCommand.getISubCommands().forEach((s, iSubCommand) -> {
                    iSubCommand.forEach(command -> {
                        if (command.getBeforeCommand().equals("")) commands.add(s);
                    });
                });
                return getTabComplete(args[0], commands);
            } else {
                ISubCommand iSubCommand = getSubCommand(iCommand, args);
                if (iSubCommand != null && !hasCompletedTab(iCommand, args)) {
                    List<String> tab = iSubCommand.getTabComplete(commandSender);
                    List<String> commands = new ArrayList<>((tab.size() != 0 ? tab : iSubCommand.getTabComplete()));
                    iCommand.getISubCommands().forEach((s, iSub) -> {
                        iSub.forEach(command -> {
                            if (command.getBeforeCommand().equals(getBeforeCommand(getNumberOfSubCommand(iSubCommand, args), args)))
                                commands.add(s);
                        });
                    });
                    return getTabComplete(args[getNumberOfSubCommand(iSubCommand, args)], commands);
                }
            }
        }
        return null;
    }

    private ISubCommand getSubCommand(ICommand iCommand, String[] args){
        int size = (args.length - 1);
        for (int i = size; 0 <= i; i--) {
            if (iCommand.getISubCommands().containsKey(args[i])) {
                for (ISubCommand iSubCommand : iCommand.getISubCommands().get(args[i])) {
                    if (getBeforeCommand(i - 1, args).equals(iSubCommand.getBeforeCommand().replace(" ", ""))){
                        return iSubCommand;
                    }
                }
            }
        }
        return null;
    }

    private boolean hasCompletedTab(ICommand iCommand, String[] args){
        String s = args[args.length - 2];
        if (iCommand.getISubCommands().containsKey(s)) {
            for (ISubCommand iSubCommand : iCommand.getISubCommands().get(s)) {
                if (iSubCommand.getTabComplete().contains(args[args.length - 1])) return true;
            }
        }
        return false;
    }

    private int getNumberOfSubCommand(ISubCommand iSubCommand, String[] args){
        int size = (args.length -1);
        int i = 0;
        while (iSubCommand.getCommand().equals(args[i]) && i != size){
            i++;
        }
        return i;
    }

    private String getBeforeCommand(int i, String[] args){
        String s = "";
        for (int z = i; 0 <= z; z--) {
            s += args[z];
        }
        return s;
    }

    private List<String> getTabComplete(String s, List<String> args){
        if(s != null) {
            List<String> option = new ArrayList<>();
            args.forEach(a -> {
                if (a.startsWith(s)) option.add(a);
            });
            return option;
        }else {
            return args;
        }
    }
}
