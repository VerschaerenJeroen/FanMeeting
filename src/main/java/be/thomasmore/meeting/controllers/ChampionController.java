package be.thomasmore.meeting.controllers;


import be.thomasmore.meeting.model.Champion;
import be.thomasmore.meeting.repositories.ChampionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChampionController {
    private Logger logger = LoggerFactory.getLogger(ChampionRepository.class);

    @Autowired
    private ChampionRepository championRepository;

    @GetMapping("/championlist")
    public String championList(Model model,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(required = false) String filterChampions) {

        logger.info(String.format("championList -- filterchampions=%s", filterChampions));

        List<Champion> champions;
        if (keyword != null && filterChampions != null) {
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
}
