package nl.galaxias.easyskript;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 * Created by Galaxias on 15-04-15 (17:16).
 * This file is part of EasySkript in the package nl.galaxias.easyskript.
 */
public class SkinstallCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("skinstall")) {
            if (player.isOp()) {
                if (args.length == 1) {
                    String skriptUrl = args[0];
                    String saveToFile = skriptUrl.substring( skriptUrl.lastIndexOf('/')+1, skriptUrl.length() );
                    String destinationFile = EasySkript.getPlugin().getServer().getWorldContainer().getAbsolutePath();
                    destinationFile = destinationFile.substring(0, destinationFile.length() - 1) + "plugins/Skript/scripts/" + saveToFile;

                    String extension = saveToFile.substring(saveToFile.lastIndexOf(".") + 1, saveToFile.length());

                    if (!(extension.contains("sk"))) {
                        Bukkit.getLogger().info("The file that was going to be downloaded is not an .sk file!");
                        sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "EasySkript" + ChatColor.GRAY + "]" + ChatColor.DARK_RED + " this is not a Skript (.sk) file! Please note a Skript file " + ChatColor.BOLD + "always" + ChatColor.RESET + ChatColor.DARK_RED + " ends with .sk!");
                    }
                    else {
                        try {
                            EasySkript.saveSkript(skriptUrl, destinationFile);

                            Bukkit.dispatchCommand(player, "skript reload " + destinationFile);

                            sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "EasySkript" + ChatColor.GRAY + "]" + ChatColor.GREEN + " the Skript was successfully download/installed!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else if (!(player.isOp())) {
                sender.sendMessage(ChatColor.RED + "You are not allowed to use this command!");
            }
            return true;
        }
        return false;
    }
}
