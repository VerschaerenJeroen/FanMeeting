package be.thomasmore.league.controllers;


import be.thomasmore.league.model.Champion;
import be.thomasmore.league.model.Faction;
import be.thomasmore.league.repositories.ChampionRepository;
import be.thomasmore.league.repositories.FactionRepository;
import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping({"/admin"})
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ChampionRepository championRepository;

    @Autowired
    private FactionRepository factionRepository;

    @ModelAttribute("champion")
    public Champion findChampion(@PathVariable(required = false) Integer id) {
        logger.info("findChampion " + id);
        if (id == null) return new Champion();

        Optional<Champion> c = championRepository.findById(id);
        if (c.isPresent()) return c.get();
        return null;
    }

    @ModelAttribute("faction")
    public Faction findFaction(@PathVariable(required = false) Integer id) {
        logger.info("findFaction " + id);
        if (id == null) return new Faction();

        Optional<Faction> f = factionRepository.findById(id);
        if (f.isPresent()) return f.get();
        return null;
    }

    @GetMapping({"/championedit/{id}"})
    public String championEdit(Model model,
                               @PathVariable Integer id) {
        logger.info("championEdit " + id);
        model.addAttribute("factions", factionRepository.findAll());
        return "admin/championedit";
    }

    @PostMapping({"/championedit/{id}"})
    public String championEditPost(Model model,
                                   @PathVariable Integer id,
                                   @Valid @ModelAttribute("champion") Champion champion,
                                   BindingResult bindingResult,
                                   @RequestParam Integer factionId) {

        logger.info("championEditPost " + id + " -- new name=" + champion.getChampionName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("factions", factionRepository.findAll());
            return "admin/championedit";
        }

        if (champion.getFaction().getId() != factionId) {
            champion.setFaction(new Faction(factionId));
        }

        championRepository.save(champion);
        return "redirect:/championdetails/" + id;
    }

    @GetMapping({"/championnew"})
    public String champioNew(Model model) {
        model.addAttribute("champion", new Champion());
        model.addAttribute("factions", factionRepository.findAll());
        return "admin/championnew";
    }

    @PostMapping({"/championnew"})
    public String championNewPost(Model model,
                                   @Valid @ModelAttribute("champion") Champion champion,
                                   BindingResult bindingResult,
                                   @RequestParam Integer factionId) {

        logger.info(" -- new name=" + champion.getChampionName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("factions", factionRepository.findAll());
            return "admin/championnew";
        }

        champion.setFaction(new Faction(factionId));
        Champion newChampion = championRepository.save(champion);

        return "redirect:/championdetails/" + newChampion.getId();
    }

    @GetMapping({"/factionedit/{id}"})
    public String factionEdit(Model model) {
        return "admin/factionedit";
    }

    @PostMapping({"/factionedit/{id}"})
    public String factionEditPost(Model model,
                                   @PathVariable Integer id,
                                   @Valid @ModelAttribute("faction") Faction faction,
                                   BindingResult bindingResult) {

        logger.info("factionEditPost " + id + " -- new name=" + faction.getFactionName());

        if (bindingResult.hasErrors()) {
            return "admin/factionedit";
        }

        factionRepository.save(faction);
        return "redirect:/factiondetails/" + id;
    }

    @GetMapping({"/factionnew"})
    public String factionNew(Model model,
                              @PathVariable Integer id) {
        logger.info("factionEdit " + id);
        return "admin/factionnew";
    }

    @PostMapping({"/factionnew"})
    public String factionNewPost(Model model,
                                  @PathVariable Integer id,
                                  @Valid @ModelAttribute("faction") Faction faction,
                                  BindingResult bindingResult) {

        logger.info("factionEditPost " + id + " -- new name=" + faction.getFactionName());

        if (bindingResult.hasErrors()) {
            return "admin/factionnew";
        }

        Faction newFaction = factionRepository.save(faction);
        return "redirect:/factiondetails/" + newFaction.getId();
    }
}
