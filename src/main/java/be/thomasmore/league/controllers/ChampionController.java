package be.thomasmore.league.controllers;


import be.thomasmore.league.model.Champion;
import be.thomasmore.league.repositories.ChampionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ChampionController {
    private Logger logger = LoggerFactory.getLogger(ChampionRepository.class);

    @Autowired
    private ChampionRepository championRepository;

    @GetMapping({"/championlist"})
    public String championList(Model model,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(required = false) String filterChampions) {

        logger.info(String.format("championList -- filterchampions=%s", filterChampions));

        List<Champion> champions;
        if (keyword != null) {
            champions = championRepository.findByFilterQuery(keyword, filterChampions);
        }
        else {
            champions = championRepository.findAllBy();
        }

        model.addAttribute("champions", champions);
        model.addAttribute("nrOfChampions", champions.size());
        model.addAttribute("keyword", keyword);
        model.addAttribute("search", champions.isEmpty());
        model.addAttribute("filterChampions", filterChampions);

        return "championlist";
    }

    @GetMapping({"/championdetails", "/championdetails/{id}"})
    public String championDetails(Model model,
                                  @PathVariable(required = false) Integer id) {
        if (id == null) return "championdetails";

        Optional<Champion> champion = championRepository.findById(id);
        if (champion.isPresent()) {
            long nrOfChampions = championRepository.count();
            model.addAttribute("champion", champion.get());
            model.addAttribute("prev", id > 1 ? id -1 : nrOfChampions);
            model.addAttribute("next", id < nrOfChampions ? id + 1 : 1);
        }

        return "championdetails";
    }
}
