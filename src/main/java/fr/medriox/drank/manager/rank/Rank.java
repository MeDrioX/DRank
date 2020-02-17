package fr.medriox.drank.manager.rank;

import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by MeDrioX on 16/02/2020
 */


public class Rank {

    private String name;
    private String prefix;
    private String suffix;
    private boolean rankDefault;
    private ArrayList<String> permissions;

    public Rank(String name, String prefix, boolean rankDefault, String suffix) {
        this.name = name;
        this.prefix = prefix;
        this.rankDefault = rankDefault;
        this.suffix = suffix;
        this.permissions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isRankDefault() {
        return rankDefault;
    }

    public void setRankDefault(boolean rankDefault) {
        this.rankDefault = rankDefault;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.permissions = permissions;
    }
}
