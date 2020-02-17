package fr.medriox.drank.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by MeDrioX on 17/02/2020
 */


public class DRankCommands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("drank")){
           if(args.length <= 0){
               // aide
           }else if(args[0].equalsIgnoreCase("help")){
               // aide
           }else if(args[0].equalsIgnoreCase("list")){
               // listes des grades
           }else if(args[0].equalsIgnoreCase("players")){
               // envoyer la liste des joueurs avec leur grade
           }else if(args[0].equalsIgnoreCase("set")){
               // set un groupe à un joueur
           }else if(args[0].equalsIgnoreCase("create")){
               // créer un grade
           }else if(args[0].equalsIgnoreCase("config")){
               // config du plugin ou d'un grade
           }
        }
        return false;
    }
}
