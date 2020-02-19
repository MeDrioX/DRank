package fr.medriox.drank.utils.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private List<ICommand> iCommands;
    private JavaPlugin javaPlugin;

    public CommandManager(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.iCommands = new ArrayList<>();
    }

    public void registerCommand(ICommand iCommands) {
        this.iCommands.add(iCommands);
        this.registerCommands(iCommands);
    }

    public List<ICommand> getICommands() {
        return iCommands;
    }

    ICommand getICommand(String s) {
        for (ICommand iCommand : iCommands) {
            if (iCommand.getCommand().equals(s)) return iCommand;
        }
        return null;
    }

    boolean hasExecutorType(CommandSender sender, ExecutorType executorType) {
        if (executorType == ExecutorType.ALL) return true;
        if (executorType == ExecutorType.PLAYER && sender instanceof Player) return true;
        if (executorType == ExecutorType.CONSOLE && sender instanceof BukkitCommand) return true;
        return false;
    }

    private void registerCommands(ICommand iCommand) {
            try {
                Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

                bukkitCommandMap.setAccessible(true);
                CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

                commandMap.register(iCommand.getCommand(), new CommandHandler(iCommand, this));
            } catch(Exception e) {
                e.printStackTrace();
            }
    }
}
