package be.thomasmore.league.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Champion {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "champion_generator")
    @SequenceGenerator(name = "champion_generator", sequenceName = "champion_seq", allocationSize = 1)
    @Id
    private Integer id;
    @NotBlank
    private String championName;
    private String championFaction;
    @NotBlank
    private String championPictureUrl;
    private String role;
    @Column (length = 1000)
    @NotBlank
    private String lore;
    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    private Faction faction;

    public Champion() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }
}


