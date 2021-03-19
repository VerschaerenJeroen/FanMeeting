package be.thomasmore.meeting.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Champion {
    @Id
    private int id;
    private String championName;
    private String championFaction;
    private String championPictureUrl;
    private String role;
    @Column (length = 1000)
    private String lore;

    public Champion() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getChampionFaction() {
        return championFaction;
    }

    public void setChampionFaction(String championFaction) {
        this.championFaction = championFaction;
    }

    public String getChampionPictureUrl() {
        return championPictureUrl;
    }

    public void setChampionPictureUrl(String championPictureUrl) {
        this.championPictureUrl = championPictureUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }
}


