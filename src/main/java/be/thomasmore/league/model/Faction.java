package be.thomasmore.league.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Faction {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faction_generator")
    @SequenceGenerator(name = "faction_generator", sequenceName = "faction_seq", allocationSize = 1)
    @Id
    private Integer id;
    @NotBlank
    private String factionName;
    @NotBlank
    private String alias;
    @Column(length = 2000)
    @NotBlank
    private String description;
    @NotBlank
    private String factionPictureUrl;

    public Faction() {

    }

    public Faction(Integer id) {
        this.id=id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
