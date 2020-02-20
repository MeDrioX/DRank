package fr.medriox.drank.utils;

import fr.medriox.drank.DRank;
import org.bukkit.ChatColor;
import sun.rmi.runtime.Log;

import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static org.bukkit.ChatColor.GREEN;

/**
 * Created by MeDrioX on 19/02/2020
 */


public class UpdateChecker {

    private DRank dRank;
    //private String url = "https://api.spigotmc.org/legacy/update.php?resource=";
    private String url = "https://pastebin.com/raw/Zy67xgZ4";
    private String ressourceID = "0";
    private String pluginVersion;
    private String latestVersion = null;

    private boolean isAvailable;

    public UpdateChecker(DRank dRank) {
        this.dRank = dRank;
        this.check();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void check(){
        isAvailable = checkUpdate();
    }

    private boolean checkUpdate(){
        dRank.getServer().getConsoleSender().sendMessage("§a[DRank] Check for updates...");
        try {
            String pluginVersion = dRank.getDescription().getVersion();
            this.pluginVersion = pluginVersion;

            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            String raw = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

            String latestVersion;

            if(raw.contains("-")){
                latestVersion = raw.split("-")[0].trim();
                this.latestVersion = latestVersion;
            }else{
                latestVersion = raw;
                this.latestVersion = latestVersion;
            }

            if(!pluginVersion.equalsIgnoreCase(latestVersion)){
                return true;
            }

        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }
}
