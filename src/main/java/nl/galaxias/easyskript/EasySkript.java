package nl.galaxias.easyskript;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Galaxias on 15-04-15 (17:15).
 * This file is part of EasySkript in the package nl.galaxias.easyskript.
 */
public class EasySkript extends JavaPlugin {
    private static Plugin plugin;

    public void onEnable() {
        plugin = this;

        if(getServer().getPluginManager().getPlugin("Skript") == null) {
            Bukkit.getLogger().info("Skript is not installed! Installing it now...");

            installSkript();
        }

        getCommand("skinstall").setExecutor(new SkinstallCommand());
    }

    public void onDisable() {
        plugin = null;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    private void installSkript() {
        BufferedInputStream bufferedinputstream = null;
        try {
            final URL bukGetURL = new URL("http://api.bukget.org/3/plugins/bukkit/" + "skript" + "/" + "latest" + "/download");
            final URLConnection conn = bukGetURL.openConnection();
            bufferedinputstream = new BufferedInputStream(conn.getInputStream());

            File file = new File("skript" + ".jar");

            String destinationFile = "plugins/";
            Path p = Paths.get(destinationFile + file);

            Files.copy(bufferedinputstream, p);
            Bukkit.getLogger().info(ChatColor.GRAY + "[" + ChatColor.GREEN + "EasyInstall" + ChatColor.GRAY + "]" + ChatColor.GREEN + " the plugin was successfully download/installed!");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSkript(String skriptUrl, String destinationFile) throws IOException {
        URL url = new URL(skriptUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }
}