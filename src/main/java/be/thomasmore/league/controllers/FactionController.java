package be.thomasmore.league.controllers;

import be.thomasmore.league.model.Champion;
import be.thomasmore.league.model.Faction;
import be.thomasmore.league.repositories.ChampionRepository;
import be.thomasmore.league.repositories.FactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class FactionController {

    @Autowired
    private FactionRepository factionRepository;

    @Autowired
    private ChampionRepository championRepository;

    @GetMapping({"/factionlist"})
    public String factionList(Model model,
                              @RequestParam(required = false) String keyword) {
        List<Faction> factions;
        if (keyword != null) {
            factions = factionRepository.findByKeyword(keyword);
        }
        else {
            factions = factionRepository.findAllBy();
        }

        model.addAttribute("factions", factions);
        model.addAttribute("nrOfFactions", factions.size());
        model.addAttribute("keyword", keyword);
        model.addAttribute("search", factions.isEmpty());

        return "factionlist";
    }

    @GetMapping({"/factiondetails", "/factiondetails/{id}"})
    public String championDetails(Model model,
                                  @PathVariable(required = false) Integer id) {
        if (id == null) return "factiondetails";

        Optional<Faction> faction = factionRepository.findById(id);
        if (faction.isPresent()) {
            long nrOfFactions = factionRepository.count();
            List<Champion> champions = championRepository.findChampionByFaction(faction.get());
            model.addAttribute("faction", faction.get());
            model.addAttribute("prev", id > 1 ? id -1 : nrOfFactions);
            model.addAttribute("next", id < nrOfFactions ? id + 1 : 1);
            model.addAttribute("champions", champions);
        }

        return "factiondetails";
    }
}
