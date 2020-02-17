package fr.medriox.drank.manager.player;

import fr.medriox.drank.manager.rank.Rank;

import java.util.UUID;

/**
 * Created by MeDrioX on 16/02/2020
 */


public class PlayerData {

    private UUID UUID;
    private Rank rank;

    public PlayerData(UUID UUID, Rank rank) {
        this.UUID = UUID;
        this.rank = rank;
    }

    public UUID getUUID() {
        return UUID;
    }

    public void setUUID(UUID UUID) {
        this.UUID = UUID;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
