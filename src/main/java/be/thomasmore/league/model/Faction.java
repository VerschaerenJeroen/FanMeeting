package be.thomasmore.league.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Faction {
    @Id
    private int id;
    private String factionName;
    private String alias;
    @Column(length = 2000)
    private String description;
    private String factionPictureUrl;

    public Faction() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFactionPictureUrl() {
        return factionPictureUrl;
    }

    public void setFactionPictureUrl(String factionPictureUrl) {
        this.factionPictureUrl = factionPictureUrl;
    }
}
