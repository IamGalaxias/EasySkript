package nl.galaxias.easyskript;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by Galaxias on 15-04-15 (17:15).
 * This file is part of EasySkript in the package nl.galaxias.easyskript.
 */
public class EasySkript extends JavaPlugin {
    private static Plugin plugin;

    public void onEnable() {
        plugin = this;

        getCommand("skinstall").setExecutor(new SkinstallCommand());
    }

    public void onDisable() {
        plugin = null;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void saveJar(String skriptUrl, String destinationFile) throws IOException {
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